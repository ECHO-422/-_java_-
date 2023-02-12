package ChatRoom;
import java.awt.*;
import javax.swing.*;
public class ClientJFrame extends JFrame{
	JTextArea area2,area1;
	JLabel label;
	JPanel Panel[];
	String client_name[];
	String Content="";//����������
	int num;//��ǰ�ж��ٸ��û�
	String name;
	JScrollPane scro1,scro2;
	JButton button;
	MyClient client;
	@SuppressWarnings("rawtypes")
	JComboBox combox;
	@SuppressWarnings({ "rawtypes", "unchecked" })
	Font x=new Font("Serif",Font.BOLD,15);
	public ClientJFrame(String s[],int n,String na)
	{
		super(na);
		this.num=n;
		this.name=na;
		this.client_name=new String[30];//�����30
		this.label=new JLabel("ѡ�������û�");
		this.Panel=new JPanel[5];
		for(int i=0;i<5;i++)
		{
			this.Panel[i]=new JPanel();
		}
		this.area2=new JTextArea(20,50);
		this.scro1=new JScrollPane(area2); //���������
		this.button=new JButton("����");
		this.area1=new JTextArea(2,30);
		this.scro2=new JScrollPane(area1);
		this.client=new MyClient(name);//Ϊ�ͻ�������
		this.combox=new JComboBox();
		for(int i=0;i<=n;i++)
		{
			this.client_name[i]=s[i];
			System.out.println("ClientJFrame"+client_name[i]);
			this.combox.addItem(client_name[i]);
		}
		(new Thread(this.client)).start();//�����ͻ����߳�
		init();
	}
	public void init()
	{
		
		//this.combox.setEditable(true);//ʹ�����б���Ա��༭
		this.Panel[0].setLayout(new FlowLayout(FlowLayout.LEFT));
		this.Panel[0].add(label); //��ǩ
		this.Panel[1].setLayout(new FlowLayout(FlowLayout.LEFT));
		this.Panel[1].add(scro2);//���Ϳ�
		this.Panel[1].add(button);
		this.Panel[4].setLayout(new FlowLayout(FlowLayout.LEFT));//this.Panel[4].setLayout(new FlowLayout(FlowLayout.LEFT,10,0));
		this.combox.setPreferredSize(new Dimension(100,40));
		this.Panel[4].add(this.combox);
		this.Panel[2].setLayout(new GridLayout(1,2,0,20));
		this.Panel[2].add(this.Panel[4]);
		this.Panel[2].add(Panel[1]);
		this.Panel[3].setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
		this.Panel[3].add(scro1);
		Container Con=this.getContentPane();
		Con.add(Panel[3],"North");
		Con.add(Panel[0],"Center");
		Con.add(Panel[2],"South");
		
		this.area1.setFont(x);
		this.area2.setFont(x);
		this.label.setFont(x);
		this.button.setFont(x);
		this.pack();
	}
	public void addClient(String S)
	{
			this.combox.addItem(S);
	}
	public String gettxt()
	{
		return this.client.txt;
	}
}
