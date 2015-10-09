package gui;

import entities.Player;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import utils.HibernateUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.JOptionPane.showMessageDialog;

/**
 * Created by Nav on 06-10-15 21:05.
 */
class RegisterPanel extends JPanel implements ActionListener {
    private static SessionFactory factory;

    public JTextField firstnameInput;
    public JTextField lastnameInput;
    public JTextField ibanInput;
    public JTextField usernameInput;
    public JTextField passwordInput;
    public JTextField password2Input;
    public JButton submit;

    public JFrame frame;
    public JTextField nameField;
    public JTextField lastNameField;
    public JComboBox sexSelect;
    public 	String[] sexSelectText = {	"male", "Female"};


    public RegisterPanel() {
        initialize();
//
//        setLayout(new FlowLayout());
//        this.setPreferredSize(new Dimension(300,400));
//        setBounds(0,0,300,400);
//        firstnameInput = new JTextField("", 15);
//        lastnameInput = new JTextField("", 15);
//        ibanInput = new JTextField("", 15);
//        Font font = new Font("Courier", Font.BOLD,12);
//        usernameInput = new JTextField("", 15);
//        passwordInput = new JTextField("", 15);
//        password2Input = new JTextField("", 15);
//        JButton submit = new JButton("Registreer");
//        JLabel personalLabel = new JLabel("<html>Persoonlijke gegevens<br>" + "Vul je persoonlijke gegevens in. <br></html>");
//        JLabel accountLabel = new JLabel("<html>Account gegevens<br>" + "Registreer jezelf met een unieke username en eigen gekozen wachtwoord. <br></html>");
//
//        add(personalLabel);
//
//        add(new JLabel("Voornaam"));
//        add(firstnameInput);
//        add(new JLabel("Achternaam"));
//        add(lastnameInput);
//        add(new JLabel("Iban nummer"));
//        add(ibanInput);
//
//        add(accountLabel);
////        add(new JLabel("Account gegevens \n\n"));
//        add(new JLabel("Gebruikersnaam"));
//        add(usernameInput);
//        add(new JLabel("Wachtwoord"));
//        add(passwordInput);
//        add(new JLabel("Wachtwoord nogmaals"));
//        add(password2Input);
//        add(submit);
//        submit.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        String username = usernameInput.getText();
        String password = passwordInput.getText();
        String password2 = password2Input.getText();
        String firstname = firstnameInput.getText();
        String lastname = lastnameInput.getText();
        String iban = ibanInput.getText();
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        if (username.isEmpty()) {
            showMessageDialog(null, "Gebruikersnaam is niet ingevuld! ");
        }
        else if (password.isEmpty()) {
            showMessageDialog(null, "Wachtwoord is niet ingevuld! ");
        }
        else if (password2.isEmpty()) {
            showMessageDialog(null, "Controle Wachtwoord is niet ingevuld! ");
        }
//
//        session.beginTransaction();
//        int p;
//        p = session
//                .createQuery("from Player where username = :username")
//                .setString("username", username)
//                .executeUpdate();
//        session.getTransaction().commit();
//        if(p == 1) System.out.println(p);
//        else System.out.println("did not find");
//
//        showMessageDialog(null, "Gebruikersnaam al in gebruik, kies een andere gebruikersnaam. ");
//
//        // Check password controle
//        if(!password.equals(password2)){
//            showMessageDialog(null, "Wachtwoord controle gefaald, controleer uw wachtwoord.");
//        }











//        this.hide();
    }

    public JTextField getUsernameInput() {
        return usernameInput;
    }

    public RegisterPanel setUsernameInput(JTextField usernameInput) {
        this.usernameInput = usernameInput;
        return this;
    }

    public JTextField getPasswordInput() {
        return passwordInput;
    }

    public RegisterPanel setPasswordInput(JTextField passwordInput) {
        this.passwordInput = passwordInput;
        return this;
    }

    public JButton getSubmit() {
        return submit;
    }

    public RegisterPanel setSubmit(JButton submit) {
        this.submit = submit;
        return this;
    }

    public void initialize(){
        final JFrame frame = new JFrame();
        frame.setBounds(100, 100, 565, 456);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblName = new JLabel("Voornaam");
        lblName.setBounds(10, 10, 417, 27);
        frame.getContentPane().add(lblName);

        JLabel label = new JLabel("Achternaam");
        label.setBounds(10, 50, 417, 27);
        frame.getContentPane().add(label);

        JLabel iban = new JLabel("Iban");
        iban.setBounds(10, 90, 76, 33);
        frame.getContentPane().add(iban);

        final JLabel label_1 = new JLabel("ACCOUNT GEGEVENS");
        label_1.setBounds(10, 130, 300, 33);
        frame.getContentPane().add(label_1);

        final JTextField firstnameField = new JTextField();
        firstnameField.setBackground(SystemColor.menu);
        firstnameField.setBounds(122, 10, 417, 27);
        firstnameField.setColumns(10);
        frame.getContentPane().add(firstnameField);

        final JTextField lastNameField = new JTextField();
        lastNameField.setColumns(10);
        lastNameField.setBounds(122, 50, 417, 27);
        frame.getContentPane().add(lastNameField);
        final JTextField ibanField = new JTextField();
        ibanField.setBackground(SystemColor.menu);
        ibanField.setColumns(10);
        ibanField.setBounds(122, 90, 417, 27);
        frame.getContentPane().add(ibanField);

        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setBounds(10, 175, 100, 33);
        frame.getContentPane().add(usernameLabel);
        final JTextField usernameField = new JTextField();
        usernameField.setColumns(10);
        usernameField.setBounds(122, 175, 417, 27);
        frame.getContentPane().add(usernameField);

        JLabel passwordLabel = new JLabel("Wachtwoord");
        passwordLabel.setBounds(10, 225, 100, 33);
        frame.getContentPane().add(passwordLabel);
        final JTextField passwordField = new JTextField();
        passwordField.setColumns(10);
        passwordField.setBounds(122, 225, 417, 27);
        frame.getContentPane().add(passwordField);

        final JButton submitButton = new JButton("Registreer");
        submitButton.setBounds(10, 361, 118, 46);
        frame.getContentPane().add(submitButton);

        frame.setVisible(true);
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                String u = usernameField.getText();
                String p = passwordField.getText();

                if(!u.isEmpty() && !p.isEmpty()){
                    Session registreerSessie = HibernateUtil.getSessionFactory()
                            .getCurrentSession();
                    registreerSessie.beginTransaction();

                    boolean exists = registreerSessie
                                        .createQuery("from Player where username = :username")
                                        .setParameter("username", u)
                                        .setMaxResults(1)
                                        .uniqueResult() != null;

                    if (exists == true) {
                        showMessageDialog(null, "Gebruikersnaam al in gebruik, kies een andere.");
                        registreerSessie.getTransaction().rollback();
                        frame.setVisible(true);
                    } else {
                        Player user = new Player();
                        user.setBalance("0");
                        user.setBanned(false);
                        user.setCharacterslots("5");
                        user.setFirstname(firstnameField.getText());
                        user.setLastname(lastNameField.getText());
                        user.setIban(ibanField.getText());
                        user.setUsername(usernameField.getText());
                        user.setPassword(passwordField.getText());
                        registreerSessie.save(user);
                        registreerSessie.getTransaction().commit();
                        showMessageDialog(null, "Gebruiker " + usernameField.getText() + " is aangemaakt. Login met je wachtwoord: " + passwordField.getText());
                        frame.dispose();
                    }




                }

            }
        });
    }

}
