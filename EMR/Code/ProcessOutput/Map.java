/**
 * 
 */
package output;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

/**
 * 
 * @author Guangnan Liang
 * ID: 001670972
 *
 */
public class Map extends MapReduceBase
	implements Mapper<LongWritable, Text, IntWritable, Text>{

	/* (non-Javadoc)
	 * @see org.apache.hadoop.mapred.Mapper#map(java.lang.Object, java.lang.Object, org.apache.hadoop.mapred.OutputCollector, org.apache.hadoop.mapred.Reporter)
	 */
	private static int k = 0;
	private static int n = 0;
	private final static int nl = 3;
	@Override
	public void map(LongWritable key, Text value, OutputCollector<IntWritable, Text> output, Reporter reporter) throws IOException {
		// TODO Auto-generated method stub
		
		if(n == nl) {
			k++;
			n = 0;
		}
		
		//System.out.println(k + " " + n);
		if(value.toString().contains("$1") ||
				value.toString().contains("0 classified by model as 1:") ||
				value.toString().contains("Accuracy:")) {
			System.out.println(value.toString());
			n++;
			output.collect(new IntWritable(k), value);
		}
			
	}

}
