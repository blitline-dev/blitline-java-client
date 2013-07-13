import com.blitline.client.BlitlineClient;
import com.blitline.client.BlitlineResult;
import com.blitline.client.BlitlineSubmissionException;

public class Sample {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    	if (args.length == 0) {
	        System.out.println("Application ID required for sample");
	        System.out.println("Pass Application ID as argument to Sample app");
	        System.out.println("Example: java -classpath .:lib:bin Sample MY_APP_ID");
	        System.out.println("(Where MY_APP_ID is your Blitline.com Application ID)");
	        return; 
	    }

	    String appId = args[0];
	    String sampleJSON = "{ \"application_id\": \"" + appId + "\", \"src\" : \"http://www.google.com/logos/2011/yokoyama11-hp.jpg\", \"functions\" : [ {\"name\": \"blur\", \"params\" : {\"radius\" : 0.0,  \"sigma\" : 2.0}, \"save\" : { \"image_identifier\" : \"MY_CLIENT_ID\" }} ]}";

	    try {
		    BlitlineResult result = BlitlineClient.submitJsonToBlitline(sampleJSON);
			System.out.println("Completed");
		}catch(BlitlineSubmissionException bse) {
			System.out.println("Do something to handle failures");
		}
    }
}