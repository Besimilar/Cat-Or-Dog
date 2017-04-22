/**
 * 
 */
package preprocess;

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
		BufferedWriter bw = new BufferedWriter(new FileWriter("50 line.txt", false));
		int N = 50;
		int id = 0; 
		int batch, epochs, hiddennode;
		double learningRate = 0, mnt = 0;
		for(int i = 0; i < N; i++) {
			id++;
			
			double rand = Math.random();
			rand = rand > 0.01 ? rand : 1;
			batch = (int) (rand * 100) * 100;
			
			epochs = 50;
			
			rand = Math.random();
			rand = rand > 0.01 ? rand : 1;
			hiddennode = (int) (rand * 30) * 10;
			
			while((rand = Math.random()) > 0.1) {
				learningRate = (int) (rand * 10) / 1000.0;
			}
			
			while((rand = Math.random()) > 0.1) {
				mnt = (int) (rand * 10) / 10.0;
			}
			
			String line = id + " " + batch + " " + epochs + " "
					+ hiddennode + " " + learningRate + " " + mnt;
			
			bw.write(line);
			bw.newLine();
		}
		bw.close();
	}

}
