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
	public void actionPerformed(ActionEvent evt)//��¼��ť�¼����õ�����������
	{
		count++;
		chr.s=chr.field.getText();
		String s[]=new String[30];
		for(int i=0;i<count;i++)
		{
			s[i]=chr.terminal.client_name[i];//��ȡ�û�����,���ն˴���
		}
		ch1[count-1]=new otherchatroom(chr.s,s,count,chr.terminal.server);//��������С����
		new Thread(new ClientJThread(ch1[count-1])).start();//����С����ˢ���߳�
		System.out.println("��ǰ��"+count);
		JOptionPane.showMessageDialog(chr, "��½�ɹ���");
		for(int i=0;i<count-1;i++)//���ֻ��Ǽ�������
		{
			ch1[i].addClient(chr.s);
		}
		chr.terminal.client_name[count]=chr.s;//��������
		chr.close();//�رմ���
		SendAction action=new SendAction(ch1[count-1]);
		ch1[count-1].server.link();
		ch1[count-1].b1.addActionListener(action);//��ӷ����¼�
		ch1[count-1].present();//չʾ����
		(new Thread(ch1[count-1])).start();
		this.chr.field.setText("");
	}
}
