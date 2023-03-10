package ChatRoom;
import java.awt.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.*;
public class ServerJFrame extends JFrame implements Runnable{//服务器监视器界面刷新线程
	JScrollPane scroll;
	static JTextArea area;
	Server server;
	//ServerSocket serversocket2;
	public ServerJFrame(Server s)
	{
		super("服务器监视器");
		this.server=s;
		this.area=new JTextArea(20,30);
		area.setFont(new Font("宋体",1,16));
		this.scroll=new JScrollPane(this.area);
		this.init();
	}
	public void init()
	{
		area.setText("等待连接");
		Container Con=this.getContentPane();
		Con.add(scroll,"Center");
		//
		/* new Thread(new Runnable() {
             private ServerSocket serversocket2;

			public void run() {
                 try {
                this.serversocket2=new ServerSocket(5433);
                int processors=Runtime.getRuntime().availableProcessors();//CPU数
                ExecutorService fixedPool=Executors.newFixedThreadPool(processors*2);//创建固定大小线程池  
                 while (true) { //处理所有客户机连接
                     Socket fileSocket=serversocket2.accept();//如果无连接，则阻塞，否则接受连接并创建新的会话套接字
                     //文件接收线程为SwingWorker类型的后台工作线程
                     SwingWorker<Integer,Object> recver=new RevFileThread(fileSocket); //创建客户线程
                     fixedPool.execute(recver); //用线程池调度客户线程运行
                     //recver.execute();
                 }//end while 
                 } catch (IOException ex) {
                     JOptionPane.showMessageDialog(null, ex.getMessage(), "错误提示", JOptionPane.ERROR_MESSAGE);
                 }//end try catch            
             }//end run()
         }).start();  */
		//
		// System.out.println("服务器接收文件线程启动");
		this.pack();
	}
	public void run()
	{
		try
		{
			String s="";
			while(true)
			{
				s=this.server.Status();
				area.setText(s);
			}
		}
		catch(Exception e)
		{
			throw new RuntimeException(e);
		}
	}
	
}
