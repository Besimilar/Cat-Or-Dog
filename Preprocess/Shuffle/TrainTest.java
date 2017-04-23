/**
 * 
 */
package preprocess;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * 
 * @author Guangnan Liang
 * ID: 001670972
 *
 */
public class TrainTest {
	
	public static void run() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("Random.csv"));
		BufferedWriter train = new BufferedWriter(new FileWriter("train.csv", false));
		BufferedWriter test = new BufferedWriter(new FileWriter("test.csv", false));
		String line;
		
		while((line = reader.readLine()) != null) {
			if(Math.random() > 0.25){
				train.write(line);
				train.newLine();
			} else {
				test.write(line);
				test.newLine();
			}
		}
		reader.close();
		train.close();
		test.close();
	}
	
	public static void main(String[] args) throws IOException {
		System.out.println("Please Enter the times of shuffle");
		Scanner s = new Scanner(System.in);
		int num = s.nextInt();
		for(int i = 0; i < num; i++) {
			System.out.println(i);
			RandomInput.run();
			Combine.run();
		}
		run();
	}

}
