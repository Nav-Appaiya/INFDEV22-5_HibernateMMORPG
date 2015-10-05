package entities;

import javax.persistence.*;

/**
 * Created by Nav on 04-10-15 19:28.
 */
@Entity
public class Stores {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private String id;

    @ManyToOne
    @JoinColumn(name = "address")
    private Server address;

    @ManyToOne
    @JoinColumn(name = "user name")
    private Player player;

    public String getId() {
        return id;
    }

    public Stores setId(String id) {
        this.id = id;
        return this;
    }

    public Server getAddress() {
        return address;
    }

    public Stores setAddress(Server address) {
        this.address = address;
        return this;
    }

    public Player getPlayer() {
        return player;
    }

    public Stores setPlayer(Player player) {
        this.player = player;
        return this;
    }
}
