package ChatRoom;
import java.net.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
public class Server extends Thread{
	ServerSocket serversocket;
	String text;
	HashMap<String,Socket>SocketMap;
	public Server()
	{
		super();
		this.text="�ȴ�����";
		this.SocketMap=new HashMap<String,Socket>();
		
	}
	public void run()
	{
		try
		{
			this.serversocket=new ServerSocket(5432);
			System.out.println("��������������!!");
			while(true)
			{
				System.out.println("���Ǵ��߳�");
				Socket ss=this.serversocket.accept(); //��������
				String name=(new MyServer(ss,this.SocketMap)).getname(); //����Ϊ�˿ͻ�������Ϣ���߳�
				//this.SocketMap.put(name, ss);
				//BufferedReader in=new BufferedReader(new InputStreamReader(ss.getInputStream()));//�������������������
				if(text.equals("�ȴ�����"))
				{
					text="";
				}
				try
				{
				//	String name=in.readLine();
					
					text=text+"\n"+name+"���������������";
					
				}
				catch(Exception ev)
				{
					System.out.println("�������̳߳���");
				}
				
			}
		}
		catch(Exception e)
		{
			System.out.println("��������������");
		}
	}
	public String Status()//���ط�������״̬
	{
		String str="";
		return text;
	}
	public int client_num()
	{
		return (MyServer.count);
	}
}
