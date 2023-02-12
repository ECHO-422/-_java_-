package p2p;
import java.awt.event.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
public class CloseServer implements ActionListener{
	Terminal terminal;
	public CloseServer(Terminal ter)
	{
		this.terminal=ter;
	}
	public void actionPerformed(ActionEvent evt)
	{
		//this.terminal.server.interrupt();
		JOptionPane.showMessageDialog(terminal, "关闭服务器成功！");
		//this.terminal.dispose();
		System.exit(0);
	}
}
