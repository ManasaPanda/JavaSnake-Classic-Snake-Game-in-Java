
package snakegame;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Board extends JPanel implements ActionListener{
    
    private Image apple;
    private Image dot;
    private Image head;
    
    private final int ALL_DOTS=900;
    private final int DOT_SIZE=10;
    private final int RANDOM_POSITION=30;
    
    private  int apple_x;
    private int apple_y;
    
    private final int x[]=new int[ALL_DOTS];
    private final int y[]=new int[ALL_DOTS];
    
    private boolean inGame=true;
    
    private boolean left=false;
    private boolean right=true;
    private boolean up=false;
    private boolean down=false;
    
    private int dots;
     private Timer timer;
    Board()
    {
        addKeyListener(new TAdapter());
        
        setBackground(Color.BLACK);
        setFocusable(true);//when frame open automatically focus will come their or game automatically start
        
        loadImages();
        initGame();
    }

    public void initGame() {
        dots=3;
        
        for(int i=0;i<dots;i++)
        {
            y[i]=50;
            x[i]=50 - i * DOT_SIZE;
        }
        
        locateApple();///to fix apple in a random position
        
         timer=new Timer(140,this);//to increase snake with time
        timer.start();
    }
    
    public void locateApple()
    {
        int r=(int)(Math.random()*RANDOM_POSITION);
        apple_x=r*DOT_SIZE;//to placed apple in postion of x axis
        
        r=(int)Math.random()*RANDOM_POSITION;
        apple_y=r*DOT_SIZE;
    }

    public void loadImages() {
       ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("snakegame/icons/apple.png"));//to get  an image from file.
       apple=i1.getImage();
       
       
       ImageIcon i2=new ImageIcon(ClassLoader.getSystemResource("snakegame/icons/dot.png"));
       dot=i2.getImage();
       
      ImageIcon i3=new ImageIcon(ClassLoader.getSystemResource("snakegame/icons/head.png"));
      head=i3.getImage();
    }
    
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);//to paint/draw images in frame
        
        draw(g);
    }

    public void draw(Graphics g) {
        if(inGame)
        {
        g.drawImage(apple, apple_x, apple_y, this);
        
        for(int i=0;i<dots;i++)
        {
            if(i == 0)
                g.drawImage(head, x[i], y[i], this);
            else
                g.drawImage(dot, x[i], y[i], this);
        }
        Toolkit.getDefaultToolkit().sync();//to innitialized in a sync (one after another)
        }
        else
        {
            gameOver(g);
            }
    }
    public void gameOver(Graphics g)
    {
        String msg="Game Over";
        Font font= new Font("SAN_SERIF",Font.BOLD,14);
        FontMetrics metrices= getFontMetrics(font);
        
        g.setColor(Color.red);
        g.setFont(font);
        g.drawString(msg, (300- metrices.stringWidth(msg))/2, 300/2);
    }
    
    public void move()
    {
        for(int i=dots ;i>0;i--)
        {
            x[i]=x[i-1];
            y[i]=y[i-1];
        }
        if(left)
        {
            x[0]=x[0] - DOT_SIZE;//if snake is moving left then substract one one dotsize
        }
        if(right)
        {
            x[0]=x[0] + DOT_SIZE;//if snake is moving left then substract one one dotsize
        }
        if(up)
        {
            y[0]=y[0] - DOT_SIZE;//if snake is moving left then substract one one dotsize
        }
        if(down)
        {
            y[0]=y[0] + DOT_SIZE;//if snake is moving left then substract one one dotsize
        }
        
    }
    
    public void checkApple()//if snake taken apple or not
    {
        if((x[0] == apple_x) && (y[0] == apple_y)){
        //if cordinates are equal then snake got apple
            dots++;
        locateApple();//to generate next apple
        }
    }
    
    public void checkCollision()
    {
      for(int z=dots;z>0;z--)
      {
          if((z > 4) && (x[0] == x[z]) && (y[0] == y[z]))
          {
              inGame=false;
          }
      }
      if(y[0] >= 300)
      { 
       inGame=false;
      }
      if(x[0] >= 300)
      { 
       inGame=false;
      }
      if(y[0] < 0)
      { 
       inGame=false;
      }
      if(x[0] < 0)
      { 
       inGame=false;
      }
      if(!inGame)
      {
          timer.stop();
      }
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(inGame)
        {
        checkApple();
        
        checkCollision();
        
        move();
        }
        repaint();
    }
    public class TAdapter extends KeyAdapter{
      @Override
      public void keyPressed(KeyEvent e)
      {
          int key= e.getKeyCode();
          
          if(key == KeyEvent.VK_LEFT && (!right))//if left arrow pressed
           {
               left=true;
               up=false;
               down=false;
           }
           if(key == KeyEvent.VK_RIGHT && (!left))//if right arrow pressed
           {
               right=true;
               up=false;
               down=false;
           }
           if(key == KeyEvent.VK_UP && (!down))//if up arrow pressed
           {
               up=true;
               left=false;
               right=false;
           }
           if(key == KeyEvent.VK_DOWN && (!up))//if down arrow pressed
           {
               down=true;
               left=false;
               right=false;
           }
           if(key == KeyEvent.VK_ENTER)
           {
               System.exit(0);
           }
           if(key == KeyEvent.VK_SPACE)
           {
                new SnakeGame().setVisible(true);
           }
      }
    }
}







