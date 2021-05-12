package fr.taliraz.botdiscord.service;

import fr.taliraz.botdiscord.model.Exemple;
import fr.taliraz.botdiscord.util.Util;

import javax.persistence.RollbackException;
import java.util.List;

public class ExempleService {

    public Exemple findByNom(String nom) {
        return Util.getEntityManager().find(Exemple.class, nom);
    }

    public void save(Exemple exemple) {
        try{
        Util.getEntityManager().getTransaction().begin();
        Util.getEntityManager().persist(exemple);
        Util.getEntityManager().getTransaction().commit();
        } catch (RollbackException e){
            e.printStackTrace();
        }
    }

    public List<Exemple> getAll() {
        return Util.getEntityManager().createQuery("SELECT a FROM Exemple a", Exemple.class).getResultList();
    }
}
