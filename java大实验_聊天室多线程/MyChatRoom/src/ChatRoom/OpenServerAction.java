package ChatRoom;
import java.awt.event.*;

import javax.swing.JOptionPane;
public class OpenServerAction implements ActionListener{ //�����������¼�
	HomePage home;
	public OpenServerAction(HomePage h)
	{
		this.home=h;
	}
	public void actionPerformed(ActionEvent evt)
	{
		this.home.server.start(); //����������
		JOptionPane.showMessageDialog(home, "�����������ɹ���"); //���������������û������������ɹ�
		this.home.SJ.setVisible(true);//�򿪷�����������
		Thread t=new Thread(home.SJ);
		t.start();
	}
}
