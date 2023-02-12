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
	 Statement stmt=null;//�۴���Statement����
	 ResultSet  rs=null;//�õ���ѯ���������ִ��update�Ȳ���
	 public PreparedStatement pst = null;
	 public DBHelper(String sql) {  
		        try {  
		            Class.forName(name);//ָ����������  
		            conn = DriverManager.getConnection(url,user,password);//��ȡ����  
		            System.out.println("���ݿ����ӳɹ�"+conn);
		            stmt=conn.createStatement();//statement��һ���ӿڣ��ṩ�������ݿⷢ��ִ�����ͻ�ȡ����ķ���
		           /* String users="CREATE TABLE USERS("+"name varchar(15) primary key,"+"password varchar(15) not null"+")charset=utf8;";
		            stmt=conn.createStatement();
		            if(0 == stmt.executeLargeUpdate(users))
		            {
		                System.out.println("�ɹ�������");
		            }
		            else
		            {
		                System.out.println("������ʧ�ܣ�");
		            }*///�������
		        } catch (Exception e) {  
		            e.printStackTrace();  
		            System.out.println(e.getMessage());
		        }  
		    }  
		public void close() {  //�ر����ݿ�
	        try {  
	            this.conn.close();   
	        } catch (SQLException e) {  
	            e.printStackTrace();  
	        }  
	    }
		public int eUpdate(String sql)//ִ�в�����䡢������䡢ɾ��
		{
			int rowCount=0;
			try {
				System.out.println("ִ�в������"+conn);
				rowCount=stmt.executeUpdate(sql);//ִ��SQL ������䣬����Update, Delete��Insert���
				//rowCount=pst.executeUpdate(sql);
				//conn.commit();
			}
			catch(SQLException e)
			{
				System.err.println("�����쳣"+e.getMessage());
			}
			return rowCount;
		}
		public ResultSet select(String sql) //��ѯ
		{
			ResultSet set=null;
			try {
				 set=stmt.executeQuery(sql);//ִ��SQL Select ��ѯ���
				return set;
			}catch(Exception e)
			{
				System.out.println("��ѯʧ��"+e.getMessage());
			}
			return set;
		}
}
