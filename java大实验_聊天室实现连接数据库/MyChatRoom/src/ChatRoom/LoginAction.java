package ChatRoom;
import java.awt.event.*;
import java.sql.ResultSet;

import javax.swing.JOptionPane;
public class LoginAction implements ActionListener {
	LoginJFrame home;
	static int i=0;
	ClientJFrame CFrame[]=new ClientJFrame[30];//����ɳ�Ա����
	public LoginAction(LoginJFrame h)
	{
		this.home=h;
	}
	public void actionPerformed(ActionEvent evt)
	{
		String na=this.home.txtUserId.getText(); //����û���
		String password=new String (this.home.txtPassword.getPassword());//�������
		if(na.equals("") || password.equals(""))
		{
			JOptionPane.showMessageDialog(null, "û�������û�����������!","����",JOptionPane.WARNING_MESSAGE);
		}
		else
		{
			ResultSet rs=this.home.helper.select("select *from USERS where name='"+na+"'");//��ȡ���ݿ��е��û�
			System.out.println("rs:"+rs);
			if(rs ==null)
			{
				JOptionPane.showMessageDialog(null, "���û�û��ע�ᣬ��ע��");
				this.home.txtUserId.setText("");
				this.home.txtPassword.setText("");
			}
			else
			{
				boolean exit=false;//�û��Ƿ���ڱ���
				try {	
					while(rs.next())//�����ݿ��б�������
					{
						String myname=rs.getNString("name");//��ȡ����
						if(na.equals(myname))//�û�����ȷ
						{
							String passwd=rs.getNString("password");
							if(passwd.equals(password))//������ȷ,����ҳ�棬�����û��߳�
							{
								int j=0;
								String s[]=HomePage.client_name;
								int n=this.home.server.client_num();
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
								HomePage.client_name[i]=na;//����ҳ���������м��������
								this.home.txtUserId.setText("");
								this.home.txtPassword.setText("");
								exit=true;//�û�����
							}
							else
							{
								JOptionPane.showMessageDialog(null, "�������!");
								exit=true;
							}
						}
					}
				}catch(Exception e)
				{
					System.out.println("�������ϳ���"+e.getMessage());
					
				}
				if(!exit)//û���ҵ�
				{
					JOptionPane.showMessageDialog(null, "���û�û��ע�ᣡ");
					this.home.txtUserId.setText("");
					this.home.txtPassword.setText("");
				}
			}
		}
		
	}
}