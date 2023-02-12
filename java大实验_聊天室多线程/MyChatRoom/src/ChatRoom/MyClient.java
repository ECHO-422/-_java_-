package ChatRoom;
import java.io.*;
import java.net.*;
import java.util.Scanner;
public class MyClient implements Runnable{
	Socket socket;
	String name;
	String txt="";
	public MyClient(String n)
	{
		this.name=n;
		try {//将自己的名字发送给服务器，服务器建立线程
			this.socket=new Socket("127.0.0.1",5432);
			PrintStream out=new PrintStream(this.socket.getOutputStream());
			out.println(name);
		}
		catch(Exception e)
		{
			System.out.println(name+"用户出了问题");
		}
		
	}
	public void send(String str,String des)
	{
		try
		{
			PrintStream out=new PrintStream(this.socket.getOutputStream(),true);
			//out.println(des);
			out.println(des+"`"+"发送方："+name+"  "+str);//目的地dest用倒引号进行分割
			//out.println(des+"`"+":\n"+str);
			System.out.println(name+"发送给"+des+" "+str);
			System.out.println("发送信息成功");
		}
		catch(Exception e)
		{
			System.out.println(name+"客户发送信息出现问题");
		}
	}
	public void run()
	{
		System.out.println(name+"接收消息");
		try
		{
			while(true)
			{
				try
				{
					Thread.sleep(500);
				}catch(InterruptedException e)
				{
					System.out.println("***********");
					e.printStackTrace();
				}
				String line=null;
				//BufferedReader in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
				Scanner in=new Scanner(socket.getInputStream());
				in.useDelimiter("\n");
				//System.out.println("缓冲区还有"+socket.getInputStream().available());
				if(socket.getInputStream().available()!=0)//缓冲区还有东西，否则会阻塞自己
				{
					while(socket.getInputStream().available()!=0)//要判断缓冲区有没有东西
					{
						line=in.nextLine();
						txt=txt+line+"\r"+"\n";//txt=txt+line+"\n";
					}
					System.out.println(name+"接收到  "+txt);
				}
			}
		}
		catch(Exception e)
		{
			System.out.println("接受信息出错");
		}
	}
	
}
