package gui;

/**
 * Created by Nav on 07-10-15 21:57.
 */
import entities.Player;

import javax.swing.*;
import java.awt.*;

public class UserFrame extends JFrame
{
    JPanel p1;
    Dimension d;
    public Player player;

    public UserFrame()
    {
        createAndShowGUI();
    }

    public UserFrame(Player p){
        this.player = p;
        createAndShowGUI();
    }

    private void createAndShowGUI()
    {
        setTitle("MMORPG GAME - User dashboard");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        p1=new JPanel();
        d=new Dimension(200,500);
        p1.setPreferredSize(d);
        p1.setBackground(Color.LIGHT_GRAY);
        p1.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2, true));
        p1.add(new JLabel("Welkom "+this.player.getFirstname() + " " + this.player.getLastname()));
        p1.add(new JLabel("Je balans is "+this.player.getBalance()));
        p1.add(new JLabel("Je hebt nog "+this.player.getCharacterslots()+" slots"));
        add(p1);
        setSize(200, 400);
        setVisible(true);
        pack();
    }

    public static void runner()
    {
        new UserFrame();
    }
}