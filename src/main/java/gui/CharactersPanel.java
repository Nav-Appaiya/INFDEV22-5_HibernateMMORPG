package gui;

import entities.*;
import entities.Character;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import utils.HibernateUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

import static javax.swing.JOptionPane.showMessageDialog;
/**
 * Created by tymofiymishutin on 08/10/15.
 */
public class CharactersPanel extends JFrame implements ActionListener {

    private static JButton newCharacterButton;
    private static JButton viewCharacterButton;

    public Player player;

    public CharactersPanel(Player p) {
        this.player = p;
        initialize();
    }


    public void initialize() {

        JFrame frame = new JFrame();
        frame.setBounds(100, 100, 565, 456);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.getContentPane().setLayout(null);


        Set<entities.Character> characters = this.player.getCharacters();
        int countCharacters = characters.size();

        if (countCharacters > 0) {
            JComboBox characterList = new JComboBox<String>();
            for (entities.Character character : characters) {
                characterList.addItem(character.getName());
            }
            characterList.setBounds(10, 10, 200, 40);
            frame.getContentPane().add(characterList);
            frame.setVisible(true);
            characterList.addActionListener(this);

            viewCharacterButton = new JButton("View this character");
            viewCharacterButton.setBounds(10, 40, 200, 60);
            frame.getContentPane().add(viewCharacterButton);
            frame.setVisible(true);
            viewCharacterButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {

                    String selectedCharacter = (String)characterList.getSelectedItem();

                    Session sessionA = (Session) HibernateUtil.getSessionFactory()
                            .getCurrentSession();

                    sessionA.beginTransaction();
                    Character character = (Character) HibernateUtil
                            .getSessionFactory()
                            .getCurrentSession()
                            .createQuery(
                                    "from Character where name = :name")
                            .setParameter("name", selectedCharacter)
                            .list().get(0);

                    sessionA.getTransaction().commit();

                    JFrame frame = new JFrame();
                    frame.setBounds(100, 100, 565, 456);
                    frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                    frame.getContentPane().setLayout(null);

                    JLabel namelbl = new JLabel("Name");
                    namelbl.setBounds(10, 10, 417, 27);
                    frame.getContentPane().add(namelbl);

                    JLabel charClasslbl = new JLabel("Class");
                    charClasslbl.setBounds(10, 50, 417, 27);
                    frame.getContentPane().add(charClasslbl);

                    JLabel racelbl = new JLabel("Race");
                    racelbl.setBounds(10, 90, 76, 33);
                    frame.getContentPane().add(racelbl);

                    JLabel levelbl = new JLabel("Level");
                    levelbl.setBounds(10, 140, 76, 33);
                    frame.getContentPane().add(levelbl);

                    final JTextField name = new JTextField();
                    name.setBackground(SystemColor.menu);
                    name.setBounds(122, 10, 417, 27);
                    name.setColumns(10);
                    name.setEnabled(false);
                    name.setEditable(false);
                    name.setText(character.getName());
                    frame.getContentPane().add(name);

                    final JTextField charClass = new JTextField();
                    charClass.setColumns(10);
                    charClass.setEnabled(false);
                    charClass.setEditable(false);
                    charClass.setBounds(122, 50, 417, 27);
                    charClass.setText(character.getCharclass());
                    frame.getContentPane().add(charClass);

                    final JTextField race = new JTextField();
                    race.setBackground(SystemColor.menu);
                    race.setColumns(10);
                    race.setEnabled(false);
                    race.setEditable(false);
                    race.setBounds(122, 90, 417, 27);
                    race.setText(character.getRace());
                    frame.getContentPane().add(race);

                    final JTextField level = new JTextField();
                    level.setBackground(SystemColor.menu);
                    level.setColumns(10);
                    level.setEnabled(false);
                    level.setEditable(false);
                    level.setBounds(122, 130, 417, 27);
                    level.setText("" + character.getLevel() + "");
                    frame.getContentPane().add(level);

                    frame.setVisible(true);
                }
            });
        }

        int avilibSlots = Integer.parseInt(this.player.getCharacterslots());
        if (avilibSlots == 0) {
            newCharacterButton = new JButton("Create new character");
            newCharacterButton.setBounds(250, 40, 200, 60);
            frame.getContentPane().add(newCharacterButton);
            frame.setVisible(true);
            newCharacterButton.addActionListener(this);
        }

    }


    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == newCharacterButton) {
            JFrame frame = new JFrame();
            frame.setBounds(100, 100, 565, 456);
            frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            frame.getContentPane().setLayout(null);

            JLabel namelbl = new JLabel("Name");
            namelbl.setBounds(10, 10, 417, 27);
            frame.getContentPane().add(namelbl);

            JLabel charClasslbl = new JLabel("Class");
            charClasslbl.setBounds(10, 50, 417, 27);
            frame.getContentPane().add(charClasslbl);

            JLabel racelbl = new JLabel("Race");
            racelbl.setBounds(10, 90, 76, 33);
            frame.getContentPane().add(racelbl);

            final JTextField name = new JTextField();
            name.setBackground(SystemColor.menu);
            name.setBounds(122, 10, 417, 27);
            name.setColumns(10);
            frame.getContentPane().add(name);

            final JTextField charClass = new JTextField();
            charClass.setColumns(10);
            charClass.setBounds(122, 50, 417, 27);
            frame.getContentPane().add(charClass);
            final JTextField race = new JTextField();
            race.setBackground(SystemColor.menu);
            race.setColumns(10);
            race.setBounds(122, 90, 417, 27);
            frame.getContentPane().add(race);

            JButton saveNewCharacterButton = new JButton("Save character");
            saveNewCharacterButton.setBounds(10, 361, 118, 46);
            frame.getContentPane().add(saveNewCharacterButton);
            frame.setVisible(true);
            saveNewCharacterButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    String uName = name.getText();

                    if(!uName.isEmpty()){
                        Session registreerSessie = HibernateUtil.getSessionFactory()
                                .getCurrentSession();
                        registreerSessie.beginTransaction();

                        boolean exists = registreerSessie
                                .createQuery("from Character where name = :name")
                                .setParameter("name", uName)
                                .setMaxResults(1)
                                .uniqueResult() != null;

                        if (exists == true) {
                            showMessageDialog(null, "Naam al in gebruik, kies een andere.");
                            registreerSessie.getTransaction().rollback();
                            frame.setVisible(true);
                        } else {
                            Character character = new Character();
                            character.setName(uName);
                            character.setCharclass(charClass.getText());
                            character.setRace(race.getText());
                            character.setLevel(0);
                            character.setPlayer(player);

                            registreerSessie.save(character);
                            registreerSessie.getTransaction().commit();

                            int avilibSlots = Integer.parseInt(player.getCharacterslots());
                            player.setCharacterslots(""+(avilibSlots-1)+"");
                            showMessageDialog(null, "Character " + uName + " is aangemaakt.");
                            frame.dispose();
                        }




                    }

                }
            });

        }

    }

}
