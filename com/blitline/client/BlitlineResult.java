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
    private ArrayList<HashMap<String,String>> images;
    private String jobId = "";
    
    public BlitlineResult(String jobId, ArrayList<HashMap<String,String>> images) {
        this.images = images;
        this.jobId = jobId;
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
        sb.append("Job ID=");
        sb.append(this.jobId);
        if (this.images != null) {
            sb.append(" Images=");
            sb.append(this.images.toString());
        }
        return sb.toString();     
    }
    
}
