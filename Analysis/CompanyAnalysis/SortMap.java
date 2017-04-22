/**
 * 
 */
package preprocess;

import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
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
public class SortMap extends MapReduceBase
	implements Mapper<LongWritable, Text, DoubleWritable, Text> {

	/* (non-Javadoc)
	 * @see org.apache.hadoop.mapred.Mapper#map(java.lang.Object, java.lang.Object, org.apache.hadoop.mapred.OutputCollector, org.apache.hadoop.mapred.Reporter)
	 */
	@Override
	public void map(LongWritable key, Text value, OutputCollector<DoubleWritable, Text> output, Reporter reporter)
			throws IOException {
		String[] data = value.toString().split("\t");
		Double rate = Double.parseDouble(data[0]);
		output.collect(new DoubleWritable(rate), new Text(data[1]));
	}

}
