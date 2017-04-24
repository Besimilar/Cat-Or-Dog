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
import org.deeplearning4j.util.ModelSerializer;
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
	
	double learningRate = 0.0001; //
    int batchSize = 30; //
    double mmt = 0.6; //
    int numHiddenNodes = 6;

	public H1BDemo(int a, double b, double c, int nodes) {
		batchSize = a;
		learningRate = b;
		mmt = c;
		numHiddenNodes = nodes;
	}
	
    public void run() throws Exception {
        
    		int seed = 123;
        
    		int index = 1; // test ID, your can ignore this line
        
        int layer = 2;
        int nEpochs = 5;
        int iter = 1;
        
        
        int numInputs = 9;
        int numOutputs = 2;
        
        // model saving parameters
        // File output = new File("Trained_model.zip");
        // if you need to further train a model, set true
        // boolean saveUpdater = false;

        // for 20k demo data
        final String filenameTrain  = new ClassPathResource("train-clean.csv").getFile().getPath();
        final String filenameTest  = new ClassPathResource("test-clean.csv").getFile().getPath();
        
        //Load the training data:
        RecordReader rr = new CSVRecordReader();
        rr.initialize(new FileSplit(new File(filenameTrain)));
        DataSetIterator trainIter = new RecordReaderDataSetIterator(rr,batchSize,0,2);	

        //Load the test/evaluation data:
        RecordReader rrTest = new CSVRecordReader();
        rrTest.initialize(new FileSplit(new File(filenameTest)));
        DataSetIterator testIter = new RecordReaderDataSetIterator(rrTest,batchSize,0,2);

        // Data Normalization for training data
        /*DataNormalization nmm = new  NormalizerMinMaxScaler();
        nmm.fit(trainIter);
        trainIter.setPreProcessor(nmm);*/

        // ANN Configuration
        MultiLayerConfiguration conf = new NeuralNetConfiguration.Builder()
                .seed(seed)
                .iterations(iter)

                .optimizationAlgo(OptimizationAlgorithm.STOCHASTIC_GRADIENT_DESCENT)
                .learningRate(learningRate)
                .updater(Updater.NESTEROVS).momentum(mmt)
                .list()
                .layer(0, new DenseLayer.Builder().nIn(numInputs).nOut(numHiddenNodes)
                        .weightInit(WeightInit.XAVIER)
                        .activation(Activation.RELU)
                        .build())
                
                .layer(1, new DenseLayer.Builder().nIn(numHiddenNodes).nOut(numHiddenNodes)
                        .weightInit(WeightInit.XAVIER)
                        .activation(Activation.RELU)
                        .build())
                
                .layer(layer, new OutputLayer.Builder(LossFunction.NEGATIVELOGLIKELIHOOD)
                        .weightInit(WeightInit.XAVIER)
                        .activation(Activation.SOFTMAX).weightInit(WeightInit.XAVIER)
                        .nIn(numHiddenNodes).nOut(numOutputs).build())
                .pretrain(false).backprop(true).build();
        
	
        // if we load model from previous work
        // output: the model you saved.
        // MultiLayerNetwork model = ModelSerializer.restoreMultiLayerNetwork(output);
        
        
        MultiLayerNetwork model = new MultiLayerNetwork(conf);
        model.init();
        //model.setListeners(new ScoreIterationListener(100));  //Print score every 10 parameter updates

//        // view training information in UI
//        //Initialize the user interface backend
//        UIServer uiServer = UIServer.getInstance();
//        //Configure where the network information (gradients, score vs. time etc) is to be stored. Here: store in memory.
//        StatsStorage statsStorage = new InMemoryStatsStorage();         //Alternative: new FileStatsStorage(File), for saving and loading later
//        //Attach the StatsStorage instance to the UI: this allows the contents of the StatsStorage to be visualized
//        uiServer.attach(statsStorage);
//        //Then add the StatsListener to collect this information from the network, as it trains
//        model.setListeners(new StatsListener(statsStorage));

        // ******Start training
        for ( int n = 0; n < nEpochs; n++) {
            model.fit( trainIter );
        }

        // Evaluate Model
        System.out.println("Evaluate model....");
        Evaluation eval = new Evaluation(numOutputs);

        // Normalization for Test Data
        /*nmm.fit(testIter);
        testIter.setPreProcessor(nmm);*/

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
        
        System.out.println("ID:\t" + "LRate\t" + "BSize\t"+ "Epochs\t" + "Mmt\t" + "NodesPL\t" + "Layer");
        System.out.println("$" + index + "\t" + learningRate + "\t" + batchSize
        				+ "\t" + nEpochs + "\t" + mmt + "\t" + numHiddenNodes +  "\t" + layer);
        System.out.println();
        System.out.println("****************Training finished********************");
        
        // save model
        /*System.out.println();
        System.out.println("Save Model as " + output.getName());
        ModelSerializer.writeModel(model, output, saveUpdater);*/
       
        //System.exit(0);
        //statsStorage.close();
        //statsStorage.removeAllListeners();
        //uiServer.detach(statsStorage);
        //uiServer.stop();
    }
}