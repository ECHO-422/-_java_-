package ChatRoom;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import javax.swing.JOptionPane;

import SQL.DBHelper;
public class LoginJFrame extends javax.swing.JFrame{
	javax.swing.JButton btnGetPassword;
    javax.swing.JButton btnLogin;
    javax.swing.JButton btnRegister;
    javax.swing.JCheckBox chkAutoLogin;
    javax.swing.JCheckBox chkRemember;
    javax.swing.JLabel jLabel2;
    javax.swing.JLabel jLabel3;
    javax.swing.JLabel lblLogo;
    javax.swing.JPasswordField txtPassword;
    javax.swing.JTextField txtRemoteName;
    javax.swing.JTextField txtRemotePort;
    javax.swing.JTextField txtUserId;
    LoginAction laction;
    RegisterAction raction;
    DBHelper helper;
    Server server;
    //String client_name[];
	public LoginJFrame(DBHelper db,Server ser)
	{
		super("用户登录");
		int x = (Toolkit.getDefaultToolkit().getScreenSize().width - this.getSize().width)/2;
        int y = (Toolkit.getDefaultToolkit().getScreenSize().height - this.getSize().height)/2;
        this.setLocation(x, y);
        this.helper=db;
        this.server=ser;
       // this.client_name=new String[30]; //最多可以有三十个用户
		//this.client_name[0]="All";
        laction=new LoginAction(this);
        raction=new RegisterAction(this);
        initComponents();
	}
    private void initComponents() {

        lblLogo = new javax.swing.JLabel();
        txtUserId = new javax.swing.JTextField();
        btnRegister = new javax.swing.JButton();
        btnGetPassword = new javax.swing.JButton();
        txtPassword = new javax.swing.JPasswordField();
        chkRemember = new javax.swing.JCheckBox();
        chkAutoLogin = new javax.swing.JCheckBox();
        btnLogin = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtRemoteName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtRemotePort = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("用户登录");
        setIconImage(null);

        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ChatRoom/images/Login.png"))); // NOI18N

        txtUserId.setFont(new java.awt.Font("宋体", 1, 18)); // NOI18N

        btnRegister.setFont(new java.awt.Font("宋体", 0, 14)); // NOI18N
        btnRegister.setText("注册帐号");
        btnRegister.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRegister.addActionListener(raction);

        btnGetPassword.setFont(new java.awt.Font("宋体", 0, 14)); // NOI18N
        btnGetPassword.setText("找回密码");
        btnGetPassword.setBorderPainted(false);
        btnGetPassword.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        txtPassword.setFont(new java.awt.Font("宋体", 1, 18)); // NOI18N

        chkRemember.setText("记住密码");
        chkRemember.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        chkAutoLogin.setText("自动登录");
        chkAutoLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btnLogin.setBackground(new java.awt.Color(153, 153, 255));
        btnLogin.setFont(new java.awt.Font("宋体", 1, 18)); // NOI18N
        btnLogin.setText("登  录");
        btnLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLogin.addActionListener(laction);
        

        jLabel2.setText("服务器主机名：");

        txtRemoteName.setText("127.0.0.1");

        jLabel3.setText("服务器端口：");
        txtRemotePort.setText("8080");
        //

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtUserId, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                                    .addComponent(txtPassword, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(chkRemember, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                                .addGap(13, 13, 13)
                                .addComponent(chkAutoLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnRegister, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                            .addComponent(btnGetPassword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtRemoteName, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtRemotePort)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtUserId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnRegister))
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnGetPassword))
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(chkRemember)
                            .addComponent(chkAutoLogin)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(lblLogo)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnLogin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtRemoteName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txtRemotePort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }
	
}
