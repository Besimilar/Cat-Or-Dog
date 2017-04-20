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
import java.util.ArrayList;

/**
 * 
 * @author Guangnan Liang
 * ID: 001670972
 *
 */
public class RemoveComma {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader reader = new BufferedReader(new FileReader("h1b_kaggle.csv"));
		BufferedWriter writer = new BufferedWriter(new FileWriter("INPUT.csv", true));
		
		BufferedWriter employer = new BufferedWriter(new FileWriter("EMPLOYER_NAME.csv", true));
		BufferedWriter soc = new BufferedWriter(new FileWriter("SOC_NAME.csv", true));
		BufferedWriter jobtitle = new BufferedWriter(new FileWriter("JOB_TITLE.csv", true));
		BufferedWriter worksite = new BufferedWriter(new FileWriter("WORKSITE.csv", true));
		
		ArrayList em = new ArrayList();
		ArrayList sc = new ArrayList();
		ArrayList jt = new ArrayList();
		ArrayList ws = new ArrayList();
		
		String line;
		int i = 0;
		reader.readLine();
		while((line = reader.readLine()) != null && i < 15){
			//System.out.println(line);
			line = line.replaceAll(", ", " ");
			line = line.replaceAll("\"", "");
			//System.out.println(line);
			String[] data = line.split(",");
			System.out.println(data.length);
			i++;
			writer.write(line);
			writer.newLine();
			
		}
		writer.close();
	}

}
