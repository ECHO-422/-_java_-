package ChatRoom;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

import javax.swing.SwingWorker;


public class RECTHread extends Thread{
	HashMap<String,Socket>SocketMap1;
	 private ServerSocket serversocket2;
	public RECTHread()
	{
		this.SocketMap1=new HashMap<String,Socket>();
		//this.serversocket2=new ServerSocket(5433);
	}
	public void run()
	{
		try
		{
			this.serversocket2=new ServerSocket(5433);
			
			System.out.println("接收文件服务器正常启动!!");
			while(true)
			{
				System.out.println("我是大线程");
				Socket ss=this.serversocket2.accept(); //建立连接
				Thread recver=new RevFileThread(ss,this.SocketMap1); //建立为此客户传输消息的线程
			}
		}
		catch(Exception e)
		{
			System.out.println("服务器出错！！！");
		}
	}
	
}
