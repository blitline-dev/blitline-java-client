package com.blitline.client;

import org.json.simple.JSONObject;
import java.util.HashMap;
import java.util.Iterator;
/**
 *
 * @author Blitline Developer
 */
public class BlitlineUtils {
    /**
     * Converts JSONObject to HashMap
     * 
     * @param jsonObject
     * @return 
     */
    public static HashMap<String, String> convertJSONObjectToHashMap(JSONObject jsonObject) {
        HashMap<String, String> hash = new HashMap<String, String>();
        
        Iterator it = jsonObject.keySet().iterator();
        while(it.hasNext()) {
            String key = it.next().toString();
            hash.put(key, jsonObject.get(key).toString());
        }
        
        return hash;      
    } 

}
