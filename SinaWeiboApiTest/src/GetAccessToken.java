import java.util.HashMap;
import java.util.Map;


public class GetAccessToken {
	public static void main(String[] args){
		//根据获取的CODE换取Access Token  只能用POST请求
    	String url="https://api.weibo.com/oauth2/access_token";
    	String code="c9c5ab7aea8cafd66873266a9d3fc45f";
    	Map paramMap=new HashMap<>();
    	paramMap.put("client_id", "1059591471");
    	paramMap.put("client_secret","c64bd653ed3a7716930a99d425783397");
    	paramMap.put("grant_type","authorization_code");
    	paramMap.put("redirect_uri","http://www.baidu.com");
    	paramMap.put("code",code);
    	String result = HttpConnect.getBodyPost(url, paramMap);
    	System.out.println(result);
	}

}
