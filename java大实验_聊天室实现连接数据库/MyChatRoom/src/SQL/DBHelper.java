package SQL;
import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBHelper {
	 public static final String url = "jdbc:mysql://127.0.0.1/student?useSSL=false";  
	 public static final String name = "com.mysql.jdbc.Driver";  
	 public static final String user = "root";  
	 public static final String password = "ECHO422000";  
	 Connection conn=null;
	 Statement stmt=null;//③创建Statement对象
	 ResultSet  rs=null;//得到查询结果集或者执行update等操作
	 public PreparedStatement pst = null;
	 public DBHelper(String sql) {  
		        try {  
		            Class.forName(name);//指定连接类型  
		            conn = DriverManager.getConnection(url,user,password);//获取连接  
		            System.out.println("数据库连接成功"+conn);
		            stmt=conn.createStatement();//statement是一个接口，提供了向数据库发送执行语句和获取结果的方法
		           /* String users="CREATE TABLE USERS("+"name varchar(15) primary key,"+"password varchar(15) not null"+")charset=utf8;";
		            stmt=conn.createStatement();
		            if(0 == stmt.executeLargeUpdate(users))
		            {
		                System.out.println("成功创建表！");
		            }
		            else
		            {
		                System.out.println("创建表失败！");
		            }*///建表语句
		        } catch (Exception e) {  
		            e.printStackTrace();  
		            System.out.println(e.getMessage());
		        }  
		    }  
		public void close() {  //关闭数据库
	        try {  
	            this.conn.close();   
	        } catch (SQLException e) {  
	            e.printStackTrace();  
	        }  
	    }
		public int eUpdate(String sql)//执行插入语句、更新语句、删除
		{
			int rowCount=0;
			try {
				System.out.println("执行插入语句"+conn);
				rowCount=stmt.executeUpdate(sql);//执行SQL 更新语句，包括Update, Delete和Insert语句
				//rowCount=pst.executeUpdate(sql);
				//conn.commit();
			}
			catch(SQLException e)
			{
				System.err.println("发生异常"+e.getMessage());
			}
			return rowCount;
		}
		public ResultSet select(String sql) //查询
		{
			ResultSet set=null;
			try {
				 set=stmt.executeQuery(sql);//执行SQL Select 查询语句
				return set;
			}catch(Exception e)
			{
				System.out.println("查询失败"+e.getMessage());
			}
			return set;
		}
}
