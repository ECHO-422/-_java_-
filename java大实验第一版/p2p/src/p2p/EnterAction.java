package p2p;
import java.awt.event.*;
public class EnterAction implements ActionListener{
	ChatRoom chat;
	Terminal terminal;
	public EnterAction(Terminal t)
	{
		this.terminal=t;
		this.chat=new ChatRoom(terminal);//�������ֺͷ�����
	}
	public void actionPerformed(ActionEvent evt)
	{
		this.chat.setVisible(true);
	}
}
