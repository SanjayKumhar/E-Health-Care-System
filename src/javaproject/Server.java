
package javaproject;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;
import javax.swing.plaf.basic.*;

import java.util.Calendar;
import java.text.SimpleDateFormat;

public class Server {

    static JPanel p1;
    static JTextField t1;
    static JButton b1;
    static JPanel a1;
    // static JTextArea a1;
    public static JFrame f1 = new JFrame();

    static Box vertical = Box.createVerticalBox();

    static ServerSocket skt;
    static Socket s;
    static DataInputStream din;
    static DataOutputStream dout;

    static Boolean typing;

    static String serverPersonName = "Server";

    public static void Doctor() {
        p1 = new JPanel();
        p1.setLayout(null);
        p1.setBackground(new Color(100, 100, 100));
        p1.setBounds(0, 0, 450, 70);
        f1.add(p1);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("javaproject/3.png"));
        Image i2 = i1.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l1 = new JLabel(i3);
        l1.setBounds(10, 10, 50, 50);
        p1.add(l1);

        l1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent ae) {
                Project.doctorlogin();
                f1.setVisible(false);
            }
        });

        JLabel exit = new JLabel("X");
        exit.setBounds(425, 10, 100, 30);
        exit.setForeground(new Color(215, 215, 215));
        exit.setFont(new Font("Tahoma", Font.PLAIN, 20));
        p1.add(exit);

        exit.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent me) {
                exit.setForeground(Color.RED);
            }
        });

        exit.addMouseListener(new MouseAdapter() {
            public void mouseExited(MouseEvent me) {
                exit.setForeground(new Color(215, 215, 215));
            }
        });

        exit.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                System.exit(0);
            }
        });

        JLabel l3 = new JLabel(serverPersonName);
        l3.setFont(new Font("SAN_SERIF", Font.BOLD, 18));
        l3.setForeground(Color.WHITE);
        l3.setBounds(110, 15, 100, 18);
        p1.add(l3);

        JLabel l4 = new JLabel("Active Now");
        l4.setFont(new Font("SAN_SERIF", Font.PLAIN, 14));
        l4.setForeground(Color.WHITE);
        l4.setBounds(110, 35, 100, 20);
        p1.add(l4);

        Timer t = new Timer(1, new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                if (!typing) {
                    l4.setText("Active Now");
                }
            }
        });

        t.setInitialDelay(2000);

        /*
         * a1=new JTextArea();
         * a1.setBounds(5,75,440,570);
         * a1.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
         * a1.setEditable(false);
         * f1.add(a1);
         */

        a1 = new JPanel();
        a1.setBackground(new Color(59, 59, 59));
        a1.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));

        JScrollPane sp = new JScrollPane(a1);
        sp.setBounds(5, 75, 440, 570);
        sp.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = new Color(80, 80, 80);
            }
        });
        sp.getVerticalScrollBar().setBackground(new Color(110, 110, 110));
        sp.setBorder(BorderFactory.createEmptyBorder());
        f1.add(sp);

        t1 = new JTextField();
        t1.setBounds(5, 655, 310, 40);
        t1.setBackground(new Color(100, 100, 100));
        t1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        t1.setForeground(Color.WHITE);
        f1.add(t1);

        t1.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) {
                l4.setText("typing...");

                t.stop();

                typing = true;
            }

            public void keyReleased(KeyEvent ke) {
                typing = false;

                if (!t.isRunning()) {
                    t.start();
                }
            }
        });

        b1 = new JButton("Send");
        b1.setBounds(320, 655, 123, 40);
        b1.setBackground(new Color(100, 100, 100));
        b1.setForeground(Color.WHITE);
        b1.setFont(new Font("Tahoma", Font.BOLD, 16));
        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                try {
                    String out = t1.getText();

                    JPanel p2 = formatLabel(out);
                    p2.setBackground(new Color(59, 59, 59));
                    a1.setLayout(new BorderLayout());

                    JPanel right = new JPanel(new BorderLayout());
                    right.setBackground(new Color(59, 59, 59));
                    right.add(p2, BorderLayout.LINE_END);
                    vertical.add(right);
                    vertical.add(Box.createVerticalStrut(15));

                    a1.add(vertical, BorderLayout.PAGE_START);

                    dout.writeUTF(out);
                    t1.setText("");
                } catch (Exception e) {
                    System.out.println(e);
                }
            }

        });
        f1.add(b1);

        f1.getContentPane().setBackground(new Color(59, 59, 59));
        f1.setLayout(null);
        f1.setSize(450, 700);
        f1.setLocation(550, 0);
        f1.setUndecorated(true);
        f1.setVisible(true);
    }

    public static JPanel formatLabel(String out) {
        JPanel p3 = new JPanel();
        p3.setBackground(new Color(59, 59, 59));
        p3.setLayout(new BoxLayout(p3, BoxLayout.Y_AXIS));

        JLabel l1 = new JLabel("<html><p style = \"width : 150px\">" + out + "</p></html>");
        l1.setFont(new Font("Tahoma", Font.PLAIN, 16));
        l1.setForeground(Color.WHITE);
        l1.setBackground(new Color(100, 100, 100));
        l1.setOpaque(true);
        l1.setBorder(new EmptyBorder(15, 15, 15, 50));

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

        JLabel l2 = new JLabel();
        l2.setText(sdf.format(cal.getTime()));
        l2.setForeground(Color.GREEN);

        p3.add(l1);
        p3.add(l2);
        return p3;
    }

    public static void chat() {
        Doctor();
        Thread socketThread = new Thread(() -> { 
            try {
                skt = new ServerSocket(6001);
                s = skt.accept();
                din = new DataInputStream(s.getInputStream());
                dout = new DataOutputStream(s.getOutputStream());
                String msginput = "";
                while (true) {
                    msginput = din.readUTF();
                    JPanel p2 = formatLabel(msginput);
                    JPanel left = new JPanel(new BorderLayout());
                    left.setBackground(new Color(59, 59, 59));
                    left.add(p2, BorderLayout.LINE_START);
                    vertical.add(left);
                    f1.validate();
                }
    
            } catch (Exception e) {
                System.out.println(e);
                System.exit(0);
            }
        });
        socketThread.start();
    }

    public static void main(String[] args) {
        Doctor();

        String msginput = "";
        try {
            skt = new ServerSocket(6001);
            s = skt.accept();
            din = new DataInputStream(s.getInputStream());
            dout = new DataOutputStream(s.getOutputStream());

            while (true) {
                msginput = din.readUTF();
                JPanel p2 = formatLabel(msginput);
                JPanel left = new JPanel(new BorderLayout());
                left.setBackground(new Color(59, 59, 59));
                left.add(p2, BorderLayout.LINE_START);
                vertical.add(left);
                f1.validate();
            }

        } catch (Exception e) {
        }
    }
}