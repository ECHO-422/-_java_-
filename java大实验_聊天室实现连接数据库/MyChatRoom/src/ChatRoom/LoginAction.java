package ChatRoom;
import java.awt.event.*;
import java.sql.ResultSet;

import javax.swing.JOptionPane;
public class LoginAction implements ActionListener {
	LoginJFrame home;
	static int i=0;
	ClientJFrame CFrame[]=new ClientJFrame[30];//定义成成员变量
	public LoginAction(LoginJFrame h)
	{
		this.home=h;
	}
	public void actionPerformed(ActionEvent evt)
	{
		String na=this.home.txtUserId.getText(); //获得用户名
		String password=new String (this.home.txtPassword.getPassword());//获得密码
		if(na.equals("") || password.equals(""))
		{
			JOptionPane.showMessageDialog(null, "没有输入用户名或者密码!","警告",JOptionPane.WARNING_MESSAGE);
		}
		else
		{
			ResultSet rs=this.home.helper.select("select *from USERS where name='"+na+"'");//获取数据库中的用户
			System.out.println("rs:"+rs);
			if(rs ==null)
			{
				JOptionPane.showMessageDialog(null, "该用户没有注册，请注册");
				this.home.txtUserId.setText("");
				this.home.txtPassword.setText("");
			}
			else
			{
				boolean exit=false;//用户是否存在变量
				try {	
					while(rs.next())//从数据库中遍历名字
					{
						String myname=rs.getNString("name");//获取姓名
						if(na.equals(myname))//用户名正确
						{
							String passwd=rs.getNString("password");
							if(passwd.equals(password))//密码正确,创建页面，开启用户线程
							{
								int j=0;
								String s[]=HomePage.client_name;
								int n=this.home.server.client_num();
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
								HomePage.client_name[i]=na;//在主页名字数组中加入该名字
								this.home.txtUserId.setText("");
								this.home.txtPassword.setText("");
								exit=true;//用户存在
							}
							else
							{
								JOptionPane.showMessageDialog(null, "密码错误!");
								exit=true;
							}
						}
					}
				}catch(Exception e)
				{
					System.out.println("遍历集合出错！"+e.getMessage());
					
				}
				if(!exit)//没有找到
				{
					JOptionPane.showMessageDialog(null, "该用户没有注册！");
					this.home.txtUserId.setText("");
					this.home.txtPassword.setText("");
				}
			}
		}
		
	}
}