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
	public void actionPerformed(ActionEvent e) //������Ϣ
	{
		String name=new String();
		 name=this.othm.combox.getSelectedItem()+"";//��ȡ�Է�������
		//��ȡ������Ϣ
		System.out.println(this.othm.name+"���͸�"+name);
		SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //��ȡϵͳʱ��
		String s=new String();
		 s="���ͷ�:"+othm.name+"   "+time.format(new Date())+" "+this.othm.area2.getText(); //��������������ֺ�ϵͳʱ��
		System.out.println(s);
		this.othm.client.send(s,name);
		this.othm.server.store_forward(othm.name, name);
		//String s1=this.othm.server.getstring();
		//String s1=this.othm.client.getString();
		//this.othm.area1.setText(s1);
		this.othm.area2.setText("");
		
	}
}
