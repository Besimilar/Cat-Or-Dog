package sundays.deeplearning.h1b;

import org.datavec.api.records.reader.RecordReader;
import org.datavec.api.records.reader.impl.csv.CSVRecordReader;
import org.datavec.api.split.FileSplit;
import org.datavec.api.util.ClassPathResource;
import org.deeplearning4j.api.storage.StatsStorage;
import org.deeplearning4j.datasets.datavec.RecordReaderDataSetIterator;
import org.deeplearning4j.eval.Evaluation;
import org.deeplearning4j.nn.api.OptimizationAlgorithm;
import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.Updater;
import org.deeplearning4j.nn.conf.layers.DenseLayer;
import org.deeplearning4j.nn.conf.layers.OutputLayer;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.nn.weights.WeightInit;
import org.deeplearning4j.optimize.listeners.ScoreIterationListener;
import org.deeplearning4j.ui.api.UIServer;
import org.deeplearning4j.ui.stats.StatsListener;
import org.deeplearning4j.ui.storage.InMemoryStatsStorage;
import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.lossfunctions.LossFunctions.LossFunction;
import org.nd4j.linalg.dataset.api.iterator.DataSetIterator;
import org.nd4j.linalg.dataset.api.preprocessor.DataNormalization;
import org.nd4j.linalg.dataset.api.preprocessor.NormalizerMinMaxScaler;

import java.io.File;

/**
 * 
 * @author Hongwei Hu
 * 
 */
public class H1BDemo {


    public static void main(String[] args) throws Exception {
        int seed = 123;
        
        int index = 1; // test ID, your can ignore this line
        int layer = 9; 
        double learningRate = 0.01;
        int batchSize = 1000;
        int nEpochs = 100;
        double mtn = 0.9;
        int iter = 1;

        int numInputs = 9;
        int numOutputs = 2;
        int numHiddenNodes = 30;

        System.out.println(numHiddenNodes);
        
        // test code
        /*System.out.println(Thread.currentThread().getContextClassLoader());
        ClassLoader loader = ClassPathResource.class.getClassLoader();
        System.out.println(loader);
        URL url = loader.getResource("classification/INPUTdemo.csv");
        System.out.println(url);
       */
   
        // for train/test data
        final String filenameTrain  = new ClassPathResource("BalancedData.csv").getFile().getPath();
        final String filenameTest  = new ClassPathResource("TestData.csv").getFile().getPath();
        
        // for 20k data
        //final String filenameTrain  = new ClassPathResource("INPUTdemo.csv").getFile().getPath();
        //final String filenameTest  = new ClassPathResource("INPUTdemo-test.csv").getFile().getPath();
        
        // for mini data
        //final String filenameTrain  = new ClassPathResource("INPUTmini.csv").getFile().getPath();
        //final String filenameTest  = new ClassPathResource("INPUTmini.csv").getFile().getPath();
        
        //Load the training data:
        RecordReader rr = new CSVRecordReader();
        rr.initialize(new FileSplit(new File(filenameTrain)));
        DataSetIterator trainIter = new RecordReaderDataSetIterator(rr,batchSize,0,2);	

        //Load the test/evaluation data:
        RecordReader rrTest = new CSVRecordReader();
        rrTest.initialize(new FileSplit(new File(filenameTest)));
        DataSetIterator testIter = new RecordReaderDataSetIterator(rrTest,batchSize,0,2);

        // Data Normalization for training data
        DataNormalization nmm = new  NormalizerMinMaxScaler();
        nmm.fit(trainIter);
        trainIter.setPreProcessor(nmm);

        // ANN Configuration
        MultiLayerConfiguration conf = new NeuralNetConfiguration.Builder()
                .seed(seed)
                .iterations(iter)

                .optimizationAlgo(OptimizationAlgorithm.STOCHASTIC_GRADIENT_DESCENT)
                .learningRate(learningRate)
                .updater(Updater.NESTEROVS).momentum(mtn)
                .list()
                .layer(0, new DenseLayer.Builder().nIn(numInputs).nOut(numHiddenNodes)
                        .weightInit(WeightInit.XAVIER)
                        .activation(Activation.RELU)
                        .build())
                
               .layer(1, new DenseLayer.Builder().nIn(numHiddenNodes).nOut(numHiddenNodes)
                        .weightInit(WeightInit.XAVIER)
                        .activation(Activation.RELU)
                        .build())
               
               .layer(2, new DenseLayer.Builder().nIn(numHiddenNodes).nOut(numHiddenNodes)
                       .weightInit(WeightInit.XAVIER)
                       .activation(Activation.RELU)
                       .build())
              
               .layer(3, new DenseLayer.Builder().nIn(numHiddenNodes).nOut(numHiddenNodes)
                       .weightInit(WeightInit.XAVIER)
                       .activation(Activation.RELU)
                       .build())
               
               .layer(4, new DenseLayer.Builder().nIn(numHiddenNodes).nOut(numHiddenNodes)
                       .weightInit(WeightInit.XAVIER)
                       .activation(Activation.RELU)
                       .build())
               
               .layer(5, new DenseLayer.Builder().nIn(numHiddenNodes).nOut(numHiddenNodes)
                       .weightInit(WeightInit.XAVIER)
                       .activation(Activation.RELU)
                       .build())
               
               .layer(6, new DenseLayer.Builder().nIn(numHiddenNodes).nOut(numHiddenNodes)
                       .weightInit(WeightInit.XAVIER)
                       .activation(Activation.RELU)
                       .build())
               
               .layer(7, new DenseLayer.Builder().nIn(numHiddenNodes).nOut(numHiddenNodes)
                       .weightInit(WeightInit.XAVIER)
                       .activation(Activation.RELU)
                       .build())
               
               .layer(8, new DenseLayer.Builder().nIn(numHiddenNodes).nOut(numHiddenNodes)
                       .weightInit(WeightInit.XAVIER)
                       .activation(Activation.RELU)
                       .build())
                
                .layer(9, new OutputLayer.Builder(LossFunction.NEGATIVELOGLIKELIHOOD)
                        .weightInit(WeightInit.XAVIER)
                        .activation(Activation.SOFTMAX).weightInit(WeightInit.XAVIER)
                        .nIn(numHiddenNodes).nOut(numOutputs).build())
                .pretrain(false).backprop(true).build();
        
		        /*.layer(2, new OutputLayer.Builder(LossFunction.NEGATIVELOGLIKELIHOOD)
		                .weightInit(WeightInit.XAVIER)
		                .activation(Activation.SOFTMAX).weightInit(WeightInit.XAVIER)
		                .nIn(numHiddenNodes/2).nOut(numOutputs).build())
		        .pretrain(false).backprop(true).build();*/


        MultiLayerNetwork model = new MultiLayerNetwork(conf);
        model.init();
        //model.setListeners(new ScoreIterationListener(100));  //Print score every 10 parameter updates

        // view training information in UI
        //Initialize the user interface backend
        UIServer uiServer = UIServer.getInstance();
        //Configure where the network information (gradients, score vs. time etc) is to be stored. Here: store in memory.
        StatsStorage statsStorage = new InMemoryStatsStorage();         //Alternative: new FileStatsStorage(File), for saving and loading later
        //Attach the StatsStorage instance to the UI: this allows the contents of the StatsStorage to be visualized
        uiServer.attach(statsStorage);
        //Then add the StatsListener to collect this information from the network, as it trains
        model.setListeners(new StatsListener(statsStorage));

        // ******Start training
        for ( int n = 0; n < nEpochs; n++) {
            model.fit( trainIter );
        }

        // Evaluate Model
        System.out.println("Evaluate model....");
        Evaluation eval = new Evaluation(numOutputs);

        // Normalization for Test Data
        nmm.fit(testIter);
        testIter.setPreProcessor(nmm);

        while(testIter.hasNext()){
            DataSet t = testIter.next();
            INDArray features = t.getFeatureMatrix();
            INDArray lables = t.getLabels();
            INDArray predicted = model.output(features,false);

            eval.eval(lables, predicted);

        }

        //Print the evaluation statistics
        System.out.println(eval.stats());
        
        // test Code for output model
        // System.out.println(model.params());
        System.out.println("#Weights: " + model.numParams());
        /*System.out.println(model.numParams(true));
        System.out.println(model.numParams(false));*/
        //System.out.println(model);
        
        System.out.println("ID:\t" + "LRate\t" + "BSize\t"+ "Epochs\t" + "Momentum\t" + "Layer");
        System.out.println(index + "\t" + learningRate + "\t" + batchSize
        				+ "\t" + nEpochs + "\t" + mtn + "\t" + layer);
        System.out.println("****************Example finished********************");
        
    }
}
