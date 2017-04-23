## For locally training: 
  
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
