package p2p;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class ChatRoom extends JFrame{
	JPanel p,p1,p2,p3;
	JLabel label,label1;
	JTextField field;
	JButton b1,b2;
	String s;
	Login login;
	Terminal terminal;
	//Myserver server;
	public ChatRoom(Terminal ter)
	{
		super("ÁÄÌìÊÒ");
		s="";
	//	this.server=ser;
		this.p=new JPanel();
		this.label=new JLabel();
		this.label1=new JLabel();
		this.field=new JTextField(15);
		this.b1=new JButton("µÇÂ¼");
		this.b2=new JButton("ÍË³ö");
		this.p1=new JPanel();
		this.p2=new JPanel();
		this.p3=new JPanel();
		//this.p4=new JPanel();
		login=new Login(this);
		this.terminal=ter;
		init();
	}
	public void init()
	{
		this.p.setLayout(new BorderLayout());
		this.p1.setLayout(new FlowLayout(FlowLayout.CENTER,30,45));
		this.p2.setLayout(new FlowLayout(FlowLayout.CENTER,30,45));
		this.p3.setLayout(new FlowLayout(FlowLayout.CENTER,30,45));
		this.label.setText("»¶Ó­À´µ½ÁÄÌìÊÒ");
		this.label1.setText("µÇÂ¼Ãû");
		this.p1.add(label);
		p1.setSize(10,30);
		this.p2.add(label1);
		this.p2.add(field);
		p2.setSize(40,100);
		this.p3.add(b1);
		this.p3.add(b2);
		this.p.add(p1,"North");
		this.p.add(p2,"Center");
		this.p.add(p3,"South");
		this.b1.addActionListener(login);
		Container Con=this.getContentPane();
		Con.add(p,"Center");
		this.pack();
		this.setVisible(false);
		s=this.field.getText();
		System.out.println("S:"+s);
	}
	public void close()
	{
		this.setVisible(false);
	}
	public void present()
	{
		this.setVisible(true);
	}
	
}
