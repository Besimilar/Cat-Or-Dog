/**
 * 
 */
package output;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

/**
 * 
 * @author Guangnan Liang
 * ID: 001670972
 *
 */
public class Reduce extends MapReduceBase 
	implements Reducer<IntWritable, Text, DoubleWritable, Text>{

	/* (non-Javadoc)
	 * @see org.apache.hadoop.mapred.Reducer#reduce(java.lang.Object, java.util.Iterator, org.apache.hadoop.mapred.OutputCollector, org.apache.hadoop.mapred.Reporter)
	 */
	@Override
	public void reduce(IntWritable key, Iterator<Text> values, OutputCollector<DoubleWritable, Text> output, Reporter reporter)
			throws IOException {
		// TODO Auto-generated method stub
		String PM = null;
		String EG = null;
		String AC = null;
		int i = 0;
		while(values.hasNext()) {
			String line = values.next().toString();
			if(line.contains("$1")) {
				PM = line.replaceAll("$1", "");
				PM = PM.replaceAll("\t", " ");
			}
			else if(line.contains("0 classified by model as 1:")) {
				EG = line;
				
			}
			else AC = line.replaceAll(" Accuracy:        ", "");
		}
		Double ac = Double.parseDouble(AC);
		String v = "  "  + PM + "  " + EG + "  ";
		output.collect(new DoubleWritable(-ac), new Text(v));
	}

}
