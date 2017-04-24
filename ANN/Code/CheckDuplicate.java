/**
 * 
 */
package sundays.deeplearning.check;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * @author Hongwei Hu
 * NUID 001677683
 *
 */
public class CheckDuplicate {

	public static void main(String args[]) {
		
		try {
			FileReader fr = new FileReader("test.csv");
			BufferedReader br = new BufferedReader(fr);
			
			BufferedWriter bw = new BufferedWriter(new FileWriter("test-clean.csv"));
			
			String line = "";
			int numLine = 0;
			ArrayList<String> data = new ArrayList<>();
			ArrayList<String> id = new ArrayList<>();
			
			while ((line = br.readLine()) != null){
				
				numLine++;
				
				// int index = line.indexOf(",");
				String label = line.substring(0, 1);
				line = line.substring(2);
				
				if (!data.contains(line)) {
					id.add(label);
					data.add(line);
				}
				else {
					int location = data.indexOf(line);
					if (label.equals("1")) {
						data.remove(location);
						id.remove(location);
						data.add(line);
						id.add(label);
					}
				}
				
				if ((numLine % 1000) == 0) System.out.println("Current: " + numLine);
				
			}
			
			System.out.println("label: " + id.size());
			System.out.println("Data: " + data.size());
			
			System.out.println("Generate clean dataset...");
			
			for (int i = 0; i<id.size(); i++) {
			
				bw.write(id.get(i) + "," + data.get(i));
				bw.newLine();
			}
			
			System.out.println("Total lines: " + numLine);
			System.out.println("No Repeat: " + data.size());
			double rate = (double)data.size() / numLine;
			System.out.println("Rate: " + rate);
			
			br.close();
			fr.close();
			bw.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
