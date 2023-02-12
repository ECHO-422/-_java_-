package p2p;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.Dimension;
public class otherchatroom extends JFrame implements Runnable{
	String name;
	JPanel p[];
	JTextArea area1,area2;
	JButton b1;
	JLabel label;
	Myclient client;
	Myserver server;
	//SendAction action;
	JScrollPane pane,pane1;
	JComboBox combox;
	Font x=new Font("Serif", Font.BOLD, 20);
	public otherchatroom(String s,String na[],int num,Myserver ser)
	{
		super("聊天室");
		this.name=s;
		this.server=ser;
		this.p=new JPanel[8];
		for(int i=0;i<8;i++)
		{
			this.p[i]=new JPanel();
		}
		this.area1=new JTextArea(10,30);
		this.area2=new JTextArea(5,30);
		this.b1=new JButton("发送");
		this.label=new JLabel();
		//this.b2=new JButton(name);
		//this.b3=new JButton("ALL");
		this.client=new Myclient(name);
	//	this.action=new SendAction(this);
		this.pane=new JScrollPane(area1);
		this.pane1=new JScrollPane(area2);
		this.combox=new JComboBox();
		for(int i=0;i<num;i++)
		{
			this.combox.addItem(na[i]);//将名字加进去
		}
		(new Thread(client)).start();
		init();
	}
	public void init()
	{
		//this.b1.addActionListener(action);
		this.label.setText("名字： "+name);
		this.p[0].setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
		this.p[0].add(pane);//this.p[0].add(area1);
		//this.p[0].setSize(30,40);
		this.p[5].setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
		this.p[5].add(pane1);//this.p[5].add(area2);
		//this.p[5].setSize(30,40);
		this.p[1].setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
		this.p[1].add(b1);
		this.p[1].setSize(10,10);
		//this.p[2].setLayout(new GridLayout(2,1,10,10));
		this.p[2].setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
		this.p[2].add(combox);
	//	b2.setPreferredSize(new Dimension(100,40));
	//	this.p[2].add(b2);
	//	b3.setPreferredSize(new Dimension(100,40));//设置按钮的大小
	//	this.p[2].add(b3);
		//this.p[7].setSize(25, 20);
		this.p[6].setLayout(new BorderLayout());
		this.p[6].add(p[2],"North");
		this.p[6].add(p[7],"Center");
		this.p[3].setLayout(new BorderLayout());
		this.p[3].add(p[0],"North");
		this.p[3].add(p[5],"Center");
		this.p[3].add(p[1],"South");
		this.p[4].setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
		this.p[4].add(label);
		Container Con=this.getContentPane();
		Con.add(p[4],"North");
		Con.add(p[3],"Center");
		p[2].setSize(10, 20);
		Con.add(p[6],"East");
		this.area1.setFont(x);
		this.area2.setFont(x);
		this.pack();
	}
	public void present()
	{
		this.setVisible(true);
	}
	public void addClient(String S)
	{
			this.combox.addItem(S);
	}
	/*public void show()
	{
		for(int i=0;i<this.combox.getItemCount();i++)
		{
			System.out.println(name+"好友列表有"+this.combox.getItemCount()+this.combox.getSelectedItem());
		}
	}*/
	public void run()
	{
		String s=this.client.txt;
		this.area1.setText(s);
	}
}
