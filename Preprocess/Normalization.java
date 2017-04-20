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
import java.text.DecimalFormat;

/**
 * 
 * @author Guangnan Liang
 * ID: 001670972
 *
 */
public class Normalization {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("INPUTclean.csv"));
		BufferedReader reader1 = new BufferedReader(new FileReader("INPUTclean.csv"));
		BufferedWriter writer = new BufferedWriter(new FileWriter("NormalizedData2.csv", true));
		
		int N = 10;
		String line;
		double max[] = new double[N];
		double min[] = new double[N];
		double range[] = new double[N];
		
		for(int i = 0; i < N; i++) {
			max[i] = Double.MIN_VALUE;
			min[i] = Double.MAX_VALUE;
		}
		
		while((line = reader.readLine()) != null) {
			String[] data = line.split(",");
			double[] value = new double[N];
			// System.out.println(data.length);
			
			for(int i = 0; i < N; i++) {
				value[i] = Double.parseDouble(data[i]);
				if(value[i] > max[i]) max[i] = value[i];
				if(value[i] < min[i]) min[i] = value[i];
			}
		}
		
		for(int i = 0; i < N; i++)
			range[i] = max[i] - min[i];

		for(int i = 0; i < N; i++) 
			System.out.println("Max: " + max[i] + ", Min: " + min[i] + ", Range: " + range[i]);
		
		while((line = reader1.readLine()) != null) {
			String[] data = line.split(",");
			Double[] value = new Double[N];
			DecimalFormat df = new DecimalFormat("0.000000");   
			
			for(int i = 0; i < N; i++) {
				value[i] = Double.parseDouble(data[i]);
				if(i != 0 || i != 4) 
					value[i] = (value[i] - min[i]) / range[i];
			}
			
			line = "";
			for(int i = 0; i < N - 1; i++) 
				line += df.format(value[i]).toString() + ",";
			line += value[N - 1];
			writer.write(line);
			writer.newLine();
		}
		
		writer.close();
		reader.close();
		reader1.close();
	}

}
