package entities;

import org.hibernate.Session;
import utils.HibernateUtil;
import utils.Helpers;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Nav on 04-10-15 18:11.
 */
@Entity
public class Player {
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String username;
    private String balance;
    private String firstname;
    private String lastname; 
    private String iban; 
    private String characterslots; 
    private String lastpayment; 
    private String monthspayed; 
    // @Column(unique = true)
    private String password; 
    private Boolean banned;
    
    @OneToMany(mappedBy="player", cascade = CascadeType.ALL) 
    private Set<Character> characters = new HashSet<Character>();
    
    @OneToMany(mappedBy="player", cascade = CascadeType.ALL) 
    private Set<Server> servers = new HashSet<Server>();

    
    public Player() {}

    public Player(String username, String balance, String firstname, String lastname, 
    String iban, String characterslots, String lastpayment, String monthspayed, 
    String password, Boolean banned) {
        this();
        this.username = username;
        this.balance = balance;
        this.firstname = firstname;
        this.lastname = lastname;
        this.iban = iban;
        this.characterslots = characterslots;
        this.lastpayment = lastpayment;
        this.monthspayed = monthspayed;
        this.password = password;
        this.banned = banned;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    
    public Set<Character> getCharacters()  
    {  
        return characters;  
    }  
    public void setCharacters(Set<Character> characters)  
    {  
        this.characters = characters;  
    }  
    

    
    public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public String getCharacterslots() {
		return characterslots;
	}

	public void setCharacterslots(String characterslots) {
		this.characterslots = characterslots;
	}

	public String getLastpayment() {
		return lastpayment;
	}

	public void setLastpayment(String lastpayment) {
		this.lastpayment = lastpayment;
	}

	public String getMonthspayed() {
		return monthspayed;
	}

	public void setMonthspayed(String monthspayed) {
		this.monthspayed = monthspayed;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getBanned() {
		return banned;
	}

	public void setBanned(Boolean banned) {
		this.banned = banned;
	}

	@Override
	public String toString() {
		return "Player [id=" + id + ", username=" + username + ", balance="
				+ balance + ", firstname=" + firstname + ", lastname="
				+ lastname + ", iban=" + iban + ", characterslots="
				+ characterslots + ", lastpayment=" + lastpayment
				+ ", monthspayed=" + monthspayed + ", password=" + password
				+ ", banned=" + banned + ", characters=" + characters
				+ ", servers=" + servers + "]";
	}

    public void generateData(int countRows)
    {
        Session session = (Session) HibernateUtil.getSessionFactory().getCurrentSession();

        int i = 0; // counter
        Helpers helpers = new Helpers();

        session.beginTransaction();
        for (i = 0; i < countRows; i++) {

            Player user = new Player();
            user.setBalance("0");
            user.setBanned(false);
            user.setCharacterslots("5");
            user.setFirstname(helpers.generateRandom(30, false));
            user.setLastname(helpers.generateRandom(20, false));
            user.setIban(helpers.generateRandom(35, true));
            user.setUsername(helpers.generateRandom(15, true));
            user.setPassword(helpers.generateRandom(24, true));

            Set<Character> userCharacters = new HashSet<Character>();

            // two characters per user
            Character character1 = new Character(
                    helpers.generateRandom(15, false),
                    helpers.generateRandom(20, false),
                    helpers.generateRandom(20, false),
                    0
            );
            character1.setPlayer(user);
            userCharacters.add(character1);

            Character character2 = new Character(
                    helpers.generateRandom(15, false),
                    helpers.generateRandom(20, false),
                    helpers.generateRandom(20, false),
                    0
            );
            character2.setPlayer(user);
            userCharacters.add(character2);

            user.setCharacters(userCharacters);

            // save
            session.save(character1);
            session.save(character2);
            session.save(user);
        }

        session.getTransaction().commit();
        System.out.println("Inserted "+countRows+" random rows to players database.");
    }

      
}

