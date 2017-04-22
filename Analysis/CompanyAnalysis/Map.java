/**
 * 
 */
package preprocess;

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
	implements Mapper<LongWritable, Text, Text, IntWritable> {

	/* (non-Javadoc)
	 * @see org.apache.hadoop.mapred.Mapper#map(java.lang.Object, java.lang.Object, org.apache.hadoop.mapred.OutputCollector, org.apache.hadoop.mapred.Reporter)
	 */
	private final static IntWritable one = new IntWritable(1);
	private final static IntWritable zero = new IntWritable(0);
	@Override
	public void map(LongWritable key, Text value, OutputCollector<Text, IntWritable> output, Reporter reporter) throws IOException {
		// TODO Auto-generated method stub
		String line = value.toString();
		line = line.replaceAll(", ", " ");
		line = line.replaceAll("\"", "");
		String[] data = line.split(",");
		if(data[1].equals("CERTIFIED")) 
			output.collect(new Text(data[2]), one);
		else output.collect(new Text(data[2]), zero);
	}

}
