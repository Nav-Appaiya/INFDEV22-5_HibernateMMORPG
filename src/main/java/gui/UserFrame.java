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

public class UserFrame extends JFrame implements ActionListener
{
    JPanel p1;
    Dimension d;
    public Player player;
    public JButton characterButton;
    public JButton buySlotsButton;

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
        characterButton = new JButton("Characters management");
        buySlotsButton = new JButton("Extra slots kopen");
        characterButton.addActionListener(this);
        buySlotsButton.addActionListener(this);
        p1.add(characterButton);
        p1.add(buySlotsButton);
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

    public void actionPerformed(ActionEvent event) {

        if (event.getSource() == characterButton) {
            new CharactersPanel(this.player);
        }
        if(event.getSource() == buySlotsButton ){
            boolean canbuy = true;
            int playerBalance = Integer.parseInt(player.getBalance());
            int maxSlotsForPlayer = (int) playerBalance / 250;

            String slotInputDialog = JOptionPane.showInputDialog(null, "1 Slot kost $250. Je kan maximaal nog " +
                    maxSlotsForPlayer +
                    " slots kopen. Hoeveel slots wil je kopen?");

            if (slotInputDialog != null && !"".equals(slotInputDialog)) {

                int slotInput = Integer.parseInt(slotInputDialog);

                if (slotInput > maxSlotsForPlayer) {
                    JOptionPane.showMessageDialog(null, "Zoveel slots kun je niet kopen. ");
                    canbuy = false;
                }

                if (canbuy == true){
                    playerBalance = playerBalance - (slotInput*250);
                    String finalBalance = playerBalance + "";
                    Session s = HibernateUtil.getSessionFactory().getCurrentSession();
                    s.beginTransaction();
                    player.setBalance(finalBalance);
                    int f = Integer.parseInt(player.getCharacterslots());
                    f = f+slotInput;
                    player.setCharacterslots(f+"");
                    s.saveOrUpdate(player);
                    s.getTransaction().commit();
                    p1.setVisible(false);
                    createAndShowGUI();

                    String slotsSlot = "slots";
                    if (slotInput == 1) {
                        slotsSlot = "slot";
                    }

                    JOptionPane.showMessageDialog(null, "Cool! Je hebt "+slotInput+" "+slotsSlot+" gekocht. Je hebt nu in totaal "+player.getCharacterslots()+" slots");
                    System.out.println("User new slot level = " + player.getCharacterslots());
                }
            }
        }
    }

    public static void runner()
    {
        new UserFrame();
    }
}