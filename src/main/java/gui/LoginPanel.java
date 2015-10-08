package gui;

import entities.Player;
import org.hibernate.Session;
import utils.HibernateUtil;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Nav on 04-10-15 18:52.
 */
public class LoginPanel extends JFrame implements ActionListener {

	private static final long serialVersionUID = -4251702363640218639L;
	private static String username;
	public static String password;
	public static JPanel loginPanel;
	public static JLabel incorrect;
	public static JPanel loginHolder;

	public JButton loginButton;
	public JButton registerButton;
	public JLabel labelUsername;
	public JLabel labelPassword;
	public JTextField inputPassword;
	public JTextField inputUsername;

	public LoginPanel() {
		createLoginPanel();
	}

	private void createLoginPanel() {
		loginHolder = new JPanel();
		loginPanel = new JPanel();
		loginButton = new JButton("Login");
		registerButton = new JButton("Registreer");
		inputUsername = new JTextField("", 15);
		labelUsername = new JLabel("Gebruiker:");
		inputPassword = new JTextField("", 15);
		labelPassword = new JLabel("Wachtwoord:");
		incorrect = new JLabel();
		registerButton.addActionListener(this);
		loginButton.addActionListener(this);
		this.setLocation(400,300);
		FlowLayout experimentLayout = new FlowLayout();
		Font bigFont = loginHolder.getFont().deriveFont(Font.PLAIN, 15f);
		inputUsername.setFont(bigFont);
		inputPassword.setFont(bigFont);
		loginPanel.setPreferredSize(new Dimension(250, 200));
		loginHolder.add(loginPanel, BorderLayout.SOUTH);
		this.add(loginHolder);
		loginPanel.add(labelUsername);
		loginPanel.add(inputUsername);
		loginPanel.add(labelPassword);
		loginPanel.add(inputPassword);
		loginPanel.add(loginButton);
		loginPanel.add(registerButton);
		loginPanel.add(incorrect);
	}

	public static void addIncorrectMessage() {
		incorrect.setText("Username of wachtwoord niet correct");
        incorrect.setForeground(Color.red);
        incorrect.setFont(new Font("Courier New", Font.ITALIC, 12));
        loginPanel.add(incorrect);
	}

	public static String getPasswInput() {
		return password;
	}
	
	public static String getUsernameInput() {
		return username;
	}

	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == loginButton) {
			username = inputUsername.getText();
			password = inputPassword.getText();
            this.login();
		}

		if (event.getSource() == registerButton) {
            new RegisterPanel();
		}
	}

	public boolean login() {
		System.out.println("LOGIN ATTEMPT");

        if (LoginPanel.getUsernameInput().isEmpty() || LoginPanel.getPasswInput().isEmpty()) {
            LoginPanel.addIncorrectMessage();
            return false;
        }

		Session sessionA = (Session) HibernateUtil.getSessionFactory()
				.getCurrentSession();
		try {
			sessionA.beginTransaction();
			Player result = (Player) HibernateUtil
					.getSessionFactory()
					.getCurrentSession()
					.createQuery(
							"from Player where username = :username and password = :password")
					.setParameter("username", LoginPanel.getUsernameInput())
					.setParameter("password", LoginPanel.getPasswInput())
                    .list().get(0);
			sessionA.getTransaction().commit();

            if(result instanceof Player){
                System.out.println("FOUND USER");
                super.dispose();
                new UserFrame(result);
            }
		} catch (IndexOutOfBoundsException e) {
			LoginPanel.addIncorrectMessage();
			sessionA.getTransaction().commit();
		}

        return true;
	}

	public static void registerUser() {
		loginPanel.add(new RegisterPanel());
	}

}