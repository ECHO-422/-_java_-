package ChatRoom;
import java.awt.event.*;

import javax.swing.JOptionPane;
public class OpenServerAction implements ActionListener{ //�����������¼�
	HomePage home;
	RECTHread Server;
	public OpenServerAction(HomePage h)
	{
		this.home=h;
		this.Server=new RECTHread();
	}
	public void actionPerformed(ActionEvent evt)
	{
		this.home.server.start(); //����������
		this.Server.start();
		JOptionPane.showMessageDialog(home, "�����������ɹ���"); //���������������û������������ɹ�
		this.home.SJ.setVisible(true);//�򿪷�����������
		Thread t=new Thread(home.SJ);
		t.start();
	}
}
