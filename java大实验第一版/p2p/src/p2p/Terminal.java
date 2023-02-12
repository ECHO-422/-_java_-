package p2p;
import javax.swing.*;
import java.awt.*;
public class Terminal extends JFrame{
	JPanel p1,p2;
	JButton b1,b2,b3;
	JTextArea area;
	Myserver server;
	//ChatRoom chat;
	OpenServer action;
	CloseServer closeaction;
	EnterAction action1;
	String client_name[]=new String[30];
	
	public Terminal()
	{
		super("聊天室服务器终端");
		this.p1=new JPanel();
		//this.p2=new JPanel();
		this.b1=new JButton("开启服务器");
		this.b2=new JButton("关闭服务器");
		this.area=new JTextArea(20,30);
		this.server=new Myserver();
		//chat=new ChatRoom("",server);
		this.action=new OpenServer(this);
		b1.addActionListener(action);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.closeaction=new CloseServer(this);
		b2.addActionListener(closeaction);
		this.b3=new JButton("进入聊天室");
		this.p2=new JPanel();
		client_name[0]="All";
		this.action1=new EnterAction(this);
		b3.addActionListener(action1);
		//this.SeverJ=new ServerJFrame(this.server);
	}
	public void init()
	{
		this.p1.setLayout(new FlowLayout(FlowLayout.CENTER,20,10));
		this.p1.add(b1);
		this.p1.add(b2);
		p2.setLayout(new FlowLayout(FlowLayout.CENTER,20,20));
		p2.add(b3);
		Container Con=this.getContentPane();
		Con.add(p1,"North");
		//Con.add(this.area,"Center");
		Con.add(this.p2,"Center");
		Con.add(area,"South");
		this.pack();
		this.setVisible(true);
	}
	
}
