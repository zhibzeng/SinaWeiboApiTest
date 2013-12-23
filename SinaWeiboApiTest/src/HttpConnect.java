import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;


public class HttpConnect {
	 /**
	  * POST请求
    * 得到Http 请求结果
    * @param url请求地址
    * @param parms请求参数
    * @return
    */
   public synchronized static String getBodyPost(String url, Map parms) {
       // byte[] body = null;
       String resultstr = null;
       // 构造HttpClient的实例
       HttpClient httpClient = new HttpClient();
       // 创建POST方法的 实例
       PostMethod postMethod = new PostMethod(url);
       // 填入各个表单域的 值
       NameValuePair[] data = new NameValuePair[parms.keySet().size()];
       Iterator it = parms.entrySet().iterator();
       int i = 0;
       while (it.hasNext()) {
           Map.Entry entry = (Map.Entry) it.next();
           Object key = entry.getKey();
           Object value = entry.getValue();
           data[i] = new NameValuePair(key.toString(), value.toString());
           i++;
       }
       // 将表单的 值放入postMethod中
       postMethod.setRequestBody(data);
       try {
           // 执行postMethod
           int statusCode = httpClient.executeMethod(postMethod);// httpclient对于要求接受后继服务的请求，等待返回
           // 象post和put等不能自动处理转发
           // 301或者302
           if (statusCode == HttpStatus.SC_MOVED_PERMANENTLY
                   || statusCode == HttpStatus.SC_MOVED_TEMPORARILY) {
               // 从 头中取出转向的地址
               Header locationHeader = postMethod
                       .getResponseHeader("location");
               String location = null;
               if (locationHeader != null) {
                   location = locationHeader.getValue();
                   System.out.println("The page was redirected to:" + location);
               } else {
                   System.out.println("Location field value is null");
               }
           }
           // 第一张接收返回信息方式
           // body = postMethod.getResponseBody();
           resultstr = new String(postMethod.getResponseBodyAsString()
                   .getBytes(), "UTF-8");

           /**
            * 第二张接收返回信息方式 String newStr = new
            * String(postMethod.getResponseBodyAsString().getBytes(), "UTF-8"
            * ); System.out.println(newStr);
            */

       } catch (Exception e) {

           e.printStackTrace();
       } finally {
           postMethod.releaseConnection();
       }
       return resultstr;
   }
   
   /**
    * GET请求
    * @param args
    * @throws IOException 
    * @throws HttpException 
    */
   public synchronized static String getBodyGET(String url) throws HttpException, IOException{
   	String reslut = null; 
   	//构造HttpClient的实例
   	  HttpClient httpClient = new HttpClient();
   	  //创建GET方法的实例
   	  GetMethod getMethod = new GetMethod(url);
   	  //使用系统提供的默认的恢复策略
   	  getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
   	    new DefaultHttpMethodRetryHandler());
   	  try {
   	   //执行getMethod
   	   int statusCode = httpClient.executeMethod(getMethod);
   	   if (statusCode != HttpStatus.SC_OK) {
   	    System.err.println("Method failed: "
   	      + getMethod.getStatusLine());
   	   }
   	   //读取内容 
   	   byte[] responseBody = getMethod.getResponseBody();
   	   reslut=new String(responseBody);
   	  } catch (HttpException e) {
   	   //发生致命的异常，可能是协议不对或者返回的内容有问题
   	   System.out.println("Please check your provided http address!");
   	   e.printStackTrace();
   	  } catch (IOException e) {
   	   //发生网络异常
   	   e.printStackTrace();
   	  } finally {
   	   //释放连接
   	   getMethod.releaseConnection();
   	  }
		return reslut;
   };

}
