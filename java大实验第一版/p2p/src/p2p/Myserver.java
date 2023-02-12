package p2p;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.io.*;
import java.lang.Thread;
public class Myserver {
	String txt;
	Map<String,Socket>SocketMap;
	ServerSocket server;
	ServerJFrame serverjframe;
	public Myserver()
	{
		this.SocketMap=new HashMap<String,Socket>();
		txt=new String("");
		try
		{
			this.server=new ServerSocket(5432);
		}
		catch(Exception evt)
		{
			System.out.println("创建serversocket失败！"+evt.getMessage());
		}
		this.serverjframe=new ServerJFrame();
	}
	public void link()//建立连接
	{
		try
		{
			Socket ss=this.server.accept();
			BufferedReader in=new BufferedReader(new InputStreamReader(ss.getInputStream()));
			if(ss.getInputStream().available()!=0)
			{
				String name=in.readLine();//读取名字
				this.SocketMap.put(name, ss);//加进MAP里
				System.out.println(name+"和服务器建立连接");
				this.serverjframe.area.append(name+"和服务器建立连接"+"\n");
				
			}
		}catch(Exception e)
		{
			System.out.println("连接建立失败！"+e.getMessage());
		}
	}
	public void store_forward(String soursename,String dest)//存储转发
	{
		Socket Source=this.SocketMap.get(soursename);
		try
		{
			if(Source!=null)
			{
				BufferedReader in=new BufferedReader(new InputStreamReader(Source.getInputStream()));
				String txt="";
				String line=null;
				while(Source.getInputStream().available()!=0)
				{
					line=in.readLine();
					txt+=line;
				}
				PrintStream print=new PrintStream(Source.getOutputStream(),true);//发送给自己
				print.println(txt);
				this.serverjframe.area.append(txt+" 发送给  "+dest+"\n");//加在显示面板里
				if(!dest.equals("All"))
				{
					Socket Dest=this.SocketMap.get(dest);
					if(Dest!=null)
					{
						PrintStream out=new PrintStream(Dest.getOutputStream(),true);//发送给对方
						out.println(txt);
					}
				}
				else
				{
					Iterator<String> it=this.SocketMap.keySet().iterator();//获取SocketMap中所有的key元素
					while(it.hasNext())
					{
						String key=it.next();
						if(!key.equals(soursename))//不为源地址
						{
							Socket ss=this.SocketMap.get(key);
							PrintStream out=new PrintStream(ss.getOutputStream(),true);//发送给对方
							out.println(txt);
						}
					}
				}
			}
			
		}catch(Exception e)
		{
			System.out.println("存储转发出错!");
		}
	}
}
