package fr.taliraz.botdiscord.util;

import fr.taliraz.botdiscord.BotDiscord;
import fr.taliraz.botdiscord.languages.TradFr;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

public class Util {
    private static BotDiscord botDiscord;
    private static Properties properties;
    private static EntityManagerFactory entityManagerFactory;
    private static EntityManager entityManager;

    public static void init(BotDiscord botDiscord) throws IOException {
        botDiscord = botDiscord;
        properties = new Properties();
        properties.load(botDiscord.getClass().getClassLoader().getResourceAsStream("project.properties"));
        entityManagerFactory = Persistence.createEntityManagerFactory(Util.getProperties().getProperty("unit"));
        entityManager = entityManagerFactory.createEntityManager();
    }

    public static String traduction(String key) {
        return botDiscord.getLanguage().getString(key);
    }

    public static Date convertStringToDate(String dateString) {
        String[] dateTab = dateString.split("/");
        Date date = new Date(dateTab[1] + "/" + dateTab[0] + "/" + dateTab[2]);
        return date;
    }

    public static String dateFormat(Date date) {
        String[] jours = {"Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi", "Dimanche", "Lundi"};
        String[] mois = {"Janvier", "Février", "Mars", "Avril", "Mai", "Juin", "Juillet", "Août", "Septembre", "Octobre", "Novembre", "Décembre"};
        if (botDiscord.getLanguage() instanceof TradFr) {
            return traduction(jours[date.getDay()]) + " " + date.getDate() + " " + traduction(mois[date.getMonth()]) + " " + (date.getYear() + 1900);
        } else {
            return traduction(jours[date.getDay()]) + ", " + traduction(mois[date.getMonth()]) + " " + date.getDate() + "th " + (date.getYear() + 1900);
        }
    }

    public static Properties getProperties(){
        return properties;
    }

    public static EntityManager getEntityManager() {
        return entityManager;
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }
}
