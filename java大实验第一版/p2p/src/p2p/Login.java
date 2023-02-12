package p2p;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Login implements ActionListener{
	ChatRoom chr;
	otherchatroom ch1[]=new otherchatroom[30];
	static int count=0;
	public Login(ChatRoom chr)
	{
		this.chr=chr;
	}
	public void actionPerformed(ActionEvent evt)//登录按钮事件，得到输入框的内容
	{
		count++;
		chr.s=chr.field.getText();
		String s[]=new String[30];
		for(int i=0;i<count;i++)
		{
			s[i]=chr.terminal.client_name[i];//获取用户姓名,从终端传参
		}
		ch1[count-1]=new otherchatroom(chr.s,s,count,chr.terminal.server);//建立聊天小房间
		new Thread(new ClientJThread(ch1[count-1])).start();//开启小房间刷新线程
		System.out.println("当前有"+count);
		JOptionPane.showMessageDialog(chr, "登陆成功！");
		for(int i=0;i<count-1;i++)//在轮滑那加上名字
		{
			ch1[i].addClient(chr.s);
		}
		chr.terminal.client_name[count]=chr.s;//加上名字
		chr.close();//关闭窗口
		SendAction action=new SendAction(ch1[count-1]);
		ch1[count-1].server.link();
		ch1[count-1].b1.addActionListener(action);//添加发送事件
		ch1[count-1].present();//展示出来
		(new Thread(ch1[count-1])).start();
		this.chr.field.setText("");
	}
}
