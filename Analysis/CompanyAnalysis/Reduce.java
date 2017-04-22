/**
 * 
 */
package preprocess;

import java.io.IOException;
import java.text.DecimalFormat;
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
	implements Reducer<Text, IntWritable, DoubleWritable, Text>{

	/* (non-Javadoc)
	 * @see org.apache.hadoop.mapred.Reducer#reduce(java.lang.Object, java.util.Iterator, org.apache.hadoop.mapred.OutputCollector, org.apache.hadoop.mapred.Reporter)
	 */
	@Override
	public void reduce(Text key, Iterator<IntWritable> values, OutputCollector<DoubleWritable, Text> output, Reporter reporter)
			throws IOException {
		// TODO Auto-generated method stub
		int sum = 0;
		int num = 0;
		while(values.hasNext()) {
			sum++;
			if(values.next().get() > 0) num++;
		}
		DecimalFormat df = new DecimalFormat("0.000");   
		Double rate = (double) num / sum;
		rate = Double.parseDouble(df.format(rate));
		String line = key.toString() + " " + sum;
		//System.out.println(rate);
		if(sum > 350) output.collect(new DoubleWritable(-rate), new Text(line));
	}

}
