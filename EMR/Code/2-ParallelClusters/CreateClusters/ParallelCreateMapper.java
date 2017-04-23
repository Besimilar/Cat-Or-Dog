/**
 * 
 */
package sundays.deeplearning.h1b.cluster;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

public class ParallelCreateMapper extends MapReduceBase
	implements Mapper<LongWritable, Text, Text, Text>{

	public void map(LongWritable key, Text value, OutputCollector<Text, Text> output, Reporter reporter) throws IOException {
		
		//System.out.println(value.toString());
		H1BEmrMR hmr = new H1BEmrMR(value.toString());
		output.collect(new Text("Parameters"), value);
	}

}
