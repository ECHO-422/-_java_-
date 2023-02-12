package p2p;
import javax.swing.*;
import java.awt.*;
public class ServerJFrame extends JFrame{
	//Myserver server;
	JPanel p1;
	JTextArea area;
	JLabel label;
	JScrollPane scroll;
	public ServerJFrame()
	{
		super("服务器监视器");
		//this.server=ser;
		this.p1=new JPanel();
		this.area=new JTextArea(20,30);
		this.scroll=new JScrollPane(area);
		this.label=new JLabel("服务器状态");
		init();
	}
	public void init()
	{
		this.p1.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
		this.p1.add(label);
		Container Con=this.getContentPane();
		Con.add(p1,"North");
		Con.add(this.scroll,"Center");
		this.pack();
	}
	public void present()
	{
		this.setVisible(true);
	}
}
