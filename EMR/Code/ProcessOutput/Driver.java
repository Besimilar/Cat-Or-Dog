/**
 * 
 */
package output;

import java.io.IOException;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.TextInputFormat;
import org.apache.hadoop.mapred.TextOutputFormat;



/**
 * 
 * @author Guangnan Liang
 * ID: 001670972
 *
 */
public class Driver {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		JobConf conf = new JobConf(Driver.class);  
	    conf.setJobName("Collection Result");  
	  
	    conf.setOutputKeyClass(DoubleWritable.class);  
	    conf.setOutputValueClass(Text.class); 
	    
	    conf.setMapOutputKeyClass(IntWritable.class);
	    
	    conf.setMapperClass(Map.class);   
	    conf.setReducerClass(Reduce.class);  
	  
	    conf.setInputFormat(TextInputFormat.class);  
	    conf.setOutputFormat(TextOutputFormat.class);  
	  
	    conf.setOutputKeyComparatorClass(DoubleWritable.Comparator.class);
	    FileInputFormat.setInputPaths(conf, new Path(args[0]));  
	    FileOutputFormat.setOutputPath(conf, new Path("temp"));  
	  
	    JobClient.runJob(conf);  
	    
	    JobConf conf1 = new JobConf(Driver.class);  
	    conf1.setJobName("Sort the Accuracy");  
	  
	    conf1.setOutputKeyClass(DoubleWritable.class);  
	    conf1.setOutputValueClass(Text.class);  
	    
	    conf1.setMapperClass(SortMap.class);   
	    
	    conf1.setInputFormat(TextInputFormat.class);  
	    conf1.setOutputFormat(TextOutputFormat.class);  
	  
	    conf1.setOutputKeyComparatorClass(DoubleWritable.Comparator.class);
	    FileInputFormat.setInputPaths(conf1, new Path("temp"));  
	    FileOutputFormat.setOutputPath(conf1, new Path(args[1]));  
	  
	    JobClient.runJob(conf1); 
	    
	}

}
