
package javaproject;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class Project 
{
    static Connection con=null;
    static PreparedStatement pst=null;
    static ResultSet rs=null;
    
    public static void connector()
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","san885MAN$&");
        }
        catch(Exception ce)
        {
            System.out.println(ce);
        }
    }
    
    public static void patientlogin()
    {
        JFrame f=new JFrame();
        JPanel p=new JPanel();
        p.setBackground(new Color(59,59,59));
        p.setLayout(null);
        p.setBounds(0,0,500,500);
        f.add(p);
        Font f2=new Font("Tahoma",Font.PLAIN,15);
        Font f1=new Font("Serif",Font.BOLD,32);
        Font f3=new Font("Tahoma",Font.PLAIN,20);
        
        JLabel back=new JLabel("< Back");
        back.setBounds(0,0,100,30);
        back.setForeground(new Color(215,215,215));
        back.setFont(f2);
        p.add(back);
              
        JLabel home=new JLabel("Patient's login page");
        home.setBounds(190,0,300,30);
        home.setForeground(new Color(215,215,215));
        home.setFont(f2);
        p.add(home);
        
        JLabel w=new JLabel("Enter your email and password to login.");
        w.setBounds(100,80,500,30);
        w.setForeground(new Color(215,215,215));
        w.setFont(f2);
        p.add(w);
        
        
        JLabel exit=new JLabel("X");
        exit.setBounds(475,0,100,30);
        exit.setForeground(new Color(215,215,215));
        exit.setFont(f2);
        p.add(exit);
        
        exit.addMouseListener(new MouseAdapter()
        {
            public void mouseEntered(MouseEvent me)
            {
                exit.setForeground(Color.RED);
            }
        });
        
        exit.addMouseListener(new MouseAdapter()
        {
            public void mouseExited(MouseEvent me)
            {
                exit.setForeground(new Color(215,215,215));
            }
        });
        
        back.addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent me)
            {
                Project.main(null);
            }
        });
        
        exit.addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent me)
            {
                System.exit(0);
            }
        });
        
        JLabel l1=new JLabel("Enter Email-ID:");
        l1.setBounds(50,140,150,20);
        l1.setForeground(new Color(215,215,215));
        l1.setFont(f2);
        
        JTextField tf1=new JTextField();  
        tf1.setBounds(50,180, 250,20);  
        tf1.setBackground(new Color(215,215,215));
        tf1.setFont(f2);
        
        JLabel l2=new JLabel("Enter Password:");
        l2.setBounds(50,220,150,20);
        l2.setForeground(new Color(215,215,215));
        l2.setFont(f2);
        
        JPasswordField tf2=new JPasswordField();
        tf2.setBounds(50,260,150,20);
        tf2.setEchoChar('*');
        tf2.setBackground(new Color(215,215,215));
        tf2.setFont(f2);
        JCheckBox c=new JCheckBox("Show password");
        c.setBounds(230,260,200,20);
        c.setForeground(new Color(215,215,215));
        c.setBackground(new Color(59,59,59));
        p.add(c);
        c.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent me)
            {
                if(c.isSelected())
                    tf2.setEchoChar((char)0);
                else
                    tf2.setEchoChar('*');
            }
        });
        
        
        JButton b=new JButton("Login");  
        b.setBounds(50,300,100,30);  
        b.setBackground(new Color(115,115,115));
        b.setFont(f3);
        
        b.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if(e.getSource()==b)
                {
                    try
                    {
                        connector();
                        String q1="select * from patient where Email_ID=? and Password=?";
                        pst=con.prepareStatement(q1);
                        pst.setString(1, tf1.getText());
                        pst.setString(2, tf2.getText());
                        rs=pst.executeQuery();
                        if(rs.next())
                        {
                            JOptionPane.showMessageDialog(f,"Login Successful.");
                            String getNameQuery = "select Name from patient where Email_ID=?";
                            pst = con.prepareStatement(getNameQuery);
                            pst.setString(1, tf1.getText());
                            rs=pst.executeQuery();
                            if (rs.next()) {
                                Client.clientPersonName=rs.getString("Name");  
                            }
                            else{
                                Client.clientPersonName="Patient";  
                            }
                            f.setVisible(false);  
                            Client.chat();
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(f,"Incorrect email or password.");
                        }
                    }
                    catch(Exception e1)
                    {
                        JOptionPane.showMessageDialog(f,e1);
                    }
                            
                } 
            }
        });
        
        p.add(b);p.add(tf1);p.add(tf2);p.add(l1);p.add(l2);
        f.setLayout(null);
        f.setSize(500,500);   
        f.setLocation(450,100);
        f.setUndecorated(true);  
        f.setVisible(true);   
    }
    
    public static void doctorlogin()
    {
        JFrame f=new JFrame();
        JPanel p=new JPanel();
        p.setBackground(new Color(59,59,59));
        p.setLayout(null);
        p.setBounds(0,0,500,500);
        f.add(p);
        Font f2=new Font("Tahoma",Font.PLAIN,15);
        Font f1=new Font("Serif",Font.BOLD,32);
        Font f3=new Font("Tahoma",Font.PLAIN,20);
        
        JLabel back=new JLabel("< Back");
        back.setBounds(0,0,100,30);
        back.setForeground(new Color(215,215,215));
        back.setFont(f2);
        p.add(back);
              
        JLabel home=new JLabel("Doctor's login page");
        home.setBounds(190,0,300,30);
        home.setForeground(new Color(215,215,215));
        home.setFont(f2);
        p.add(home);
        
        JLabel w=new JLabel("Enter your email and password to login.");
        w.setBounds(100,80,500,30);
        w.setForeground(new Color(215,215,215));
        w.setFont(f2);
        p.add(w);
        
        
        JLabel exit=new JLabel("X");
        exit.setBounds(475,0,100,30);
        exit.setForeground(new Color(215,215,215));
        exit.setFont(f2);
        p.add(exit);
        
        exit.addMouseListener(new MouseAdapter()
        {
            public void mouseEntered(MouseEvent me)
            {
                exit.setForeground(Color.RED);
            }
        });
        
        exit.addMouseListener(new MouseAdapter()
        {
            public void mouseExited(MouseEvent me)
            {
                exit.setForeground(new Color(215,215,215));
            }
        });
        
        back.addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent me)
            {
                Project.main(null);
            }
        });
        
        exit.addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent me)
            {
                System.exit(0);
            }
        });
        
        JLabel l1=new JLabel("Enter Email-ID:");
        l1.setBounds(50,140,150,20);
        l1.setForeground(new Color(215,215,215));
        l1.setFont(f2);
        
        JTextField tf1=new JTextField();  
        tf1.setBounds(50,180, 250,20);  
        tf1.setBackground(new Color(215,215,215));
        tf1.setFont(f2);
        
        JLabel l2=new JLabel("Enter Password:");
        l2.setBounds(50,220,150,20);
        l2.setForeground(new Color(215,215,215));
        l2.setFont(f2);
        
        JPasswordField tf2=new JPasswordField();
        tf2.setBounds(50,260,150,20);
        tf2.setEchoChar('*');
        tf2.setBackground(new Color(215,215,215));
        tf2.setFont(f2);
        JCheckBox c=new JCheckBox("Show password");
        c.setBounds(230,260,200,20);
        c.setForeground(new Color(215,215,215));
        c.setBackground(new Color(59,59,59));
        p.add(c);
        c.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent me)
            {
                if(c.isSelected())
                    tf2.setEchoChar((char)0);
                else
                    tf2.setEchoChar('*');
            }
        });
        
        
        JButton b=new JButton("Login");  
        b.setBounds(50,300,100,30);  
        b.setBackground(new Color(115,115,115));
        b.setFont(f3);
        
        b.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if(e.getSource()==b)
                {
                    try
                    {
                        connector();
                        String q1="select * from doctor where Email_ID=? and Password=?";
                        pst=con.prepareStatement(q1);
                        pst.setString(1, tf1.getText());
                        pst.setString(2, tf2.getText());
                        rs=pst.executeQuery();
                        if(rs.next())
                        {
                            JOptionPane.showMessageDialog(f,"Login Successful.");    
                            String getNameQuery = "select Name from doctor where Email_ID=?";
                            pst = con.prepareStatement(getNameQuery);
                            pst.setString(1, tf1.getText());
                            rs=pst.executeQuery();
                            if (rs.next()) {
                                Server.serverPersonName=rs.getString("Name");  
                            }
                            else{
                                Server.serverPersonName="Doctor";  
                            }
                            f.setVisible(false);
                            // Server.Doctor();
                            Server.chat();
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(f,"Incorrect email or password.");
                        }
                    }
                    catch(Exception e1)
                    {
                        //JOptionPane.showMessageDialog(f,e1);
                    }
                            
                } 
            }
        });
        
        p.add(b);p.add(tf1);p.add(tf2);p.add(l1);p.add(l2);
        f.setLayout(null);
        f.setSize(500,500);  
        f.setLocation(450,100); 
        f.setUndecorated(true);  
        f.setVisible(true);   
    }
    
    public static void newuser()
    {
        JFrame f=new JFrame();
        JPanel p=new JPanel();
        p.setBackground(new Color(59,59,59));
        p.setLayout(null);
        p.setBounds(0,0,500,500);
        f.add(p);
        Font f2=new Font("Tahoma",Font.PLAIN,15);
        Font f1=new Font("Serif",Font.BOLD,32);
        Font f3=new Font("Tahoma",Font.PLAIN,20);
        
        JLabel back=new JLabel("< Back");
        back.setBounds(0,0,100,30);
        back.setForeground(new Color(215,215,215));
        back.setFont(f2);
        p.add(back);
              
        JLabel home=new JLabel("New user page");
        home.setBounds(190,0,300,30);
        home.setForeground(new Color(215,215,215));
        home.setFont(f2);
        p.add(home);
        
        JLabel w=new JLabel("Click the respective button to go the registration page.");
        w.setBounds(80,100,500,30);
        w.setForeground(new Color(215,215,215));
        w.setFont(f2);
        p.add(w);
        
        
        JLabel exit=new JLabel("X");
        exit.setBounds(475,0,100,30);
        exit.setForeground(new Color(215,215,215));
        exit.setFont(f2);
        p.add(exit);
        
        exit.addMouseListener(new MouseAdapter()
        {
            public void mouseEntered(MouseEvent me)
            {
                exit.setForeground(Color.RED);
            }
        });
        
        exit.addMouseListener(new MouseAdapter()
        {
            public void mouseExited(MouseEvent me)
            {
                exit.setForeground(new Color(215,215,215));
            }
        });
        
        back.addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent me)
            {
                Project.main(null);
            }
        });
        
        exit.addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent me)
            {
                System.exit(0);
            }
        });
        
        JLabel l1=new JLabel("Register As");
        l1.setBounds(165,150,200,100);
        l1.setForeground(new Color(215,215,215));
        l1.setFont(f1);
        
        JButton b1=new JButton("A Doctor");
        b1.setBounds(130,230,200,50);
        b1.setBackground(new Color(115,115,115));
        b1.setFont(f3);
        
        JButton b2=new JButton("A Patient");
        b2.setBounds(130,300,200,50);
        b2.setBackground(new Color(115,115,115));
        b2.setFont(f3);
        
        b1.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if(e.getSource()==b1)
                {
                    doctorregister();
                    f.setVisible(false);
                }   
            }
        });
        
        b2.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if(e.getSource()==b2)
                {
                    patientregister();
                    f.setVisible(false);
                }   
            }
        });
        
        p.add(b1);p.add(b2);
        p.add(l1);
        f.setLayout(null);
        f.setSize(500,500);  
        f.setLocation(450,100); 
        f.setUndecorated(true);
        f.setVisible(true);   
    }
    
    public static void patientregister()
    {
        JFrame f=new JFrame("");
        JPanel p=new JPanel();
        p.setBackground(new Color(59,59,59));
        p.setLayout(null);
        p.setBounds(0,0,500,500);
        f.add(p);
        Font f2=new Font("Tahoma",Font.PLAIN,15);
        Font f1=new Font("Tahoma",Font.PLAIN,20);
        
        JLabel back=new JLabel("< Back");
        back.setBounds(0,0,100,30);
        back.setForeground(new Color(215,215,215));
        back.setFont(f2);
        p.add(back);
              
        JLabel home=new JLabel("Patient's registration page");
        home.setBounds(190,0,300,30);
        home.setForeground(new Color(215,215,215));
        home.setFont(f2);
        p.add(home);
        
        JLabel w=new JLabel("Enter the following details to register yourself.");
        w.setBounds(130,80,500,30);
        w.setForeground(new Color(215,215,215));
        w.setFont(f2);
        p.add(w);
        
        
        JLabel exit=new JLabel("X");
        exit.setBounds(475,0,100,30);
        exit.setForeground(new Color(215,215,215));
        exit.setFont(f2);
        p.add(exit);
        
        exit.addMouseListener(new MouseAdapter()
        {
            public void mouseEntered(MouseEvent me)
            {
                exit.setForeground(Color.RED);
            }
        });
        
        exit.addMouseListener(new MouseAdapter()
        {
            public void mouseExited(MouseEvent me)
            {
                exit.setForeground(new Color(215,215,215));
            }
        });
        
        back.addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent me)
            {
                newuser();
            }
        });
        
        exit.addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent me)
            {
                System.exit(0);
            }
        });
        
        
        JLabel l1=new JLabel("Enter Name");
        l1.setBounds(30,150,150,20);
        l1.setForeground(new Color(215,215,215));
        l1.setFont(f2);
        
        JTextField tf1=new JTextField();  
        tf1.setBounds(30,180, 150,20); 
        tf1.setBackground(new Color(215,215,215));
        tf1.setFont(f2);
        
        JLabel l2=new JLabel("Enter Password");
        l2.setBounds(30,210,150,20);
        l2.setForeground(new Color(215,215,215));
        l2.setFont(f2);
        
        JTextField tf2=new JTextField();
        tf2.setBounds(30,240,150,20);
        tf2.setBackground(new Color(215,215,215));
        tf2.setFont(f2);
        
        JLabel l3=new JLabel("Enter Email-Id");
        l3.setBounds(30,270,150,20);
        l3.setForeground(new Color(215,215,215));
        l3.setFont(f2);
        
        JTextField tf3=new JTextField();
        tf3.setBounds(30,300,250,20);
        tf3.setBackground(new Color(215,215,215));
        tf3.setFont(f2);
        
        JButton b=new JButton("Submit");  
        b.setBounds(30,350,100,30);  
        b.setBackground(new Color(115,115,115));
        b.setFont(f1);
                
        b.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if(e.getSource()==b)
                {
                    try
                    {
                        connector();
                        String q1="insert into patient(Name,Password,Email_ID) VALUES(?,?,?)";
                        pst=con.prepareStatement(q1);
                        pst.setString(1, tf1.getText());
                        pst.setString(2, tf2.getText());
                        pst.setString(3, tf3.getText());
                        pst.executeUpdate();
                        JOptionPane.showMessageDialog(f,"Registration Successful. Press 'OK' to move to patient's login page");
                        patientlogin();
                        f.setVisible(false);
                    }
                    catch(Exception e1)
                    {
                        JOptionPane.showMessageDialog(f,e1);
                    }
                            
                } 
            }
        });
        
        p.add(b);p.add(tf1);p.add(tf2);p.add(l1);p.add(l2);p.add(l3);p.add(tf3);
        f.setLayout(null);
        f.setSize(500,500); 
        f.setLocation(450,100);
        f.setUndecorated(true);
        f.setVisible(true);
    }
    
    public static void doctorregister()
    {
        JFrame f=new JFrame("");
        JPanel p=new JPanel();
        p.setBackground(new Color(59,59,59));
        p.setLayout(null);
        p.setBounds(0,0,500,500);
        f.add(p);
        Font f2=new Font("Tahoma",Font.PLAIN,15);
        Font f1=new Font("Tahoma",Font.PLAIN,20);
        
        JLabel back=new JLabel("< Back");
        back.setBounds(0,0,100,30);
        back.setForeground(new Color(215,215,215));
        back.setFont(f2);
        p.add(back);
              
        JLabel home=new JLabel("Doctor's registration page");
        home.setBounds(190,0,300,30);
        home.setForeground(new Color(215,215,215));
        home.setFont(f2);
        p.add(home);
        
        JLabel w=new JLabel("Enter the following details to register yourself.");
        w.setBounds(130,55,500,30);
        w.setForeground(new Color(215,215,215));
        w.setFont(f2);
        p.add(w);
        
        
        JLabel exit=new JLabel("X");
        exit.setBounds(475,0,100,30);
        exit.setForeground(new Color(215,215,215));
        exit.setFont(f2);
        p.add(exit);
        
        exit.addMouseListener(new MouseAdapter()
        {
            public void mouseEntered(MouseEvent me)
            {
                exit.setForeground(Color.RED);
            }
        });
        
        exit.addMouseListener(new MouseAdapter()
        {
            public void mouseExited(MouseEvent me)
            {
                exit.setForeground(new Color(215,215,215));
            }
        });
        
        back.addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent me)
            {
                newuser();
            }
        });
        
        exit.addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent me)
            {
                System.exit(0);
            }
        });
        
        
        JLabel l1=new JLabel("Enter Name");
        l1.setBounds(30,100,150,20);
        l1.setForeground(new Color(215,215,215));
        l1.setFont(f2);
        
        JTextField tf1=new JTextField();  
        tf1.setBounds(30,130, 150,20); 
        tf1.setBackground(new Color(215,215,215));
        tf1.setFont(f2);
        
        JLabel l2=new JLabel("Enter Password");
        l2.setBounds(30,160,150,20);
        l2.setForeground(new Color(215,215,215));
        l2.setFont(f2);
        
        JTextField tf2=new JTextField();
        tf2.setBounds(30,190,150,20);
        tf2.setBackground(new Color(215,215,215));
        tf2.setFont(f2);
        
        JLabel l3=new JLabel("Enter Email-Id");
        l3.setBounds(30,220,150,20);
        l3.setForeground(new Color(215,215,215));
        l3.setFont(f2);
        
        JTextField tf3=new JTextField();
        tf3.setBounds(30,250,250,20);
        tf3.setBackground(new Color(215,215,215));
        tf3.setFont(f2);
        
        JLabel l4=new JLabel("Enter Specialization(eg: General Physician, Dentist, Cardiologist, etc.)");
        l4.setBounds(30,280,500,20);
        l4.setForeground(new Color(215,215,215));
        l4.setFont(f2);
        
        JTextField tf4=new JTextField();
        tf4.setBounds(30,310,200,20);  
        tf4.setBackground(new Color(215,215,215));
        tf4.setFont(f2);
        p.add(tf4);
        
        JLabel l5=new JLabel("Enter Years of experience");
        l5.setBounds(30,340,200,20);
        l5.setForeground(new Color(215,215,215));
        l5.setFont(f2);
        
        JTextField tf5=new JTextField();  
        tf5.setBounds(30,370, 150,20);
        tf5.setBackground(new Color(215,215,215));
        tf5.setFont(f2);
        
        JButton b=new JButton("Submit");  
        b.setBounds(30,410,100,30);  
        b.setBackground(new Color(115,115,115));
        b.setFont(f1);
        
        b.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if(e.getSource()==b)
                {
                    try
                    {
                        connector();
                        String q1="insert into doctor(Name,Password,Email_ID,Specialization,Years_of_experience) VALUES(?,?,?,?,?)";
                        pst=con.prepareStatement(q1);
                        pst.setString(1, tf1.getText());
                        pst.setString(2, tf2.getText());
                        pst.setString(3, tf3.getText());
                        pst.setString(4, tf4.getText());
                        pst.setString(5, tf5.getText());
                        pst.executeUpdate();
                        JOptionPane.showMessageDialog(f,"Registration Successful. Press 'OK' to move to doctor's login page");
                        doctorlogin();
                        f.setVisible(false);
                    }
                    catch(Exception e1)
                    {
                        JOptionPane.showMessageDialog(f,e1);
                    }
                            
                } 
            }
        });
        
        p.add(b);p.add(tf1);p.add(tf2);p.add(l1);p.add(l2);p.add(l3);p.add(tf3);p.add(l4);p.add(l5);p.add(tf5);
        f.setLayout(null);
        f.setSize(500,500); 
        f.setLocation(450,100);
        f.setUndecorated(true);
        f.setVisible(true);
    }
    
    
    public static void main(String[] args)
    {
        JFrame f=new JFrame();
        
        JPanel p=new JPanel();
        p.setBackground(new Color(59,59,59));
        p.setLayout(null);
        p.setBounds(0,0,500,500);
        f.add(p);
        
        Font f1=new Font(Font.SERIF,Font.BOLD,32);
        Font f2=new Font("Tahoma",Font.PLAIN,20);
        Font f3=new Font("Tahoma",Font.PLAIN,15);
        
        JLabel w=new JLabel("!! Welcome to E-Health Care Advisor !!");
        w.setBounds(130,55,300,30);
        w.setForeground(new Color(215,215,215));
        w.setFont(f3);
        p.add(w);
                
        JLabel home=new JLabel("Home page");
        home.setBounds(210,0,100,30);
        home.setForeground(new Color(215,215,215));
        home.setFont(f3);
        p.add(home);
        
        JLabel exit=new JLabel("X");
        exit.setBounds(475,0,100,30);
        exit.setForeground(new Color(215,215,215));
        exit.setFont(f3);
        p.add(exit);
        
        exit.addMouseListener(new MouseAdapter()
        {
            public void mouseEntered(MouseEvent me)
            {
                exit.setForeground(Color.RED);
                //System.exit(0);
            }
        });
        
        exit.addMouseListener(new MouseAdapter()
        {
            public void mouseExited(MouseEvent me)
            {
                exit.setForeground(new Color(215,215,215));
                //System.exit(0);
            }
        });
        
        exit.addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent me)
            {
                System.exit(0);
            }
        });
        
        JLabel l1=new JLabel("Login As");
        l1.setBounds(200,100,150,100);
        l1.setForeground(new Color(215,215,215));
        l1.setFont(f1);
        p.add(l1);
        
        JButton b1=new JButton("A Doctor");
        b1.setBounds(150,190,200,50);
        b1.setBackground(new Color(115,115,115));
        b1.setFont(f2);
        p.add(b1);
        
        JButton b2=new JButton("A Patient");
        b2.setBounds(150,260,200,50);
        b2.setBackground(new Color(115,115,115));
        b2.setFont(f2);
        p.add(b2);
        
        JLabel l2=new JLabel("OR");
        l2.setBounds(225,315,50,50);
        l2.setForeground(new Color(215,215,215));
        l2.setFont(f1);
        p.add(l2);
        
        JButton b3=new JButton("New User");
        b3.setBounds(150,370,200,50);
        b3.setBackground(new Color(115,115,115));
        b3.setFont(f2);
        p.add(b3);
        
        b1.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if(e.getSource()==b1)
                {
                    doctorlogin();
                    f.setVisible(false);
                }   
            }
        });
        
        b2.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if(e.getSource()==b2)
                {
                    patientlogin();
                    f.setVisible(false);
                }   
            }
        });
        
        b3.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if(e.getSource()==b3)
                {
                    newuser();
                    f.setVisible(false);
                }   
            }
        });
        
        f.setLayout(null);
        f.setSize(500,500);
        f.setLocation(450,100);
        f.setUndecorated(true);
        f.setVisible(true);     
                             
    }
}





   
       
