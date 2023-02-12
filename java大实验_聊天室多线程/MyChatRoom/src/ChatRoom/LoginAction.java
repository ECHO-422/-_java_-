package ChatRoom;
import java.awt.event.*;
public class LoginAction implements ActionListener {
	HomePage home;
	static int i=0;
	ClientJFrame CFrame[]=new ClientJFrame[30];//定义成成员变量
	public LoginAction(HomePage h)
	{
		this.home=h;
	}
	public void actionPerformed(ActionEvent evt)
	{
		int j=0;
		String s[]=this.home.client_name;
		int n=this.home.server.client_num();
		String na=this.home.TextField.getText(); //获得用户名
		CFrame[i++]=new ClientJFrame(s,n,na);
		j=i-1;
		for(int m=0;m<i-1;m++)
		{
			System.out.println(CFrame[m].name+"加上"+na);
			CFrame[m].addClient(na);
		}
		SendAction action=new SendAction(i,CFrame[j]);//创建事件
		CFrame[j].button.addActionListener(action);//每个页面加上事件监听器
		CFrame[j].setVisible(true);
		new Thread(new CLientJThread(CFrame[j])).start();//创建刷新线程
		this.home.client_name[i]=na;
		this.home.TextField.setText("");
	}
}
