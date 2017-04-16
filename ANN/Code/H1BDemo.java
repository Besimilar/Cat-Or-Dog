package sundays.deeplearning.h1b;

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
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.nn.weights.WeightInit;
import org.deeplearning4j.optimize.listeners.ScoreIterationListener;
import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.lossfunctions.LossFunctions.LossFunction;
import org.nd4j.linalg.dataset.api.iterator.DataSetIterator;

import java.io.File;

/**
 * 
 * @author Hongwei Hu
 * 
 */
public class H1BDemo {


    public static void main(String[] args) throws Exception {
        int seed = 123;
        
        int index = 2;
        int layer = 2;
        double learningRate = 0.001;
        int batchSize = 100;
        int nEpochs = 300;
        double mtn = 0.1;

        int numInputs = 9;
        int numOutputs = 2;
        int numHiddenNodes = 1000;

        // test code
        /*System.out.println(Thread.currentThread().getContextClassLoader());
        ClassLoader loader = ClassPathResource.class.getClassLoader();
        System.out.println(loader);
        URL url = loader.getResource("classification/INPUTdemo.csv");
        System.out.println(url);
       */
   
        final String filenameTrain  = new ClassPathResource("classification/INPUTdemo.csv").getFile().getPath();
        final String filenameTest  = new ClassPathResource("classification/INPUTdemo-test.csv").getFile().getPath();

        
        //Load the training data:
        RecordReader rr = new CSVRecordReader();

        rr.initialize(new FileSplit(new File(filenameTrain)));
        DataSetIterator trainIter = new RecordReaderDataSetIterator(rr,batchSize,0,2);

        //Load the test/evaluation data:
        RecordReader rrTest = new CSVRecordReader();
        rrTest.initialize(new FileSplit(new File(filenameTest)));
        DataSetIterator testIter = new RecordReaderDataSetIterator(rrTest,batchSize,0,2);

        MultiLayerConfiguration conf = new NeuralNetConfiguration.Builder()
                .seed(seed)
                .iterations(1)

                .optimizationAlgo(OptimizationAlgorithm.STOCHASTIC_GRADIENT_DESCENT)
                .learningRate(learningRate)
                .updater(Updater.NESTEROVS).momentum(mtn)
                .list()
                .layer(0, new DenseLayer.Builder().nIn(numInputs).nOut(numHiddenNodes)
                        .weightInit(WeightInit.XAVIER)
                        .activation(Activation.RELU)
                        .build())
                
                /*.layer(1, new DenseLayer.Builder().nIn(numHiddenNodes).nOut(numHiddenNodes/2)
                        .weightInit(WeightInit.XAVIER)
                        .activation(Activation.RELU)
                        .build())*/
                
                .layer(1, new OutputLayer.Builder(LossFunction.NEGATIVELOGLIKELIHOOD)
                        .weightInit(WeightInit.XAVIER)
                        .activation(Activation.SOFTMAX).weightInit(WeightInit.XAVIER)
                        .nIn(numHiddenNodes).nOut(numOutputs).build())
                .pretrain(false).backprop(true).build();


        MultiLayerNetwork model = new MultiLayerNetwork(conf);
        model.init();
        model.setListeners(new ScoreIterationListener(100));  //Print score every 10 parameter updates


        for ( int n = 0; n < nEpochs; n++) {
            model.fit( trainIter );
        }

        System.out.println("Evaluate model....");
        Evaluation eval = new Evaluation(numOutputs);
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
        System.out.println(model.params());
        System.out.println(model.numParams());
        /*System.out.println(model.numParams(true));
        System.out.println(model.numParams(false));*/
        //System.out.println(model);
        
        System.out.println("ID:\t" + "LRate\t" + "BSize\t"+ "Epochs\t" + "Momentum\t" + "Layer");
        System.out.println(index + "\t" + learningRate + "\t" + batchSize
        				+ "\t" + nEpochs + "\t" + mtn + "\t" + layer);
        System.out.println("****************Example finished********************");
        
    }
}
