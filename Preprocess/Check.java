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

import javax.sound.sampled.Line;

/**
 * 
 * @author Guangnan Liang
 * ID: 001670972
 *
 */
public class Check {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("h1b_kaggle.csv"));
		
		BufferedReader reader1 = new BufferedReader(new FileReader("INPUT.csv"));
		BufferedReader reader2 = new BufferedReader(new FileReader("EMPLOYER_NAME.csv"));
		BufferedReader reader3 = new BufferedReader(new FileReader("SOC_NAME.csv"));
		BufferedReader reader4 = new BufferedReader(new FileReader("JOB_TITLE.csv"));
		BufferedReader reader5 = new BufferedReader(new FileReader("WORKSITE.csv"));
		
		String line = "", line1 = "", s;
		String[] data, data1;
		boolean[] flag = new boolean[6];;
		
		int num = (int) (Math.random() * 3000000);
		
		for(int j = 0; j < 6; j++)
			flag[j] = false;
		
		reader.readLine();
		while(num > 0) {
			line1 = reader1.readLine();
			line = reader.readLine();
			num--;
		}
		
		line = line.replaceAll(", ", " ");
		line = line.replaceAll("\"", "");
		
		data = line.split(",");
		data1 = line1.split(",");
		
		if((data[1].equals("CERTIFIED") && data1[1].equals("1")) 
				|| (!data[1].equals("CERTIFIED") && data1[1].equals("0")))
			flag[0] = true;
		else flag[0] = false;
		
		if((data[5].equals("Y") && data1[5].equals("1")) 
				|| (data[1].equals("N") && data1[1] == "0"))
			flag[1] = true;
		else flag[1] = false;
		
		while((s = reader2.readLine()) != null){
			String[] d = s.split(",");
			if(d[0].equals(data1[2]) && d[1].equals(data[2]))
				flag[2] = true;
		}
		
		while((s = reader3.readLine()) != null){
			String[] d = s.split(",");
			if(d[0].equals(data1[3]) && d[1].equals(data[3]))
				flag[3] = true;
		}
		
		while((s = reader4.readLine()) != null){
			String[] d = s.split(",");
			if(d[0].equals(data1[4]) && d[1].equals(data[4]))
				flag[4] = true;
		}
		
		while((s = reader5.readLine()) != null){
			String[] d = s.split(",");
			if(d[0].equals(data1[8]) && d[1].equals(data[8]))
				flag[5] = true;
		}
		
		for(boolean b : flag)
			System.out.print(b + " ");
		System.out.println();
		
	}

}
