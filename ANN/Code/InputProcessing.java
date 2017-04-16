/**
 * 
 */
package sundays.deeplearning.h1b;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * @author Hongwei Hu
 * NUID 001677683
 *
 */
public class InputProcessing {

	public static void main(String args[]) {
		
		try {
			ClassLoader loader = Thread.currentThread().getContextClassLoader();
			URL url = loader.getResource("classification/INPUT.csv");
			
			// test code
			// URL url = loader.getResource("classification/linear_data_train.csv");
			
			FileInputStream fr = new FileInputStream(url.getFile());
			InputStreamReader isr = new InputStreamReader(fr);
			BufferedReader br = new BufferedReader(isr);
			
			// save to csv
			BufferedWriter pw = new BufferedWriter(new FileWriter("INPUTdemo-test.csv"));
			// BufferedWriter pw = new BufferedWriter(new FileWriter("TRAINdemo.csv"));
			
			String line = "";	
			int count = 0;	
			int countNA = 0;
			int numLine = 40000;
			int startLine = 20000;
			
			// only save first numLine to csv as demo Input file
			while(((line = br.readLine()) != null) && (count < numLine)){
				
				// find 1st comma & remove last comma
				// System.out.println(line);
				int startIndex = line.indexOf(',');
				line = line.substring(startIndex+1, line.length()-1);
				//System.out.println(line);
				
				// remove NA lines
				if (!line.contains("NA")) {
				
					// replace NA to 0 
					// System.out.println(line);
					// line = line.replaceAll("NA", "0.0");
					// System.out.println(line);
					// countNA++;
					
					// remove un-pre-processed data
					if (!line.contains("Y") && !line.contains("N")){
						
						if (count >= startLine) {
							pw.write(line);
							pw.newLine();
						}
						
						count++;
					}	
					
					else System.out.println(line);
					
				}
				
				// System.out.println(line);
				// pw.write(line);
				// pw.newLine();
			}
			
			System.out.println("Total: " + countNA);

			pw.close();
			fr.close();
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
