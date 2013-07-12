/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blitline.client;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;


/**
 * BlitlinePostback is the object representation of the JSON that would 
 * get submitted back to your server via a Postback call from Blitline.
 * 
 * You can also use the BlitlineClient.longPoll method to simulate a postback.
 * AS PER THE BLITLINE DOCUMENTATION, it is NOT recommended that you use
 * the longpolling option in production code. The longpolling functionality
 * is supplied by Blitline as a development tool. Actual postbacks to your
 * server are preferred over longpolling.
 * 
 * @author Blitline Developer
 */
public class BlitlinePostback {
    String postbackJson;
    JSONObject baseJson = null;
    String jobId="";
    String errorMessage=null;
    HashMap<String, String> originaMeta = null;
    ArrayList<HashMap<String, String>> images = new ArrayList<HashMap<String, String>>();
    
    public BlitlinePostback(String json) {
        this.postbackJson = json;
        JSONParser parser = new JSONParser();

        try {
            JSONObject rawJason = (JSONObject)parser.parse(json);
            String resultsJson = rawJason.get("results").toString();
            try {
                baseJson = (JSONObject)parser.parse(resultsJson);
            }catch(ParseException parsex) {
                baseJson = (JSONObject)rawJason.get("results");
            }
            
            if (baseJson != null) {
                Object val = baseJson.get("job_id");
                this.jobId = (val != null) ? val.toString() : "";   
                parseOriginalMeta();
                Object hashImages = baseJson.get("images");
                if (hashImages != null) {
                    parseImages((JSONArray)hashImages);                
                }
            }
        }catch( Exception pex) {
            System.out.println(json);
            System.out.println(pex.getMessage());
            baseJson = null;
        }
    }
    
    public boolean hasError() {
        return (this.errorMessage != null);
    }
    
    public String getErrorMessage() {
        return this.errorMessage;
    }
    
    public String getJobId() {
        return this.jobId;
    }
    
    public HashMap<String, String> getOriginalMeta() {
        return this.originaMeta;
    }
    
    public ArrayList<HashMap<String, String>> getImages() {
        return this.images;
    }
    
    private void parseImages(JSONArray images) {
        Iterator it = images.iterator();
        while(it.hasNext()) {
            JSONObject item = (JSONObject)it.next();
            Object error = item.get("error");
            if (error != null) {
                this.errorMessage = (String)error;
                Object failedIdentifiers = item.get("failed_image_identifiers");
                if (failedIdentifiers != null) {
                    System.out.println(failedIdentifiers.toString());
                }
            }else {
                HashMap<String, String> imageData = BlitlineUtils.convertJSONObjectToHashMap(item);
                this.images.add(imageData);
            }
        }
    }

    private void parseOriginalMeta() {
        HashMap<String, String> originalMeta = new HashMap<String, String>();
        JSONObject meta = (JSONObject)this.baseJson.get("original_meta");
        if (meta != null) {
            this.originaMeta = BlitlineUtils.convertJSONObjectToHashMap(meta);
        }
    }
}
