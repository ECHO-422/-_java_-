package ChatRoom;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

import javax.swing.SwingWorker;

public class RevFileThread extends Thread {
	private final Socket fileSocket; //接收文件的套接字
    private static final int BUFSIZE=8096;//缓冲区大小    
    HashMap<String,Socket>SocketMap;
    String name; //对应主机的名字
    public RevFileThread(Socket fileSocket,HashMap<String,Socket>So) { 
        this.fileSocket=fileSocket;
		this.SocketMap=So;
		//So.put(name, fileSocket);//将自己加进去
		this.start();
		//System.out.println("开始接收！！！");
    }
    public void run() {        
    	//获取套接字输入流
    	try {
    		//System.out.println("run！！！");
        DataInputStream in=new DataInputStream(
                           new BufferedInputStream(
                           fileSocket.getInputStream()));  
        //接收文件名、文件长度
        String txt=in.readUTF();
        System.out.println("txt"+txt);
        int index=txt.indexOf("/");
        name=txt.substring(0,index);
		//获取目的端用户名字
		System.out.println("name:"+name);
		int length=txt.length();
		int ind=txt.indexOf("`");
		String dest=txt.substring(index+1, ind);
		System.out.println("目的地:"+dest);
		String filename=txt.substring(ind+1,length);//将聊天内容提取出来
		SocketMap.put(name, fileSocket);
        //String filename=in.readUTF(); //文件名
        System.out.println("接受的文件名："+filename);
        int fileLen=(int)in.readLong(); //文件长度      
        //创建文件输出流
        File file=new File("./upload/"+filename);    
        if(file.exists()) file.delete();
		file.createNewFile(); //第一次创建文件
        //文件输出流
        BufferedOutputStream out=new BufferedOutputStream(
                                  new FileOutputStream(file));    
        //接收文件内容，存储为外部文件
        byte[] buffer=new byte[BUFSIZE]; //读入缓冲区
        int numRead=0; //单次读取的字节数
        int numFinished=0;//总完成字节数
        while (numFinished<fileLen && (numRead=in.read(buffer))!=-1) { //输入流可读      
            out.write(buffer,0,numRead);
            numFinished+=numRead; //已完成字节数
        }//end while
        System.out.println("服务器接收文件------");
        //定义字符输出流
        PrintWriter pw=new PrintWriter(fileSocket.getOutputStream(),true);       
        //关闭流
        if (numFinished>=fileLen) {//文件接收完成？
            pw.println("M_DONE"); //回送成功消息
           ServerJFrame.area.append(filename+"  接收成功！\n");
           System.out.println("服务器文件接收成功!");
        }else {
            pw.println("M_LOST"); //回送失败消息
            ServerJFrame.area.append(filename+"  接收失败！\n");
        }//end if  
        sleep(500);
        SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String text=time.format(new Date())+" ";
		Server.text="\n"+text+Server.text+"     "+name+"发送文件"+filename+"给"+dest+"请于本地文件夹upload下查看\n";
        /*if(!dest.equals("All"))
		{
			Socket i=this.findIndex(dest);//找到目的端的socket
			if(i!=null)
			{
				PrintStream outt=new PrintStream(i.getOutputStream(),true); //输入流
				outt.println(text+"     "+name+"发送文件"+filename+"给你,请于本地文件夹upload下查看");//将消息发送给对方
			}
		}
		else//是发送给All
		{
			Iterator<String>set=this.SocketMap.keySet().iterator();//建立键值集合
			while(set.hasNext())
			{
				String key=set.next();
				if(!key.equals(name))//不为自己时，需要建立流
				{
					Socket ss=this.SocketMap.get(key);
					PrintStream outt=new PrintStream(ss.getOutputStream(),true);
					outt.println(text+"     "+name+"发送文件"+filename+"给你,请于本地文件夹upload下查看");
				}
			}
		}*/
        in.close();
        out.close();
        pw.close();
        fileSocket.close();
    	}
    	catch(Exception e)
    	{
    		System.out.println("ERROR!");
    	}
    }//end doInBackground    
    public Socket findIndex(String name)
	{
		Socket k=null;
		k=this.SocketMap.get(name);
		return k;
	}
}
