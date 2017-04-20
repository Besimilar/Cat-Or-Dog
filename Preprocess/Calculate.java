/**
 * 
 */
package preprocess;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


/**
 * 
 * @author Guangnan Liang
 * ID: 001670972
 *
 */
public class Calculate {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader reader = new BufferedReader(new FileReader("h1b_kaggle.csv"));
		int c, cw, d, w, sum;
		sum = c = cw = d = w = 0;
		reader.readLine();
		String line;
		while((line = reader.readLine()) != null){
			sum++;
			System.out.println(line);
			if(line.contains("CERTIFIED-WITHDRAWN")) cw++;
			else if(line.contains("CERTIFIED"))c++;
			else if(line.contains("DENIED")) d++;
			else if(line.contains("WITHDRAWN")) w++;
		}
		System.out.println("CERTIFIED: " + c + "\n" + "CERTIFIED-WITHDRAWN: " + cw + "\n" + "DENIED: " + d + "\n" + "WITHDRAWN: " + w);
		System.out.println("Total lines: " + sum);
	}

}
