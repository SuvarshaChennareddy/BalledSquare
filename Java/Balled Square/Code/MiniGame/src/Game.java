import java.util.Random;
//import java.lang.Runnable;
//import java.lang.Exception;
//import java.lang.String;
import java.sql.*;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
//import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.InetAddress;
import java.net.UnknownHostException;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import net.proteanit.sql.DbUtils;

//import javax.swing.SwingConstants;
//import javax.swing.JRadioButton;
//import javax.swing.JLabel;

public class Game extends JFrame implements KeyListener {

    private static final long serialVersionUID = 1L;
    
    Random rand = new Random();
    Connection conn = null;
    InetAddress addr;
    int xl = 0, yl = 0, widthl = 1070, heightl = 600;
    int x = 500, y = 250, width = 50, height = 50, vel2 = 20;
    int x2 = 200, y2 = 200, width2 = 10, height2 = 10, velx = 15, vely = 15;
    int x3 = 200, y3 = 200, velx3 = 15, vely3 = 15;
    int x4 = 200, y4 = 200, velx4 = 15, vely4 = 15;
    int x5 = 200, y5 = 200, velx5 = 15, vely5 = 15;
    // int x6 = 110, y6 = 0, width3 = widthl, height3 = heightl;
    int n1 = 250, n2 = 0, n3 = 0;
    int c1 = 250, c11 = 250, c111 = 250;
    int c2 = 250, c22 = 250, c222 = 250;
    int c3 = 250, c33 = 250, c333 = 250;
    int c4 = 250, c44 = 250, c444 = 250;
    int a = x, b = y;
    int a2 = x2, b2 = y2;
    int a3 = x3, b3 = y3;
    int a4 = x4, b4 = y4;
    int a5 = x, b5 = y5;
    int g , p, ng, np;
    int probx, probx3, probx4, probx5;
    int bc = 1;
    int SCORE = 0, HIGHSCORE = 0;
    
    boolean startgame = false;
    boolean sf = false;
    boolean gameOver = false;
    boolean init1 = true;
    boolean init2 = true;
    boolean init3 = true;
    boolean now = true;
    boolean add = true;
    boolean st = true;
    boolean show = false;
    boolean kc = false;
    boolean datah = false;
    boolean changed = false;
    boolean saved = true;

    String d;
    String name = "Balled Square";
    String Creator = "By Suvarsha Ch.";
    String msg1 = "Press any Game_Control to start";
    String msg2 = "";
    String gm = "";
    String control;
    
    String UserName = null;
    
    String pillar[] = {"PlayerName", "CompName", "Highscore"};
    
    Object[][] data= {
    };
    ButtonGroup bg1 = new ButtonGroup();
    ButtonGroup bg2 = new ButtonGroup();
    
    JButton gn;
    JButton gc;
    JButton how;
    JButton avoid;
    JButton RESTART;
    JButton Up;
    JButton Down;
    JButton Left;
    JButton Right;
    
    JButton Play1;
    JButton Play2;
    JButton Data;
  
    JButton save;
    
    JTable Scores;
    JScrollPane Sb;
    
    JMenuBar mb;
    JMenu m1;
    JMenu m2;
    JMenuItem switchk;
    JMenuItem switchb;
    JMenuItem lb;
    
    JMenuItem menu;
    
    JTextField user;
    Timer t;
    Timer sb;
    Timer score;
    
    JPanel mainPane;
    JPanel contentPane;
    //JPanel contentPane2;
    JPanel menuPane;
    JPanel dataPane;
    
    CardLayout cl = new CardLayout();
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Game frame = new Game();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     * @throws SQLException
     */
    @SuppressWarnings("serial")
 public Game() throws SQLException {
     conn = SqliteConnection.dbConnector();
     
        addKeyListener(this);
        requestFocus();
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(xl, yl, widthl, heightl);
        mainPane = new JPanel();
        contentPane = new JPanel();
       // contentPane2 = new JPanel();
        dataPane = new JPanel();
        menuPane = new JPanel();
        mainPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
      //  contentPane2.setBorder(new EmptyBorder(5, 5, 5, 5));
        menuPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        dataPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        
        
        setContentPane(mainPane);
        mainPane.setLayout(cl);
        contentPane.setLayout(null);
        menuPane.setLayout(null);
        
        dataPane.setLayout(null);
        
        mb = new JMenuBar();
        mb.setBackground(Color.blue);
        setJMenuBar(mb);
        m1 = new JMenu("Go to..");
        mb.add(m1);
        m2 = new JMenu("Game Control Options");
        mb.add(m2);
        switchk = new JMenuItem("Play With/Switch to KeyBoard..");
        switchk.setActionCommand("Play1");
        switchk.addActionListener(Key);
        switchb = new JMenuItem("Play With/Switch to Buttons..");
        switchb.setActionCommand("Play2");
        switchb.addActionListener(Key);
        m2.add(switchk);
        m2.add(switchb);
         lb = new JMenuItem("Leaderboard");
         menu = new JMenuItem("Menu");
         m1.add(menu);
         m1.add(lb);
         lb.addActionListener(DataTable);
         menu.addActionListener(Menu);
        
        Play1 = new JButton("Play With KeyBoard");
        Play1.setActionCommand("Play1");
        Play1.setBounds(300, 300, 150 , 25);
        menuPane.add(Play1);
        Play1.addActionListener(Key);
        Play2 = new JButton("Play With Buttons");
        Play2.setActionCommand("Play2");
        Play2.setBounds(600, 300, 150 , 25);
        menuPane.add(Play2);
        Play2.addActionListener(Key);
        
        Up = new JButton("Up");
        Up.setBounds(890, 400, 75, 25);
        contentPane.add(Up);
        Down = new JButton("Down");
        Down.setBounds(878, 450, 100, 25);
        contentPane.add(Down);
        Left = new JButton("Left");
        Left.setBounds(825, 425, 75, 25);
        contentPane.add(Left);
        Right = new JButton("Right");
        Right.setBounds(950, 425, 75, 25);
        contentPane.add(Right);
        Up.setActionCommand("Up");
        Down.setActionCommand("Down");
        Left.setActionCommand("Left");
        Right.setActionCommand("Right");
        bg1.add(Up);
        bg1.add(Down);
        bg1.add(Left);
        bg1.add(Right);
        Up.addActionListener(one);
        Down.addActionListener(one);
        Left.addActionListener(one);
        Right.addActionListener(one);
        try
        {
            addr = InetAddress.getLocalHost();
            try {
            Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery("SELECT * FROM Highscores WHERE CompName = '" + addr.getHostName() + "'");
      while (rs.next()) {
      UserName = rs.getString("UserName");
      HIGHSCORE = rs.getInt("Highscore");
      }
      if (UserName == null) {
          UserName = addr.getHostName();
          try {
          Statement smt = conn.createStatement();
          smt.executeUpdate("INSERT INTO Highscores " + "VALUES ( '" + UserName + "', '" + UserName + "', " + HIGHSCORE +" )");
          }catch(Exception ee) {
           System.out.println("Error in inserting new data..");
          }
          }
     } catch (Exception e) {
      System.out.println("Error in retrieving data..");
}
        }
        catch (UnknownHostException ex)
        {
            UserName = "null";
        }
        
        user = new JTextField(UserName);
        user.setBounds(400, 150, 150, 25);
        menuPane.add(user);
        save = new JButton("Save");
        save.setBounds(560, 150, 75, 25);
        save.addActionListener(Save);
        menuPane.add(save);
        
        Data = new JButton("Leaderboard");
        Data.setBounds(460, 450, 150 , 25);
        Data.addActionListener(DataTable);
        menuPane.add(Data);
        
        Scores = new JTable(data, pillar) {
         public boolean isCellEditable(int data, int columns) {
          return false;
         }
        };
       
        int v = ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
        int h = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
        Sb= new JScrollPane(Scores, v, h);
        Sb.setBounds(450, 10, 450, 500);
        dataPane.add(Sb);
        
        mainPane.add(menuPane, "Menu");
        mainPane.add(contentPane, "Game");
        
        mainPane.add(dataPane, "LeaderBoard");
        cl.show(mainPane, "Menu");
        /*
        gn = new JButton(name);
        gn.setBounds(200, 250, 200, 50);
        gc = new JButton(Creator);
        gc.setBounds(610, 250, 200, 50);
        how = new JButton(msg1);
        how.setBounds(390, 500, 250, 25);
    
        add(gn);
        add(gc);
        add(how);
        
        */

        
        /*
        JRadioButton Up2 = new JRadioButton("Up 1");
        Up2.setBounds(110, 400, 75, 25);
        contentPane.add(Up2);
        JRadioButton Down2 = new JRadioButton("Down 1");
        Down2.setBounds(110, 450, 75, 25);
        contentPane.add(Down2);
        JRadioButton Left2 = new JRadioButton("Left 1");
        Left2.setBounds(45, 425, 75, 25);
        contentPane.add(Left2);
        JRadioButton Right2 = new JRadioButton("Right 1");
        Right2.setBounds(175, 425, 75, 25);
        contentPane.add(Right2);
        Up2.setActionCommand("Up2");
        Down2.setActionCommand("Down2");
        Left2.setActionCommand("Left2");
        Right2.setActionCommand("Right2");
        bg2.add(Up2);
        bg2.add(Down2);
        bg2.add(Left2);
        bg2.add(Right2);
        Up2.addActionListener(three);
        Down2.addActionListener(three);
        Left2.addActionListener(three);
        Right2.addActionListener(three);
        */
        
        
        sb = new Timer(50, small);
        score = new Timer(1000, time);
        
        
    }
    
     
    
    public void paint(Graphics g) {
     
        super.paint(g);
        
        Font one = new Font("Arial", Font.BOLD, 25);
        Font two = new Font("Arial", Font.BOLD, 15);
        Font three = new Font("Arial", Font.BOLD, 25);
        Font four = new Font("Arial", Font.BOLD, 15);
       // setBackground(Color.WHITE);
       // g.setColor(Color.white);  
       // g.fillRect(x6, y6, width3, height3);
        if (show) {
       // g.fillRect(110 ,0, widthl, 55);
        g.setColor(Color.black);
        g.setFont(four);
        g.drawString("Score: " + SCORE + " (Highscore: " + HIGHSCORE + ")", 450, 75);
        g.setFont(one);
        g.drawString(name, 250, 300);
        g.drawString(Creator, 625, 300);
        g.setFont(two);
        g.drawString(msg1, 410, 350);
        g.setFont(three);
        g.drawString(msg2, 415, 300);
        g.drawString(gm, 445, 300);
        g.clearRect(a, b, width, height);
        
        
        g.clearRect(a2, b2, width2, height2);
        g.clearRect(a3, b3, width2, height2);
        g.clearRect(a4, b4, width2, height2);
        g.clearRect(a5, b5, width2, height2);
        
        Color color = new Color (n1, n2, n3);
        g.setColor(color);
        
        g.fillRect(x, y, width, height);
        
        Color color2 = new Color (c1, c11, c111);
        Color color3 = new Color (c2, c22, c222);
        Color color4 = new Color (c3, c33, c333);
        Color color5 = new Color (c4, c44, c444);
        g.setColor(color2);
        g.fillOval(x2 ,y2 , width2, height2);
        g.setColor(color3);
        g.fillOval(x3 ,y3 , width2, height2);
        g.setColor(color4);
        g.fillOval(x4 ,y4 , width2, height2);
        g.setColor(color5);
        g.fillOval(x5 ,y5 , width2, height2);
        //g.setColor(Color.white);
        }else if (!show && !datah){
            g.setFont(one);
            g.setColor(Color.red);
            g.drawString(("BALLED SQUARE by Suvarsha Ch."), 330, 100);
            g.setColor(Color.black);
            g.drawString("Choose your USERNAME..", 375, 175);
            g.drawString("Play Right NOW...", 435, 330);
            g.drawString("COMPARE yourself with other PLAYERS..", 310, 475);
            
            if (changed) {
             g.setFont(four);
             if (saved) {
              g.drawString("Saved!", 415, 255);
             }else if (!saved) {
              g.drawString("Error! Please choose another username.", 415, 255);
             }
            }
        }else if(datah && !show){
          g.setFont(four);
          g.setColor(Color.black);
         g.drawString("Note: The Highscores of all the other players", 100, 275);
         g.drawString("are shown in the table to the your right", 100, 300);
         g.drawString("in descending order (first to last).", 100, 325);
         
        }
     }
    ActionListener Save = new ActionListener() {
     public void actionPerformed(ActionEvent ae) {
     changed = true;
     block:{
  try {
   Statement stmt = conn.createStatement();
   ResultSet rs = stmt.executeQuery("SELECT CompName FROM Highscores WHERE UserName = '" + user.getText() + "'");
   while (rs.next()) {
    if (!(rs.getString("CompName").equals(addr.getHostName()))){
     saved = false;
     repaint();
     break block;
    
    }
   }
    
     Statement smt = conn.createStatement();
     smt.executeUpdate("UPDATE Highscores SET UserName = '" + user.getText() + "' WHERE CompName = '" + addr.getHostName() + "'");
     saved = true;
     repaint();
   
  } catch (Exception e) {
   System.out.println("Unable to save UserName...");
  }
     } 
    }
    };
    
   ActionListener DataTable = new ActionListener() {
    public void actionPerformed(ActionEvent ae) {
     cl.show(mainPane, "LeaderBoard");
     show = false;
     datah = true;
     sb.stop();
           score.stop();
            
            try {
             Statement smt = conn.createStatement();
    ResultSet rs = smt.executeQuery("SELECT * FROM Highscores ORDER BY Highscore DESC");
    
    Scores.setModel(DbUtils.resultSetToTableModel(rs));
   } catch (Exception e) {
    System.out.println("Unable to load data...");
   }
           repaint();
   }
   };
   ActionListener Menu = new ActionListener() {
    public void actionPerformed(ActionEvent ae) {
     cl.show(mainPane, "Menu");
     show = false;
     datah = false;
     sb.stop();
     score.stop();
     repaint();
   }
   };
  ActionListener Key = new ActionListener() {
        public void actionPerformed(ActionEvent ae) {
            cl.show(mainPane, "Game");
            show = true;
            datah = false;
            control = ae.getActionCommand();
            if (control.equals("Play1")) {
             requestFocus();
             setFocusable(true);
             setFocusTraversalKeysEnabled(false);
             
                kc = true;
                Up.setVisible(false);
                Down.setVisible(false);
                Left.setVisible(false);
                Right.setVisible(false);
            }
            if (control.equals("Play2")) {
                kc = false;
                Up.setVisible(true);
                Down.setVisible(true);
                Left.setVisible(true);
                Right.setVisible(true);
            }
            
                repaint();
        }
    };
    
/*
ActionListener three = new ActionListener() {
    public void actionPerformed(ActionEvent ae) {
     String c = ae.getActionCommand();
     if (c.equalsIgnoreCase("Up")){
         a = x;
         b = y;
            y-=vel;
            if ((y < 30)) {
                y = 30;
            }
            
     }else if (c.equalsIgnoreCase("Down")) {
         a = x;
         b = y;
            y+=vel;
            if ((y > (heightl - height - 6))) {
                y = (heightl - height - 6);
            }
     }else if (c.equalsIgnoreCase("Left")) {
         a = x;
         b = y;
            x-=vel;
            if ((x < 5)) {
                x = 5;
            }    
     }else if(c.equalsIgnoreCase("Right")) {
         a = x;
         b = y;
            x+=vel;
            if ((x > (widthl - width - 6))) {
                x = (widthl - width - 6);
            }
     }
     repaint();
    }
};
*/
    ActionListener time = new ActionListener() {

        public void actionPerformed(ActionEvent ae) {
            
            if (!gameOver) {
                
                SCORE++;
                
                if (SCORE < 3) {
                    if (now) {
                        msg2 = "AVOID THE BALL(S)";
                         
                    }
                }else{
                    msg2 ="";
                    }
                
                if (HIGHSCORE < SCORE) {
                 
                    HIGHSCORE = SCORE;
                     
                     
                }
                /*
                hehe = new JButton("Score: " + SCORE);
                hehe.setBounds(0, 0, 100, 25);
                hehe.setBackground(Color.white);
                add(hehe);
                */
                
            }else {
                 if (HIGHSCORE < SCORE)

                     HIGHSCORE = SCORE;
              try {
                     Statement smt = conn.createStatement();
      smt.executeUpdate("UPDATE Highscores SET Highscore = " + HIGHSCORE + " WHERE CompName= '" + addr.getHostName() + "'");
                     }catch(Exception e) {
                      System.out.println("Error in saving HighScore");
                     }
            }
            repaint();
            /*
        ActionListener restart = new ActionListener(){
                    public void actionPerformed(ActionEvent ae){    
                
                t.stop();
                o.stop();
                sb.stop();
                score.stop();
                 x = 500; y = 250; width = 50; height = 50; vel2 = 20;
                 x2 = 200; y2 = 200; width2 = 10; height2 = 10; velx = 15; vely = 15;
                 x3 = 200; y3 = 200; velx3 = 15; vely3 = 15;
                 x4 = 200; y4 = 200; velx4 = 15; vely4 = 15;
                 x5 = 200; y5 = 200; velx5 = 15; vely5 = 15;
                 xl = 0; yl = 0; widthl = 1050; heightl = 600;
                 n1 = 250; n2 = 0; n3 = 0;
                 x6 = 0; y6 = 0; width3 = widthl; height3 = heightl;
                 c1 = 250; c11 = 250; c111 = 250;
                 c2 = 250; c22 = 250; c222 = 250;
                 c3 = 250; c33 = 250; c333 = 250;
                 c4 = 250; c44 = 250; c444 = 250;
                 a = x; b = y;
                 a2 = x2; b2 = y2;
                 a3 = x3; b3 = y3;
                 a4 = x4; b4 = y4;
                 a5 = x; b5 = y5;
                 bc = 1;
                 SCORE = 0;
                 sf = false;
                 gameOver = false;
                 init1 = true;
                 init2 = true;
                 init3 = true;
                 if (HIGHSCORE > SCORE){
                 HIGHSCORE = SCORE;
                 }
                 }
                 }
                 
                */
        
            
        }
       
        };
    
    ActionListener small = new ActionListener() {

        public void actionPerformed(ActionEvent ae) {
          probx = (int) (100 * rand.nextDouble());
         probx3 = (int) (100 * rand.nextDouble());
         probx4 = (int) (100 * rand.nextDouble());
         probx5 = (int) (100 * rand.nextDouble());
            
            
            if (bc >=1) {
            a2 = x2;
               b2 = y2;
               c11 = 0;
               x2+=velx;
               y2+=vely;
            if ((x2 > (widthl - width + 5))) {
                if (probx <= 50) {
                    velx = -10;
                }else {
                    velx = -15;
                }
                if (init1) bc = 2;
            }else if ((y2 > (heightl - height + 6))) {
                vely = -vely;
            
            }else if ((x2 < 5)) {
                if (probx <= 50) {
                    velx = 10;
                }else {
                    velx = 15;
                }
                
                
            }else if ((y2 < 30)) {
                vely = -vely;
                
            }
            if ((y2 < 30 && x2 < 5) || (y2 < 30 && x2 > (widthl - width + 5)) || (y2 > (heightl - height + 6) && x2 < 5) || (y2 > (heightl - height + 6) && x2 > (widthl - width + 5))  ) {
               velx = -velx;
               vely = -vely;
            }
            if (((x2 > x) && (x2 < x + width)) && ((y2 > y) && (y2 < y + height)) ) {
                 gameOver = true;
                 gm = "GAME OVER!!!";
            }
            }
            if (bc >= 2) {
                if (init1) {x3 = widthl - width + 5; y3 = y2;}
                a3 = x3;
                b3 = y3;
                c22 = 0;
                x3+=velx3;
                y3+=vely3;
                
                init1 = false;
                if ((x3 > (widthl - width + 5))) {
                    if (probx3 <= 50) {
                        velx3 = -10;
                    }else {
                        velx3 = -15;
                    }
                    
                }else if ((y3 > (heightl - height + 6))) {
                    vely3 = -vely3;
                    
                }else if ((x3 < 5)) {
                    if (probx3 <= 50) {
                        velx3 = 10;
                    }else {
                        velx3 = 15;
                    }
                    if (init2) bc = 3;
                    
                }else if ((y3 < 30)) {
                    vely3 = -vely3;
                    
                }
                if ((y3 < 30 && x3 < 5) || (y3 < 30 && x3 > (widthl - width + 5)) || (y3 > (heightl - height + 6) && x3 < 5) || (y3 > (heightl - height + 6) && x3 > (widthl - width + 5))  ) {
                    velx3 = -velx3;
                    vely3 = -vely3;
                     }
                if (((x3 > x) && (x3 < x + width)) && ((y3 > y) && (y3 < y + height)) ) {
                    gameOver = true;
                    gm = "GAME OVER!!!";
                }
                
            }
            if (bc >=3) {
                if (init2) { x4 = 5; y4 = y3;}
                a4 = x4;
                b4 = y4;
                c33 = 0;
                x4+=velx4;
                y4+=vely4;
                
                init2 = false;
                if ((x4 > (widthl - width + 5))) {
                    if (probx4 <= 50) {
                        velx4 = -10;
                    }else {
                        velx4 = -15;
                    }
                    if (init3) bc = 4;
                }else if ((y4 > (heightl - height + 6))) {
                    vely4 = -vely4;
                    
                }else if ((x4 < 5)) {
                    if (probx4 <= 50) {
                        velx4 = 10;
                    }else {
                        velx4 = 15;
                    }
                    
                    
                }else if ((y4 < 30)) {
                    vely4 = -vely4;
                    
                }
                if ((y4 < 30 && x4 < 5) || (y4 < 30 && x4 > (widthl - width + 5)) || (y4 > (heightl - height + 6) && x4 < 5) || (y4 > (heightl - height + 6) && x4 > (widthl - width + 5))  ) {
                    velx4 = -velx4;
                    vely4 = -vely4;
                     }
                if (((x4 > x) && (x4 < x + width)) && ((y4 > y) && (y4 < y + height)) ) {
                    gameOver = true;
                    gm = "GAME OVER!!!";
                }
            }
            if (bc >=4) {
                 if (init3) { x5 = widthl - width + 5; y5 = y4;}
                a5 = x5;
                b5 = y5;
                c44 = 0;
                x5+=velx5;
                y5+=vely5;
               
                init3 = false;
                if ((x5 > (widthl - width + 5))) {
                    if (probx5 <= 50) {
                        velx5 = -10;
                    }else {
                        velx5 = -15;
                    }
                    
                }else if ((y5 > (heightl - height + 6))) {
                    vely5 = -vely5;
                    
                }else if ((x5 < 5)) {
                    if (probx5 <= 50) {
                        velx5 = 10;
                    }else {
                        velx5 = 15;
                    }
                    
                    
                }else if ((y5 < 30)) {
                    vely5 = -vely5;
                    
                }
                if ((y5 < 30 && x5 < 5) || (y5 < 30 && x5 > (widthl - width + 5)) || (y5 > (heightl - height + 6) && x5 < 5) || (y5 > (heightl - height + 6) && x5 > (widthl - width + 5))  ) {
                    velx5 = -velx5;
                   vely5 = -vely5;
                     }
                if (((x5 > x) && (x5 < x + width)) && ((y5 > y) && (y5 < y + height)) ) {
                    gameOver = true;
                    gm = "GAME OVER!!!";
                }
            }
            repaint();
            
}
    };
ActionListener one = new ActionListener() {
    
    public void actionPerformed(ActionEvent ae) {
        if (!sf) {
        g = x + (vel2*5);
        p = y - (vel2*5);
        ng = x - (vel2*5);
        np = y + (vel2*5);
        d = ae.getActionCommand();
        t = new Timer(200, two);
        t.start();
        }
        sf = true;
        //height3 = 370;
        name = "";
        Creator = "";
        msg1 = "";
        //remove(gn);
        //remove(gc);
        //remove(how);
        if (!startgame) {
        score.start();
        sb.start();
        
        }
        if (add) {
            RESTART = new JButton("Restart");
            RESTART.setBounds(0,0, 100, 25);
            contentPane.add(RESTART);
            RESTART.addActionListener(restart);
        }
        add = false;
        startgame = true;
        
    }
    
    };


    ActionListener two = new ActionListener() {
        public void actionPerformed(ActionEvent ae) {
            
             if ((d.equalsIgnoreCase("Up"))) {
                 a = x;
                 b = y;
                    y-=vel2;
                    if ((y < 30)) {
                        y = 30;
                        t.stop();
                        sf = false;
                    }else if (y < p) { y = p; t.stop(); sf = false;}
                    
             }else if (d.equalsIgnoreCase("Down")) {
            
                 a = x;
                 b = y;
                    y+=vel2;
                    if ((y > (heightl - height - 6))) {
                        y = heightl - height - 6;
                        t.stop();
                        sf = false;
                    
                 }else if (y > np) {y = np; t.stop(); sf = false;}
                    
             }else if (d.equalsIgnoreCase("Left")) {
                 
                 a = x;
                 b = y;
                    x-=vel2;
                    if ((x < 5)) {
                        x = 5;
                        t.stop();
                        sf = false;
                    }else if (x < ng) { x = ng; t.stop(); sf = false;}
                    
             }else if(d.equalsIgnoreCase("Right")) {
                
                 a = x;
                 b = y;
                    x+=vel2;
                    if ((x > (widthl - width - 6))) {
                        x = widthl - width - 6;
                        t.stop();
                        sf = false;
                    }else if (x > g) { x = g; t.stop(); sf = false;}
                    
             }
             repaint();
                    
                    
            
            
     }
        
        };

public void keyPressed(KeyEvent ke) {}

public void keyReleased(KeyEvent ke) {
 if (kc) {
     int k = ke.getKeyCode();
     switch (k) {
     case KeyEvent.VK_UP:
             Up.doClick();
             break;
     case KeyEvent.VK_DOWN:
             Down.doClick();
             break;
     case KeyEvent.VK_LEFT:
             Left.doClick();
             break;
     case KeyEvent.VK_RIGHT:
             Right.doClick();
             break;
     
     }
     repaint();
     }
}
public void keyTyped(KeyEvent ke) {
    if (kc) {
    char t = ke.getKeyChar();
    String msg = "";
    msg+=t;
    if (msg.equalsIgnoreCase("w")){
        Up.doClick();
     }else if (msg.equalsIgnoreCase("s")) {
         Down.doClick();
     }else if (msg.equalsIgnoreCase("a")) {
         Left.doClick();
     }else if(msg.equalsIgnoreCase("d")) {
         Right.doClick();
     }
     repaint();
}
}
ActionListener restart = new ActionListener(){
    public void actionPerformed(ActionEvent ae){    
sb.stop();
score.stop();

 x = 500; y = 250; width = 50; height = 50; vel2 = 20;
 x2 = 200; y2 = 200; width2 = 10; height2 = 10; velx = 15; vely = 15;
 x3 = 200; y3 = 200; velx3 = 15; vely3 = 15;
 x4 = 200; y4 = 200; velx4 = 15; vely4 = 15;
 x5 = 200; y5 = 200; velx5 = 15; vely5 = 15;
 xl = 0; yl = 0; widthl = 1070; heightl = 600;
 n1 = 250; n2 = 0; n3 = 0;
 //x6 = 0; y6 = 55; width3 = widthl; height3 = heightl;
 c1 = 250; c11 = 250; c111 = 250;
 c2 = 250; c22 = 250; c222 = 250;
 c3 = 250; c33 = 250; c333 = 250;
 c4 = 250; c44 = 250; c444 = 250;
 a = x; b = y;
 a2 = x2; b2 = y2;
 a3 = x3; b3 = y3;
 a4 = x4; b4 = y4;
 a5 = x; b5 = y5;
 bc = 1;
 SCORE = 0;
 sf = false;
 gameOver = false;
 init1 = true;
 init2 = true;
 init3 = true;
 startgame = false;
 now = false;
 name = "Press any Game_";
 Creator = "_Control if you're ready";
msg1 = "";
msg2 = "";
gm = "";
requestFocus();
setFocusable(true);
setFocusTraversalKeysEnabled(false);
repaint();
/*
contentPane.add(Up);
contentPane.add(Down);
contentPane.add(Left);
contentPane.add(Right);
contentPane.add(RESTART);
 */
    }
};
}