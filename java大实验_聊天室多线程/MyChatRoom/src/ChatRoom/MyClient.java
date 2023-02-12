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
		try {//���Լ������ַ��͸��������������������߳�
			this.socket=new Socket("127.0.0.1",5432);
			PrintStream out=new PrintStream(this.socket.getOutputStream());
			out.println(name);
		}
		catch(Exception e)
		{
			System.out.println(name+"�û���������");
		}
		
	}
	public void send(String str,String des)
	{
		try
		{
			PrintStream out=new PrintStream(this.socket.getOutputStream(),true);
			//out.println(des);
			out.println(des+"`"+"���ͷ���"+name+"  "+str);//Ŀ�ĵ�dest�õ����Ž��зָ�
			//out.println(des+"`"+":\n"+str);
			System.out.println(name+"���͸�"+des+" "+str);
			System.out.println("������Ϣ�ɹ�");
		}
		catch(Exception e)
		{
			System.out.println(name+"�ͻ�������Ϣ��������");
		}
	}
	public void run()
	{
		System.out.println(name+"������Ϣ");
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
				//System.out.println("����������"+socket.getInputStream().available());
				if(socket.getInputStream().available()!=0)//���������ж���������������Լ�
				{
					while(socket.getInputStream().available()!=0)//Ҫ�жϻ�������û�ж���
					{
						line=in.nextLine();
						txt=txt+line+"\r"+"\n";//txt=txt+line+"\n";
					}
					System.out.println(name+"���յ�  "+txt);
				}
			}
		}
		catch(Exception e)
		{
			System.out.println("������Ϣ����");
		}
	}
	
}
