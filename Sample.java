import com.blitline.client.*;

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

	    // OK, since we got this far we have an App ID of some sort
	    String appId = args[0];
	    //  Here is some quick JSON to test this out.
	    String sampleJSON = "{ \"application_id\": \"" + appId + "\", \"src\" : \"http://www.google.com/logos/2011/yokoyama11-hp.jpg\", \"functions\" : [ {\"name\": \"blur\", \"params\" : {\"radius\" : 0.0,  \"sigma\" : 2.0}, \"save\" : { \"image_identifier\" : \"MY_CLIENT_ID\" }} ]}";
	    try {
		    BlitlineResult result = BlitlineClient.submitJsonToBlitline(sampleJSON);
			System.out.println("Submitted");
			System.out.println(result.toString());
			System.out.println("Once completed the file will be placed here:");
			System.out.println(result.getImages().get(0).get("s3_url"));
			System.out.println("Now we'll longpoll and wait for job to complete...");
		    BlitlinePostback postbackData = BlitlineClient.longPoll(result);
			System.out.println("Job has completed!");
			System.out.println("Here is information about the images:");
			System.out.println(postbackData.getImages().toString());
		}catch(BlitlineSubmissionException bse) {
			System.out.println("Do something to handle failures:" + bse.getMessage());
		}
    }
}