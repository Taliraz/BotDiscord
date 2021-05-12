package fr.taliraz.botdiscord.model;

import javax.persistence.*;

@Entity
@Table
public class Exemple {
    @Id()
    @GeneratedValue
    private int id;

    @Column
    private String test;

    public Exemple(String test) {
        this.test = test;
    }

    public Exemple() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }
}
