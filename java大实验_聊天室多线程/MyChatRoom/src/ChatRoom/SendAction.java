package ChatRoom;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;
public class SendAction implements ActionListener{
	//ClientJFrame CFrame[]=new ClientJFrame[30];
	int num;
	ClientJFrame MyCFrame;
	public SendAction(int n,ClientJFrame CF)
	{
		this.num=n;
		this.MyCFrame=CF;
		/*for(int i=0;i<num;i++)
		{
			this.CFrame[i]=C[i];
		}*/
	}
	public void actionPerformed(ActionEvent evt)
	{
		String dest=this.MyCFrame.combox.getSelectedItem()+"";//目的主机的用户名,获得字符串
		SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String text=time.format(new Date())+"   "+this.MyCFrame.area1.getText();
		this.MyCFrame.client.send(text, dest);//发送消息
		this.MyCFrame.area1.setText("");
	}

}
