package gui;

/**
 * Created by Nav on 07-10-15 21:57.
 */
import entities.Player;
import org.hibernate.Session;
import utils.HibernateUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        JButton addFiveButton = new JButton("Add 5");
        JButton addTenButton = new JButton("Add 10");
        JButton addFiftyButton = new JButton("Add 50");
        p1.setPreferredSize(d);
        p1.setBackground(Color.LIGHT_GRAY);
        p1.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2, true));
        p1.add(new JLabel("Welkom " + this.player.getFirstname() + " " + this.player.getLastname()));
        p1.add(new JLabel("Je balans is $" + this.player.getBalance()));
        p1.add(new JLabel("Je hebt nog " + this.player.getCharacterslots() + "x slots"));
        p1.add(addFiveButton);
        p1.add(addTenButton);
        p1.add(addFiftyButton);

        add(p1);
        setSize(200, 400);
        setVisible(true);
        pack();


        addFiveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int s = Integer.parseInt(player.getBalance());
                s = s + 5;
                String b = Integer.toString(s);
                player.setBalance(b);
                System.out.println("Setting player balance to $" + player.getBalance());
                Session session = HibernateUtil.getSessionFactory().getCurrentSession();
                session.beginTransaction();
                session.saveOrUpdate(player);
                session.getTransaction().commit();
                System.out.println("Persisted player with new balance: $" + player.getBalance());
                JOptionPane.showMessageDialog(null, "Updated player with new balance: $" + player.getBalance(), "Money Transfer", 1);
                p1.setVisible(false);
                createAndShowGUI();
            }
        });
        addFiftyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int s = Integer.parseInt(player.getBalance());
                s = s + 50;
                String b = Integer.toString(s);
                player.setBalance(b);
                System.out.println("Setting player balance to" + player.getBalance());
                Session session = HibernateUtil.getSessionFactory().getCurrentSession();
                session.beginTransaction();
                session.saveOrUpdate(player);
                session.getTransaction().commit();
                System.out.println("Persisted player with new balance:" + player.getBalance());
                JOptionPane.showMessageDialog(null, "Updated player with new balance: $" + player.getBalance(), "Money Transfer", 1);
                p1.setVisible(false);
                createAndShowGUI();
            }
        });
        addTenButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int s = Integer.parseInt(player.getBalance());
                s = s + 10;
                String b = Integer.toString(s);
                player.setBalance(b);
                System.out.println("Setting player balance to $" + player.getBalance());
                Session session = HibernateUtil.getSessionFactory().getCurrentSession();
                session.beginTransaction();
                session.saveOrUpdate(player);
                session.getTransaction().commit();
                System.out.println("Persisted player with new balance:" + player.getBalance());
                JOptionPane.showMessageDialog(null, "Updated player with new balance:" + player.getBalance(), "Money Transfer", 1);
                p1.setVisible(false);
                createAndShowGUI();
            }
        });
    }

    public static void runner()
    {
        new UserFrame();
    }
}