package sundays.deeplearning.h1b_spark;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.datavec.api.records.reader.RecordReader;
import org.datavec.api.records.reader.impl.csv.CSVRecordReader;
import org.datavec.api.split.FileSplit;
import org.datavec.api.util.ClassPathResource;
import org.deeplearning4j.datasets.datavec.RecordReaderDataSetIterator;
import org.deeplearning4j.eval.Evaluation;
import org.deeplearning4j.nn.api.OptimizationAlgorithm;
import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.Updater;
import org.deeplearning4j.nn.conf.layers.DenseLayer;
import org.deeplearning4j.nn.conf.layers.OutputLayer;
import org.deeplearning4j.nn.weights.WeightInit;
import org.deeplearning4j.spark.api.TrainingMaster;
import org.deeplearning4j.spark.impl.multilayer.SparkDl4jMultiLayer;
import org.deeplearning4j.spark.impl.paramavg.ParameterAveragingTrainingMaster;
import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.dataset.api.iterator.DataSetIterator;
import org.nd4j.linalg.lossfunctions.LossFunctions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Hongwei Hu
 *
 * To run code using Spark submit (for example on a cluster): pass "-useSparkLocal false" as the application argument,
 *   OR first modify code by setting the field "useSparkLocal = false"
 *   
 */

public class H1BSpark {
	
    private static final Logger log = LoggerFactory.getLogger(H1BSpark.class);

    @Parameter(names = "-useSparkLocal", description = "Use spark local", arity = 0)
    private boolean useSparkLocal = false;

    @Parameter(names = "-batchSizePerWorker", description = "Number of examples to fit each worker with")
    private int batchSizePerWorker = 500;

    @Parameter(names = "-numEpochs", description = "Number of epochs for training")
    private int numEpochs = 1;
    
    @Parameter(names = "-hiddenNodes", description = "Number of hiddenNodes per layer")    
    private int numHiddenNodes = 10;
    
    @Parameter(names = "-learningRate", description = "Learning Rate")    
    private double learningRate = 0.001; 
    
    @Parameter(names = "-momentum", description = "Momentum")    
    private double mtn = 0.9;
    
    @Parameter(names = "-iter", description = "Iteration per batch")    
    private int iter = 1;
    
    @Parameter(names = "-trainData", description = "Data for training")
    private String trainInput = "train.csv";
    
    @Parameter(names = "-testData", description = "Data for testing")
    private String testInput = "test.csv";
    
    int seed = 123;
    int numInputs = 9;
    int numOutputs = 2;

    public static void main(String[] args) throws Exception {
        new H1BSpark().entryPoint(args);
    }

    protected void entryPoint(String[] args) throws Exception {
        //Handle command line arguments
        JCommander jcmdr = new JCommander(this);
        try {
            jcmdr.parse(args);
        } catch (ParameterException e) {
            //User provides invalid input -> print the usage info
            jcmdr.usage();
            try { Thread.sleep(500); } catch (Exception e2) { }
            throw e;
        }

        SparkConf sparkConf = new SparkConf();
        if (useSparkLocal) {
            sparkConf.setMaster("local[*]");
        }
        sparkConf.setAppName("H1B DL4J Spark");
        JavaSparkContext sc = new JavaSparkContext(sparkConf);

        // Need to modify this part
        //Load the data into memory then parallelize
        //This isn't a good approach in general - but is simple to use for this example
        final String filenameTrain  = new ClassPathResource(trainInput).getFile().getPath();
        final String filenameTest  = new ClassPathResource(testInput).getFile().getPath();
        
        //Load the training data:
        RecordReader rr = new CSVRecordReader();
        rr.initialize(new FileSplit(new File(filenameTrain)));
        DataSetIterator trainIter = new RecordReaderDataSetIterator(rr,batchSizePerWorker,0,2);	
        //Load the test/evaluation data:
        RecordReader rrTest = new CSVRecordReader();
        rrTest.initialize(new FileSplit(new File(filenameTest)));
        DataSetIterator testIter = new RecordReaderDataSetIterator(rrTest,batchSizePerWorker,0,2);

        List<DataSet> trainDataList = new ArrayList<>();
        List<DataSet> testDataList = new ArrayList<>();
        while (trainIter.hasNext()) {
            trainDataList.add(trainIter.next());
        }
        while (testIter.hasNext()) {
            testDataList.add(testIter.next());
        }
        JavaRDD<DataSet> trainData = sc.parallelize(trainDataList);
        JavaRDD<DataSet> testData = sc.parallelize(testDataList);

        //----------------------------------
        //Create network configuration and conduct network training
        MultiLayerConfiguration conf = new NeuralNetConfiguration.Builder()
            .seed(seed)
            .optimizationAlgo(OptimizationAlgorithm.STOCHASTIC_GRADIENT_DESCENT).iterations(iter)
            
            //.activation(Activation.LEAKYRELU)
            .activation(Activation.RELU)
            .weightInit(WeightInit.XAVIER)
            .learningRate(learningRate)
            .updater(Updater.NESTEROVS).momentum(mtn)
            .regularization(true).l2(1e-4)
            .list()
            .layer(0, new DenseLayer.Builder().nIn(numInputs).nOut(numHiddenNodes).build())
            .layer(1, new DenseLayer.Builder().nIn(numHiddenNodes).nOut(numHiddenNodes).build())
            .layer(2, new DenseLayer.Builder().nIn(numHiddenNodes).nOut(numHiddenNodes).build())
            .layer(3, new DenseLayer.Builder().nIn(numHiddenNodes).nOut(numHiddenNodes).build())
            .layer(4, new DenseLayer.Builder().nIn(numHiddenNodes).nOut(numHiddenNodes).build())
            .layer(5, new DenseLayer.Builder().nIn(numHiddenNodes).nOut(numHiddenNodes).build())
            .layer(6, new DenseLayer.Builder().nIn(numHiddenNodes).nOut(numHiddenNodes).build())
            .layer(7, new DenseLayer.Builder().nIn(numHiddenNodes).nOut(numHiddenNodes).build())
            .layer(8, new DenseLayer.Builder().nIn(numHiddenNodes).nOut(numHiddenNodes).build())
            .layer(9, new OutputLayer.Builder(LossFunctions.LossFunction.NEGATIVELOGLIKELIHOOD)
                .activation(Activation.SOFTMAX).nIn(numHiddenNodes).nOut(numOutputs).build())
            .pretrain(false).backprop(true)
            .build();

        //Configuration for Spark training
        //a. If you are training with pre-processed DataSet objects, 
        // 	 this will be the size of those preprocessed DataSets
        //b. If you are training directly from Strings 
        //   (for example, CSV data to a RDD<DataSet> though a number of steps) then this will usually be 1
        
        TrainingMaster tm = new ParameterAveragingTrainingMaster.Builder(1) 
            .averagingFrequency(5)
            .workerPrefetchNumBatches(2)            //Async prefetching: 2 examples per worker
            .batchSizePerWorker(batchSizePerWorker)
            .build();

        //Create the Spark network
        SparkDl4jMultiLayer sparkNet = new SparkDl4jMultiLayer(sc, conf, tm);

        //Execute training:
        for (int i = 0; i < numEpochs; i++) {
            sparkNet.fit(trainData);
            log.info("Completed Epoch {}", i);
        }

        //Perform evaluation (distributed)
        Evaluation evaluation = sparkNet.evaluate(testData);
        log.info("***** Evaluation *****");
        log.info(evaluation.stats());

        //Delete the temp training files
        tm.deleteTempFiles(sc);

        log.info("***** Training Complete *****");
    }
}
