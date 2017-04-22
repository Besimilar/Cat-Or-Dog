/**
 * 
 */
package sundays.deeplearning.h1b;

import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 * 
 * @author Guangnan Liang
 * ID: 001670972
 *
 */
public class Test {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		System.setOut(new PrintStream(new FileOutputStream("log.txt", true)));
		
		for(int i = 100; i <= 1000; i += 100) {
			for(double j = 0.0001; j < 0.003; j += 0.0005) {
				H1BDemo a = new H1BDemo(i, j, 0.8);
				a.run();
			}
				
		}
	}

}
