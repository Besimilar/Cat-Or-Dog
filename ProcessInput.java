package assign7;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProcessInput {

	private void run() throws IOException {
		
		FileInputStream fin = new FileInputStream("input.txt");
		
		InputStreamReader isr = new InputStreamReader(fin);
		
		BufferedReader br = new BufferedReader(isr);
		
		FileWriter fw = new FileWriter("input-processed.txt");
		
		String line = "";
		
		while ((line = br.readLine()) != null) {
			
			//System.out.println(line);
			
			Pattern regex = Pattern.compile(",");
			Matcher match = regex.matcher(line);
			while (match.find()) {
				
				String str = match.group();
				//System.out.println(str);
				int index = line.indexOf(str);
				String op = line.substring(0, index);
				String op1 = line.substring(index+1);
				String op2 = "1";
				fw.write(op+ "\t" + op1 + "\t" + op2 + "\n");
			}
		}
		
		fin.close();
		fw.close();

	}
	
	
	
	public static void main(String args[]) throws IOException {
		
		ProcessInput p = new ProcessInput();
		
		p.run();
	}
}
