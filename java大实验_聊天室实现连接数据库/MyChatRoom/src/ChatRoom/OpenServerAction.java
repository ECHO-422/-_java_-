package ChatRoom;
import java.awt.event.*;

import javax.swing.JOptionPane;
public class OpenServerAction implements ActionListener{ //开启服务器事件
	HomePage home;
	RECTHread Server;
	public OpenServerAction(HomePage h)
	{
		this.home=h;
		this.Server=new RECTHread();
	}
	public void actionPerformed(ActionEvent evt)
	{
		this.home.server.start(); //开启服务器
		this.Server.start();
		JOptionPane.showMessageDialog(home, "开启服务器成功！"); //弹出弹窗，告诉用户开启服务器成功
		this.home.SJ.setVisible(true);//打开服务器监视器
		Thread t=new Thread(home.SJ);
		t.start();
	}
}
