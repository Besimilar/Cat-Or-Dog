# Deep Learning
Final Project for INFO 7250

## Team Members:

Byron Kiriibwa

Guangnan Liang

Hongwei Hu

## Introduction:

1. Preprocess: Code for how to preprocess the original DataSet.

2. Analysis: to use MapReduce to analyze the DataSet.

3. ANN: Code for training ANN in local machines. 

4. EMR: Code for parallel trainning ANN in Clusters.


## Instruction:

### For locally training: 
  
  1. ANN/Code (Eclipse): 
      1. Build a maven project using all java files and paste pom.xml
      2. Use H1BDemo class as main class to train ANN:
          * Simply change parameters at the start (Hyperparametes, HiddenNodes, etc.)
          * Add or remove layers: copy or remove .layer(), and set 1st parameter as the layer's location.

  2. ANN/Tune: same as previous step
      1. It's for semi-automatic tuning hyperparameters.
      2. Use GenerateInputParameter class as main class to tune.
          * Simply modify ranges of 4 parameters (BatchSize, LearningRate, Momentum, HiddenNodesPerLayer)
          * It's for 2 layers, you can modified layers in H1BDemo.java
      
### For parallel training:
  
  1. EMR/Code/Demo: basical code for creating EMR(H1BEmr.java) and running Spark jobs(H1BSpark.java).
  
  2. EMR/Code/CreateInput: It's for randomly generating hyperparameter combinations.
  
  3. EMR/Code/ParallelClusters: 
      1. CreateClusters/: Use ParallelCreateDriver as MapReduce main Class. It will use MR to read combinations from last step and then create clusters on AWS. 
      2. TrainningANN/: 
          * Build this maven project, upload the uberjar to S3 (Clusters will use this jar from S3).
          * Run last step, and then it will execute this step automatically.
          * Modify layers, simply modify ANN structure in "H1BSpark.java".
          * All results of clusters will be automatically uploaded to S3.
   
  4. EMR/Code/ProcessOutput:
      * All results will be stored in S3://bucketName/output folder. Download them to local file system.
      * Use Driver.java as MapReduce main class to MR results.
      * The output will rank all combinations by accuracy.
    
      
