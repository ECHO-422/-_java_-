package ChatRoom;

import java.awt.event.*;
import java.sql.ResultSet;

import javax.swing.JOptionPane;;

public class RegisterAction implements ActionListener{
	LoginJFrame home;
	public RegisterAction(LoginJFrame h)
	{
		this.home=h;
	}
	public void actionPerformed(ActionEvent evt)
	{
		String name=this.home.txtUserId.getText();
		String passwd=new String (this.home.txtPassword.getPassword());
		if(name.equals("") || passwd.equals(""))
		{
			JOptionPane.showMessageDialog(null, "没有输入用户名或者密码!","警告",JOptionPane.WARNING_MESSAGE);
		}
		else
		{
			ResultSet set=this.home.helper.select("select * from USERS where name='"+name+"'");//按条件查询
			System.out.println("SET"+set);
			String nameo=null;
			try {
				while(set.next())
				{
					nameo=set.getNString("name");
				}
			}catch(Exception e)
			{
				System.out.println("注册出错!"+e.getMessage()+nameo);
			}
			if(nameo ==null)
			{
				String sql="INSERT INTO USERS(name,password) values"+"('"+name+"',"+"'"+passwd+"'"+");";//插入语句
				//String sql="INSERT INTO USERS(name,password) values('AA','123456');";
				System.out.println("sql："+sql);
				this.home.helper.eUpdate(sql);
				JOptionPane.showMessageDialog(null, "注册成功");
				this.home.txtUserId.setText("");
				this.home.txtPassword.setText("");
			}
			
			else
			{
				JOptionPane.showMessageDialog(null, "该用户已经注册");
			}
		}
	}
}
