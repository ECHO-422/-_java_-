package ChatRoom;
import java.awt.event.*;
public class LoginAction implements ActionListener {
	HomePage home;
	static int i=0;
	ClientJFrame CFrame[]=new ClientJFrame[30];//����ɳ�Ա����
	public LoginAction(HomePage h)
	{
		this.home=h;
	}
	public void actionPerformed(ActionEvent evt)
	{
		int j=0;
		String s[]=this.home.client_name;
		int n=this.home.server.client_num();
		String na=this.home.TextField.getText(); //����û���
		CFrame[i++]=new ClientJFrame(s,n,na);
		j=i-1;
		for(int m=0;m<i-1;m++)
		{
			System.out.println(CFrame[m].name+"����"+na);
			CFrame[m].addClient(na);
		}
		SendAction action=new SendAction(i,CFrame[j]);//�����¼�
		CFrame[j].button.addActionListener(action);//ÿ��ҳ������¼�������
		CFrame[j].setVisible(true);
		new Thread(new CLientJThread(CFrame[j])).start();//����ˢ���߳�
		this.home.client_name[i]=na;
		this.home.TextField.setText("");
	}
}
