import java.io.UnsupportedEncodingException;
import java.sql.SQLException;


public class TEST {
	public static void main(String[] args) throws ClassNotFoundException, UnsupportedEncodingException, SQLException{
		ConnectMysql.executeSql("select * from newscontent","androidserver");
	}

}
