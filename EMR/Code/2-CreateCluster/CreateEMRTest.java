/**
 * 
 */
package sundays.deeplearning.h1b_spark;

/**
 * @author Hongwei Hu
 * NUID 001677683
 *
 */
public class CreateEMRTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

//      String id = "555";
//      String batchSizePerWorker = "5555";
//      String numEpochs = "5";
//      String hiddenNodes = "55";
//      String learningRate = "0.05";
//      String momentum = "0.5";
		
		String param1 = "555 5555 1 5 0.1 0.5";
		String param2 = "5555 5555 1 5 0.1 0.5" ;
		
		new H1BEmrMR(param1);
		new H1BEmrMR(param2);

	}

}
