import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.commons.httpclient.HttpException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GetPublic_timeline {
	/**
	 * 统计公共信息中微博来源
	 * @param args
	 * @throws HttpException
	 * @throws IOException
	 * @throws JSONException
	 */
	public static void main(String[] args) throws HttpException, IOException, JSONException{
		String result=HttpConnect.getBodyGET("https://api.weibo.com/2/statuses/public_timeline.json?access_token=2.00hRU3KCBJwhJB7a7bbed0caPgNPKE&count=200");
    	//System.out.println(result);
    	JSONObject jsonObject = new JSONObject(result);
    	JSONArray array = new JSONArray(jsonObject.getString("statuses"));
	     int count=0;
	     Map<String,Integer> map = new HashMap<String, Integer>();
	     for(int i =0;i<array.length();i++){
	    	 JSONObject object= array.getJSONObject(i);
	    	 String source =  object.getString("source");//微博内容
		     String key = null;
				key=source;
				Integer n = null;
				n=map.get(key);
				if(n==null){
					n=1;
				}else{
					n++;
				}
				map.put(key, n);
		     count++;
	     }
	     ArrayList<Entry<String,Integer>> l = new ArrayList<Entry<String,Integer>>(map.entrySet());     
	     Collections.sort(l, new Comparator<Map.Entry<String, Integer>>() {     
	    	   public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {     
	    	                return (o2.getValue() - o1.getValue());
	    	                }  
	    	   });   
	     for(Entry<String,Integer> e : l) {   
	         System.out.println(e.getKey() + "::::" + e.getValue());   
	        }  
	     System.out.println("total = "+count);
    
	}
}
