package ChatRoom;
import java.net.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.io.*;
public class MyServer extends Thread{
	static int count=0;
	String name; //对应主机的名字
	Socket socket;
	HashMap<String,Socket>SocketMap;
	public MyServer(Socket s,HashMap<String,Socket>So)
	{
		super();
		this.socket=s;
		count++;
		//创建的时候就启动
		try
		{
			//BufferedReader in=new BufferedReader(new InputStreamReader(this.socket.getInputStream()));//获得输入流，输入名字
			Scanner in=new Scanner(socket.getInputStream());
			in.useDelimiter("\n");
			name=in.nextLine();
			//in.close();
			
		}
		catch(Exception e)
		{
			System.out.println("创建输入流出错");
		}
		this.SocketMap=So;
		So.put(name, s);//将自己加进去
		this.start();
	}
	public void run()
	{
		try
		{
			System.out.println(name+"服务器启动");
			Scanner in=new Scanner(socket.getInputStream());
			in.useDelimiter("\n");
			//BufferedReader in=new BufferedReader(new InputStreamReader(socket.getInputStream()));//输出流
			while(true)
			{
				sleep(500);
				//BufferedReader in=new BufferedReader(new InputStreamReader(socket.getInputStream()));//输出流
				if(socket.getInputStream().available()!=0)//流中还有字符,读取流中字符，存储
				{
					String line=null;
					String txt="";
					while(socket.getInputStream().available()!=0)
					{
						line=in.nextLine();
						txt+=line+"\r"+"\n";//txt+=line+"\n";
					}
					int index=txt.indexOf("`");
					String othername=txt.substring(0,index);
					//获取目的端用户名字
					System.out.println("othername:"+othername);
					int length=txt.length();
					txt=txt.substring(index+1,length);//将聊天内容提取出来
					System.out.println(txt);
					/*不是发送给All*///转发
					if(!othername.equals("All"))
					{
						Socket i=this.findIndex(othername);//找到目的端的socket
						if(i!=null)
						{
							PrintStream out=new PrintStream(i.getOutputStream(),true); //输入流
							out.println(txt);//将消息发送给对方
						}
					}
					else//是发送给All
					{
						Iterator<String>set=this.SocketMap.keySet().iterator();//建立键值集合
						while(set.hasNext())
						{
							String key=set.next();
							if(!key.equals(name))//不为自己时，需要建立流
							{
								Socket ss=this.SocketMap.get(key);
								PrintStream out=new PrintStream(ss.getOutputStream(),true);
								out.println(txt);
							}
						}
					}
					System.out.println(name+"服务器将发送给"+othername+txt);
					PrintStream outx=new PrintStream(this.socket.getOutputStream(),true);
					outx.println(txt);//发送给自己
					System.out.println(name+"服务器将发送给自己"+txt);
					
				}
				//System.out.println("此服务器运行成功!");
			}
			
		}
		catch(Exception e)
		{
			System.out.println(name+"线程出错！");
		}
	}
	public void setname(String s)
	{
		this.name=s;
	}
	public String getname()
	{
		return name;
	}
	public Socket findIndex(String name)
	{
		Socket k=null;
		k=this.SocketMap.get(name);
		return k;
	}
}
