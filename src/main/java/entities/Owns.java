package entities;

import javax.persistence.*;
/**
 * Created by Nav on 04-10-15 17:34.
 */

@Entity
public class Owns{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_name")
    private Player player;

    @ManyToOne
    @JoinColumn(name = "name")
    private Character character;

    public Long getId() {
        return id;
    }

    public Owns setId(Long id) {
        this.id = id;
        return this;
    }

    public Player getPlayer() {
        return player;
    }

    public Owns setPlayer(Player player) {
        this.player = player;
        return this;
    }

    public Character getCharacter() {
        return character;
    }

    public Owns setCharacter(Character character) {
        this.character = character;
        return this;
    }
}
