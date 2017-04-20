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
public class Combine {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void run() throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter("Random.csv", false));
		BufferedReader reader0 = new BufferedReader(new FileReader("0.csv"));
		BufferedReader reader1 = new BufferedReader(new FileReader("1.csv"));
		BufferedReader reader2 = new BufferedReader(new FileReader("2.csv"));
		BufferedReader reader3 = new BufferedReader(new FileReader("3.csv"));
		BufferedReader reader4 = new BufferedReader(new FileReader("4.csv"));
		BufferedReader reader5 = new BufferedReader(new FileReader("5.csv"));
		BufferedReader reader6 = new BufferedReader(new FileReader("6.csv"));
		BufferedReader reader7 = new BufferedReader(new FileReader("7.csv"));
		BufferedReader reader8 = new BufferedReader(new FileReader("8.csv"));
		BufferedReader reader9 = new BufferedReader(new FileReader("9.csv"));
		
		String line;
		
		while((line = reader0.readLine()) != null) {
			writer.write(line);
			writer.newLine();
		}
		while((line = reader1.readLine()) != null) {
			writer.write(line);
			writer.newLine();
		}
		while((line = reader2.readLine()) != null) {
			writer.write(line);
			writer.newLine();
		}
		while((line = reader3.readLine()) != null) {
			writer.write(line);
			writer.newLine();
		}
		while((line = reader4.readLine()) != null) {
			writer.write(line);
			writer.newLine();
		}
		while((line = reader5.readLine()) != null) {
			writer.write(line);
			writer.newLine();
		}
		while((line = reader6.readLine()) != null) {
			writer.write(line);
			writer.newLine();
		}
		while((line = reader7.readLine()) != null) {
			writer.write(line);
			writer.newLine();
		}
		while((line = reader8.readLine()) != null) {
			writer.write(line);
			writer.newLine();
		}
		while((line = reader9.readLine()) != null) {
			writer.write(line);
			writer.newLine();
		}
		reader0.close();
		reader1.close();
		reader2.close();
		reader3.close();
		reader4.close();
		reader5.close();
		reader6.close();
		reader7.close();
		reader8.close();
		reader9.close();
		writer.close();
	}

}
