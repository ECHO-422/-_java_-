package ChatRoom;
import java.net.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.io.*;
public class MyServer extends Thread{
	static int count=0;
	String name; //��Ӧ����������
	Socket socket;
	HashMap<String,Socket>SocketMap;
	public MyServer(Socket s,HashMap<String,Socket>So)
	{
		super();
		this.socket=s;
		count++;
		//������ʱ�������
		try
		{
			//BufferedReader in=new BufferedReader(new InputStreamReader(this.socket.getInputStream()));//�������������������
			Scanner in=new Scanner(socket.getInputStream());
			in.useDelimiter("\n");
			name=in.nextLine();
			//in.close();
			
		}
		catch(Exception e)
		{
			System.out.println("��������������");
		}
		this.SocketMap=So;
		So.put(name, s);//���Լ��ӽ�ȥ
		this.start();
	}
	public void run()
	{
		try
		{
			System.out.println(name+"����������");
			Scanner in=new Scanner(socket.getInputStream());
			in.useDelimiter("\n");
			//BufferedReader in=new BufferedReader(new InputStreamReader(socket.getInputStream()));//�����
			while(true)
			{
				sleep(500);
				//BufferedReader in=new BufferedReader(new InputStreamReader(socket.getInputStream()));//�����
				if(socket.getInputStream().available()!=0)//���л����ַ�,��ȡ�����ַ����洢
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
					//��ȡĿ�Ķ��û�����
					System.out.println("othername:"+othername);
					int length=txt.length();
					txt=txt.substring(index+1,length);//������������ȡ����
					System.out.println(txt);
					/*���Ƿ��͸�All*///ת��
					if(!othername.equals("All"))
					{
						Socket i=this.findIndex(othername);//�ҵ�Ŀ�Ķ˵�socket
						if(i!=null)
						{
							PrintStream out=new PrintStream(i.getOutputStream(),true); //������
							out.println(txt);//����Ϣ���͸��Է�
						}
					}
					else//�Ƿ��͸�All
					{
						Iterator<String>set=this.SocketMap.keySet().iterator();//������ֵ����
						while(set.hasNext())
						{
							String key=set.next();
							if(!key.equals(name))//��Ϊ�Լ�ʱ����Ҫ������
							{
								Socket ss=this.SocketMap.get(key);
								PrintStream out=new PrintStream(ss.getOutputStream(),true);
								out.println(txt);
							}
						}
					}
					System.out.println(name+"�����������͸�"+othername+txt);
					PrintStream outx=new PrintStream(this.socket.getOutputStream(),true);
					outx.println(txt);//���͸��Լ�
					System.out.println(name+"�����������͸��Լ�"+txt);
					
				}
				//System.out.println("�˷��������гɹ�!");
			}
			
		}
		catch(Exception e)
		{
			System.out.println(name+"�̳߳���");
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
