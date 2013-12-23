import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class ConnectMysql {
	public static void executeSql(String sql,String database) throws ClassNotFoundException, SQLException, UnsupportedEncodingException{
		//驱动程序名
		String driver = "com.mysql.jdbc.Driver";
		// URL指向要访问的数据库名scutcs
		String url = "jdbc:mysql://127.0.0.1:3306/"+database;
		// MySQL配置时的用户名
		String user = "root";
		// Java连接MySQL配置时的密码
		String password = "7165092054";
		// 加载驱动程序
		Class.forName(driver);
		// 连续数据库
		Connection conn = DriverManager.getConnection(url, user, password);
		if(!conn.isClosed())
		System.out.println("Succeeded connecting to the Database!");
		// statement用来执行SQL语句
		Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery(sql);  
		System.out.println("-----------------");  
		System.out.println("执行结果如下所示:");  
		System.out.println("-----------------");  
		System.out.println(" 学号" + "\t" + " 姓名");  
		System.out.println("-----------------");  
		String name = null;  
		while(rs.next()) {  
			name = rs.getString("sname");
			name = new String(name.getBytes("ISO-8859-1"),"GB2312");
			System.out.println(name);  
		}  
		rs.close();  
		conn.close();   
	}
}
