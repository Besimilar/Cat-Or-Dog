package sundays.deeplearning.h1b.cluster;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;

public class ParallelCreateDriver {
	
	public static void main(String[] args)  throws Exception
	{ 
		
		JobClient client = new JobClient();
		JobConf conf = new JobConf(ParallelCreateDriver.class);
		conf.setJobName("H1B Parallel Cluster Creation");

		// specify the map class
		conf.setMapperClass(ParallelCreateMapper.class);
		
		// only need Mapper
		conf.setNumReduceTasks(0);
		
		FileInputFormat.setInputPaths(conf, new Path(args[0]));
		FileOutputFormat.setOutputPath(conf, new Path(args[1]));

		client.setConf(conf);
		try {
			JobClient.runJob(conf);
		} catch (IOException ex) {
			Logger.getLogger(ParallelCreateDriver.class.getName()).log(
					Level.SEVERE, null, ex);
		}
		
	}
	
}
