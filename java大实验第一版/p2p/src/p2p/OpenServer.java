package p2p;
import java.awt.event.*;

import javax.swing.JOptionPane;
public class OpenServer implements ActionListener{
	Terminal terminal;
	public OpenServer(Terminal terminal)
	{
		this.terminal=terminal;
	}
	public void actionPerformed(ActionEvent evt)
	{
		this.terminal.server.serverjframe.present();//展示服务器状态
		JOptionPane.showMessageDialog(null, "开启服务器成功！");
		
	}
}
