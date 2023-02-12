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
			System.out.println("����serversocketʧ�ܣ�"+evt.getMessage());
		}
		this.serverjframe=new ServerJFrame();
	}
	public void link()//��������
	{
		try
		{
			Socket ss=this.server.accept();
			BufferedReader in=new BufferedReader(new InputStreamReader(ss.getInputStream()));
			if(ss.getInputStream().available()!=0)
			{
				String name=in.readLine();//��ȡ����
				this.SocketMap.put(name, ss);//�ӽ�MAP��
				System.out.println(name+"�ͷ�������������");
				this.serverjframe.area.append(name+"�ͷ�������������"+"\n");
				
			}
		}catch(Exception e)
		{
			System.out.println("���ӽ���ʧ�ܣ�"+e.getMessage());
		}
	}
	public void store_forward(String soursename,String dest)//�洢ת��
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
				PrintStream print=new PrintStream(Source.getOutputStream(),true);//���͸��Լ�
				print.println(txt);
				this.serverjframe.area.append(txt+" ���͸�  "+dest+"\n");//������ʾ�����
				if(!dest.equals("All"))
				{
					Socket Dest=this.SocketMap.get(dest);
					if(Dest!=null)
					{
						PrintStream out=new PrintStream(Dest.getOutputStream(),true);//���͸��Է�
						out.println(txt);
					}
				}
				else
				{
					Iterator<String> it=this.SocketMap.keySet().iterator();//��ȡSocketMap�����е�keyԪ��
					while(it.hasNext())
					{
						String key=it.next();
						if(!key.equals(soursename))//��ΪԴ��ַ
						{
							Socket ss=this.SocketMap.get(key);
							PrintStream out=new PrintStream(ss.getOutputStream(),true);//���͸��Է�
							out.println(txt);
						}
					}
				}
			}
			
		}catch(Exception e)
		{
			System.out.println("�洢ת������!");
		}
	}
}
