/**
 * 
 */
package preprocess;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 
 * @author Guangnan Liang
 * ID: 001670972
 *
 */
public class RandomInput {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void run() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("Random.csv"));
		BufferedWriter writer0 = new BufferedWriter(new FileWriter("0.csv", false));
		BufferedWriter writer1 = new BufferedWriter(new FileWriter("1.csv", false));
		BufferedWriter writer2 = new BufferedWriter(new FileWriter("2.csv", false));
		BufferedWriter writer3 = new BufferedWriter(new FileWriter("3.csv", false));
		BufferedWriter writer4 = new BufferedWriter(new FileWriter("4.csv", false));
		BufferedWriter writer5 = new BufferedWriter(new FileWriter("5.csv", false));
		BufferedWriter writer6 = new BufferedWriter(new FileWriter("6.csv", false));
		BufferedWriter writer7 = new BufferedWriter(new FileWriter("7.csv", false));
		BufferedWriter writer8 = new BufferedWriter(new FileWriter("8.csv", false));
		BufferedWriter writer9 = new BufferedWriter(new FileWriter("9.csv", false));
		
		String line;
		
		while((line = reader.readLine()) != null) {
			int i = (int) (Math.random() * 10);
			//System.out.println(i);
			switch(i) {
				case 0:
					writer0.write(line);
					writer0.newLine();
					break;
				case 1:
					writer1.write(line);
					writer1.newLine();
					break;
				case 2:
					writer2.write(line);
					writer2.newLine();
					break;
				case 3:
					writer3.write(line);
					writer3.newLine();
					break;
				case 4:
					writer4.write(line);
					writer4.newLine();
					break;
				case 5:
					writer5.write(line);
					writer5.newLine();
					break;
				case 6:
					writer6.write(line);
					writer6.newLine();
					break;
				case 7:
					writer7.write(line);
					writer7.newLine();
					break;
				case 8:
					writer8.write(line);
					writer8.newLine();
					break;
				case 9:
					writer9.write(line);
					writer9.newLine();
					break;
				default: 
					break;
			}
		}
		reader.close();
		writer0.close();
		writer1.close();
		writer2.close();
		writer3.close();
		writer4.close();
		writer5.close();
		writer6.close();
		writer7.close();
		writer8.close();
		writer9.close();
	}

}
