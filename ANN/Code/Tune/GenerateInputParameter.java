/**
 * 
 */
package sundays.deeplearning.h1b;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

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
		//BufferedWriter bw = new BufferedWriter(new FileWriter("HPcombination.txt", false));
		System.setOut(new PrintStream(new FileOutputStream("log.txt", true)));
		
		int N = 50;
		int id = 0; 
		int batch, epochs, hiddennode;
		double learningRate = 0, mnt = 0;
		for(int i = 0; i < N; i++) {
			id++;
			
			double rand = Math.random();
			//rand = rand > 0.01 ? rand : 1;
			batch = (int)(Math.ceil(rand*100 + 0.5)) * 30; // 30 - 3000
			
			epochs = 5;
			
			rand = Math.random();
			//rand = rand > 0.01 ? rand : 1;
			hiddennode = (int)(Math.ceil(rand*30 + 0.5)) * 5; // 5- 150
			
			while((rand = Math.random()) > 0.1) {
				learningRate = (int) (rand * 100) / 10000.0; // 0.0001 - 0.01
			}
			
			while((rand = Math.random()) > 0.1) {
				mnt = (int) (rand * 10) / 10.0; // 0.1 - 0.9
			}
			
			//String line = id + " " + batch + " " + epochs + " "
			//		+ hiddennode + " " + learningRate + " " + mnt;
			
			//bw.write(line);
			//bw.newLine();
			
			H1BDemo a = new H1BDemo(batch, learningRate, mnt, hiddennode);
			try {
				a.run();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//bw.close();
		
		
	}

}
