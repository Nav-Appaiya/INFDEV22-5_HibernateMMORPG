package gui;

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

    public JTextField usernameInput;
    public JTextField passwordInput;
    public JTextField password2Input;
    public JButton submit;

    public RegisterPanel() {
        setLayout(new FlowLayout());
        JPanel holder = new JPanel();
        usernameInput = new JTextField("", 15);
        passwordInput = new JTextField("", 15);
        password2Input = new JTextField("", 15);
        JButton submit = new JButton("Registreer");

        holder.add(new JLabel("Registreer jezelf met een unieke username en eigen gekozen wachtwoord. "));
        holder.add(new JLabel("Gebruikersnaam"));
        holder.add(usernameInput);
        holder.add(new JLabel("Wachtwoord"));
        holder.add(passwordInput);
        holder.add(new JLabel("Wachtwoord nogmaals"));
        holder.add(password2Input);
        holder.add(submit);

        holder.setPreferredSize(new Dimension(250, 300));
        this.add(holder, BorderLayout.SOUTH);
        submit.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        String username = usernameInput.getText();
        String password = passwordInput.getText();
        String password2 = password2Input.getText();
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        session.beginTransaction();
        int p;
        p = session
                .createQuery("from Player where username = :username")
                .setString("username", username)
                .executeUpdate();
        session.getTransaction().commit();
        if(p == 1) System.out.println(p);
        else System.out.println("did not find");

        showMessageDialog(null, "Gebruikersnaam al in gebruik, kies een andere gebruikersnaam. ");

        // Check password controle
        if(!password.equals(password2)){
            showMessageDialog(null, "Wachtwoord controle gefaald, controleer uw wachtwoord.");
        }











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
}
