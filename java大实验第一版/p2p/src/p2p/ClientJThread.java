package p2p;

public class ClientJThread implements Runnable{//刷新页面线程
	otherchatroom mychat;
	public ClientJThread(otherchatroom my)
	{
		this.mychat=my;
	}
	public void run()
	{
		try {
			while(true)
			{
				Thread.sleep(1000);
				String txt=this.mychat.client.returntxt();
				this.mychat.area1.setText(txt);
			}
		}catch(Exception t)
		{
			System.out.println("ERROR!"+t.getMessage());
		}
		
	}
}
