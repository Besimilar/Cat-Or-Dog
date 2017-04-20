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
public class ReplaceZero {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("NormalizedData2.csv"));
		BufferedWriter writer = new BufferedWriter(new FileWriter("NormalizedData2(NoZero).csv", true));
		String line;
		
		while((line = reader.readLine()) != null) {
			line = line.replaceAll(".000000", "");
			writer.write(line);
			writer.newLine();
		}
		reader.close();
		writer.close();
	}

}
