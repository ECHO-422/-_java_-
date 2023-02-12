package p2p;
import java.net.*;
import java.io.*;
import java.awt.event.*;
import java.util.Date;
import java.text.SimpleDateFormat;;
public class SendAction implements ActionListener{
	otherchatroom othm;
	//otherchatroom c[]=new otherchatroom[30];
	public SendAction(otherchatroom one)
	{
		this.othm=one;
		
	}
	public void actionPerformed(ActionEvent e) //发送消息
	{
		String name=new String();
		 name=this.othm.combox.getSelectedItem()+"";//获取对方的名字
		//获取发送信息
		System.out.println(this.othm.name+"发送给"+name);
		SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //获取系统时间
		String s=new String();
		 s="发送方:"+othm.name+"   "+time.format(new Date())+" "+this.othm.area2.getText(); //向服务器输入名字和系统时间
		System.out.println(s);
		this.othm.client.send(s,name);
		this.othm.server.store_forward(othm.name, name);
		//String s1=this.othm.server.getstring();
		//String s1=this.othm.client.getString();
		//this.othm.area1.setText(s1);
		this.othm.area2.setText("");
		
	}
}
