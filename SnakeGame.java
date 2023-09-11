
package snakegame;

import javax.swing.JFrame;


public class SnakeGame extends JFrame{
    
    SnakeGame()
    {
        super("Snake Game");
        add(new Board());
        pack();//to refresh frame or to reload frame when any changes happens
        
        setResizable(false);
        setSize(300,300);
        setLocationRelativeTo(null);//to take frame to center
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
    }
   
    public static void main(String[] args) {
        new SnakeGame().setVisible(true);
    }
    
}
