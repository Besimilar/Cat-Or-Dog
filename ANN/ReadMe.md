For locally training: 
  
  a. ANN/Code (Eclipse): 
    1. Build a maven project using all java files and paste pom.xml
    2. Use H1BDemo class as main class to train ANN:
      a. Simply change parameters at the start (Hyperparametes, HiddenNodes, etc.)
      b. Add or remove layers: copy or remove .layer(), and set 1st parameter as the layer's location.

  b. ANN/Tune: same as previous step a.1.
    1. It's for semi-automatic tuning hyperparameters.
    2. Use GenerateInputParameter class as main class to tune：
      a. Simply modify ranges of 4 parameters (BatchSize, LearningRate, Momentum, HiddenNodesPerLayer)
      b. It's for 2 layers, you can modified layers in H1BDemo.java