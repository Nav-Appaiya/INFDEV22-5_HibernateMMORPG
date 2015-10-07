import entities.Player;
import entities.Server;
import entities.Stores;
import gui.LoginPanel;
import org.hibernate.Session;
import utils.HibernateUtil;

import javax.swing.*;

/**
 * Created by Nav on 04-10-15 17:27.
 */
public class App {

	public static void main(String[] args) {
		Stores stores = new Stores();
		stores.setAddress(new Server());
		stores.setPlayer(new Player());
		Session current = HibernateUtil.getSessionFactory().getCurrentSession();

		LoginPanel loginPanel = new LoginPanel();

		loginPanel.setTitle("Game Assignment 1 - INFDEV22-5");
		loginPanel.setSize(600, 400);
		loginPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loginPanel.setVisible(true);
	}
}