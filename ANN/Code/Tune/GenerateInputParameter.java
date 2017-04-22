/**
 * 
 */
package sundays.deeplearning.h1b_spark;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 
 * @author Guangnan Liang
 * ID: 001670972
 *
 */
public class GenerateInputParameter {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter("HPcombination.txt", false));
		int N = 5;
		int id = 0; 
		int batch, epochs, hiddennode;
		double learningRate = 0, mnt = 0;
		for(int i = 0; i < N; i++) {
			id++;
			
			double rand = Math.random();
			//rand = rand > 0.01 ? rand : 1;
			batch = (int)(Math.ceil(rand*100 + 0.5)) * 100; // 100 - 10000
			
			epochs = 5;
			
			rand = Math.random();
			//rand = rand > 0.01 ? rand : 1;
			hiddennode = (int)(Math.ceil(rand*30 + 0.5)) * 5; // 5- 150
			
			while((rand = Math.random()) > 0.1) {
				learningRate = (int) (rand * 10) / 1000.0; // 0.001 - 0.01
			}
			
			while((rand = Math.random()) > 0.1) {
				mnt = (int) (rand * 10) / 10.0; // 0.1 - 0.9
			}
			
			String line = id + " " + batch + " " + epochs + " "
					+ hiddennode + " " + learningRate + " " + mnt;
			
			bw.write(line);
			bw.newLine();
		}
		bw.close();
	}

}
