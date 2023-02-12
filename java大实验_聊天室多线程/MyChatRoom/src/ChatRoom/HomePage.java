package ChatRoom;
import javax.swing.*;
import java.awt.*;
public class HomePage extends JFrame{
	JLabel Label[];
	JPanel Panel[];
	JButton Button[];
	JTextField TextField;
	Server server;
	OpenServerAction action1;
	ServerJFrame SJ;
	CloseServerAction action2;
	String client_name[];
	LoginAction action3;
	public HomePage()
	{
		super("主页");
		this.Label=new JLabel[2];
		this.Panel=new JPanel[7];
		this.Button=new JButton[3];
		this.TextField=new JTextField(15);
		for(int i=0;i<2;i++)
		{
			this.Label[i]=new JLabel();
		}
		for(int i=0;i<7;i++)
		{
			this.Panel[i]=new JPanel();
		}
		for(int i=0;i<3;i++)
		{
			this.Button[i]=new JButton();
		}
		this.Button[0].setLabel("开启服务器");
		this.Button[1].setLabel("关闭服务器");
		this.Button[2].setLabel("登陆");
		this.Label[0].setText("欢迎来到聊天室！");
		this.Label[1].setText("输入用户名");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.server=new Server();
		this.SJ=new ServerJFrame(server);
		this.action1=new OpenServerAction(this);
		this.Button[0].addActionListener(action1);//开启服务器的事件
		this.action2=new CloseServerAction(this);
		this.Button[1].addActionListener(action2);//关闭服务器的事件
		this.client_name=new String[30]; //最多可以有三十个用户
		this.client_name[0]="All";
		this.action3=new LoginAction(this);
		this.Button[2].addActionListener(action3);
	}
	public void init()
	{
		this.Panel[0].setLayout(new FlowLayout(FlowLayout.CENTER,10,30));
		this.Panel[0].add(this.Label[0]); //设置标题
		this.Panel[1].setLayout(new GridLayout(2,1,20,20));
		this.Panel[1].add(this.Button[0]);
		this.Panel[1].add(this.Button[1]);
		this.Panel[2].setLayout(new FlowLayout(FlowLayout.CENTER,20,20));
		this.Panel[2].add(this.Panel[1]);
		this.Panel[3].setLayout(new BorderLayout(20,20));
		this.Panel[3].add(this.Panel[0],"North");
		this.Panel[3].add(this.Panel[2],"Center");
		this.Panel[4].setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
		this.Panel[4].add(this.Label[1]);
		this.Panel[4].add(this.TextField);
		this.Panel[5].setLayout(new FlowLayout(FlowLayout.CENTER,40,0));
		this.Panel[5].add(this.Button[2]);
		this.Panel[6].setLayout(new GridLayout(2,1));
		this.Panel[6].add(this.Panel[4]);
		this.Panel[6].add(this.Panel[5]);
		this.setLayout(new BorderLayout(20,20));
		Container Con=this.getContentPane();
		Con.add(this.Panel[3],"North");
		Con.add(this.Panel[6],"Center");
		//Con.add(this.Panel[5],"South");
		this.setSize(600, 380);
		this.setVisible(true);
	}
}
