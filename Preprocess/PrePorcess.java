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
public class PrePorcess {

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
		double start = System.currentTimeMillis();
		reader.readLine();
		while((line = reader.readLine()) != null){
//			System.out.println();
//			System.out.println(line);
			line = line.replaceAll(", ", " ");
			line = line.replaceAll("\"", "");
//			System.out.println(line);
			String[] data = line.split(",");
//			System.out.println(data.length);
//			i++;
			
			if(data[1].equals("CERTIFIED")) data[1] = "1";
			else data[1] = "0";
			
			if(em.contains(data[2])) data[2] = em.indexOf(data[2]) + 1 + "";
			else {
				employer.write(em.size() + 1 + "," + data[2]);
				employer.newLine();
				em.add(data[2]);
				data[2] = em.size() + "";
			}
		
			if(sc.contains(data[3])) data[3] = sc.indexOf(data[3]) + 1 + "";
			else {
				soc.write(sc.size() + 1 + "," + data[3]);
				soc.newLine();
				sc.add(data[3]);
				data[3] = sc.size() + "";
			}
			
			if(jt.contains(data[4])) data[4] = jt.indexOf(data[4]) + 1 + "";
			else {
				jobtitle.write(jt.size() + 1 + "," + data[4]);
				jobtitle.newLine();
				jt.add(data[4]);
				data[4] = jt.size() + "";
			}
			
			if(data[5].equals("Y")) data[5] = "1";
			else data[5] = "0";
			
			if(ws.contains(data[8])) data[8] = ws.indexOf(data[8]) + 1 + "";
			else {
				worksite.write(ws.size() + 1 + "," + data[8]);
				worksite.newLine();
				ws.add(data[8]);
				data[8] = ws.size() + "";
			}
			
			line = "";
			for(String s : data)
				line += s + ",";
			
			writer.write(line);
			writer.newLine();
		}
		writer.close();
		employer.close();
		soc.close();
		jobtitle.close();
		worksite.close();
		double end = System.currentTimeMillis();
		end -= start;
		System.out.println("Total running time: " + (end / 1000));
		System.out.println("Process Finished!");
	}

}
