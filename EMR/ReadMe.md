For parallel training:
  
  a. EMR/Code/Demo: basical code for creating EMR(H1BEmr.java) and running Spark jobs(H1BSpark.java).
  
  b. EMR/Code/CreateInput: It's for randomly generating hyperparameter combinations.
  
  c. EMR/Code/ParallelClusters: 
    1. CreateClusters/: Use ParallelCreateDriver as MapReduce main Class. It will use MR to read combinations from last step and then create clusters on AWS. 
    2. TrainningANN/: 
      a. Build this maven project, upload the uberjar to S3 (Clusters will use this jar from S3).
      b. Run last step, and then it will execute this step automatically.
      c. Modify layers, simply modify ANN structure in "H1BSpark.java".
      d. All results of clusters will be automatically uploaded to S3.
   
   d. EMR/Code/ProcessOutput:
    1. All results will be stored in S3://bucketName/output folder. Download them to local file system.
    2. Use Driver.java as MapReduce main class to MR results.
    3. The output will rank accuracy of all combinations.