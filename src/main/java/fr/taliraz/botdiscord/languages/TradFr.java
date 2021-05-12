package fr.taliraz.botdiscord.languages;

import java.util.ListResourceBundle;

public class TradFr extends ListResourceBundle {
    @Override
    public Object[][] getContents() {
        return contents;
    }

    static final Object[][] contents = {
            {"Aide", "Aide"},
            {"Contacter le développeur", "Pour contacter le développeur et proposer des nouvelles fonctionnalités, vous pouvez envoyer un message privé au bot."},
            {"Commande sans paramètres", "Vous pouvez utiliser une commande sans paramètres pour plus d'explications sur la commande"},
            {"Inviter le Bot", "Pour inviter le bot sur votre serveur : https://top.gg/bot/769887426930933761"},
            {"Créer Salon UpdateBot", "Vous pouvez créer un salon updatebot pour afficher les futures mises à jour."},
            {"Utiliser commande", "Pour utiliser la commande utilisez : "},
            {"Il reste", "Il reste"},
            {"jours", "jours"},
            {"heures", "heures"},
            {"de", "de"},
            {"est égal à", "est égal à"},


            //Commandes
            {
                    "Commande calculerDate",
                    "=calculerDate {date de création du compte} {date de création du domaine} (Format : jj/mm/aaaa)\n" +
                            "exemple : =calculerDate 21/04/2020 18/08/2019 (Calcule dans combien de temps vous pouvez reprendre le domaine)"
            },
            {
                    "Commande de",
                    "**=de {xxdxx} (ex : 1d6)**\n" +
                            "Entrez le nombre de dés que vous voulez lancer puis la valeur maximum.\n" +
                            "Exemple : **=de 2d6** lancera 2 dés à deux faces"
            },

            //jours
            {"Lundi", "Lundi"},
            {"Mardi", "Mardi"},
            {"Mercredi", "Mercredi"},
            {"Jeudi", "Jeudi"},
            {"Vendredi", "Vendredi"},
            {"Samedi", "Samedi"},
            {"Dimanche", "Dimanche"},

            //Mois
            {"Janvier", "Janvier"},
            {"Février", "Février"},
            {"Mars", "Mars"},
            {"Avril", "Avril"},
            {"Mai", "Mai"},
            {"Juin", "Juin"},
            {"Juillet", "Juillet"},
            {"Août", "Août"},
            {"Septembre", "Septembre"},
            {"Octobre", "Octobre"},
            {"Novembre", "Novembre"},
            {"Décembre", "Décembre"}
    };
}
