/**
 * 
 */
package preprocess;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 
 * @author Guangnan Liang
 * ID: 001670972
 *
 */
public class BalanceData {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader reader = new BufferedReader(new FileReader("INPUTclean.csv"));
		BufferedWriter writer = new BufferedWriter(new FileWriter("BalancedData.csv", true));
		
		String line;
		int num = 365000;
		int deny = 0;
		int total = 0;
		int certificate = 0;
		
//		while((line = reader.readLine()) != null) {
//			//System.out.println(line);
//			if(line.charAt(0) == '0') deny++;
//			else certificate++;
//			total++;
//		}
//		System.out.println(deny + " " + certificate + " " + total);
		
		while((line = reader.readLine()) != null) {
			//System.out.println(line);
			if(deny < num && line.charAt(0) == '0') {
				writer.write(line);
				writer.newLine();
				deny++;
			}
			else if(certificate < num && line.charAt(0) == '1') {
				writer.write(line);
				writer.newLine();
				certificate++;
			}
			//System.out.println(deny + " " + certificate);
			if(deny == num && certificate == num) break;
		}
		reader.close();
		writer.close();
	}

}
