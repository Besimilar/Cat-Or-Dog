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
		System.setOut(new PrintStream(new FileOutputStream("shufflelog-6-clean.txt", true)));
		
		int N = 10;
		int id = 0; 
		int batch, epochs, hiddennode;
		double learningRate = 0, mnt = 0;
		for(int i = 0; i < N; i++) {
			id++;
			
			double rand = Math.random();
			//rand = rand > 0.01 ? rand : 1;
			//batch = (int)(Math.ceil(rand*10 + 0.5)) * 50; // 30 - 3000
			batch = (int)Math.round((rand*6 + 0.5)) * 10 + 40; // 20 - 200
			//batch = (int)Math.round((rand*100 + 0.5)) * 50; // 50 - 5000
			
			epochs = 5;
			
			rand = Math.random();
			//rand = rand > 0.01 ? rand : 1;
			hiddennode = (int)Math.round(rand*5 + 0.5) * 20 + 380; // 400 - 600
			
			rand = Math.random();
			learningRate = (int)Math.round(rand*14 + 0.5) * 0.005 + 0.03; // 0.04 - 0.1
			
			rand = Math.random();
			mnt = (int) Math.round(rand * 2 + 0.5) * 0.1 + 0.7; // 0.8 - 0.9
			
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
