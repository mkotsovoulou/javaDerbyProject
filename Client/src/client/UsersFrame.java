package client;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class UsersFrame extends JFrame {
    private JMenuBar menuBar = new JMenuBar();
    private JMenu menuFile = new JMenu();
    private JMenuItem menuFileExit = new JMenuItem();
    private JMenu menuHelp = new JMenu();
    private JMenuItem menuHelpAbout = new JMenuItem();
    private JTextField idTF = new JTextField();
    private JLabel jLabel1 = new JLabel();
    private JLabel jLabel2 = new JLabel();
    private JTextField usernameTF = new JTextField();
    private JLabel jLabel3 = new JLabel();
    private JTextArea passwordTF = new JTextArea();
    private JCheckBox isAdminCB = new JCheckBox();
    private JButton jButton1 = new JButton();
    private myDB local;
    private JButton jButton2 = new JButton();

    public UsersFrame() {
        local = new myDB();
        local.connect();
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        this.setJMenuBar( menuBar );
        this.getContentPane().setLayout( null );
        this.setSize(new Dimension(408, 243));
        this.setTitle( "Manage Users in the Database" );
        menuFile.setText( "File" );
        menuFileExit.setText( "Exit" );
        menuFileExit.addActionListener( new ActionListener() { public void actionPerformed( ActionEvent ae ) { fileExit_ActionPerformed( ae ); } } );
        menuHelp.setText( "Help" );
        menuHelpAbout.setText( "About" );
        menuHelpAbout.addActionListener( new ActionListener() { public void actionPerformed( ActionEvent ae ) { helpAbout_ActionPerformed( ae ); } } );
        idTF.setBounds(new Rectangle(65, 30, 40, 20));
        jLabel1.setText("ID");
        jLabel1.setBounds(new Rectangle(20, 30, 35, 20));
        jLabel2.setText("username");
        jLabel2.setBounds(new Rectangle(10, 65, 50, 20));
        usernameTF.setBounds(new Rectangle(65, 65, 85, 20));
        jLabel3.setText("password");
        jLabel3.setBounds(new Rectangle(165, 65, 55, 20));
        passwordTF.setBounds(new Rectangle(230, 65, 80, 20));
        isAdminCB.setText("isAdmin");
        isAdminCB.setBounds(new Rectangle(325, 65, 75, 20));
     //   isAdminCB.setSelected(false);
        jButton1.setText("Create User");
        jButton1.setBounds(new Rectangle(20, 110, 115, 30));
        jButton1.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    jButton1_actionPerformed(e);
                }
            });
        jButton2.setText("View all Users");
        jButton2.setBounds(new Rectangle(305, 155, 80, 20));
        jButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jButton2_actionPerformed(e);
            }
        });
        menuFile.add( menuFileExit );
        menuBar.add( menuFile );
        menuHelp.add( menuHelpAbout );
        menuBar.add( menuHelp );
        this.getContentPane().add(jButton2, null);
        this.getContentPane().add(jButton1, null);
        this.getContentPane().add(isAdminCB, null);
        this.getContentPane().add(passwordTF, null);
        this.getContentPane().add(jLabel3, null);
        this.getContentPane().add(usernameTF, null);
        this.getContentPane().add(jLabel2, null);
        this.getContentPane().add(jLabel1, null);
        this.getContentPane().add(idTF, null);
    }

    void fileExit_ActionPerformed(ActionEvent e) {
        System.exit(0);
    }

    void helpAbout_ActionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(this, new UsersFrame_AboutBoxPanel1(), "About", JOptionPane.PLAIN_MESSAGE);
    }
   
    public static void main (String args[]) {
        UsersFrame x = new UsersFrame();
        x.setVisible(true);
        }

    private void jButton1_actionPerformed(ActionEvent e) {
       String id, username, password, isAdmin;
              
       id = idTF.getText();
       username = usernameTF.getText();
       password = passwordTF.getText();
    
       if (isAdminCB.isSelected()) isAdmin = "Y"; 
       else isAdmin="N";
       
       if (username.length()<2 || password.length()<2) {
               JOptionPane.showMessageDialog(this,"Please enter valid username/passwords");
          return;
       }
       
       try {
       local.addUser(Integer.parseInt(id), 
                     username, 
                     password, 
                     isAdmin);
           JOptionPane.showMessageDialog(this,"User Created.");
       }catch (Exception ex) {
           JOptionPane.showMessageDialog(this,"Error creating User: " + ex.getMessage());
           }
    }

    private void jButton2_actionPerformed(ActionEvent e) {
        ViewUsers x = new ViewUsers();
        x.showForm();
    }
}
