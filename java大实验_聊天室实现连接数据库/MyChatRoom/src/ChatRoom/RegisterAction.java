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
			JOptionPane.showMessageDialog(null, "û�������û�����������!","����",JOptionPane.WARNING_MESSAGE);
		}
		else
		{
			ResultSet set=this.home.helper.select("select * from USERS where name='"+name+"'");//��������ѯ
			System.out.println("SET"+set);
			String nameo=null;
			try {
				while(set.next())
				{
					nameo=set.getNString("name");
				}
			}catch(Exception e)
			{
				System.out.println("ע�����!"+e.getMessage()+nameo);
			}
			if(nameo ==null)
			{
				String sql="INSERT INTO USERS(name,password) values"+"('"+name+"',"+"'"+passwd+"'"+");";//�������
				//String sql="INSERT INTO USERS(name,password) values('AA','123456');";
				System.out.println("sql��"+sql);
				this.home.helper.eUpdate(sql);
				JOptionPane.showMessageDialog(null, "ע��ɹ�");
				this.home.txtUserId.setText("");
				this.home.txtPassword.setText("");
			}
			
			else
			{
				JOptionPane.showMessageDialog(null, "���û��Ѿ�ע��");
			}
		}
	}
}
