package com.blitline.client;

import java.util.ArrayList;
import java.util.HashMap;
/**
 * BlitlineResult represents the result of submitting a job to Blitline
 * 
 * NOTE: The BlitlineResult does NOT indicate that the job has completed!
 * This is only an indicator that the job was submitted to Blitline.
 * Blitline processes images asynchronously, so once you submit them
 * there will be latency before they are completed. Please see the
 * Blitline website for more information about how jobs are handled.
 * 
 * 
 * @author Blitline Developer
 */
public class BlitlineResult {
    private Integer responseCode = 0;
    private String error = "No Error";
    private ArrayList<HashMap<String,String>> images;
    private String jobId = "";
    
    public BlitlineResult(String jobId, Integer responseCode, String error, ArrayList<HashMap<String,String>> images) {
        this.responseCode = responseCode;
        this.error = error;
        this.images = images;
        this.jobId = jobId;
    }
    /**
     * Indicates whether there was an error during submission to Blitline
     * This should always be checked before using the results, you can get
     * the error message via the getError() method.
     * 
     * @return boolean
     */
    public boolean hasError() {
        if (responseCode != 200) {
            return true;
        }
        return false;
    }
    
    /**
     * Indicates the server response from Blitline.
     * 
     * @return 
     */
    public int getResponseCode() {
        return this.responseCode;
    }

    /**
     * User readable error message indicating the reason for job submission failure.
     * 
     * @return 
     */
    public String getError() {
        return this.error;
    }
    /**
     * Job ID associated with the successful submission to Blitline.com
     * 
     * @return 
     */
    public String getJobId() {
        return this.jobId;
    }
    /**
     * Information relating to the images that were submitted to Blitline
     * 
     * These include the location where the file will eventually be pushed to
     * after the processing is completed.
     * 
     * @return 
     */
    public ArrayList<HashMap<String,String>> getImages() {
        return this.images;
    }
    
    @Override
    public String toString() {
        StringBuilder sb =  new StringBuilder();
        sb.append("Response Code=");
        sb.append(this.responseCode.toString());
        if (this.images != null) {
            sb.append(" Images=");
            sb.append(this.images.toString());
        }
        return sb.toString();     
    }
    
}
