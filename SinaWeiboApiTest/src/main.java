import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;
public class main {
    public static void main(String[] args) throws HttpException, IOException, JSONException{
    	String result=HttpConnect.getBodyGET("https://api.weibo.com/2/statuses/friends_timeline.json?access_token="+CommonData.access_token+"&count=100");
    	//System.out.println(result);
    	JSONObject jsonObject = new JSONObject(result);
    	JSONArray array = new JSONArray(jsonObject.getString("statuses"));
	     int count=0;
	     for(int i =0;i<array.length();i++){
	    	 JSONObject object= array.getJSONObject(i);
		     String text =  object.getString("text");//微博内容
		     String source =  object.getString("source");//微博内容
		     JSONObject user = new JSONObject(object.getString("user"));
		     String screen_name = user.getString("screen_name");
		     String descriptionString = user.getString("description");
		     System.out.println("\n"+screen_name+":"+"\n"+text+" FROM "+source+"/n"+descriptionString+"/n"+
		    		 "*****************************************"); 
		     count++;
	     }
	     System.out.println("total = "+count);
	     
    	/*
    	String result=HttpConnect.getBodyGET("https://api.weibo.com/2/statuses/public_timeline.json?access_token=2.00hRU3KCBJwhJB7a7bbed0caPgNPKE&count=200");
    	//System.out.println(result);
    	JSONObject jsonObject = new JSONObject(result);
    	JSONArray array = new JSONArray(jsonObject.getString("statuses"));
	     int count=0;
	     Map<String,Integer> map = new HashMap<String, Integer>();
	     for(int i =0;i<array.length();i++){
	    	 JSONObject object= array.getJSONObject(i);
		     String text =  object.getString("text");
		   //  System.out.println(text);
		     StringReader reader = new StringReader(text);
		     IKSegmenter  ik = new IKSegmenter (reader, true);// 当为true时，分词器进行最大词长切分
				Lexeme lexeme = null;
				while ((lexeme = ik.next()) != null){
					String key = null;
					key=lexeme.getLexemeText();
					Integer n = null;
					n=map.get(key);
					if(n==null){
						n=1;
					}else{
						n++;
					}
					map.put(key, n);
				}
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
    
    	*/
    }
}
