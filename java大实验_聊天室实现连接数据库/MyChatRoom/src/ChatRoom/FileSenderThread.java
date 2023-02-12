package ChatRoom;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetSocketAddress;
//import java.net.InetSocketAddress;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
//import java.net.SocketAddress;
import java.util.List;
import javax.swing.SwingWorker;
public class FileSenderThread extends SwingWorker<List<String>,String>{
	private File file; //文件
    private ClientJFrame parentUI; //父类
    private Socket fileSocket; //传送文件的套接字
    private static final int BUFSIZE=8096; //缓冲区大小
    private int progress=0; //文件传送进度
    private String lastResults=null; //传送结果
    String name;
	String txt="";
    //构造函数
    public FileSenderThread (File file,ClientJFrame parentUI,String n,String des) {
        this.file=file;
        this.parentUI=parentUI;
        this.name=n+'/'+des;
        try {//将自己的名字发送给服务器，服务器建立线程
        	fileSocket=new Socket();
            fileSocket.connect(new InetSocketAddress("127.0.0.1", 5433));
		}
		catch(Exception e)
		{
			System.out.println(name+"用户出了问题!");
		}
    }
    protected List<String> doInBackground() throws Exception {  
    	//
        //连接服务器
        //构建套接字输出流
        DataOutputStream out=new DataOutputStream(
                             new BufferedOutputStream(
                             fileSocket.getOutputStream()));
        //构建文件输入流
        DataInputStream in=new DataInputStream(
                           new BufferedInputStream(
                           new FileInputStream(file)));
        long fileLen=file.length();  //计算文件长度
        //发送文件名称、文件长度
        //out.writeUTF(name);
        out.writeUTF(name+'`'+file.getName());
        out.writeLong(fileLen);
        System.out.println("文件名称:"+file.getName()+" 文件长度:"+fileLen);
        out.flush();
        //传送文件内容
        int numRead=0; //单次读取的字节数
        int numFinished=0; //总完成字节数
        byte[] buffer=new byte[BUFSIZE];   
        while (numFinished<fileLen && (numRead=in.read(buffer))!=-1) { //文件可读  
            out.write(buffer,0,numRead);  //发送
            out.flush();
            numFinished+=numRead; //已完成字节数
            Thread.sleep(200); //演示文件传输进度用
            publish(numFinished+"/"+fileLen+"bytes");
            setProgress(numFinished*100/(int)fileLen);             
        }//end while
        in.close();
        System.out.println("客户端发送文件成功!");
        //接收服务器反馈信息
        BufferedReader br=new BufferedReader(
                          new InputStreamReader(
                          fileSocket.getInputStream()));
        String response=br.readLine();//读取返回串        
        if (response.equalsIgnoreCase("M_DONE")) { //服务器成功接收               
            {
            	System.out.println(file.getName()+" 传送成功！\n");
            	lastResults=  file.getName() +"  传送成功！\n" ;
            }
        }else if (response.equalsIgnoreCase("M_LOST")){ //服务器接收失败
            {
            	System.out.println(file.getName()+"  传送失败!\n");
            	lastResults=  file.getName() +"  传送失败！\n" ;
            }
        }
        else{
        	lastResults=response;
        }//end if
        //关闭流
        br.close();
        out.close();
        fileSocket.close();
        return null;
    } //doInBackground 
    
    protected void process(List<String> middleResults) {
        for (String str:middleResults) {
            parentUI.progressLabel.setText(str);
        }   
    }
    @Override
    protected void done() {
    	SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String text=time.format(new Date())+" ";
        parentUI.progressBar.setValue(parentUI.progressBar.getMaximum());
        parentUI.client.txt=parentUI.client.txt+text+"文件"+lastResults+"\n";
        parentUI.filePanel.setVisible(false);
    }
}
