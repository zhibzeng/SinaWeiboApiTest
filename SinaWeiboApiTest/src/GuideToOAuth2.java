import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.httpclient.HttpException;


public class GuideToOAuth2 {
    public static void main(String[] args) throws HttpException, IOException {
    	String result=HttpConnect.getBodyGET("https://api.weibo.com/oauth2/authorize?client_id="+
    				CommonData.client_ID+"&response_type=code&redirect_uri="+
    				"http://www.baidu.com");
    	System.out.println(result);
        //调用浏览器打开文件
    	//Runtime.getRuntime().exec( "c:/Program Files/Internet Explorer/IEXPLORE.EXE C:/hello.htm");
	}

}
