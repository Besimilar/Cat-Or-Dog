/**
 * 
 */
package sundays.deeplearning.h1b_spark;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.PutObjectRequest;

/**
 * @author Hongwei Hu
 * NUID 001677683
 *
 */
public class UploadToS3 {

	public void run(String id, String Params, String stats) {
		
		String bucketName = "sunday9";
		String accessKey = "";
		String secretKey = "";
		Regions region = Regions.US_WEST_2; 

		String fileName = id + "-result.txt"; 
		
		AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
		AmazonS3ClientBuilder s3builder = AmazonS3ClientBuilder.standard();
        s3builder.withRegion(Regions.US_WEST_2);
        s3builder.withCredentials(new AWSStaticCredentialsProvider(credentials));
        AmazonS3 s3Client = s3builder.build();
        
        File file = new File(fileName);
        
        try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
			bw.write(id.toString());
			bw.newLine();
			bw.write("Labels:\t" + "learningRate\t" + "mtn\t" + "HiddenNodes\t" 
        			+ "Epochs\t" + "batchSize\t" + "iter");
			bw.newLine();
			bw.write("Params:\t" + Params);
			bw.newLine();
			bw.write(stats);
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        String location = "output/" + fileName;
        s3Client.putObject(new PutObjectRequest(bucketName, location, file));
	}
	
}
