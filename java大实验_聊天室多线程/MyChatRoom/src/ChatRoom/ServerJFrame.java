package ChatRoom;
import java.awt.*;
import javax.swing.*;
public class ServerJFrame extends JFrame implements Runnable{//����������������ˢ���߳�
	JScrollPane scroll;
	JTextArea area;
	Server server;
	public ServerJFrame(Server s)
	{
		super("������������");
		this.server=s;
		this.area=new JTextArea(20,30);
		this.scroll=new JScrollPane(this.area);
		this.init();
	}
	public void init()
	{
		area.setText("�ȴ�����");
		Container Con=this.getContentPane();
		Con.add(scroll,"Center");
		this.pack();
	}
	public void run()
	{
		try
		{
			String s="";
			while(true)
			{
				s=this.server.Status();
				area.setText(s);
			}
		}
		catch(Exception e)
		{
			throw new RuntimeException(e);
		}
	}
	
}
