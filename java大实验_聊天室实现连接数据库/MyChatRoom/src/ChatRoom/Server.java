package ChatRoom;
import java.net.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
public class Server extends Thread{
	ServerSocket serversocket1;
	//ServerSocket serversocket2;
	static String text;
	HashMap<String,Socket>SocketMap;
	public Server()
	{
		super();
		this.text="等待连接";
		this.SocketMap=new HashMap<String,Socket>();
		
	}
	public void run()
	{
		try
		{
			this.serversocket1=new ServerSocket(5432);
			
			System.out.println("服务器正常启动!!");
			while(true)
			{
				System.out.println("我是大线程");
				Socket ss=this.serversocket1.accept(); //建立连接
				String name=(new MyServer(ss,this.SocketMap)).getname(); //建立为此客户传输消息的线程
				if(text.equals("等待连接"))
				{
					text="";
				}
				try
				{
				//	String name=in.readLine();
					
					text=text+"\n"+name+"与服务器建立连接";
					
				}
				catch(Exception ev)
				{
					System.out.println("生成子线程出错！");
				}
				
			}
		}
		catch(Exception e)
		{
			System.out.println("服务器出错！！！");
		}
	}
	public String Status()//返回服务器的状态
	{
		String str="";
		return text;
	}
	public int client_num()
	{
		return (MyServer.count);
	}
}