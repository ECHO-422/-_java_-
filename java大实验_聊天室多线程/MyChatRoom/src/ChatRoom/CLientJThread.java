package ChatRoom;

public class CLientJThread implements Runnable{//�����߳�ˢ��
	ClientJFrame ce;
	public CLientJThread(ClientJFrame c)
	{
		this.ce=c;
	}
	public void run()
	{
		try
		{
			Thread.sleep(1000);
		}
		catch(Exception e)
		{
			System.out.println("error");
		}
		while(true)
		{
			String text=this.ce.gettxt();
			this.ce.area2.setText(text);
		}
	}
}
