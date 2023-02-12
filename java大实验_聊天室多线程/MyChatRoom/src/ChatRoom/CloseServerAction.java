package ChatRoom;
import java.awt.event.*;

import javax.swing.JOptionPane;
public class CloseServerAction implements ActionListener{
	HomePage homepage;
	public CloseServerAction(HomePage h)
	{
		this.homepage=h;
	}
	public void actionPerformed(ActionEvent evt)
	{
		this.homepage.server.stop();
		JOptionPane.showMessageDialog(null, "关闭服务器成功！");
		System.exit(0);
	}
}
