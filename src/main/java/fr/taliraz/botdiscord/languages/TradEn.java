package fr.taliraz.botdiscord.languages;

import java.util.ListResourceBundle;

public class TradEn extends ListResourceBundle {
    @Override
    public Object[][] getContents() {
        return contents;
    }

    static final Object[][] contents = {

            {"Aide", "Help"},
            {"Contacter le développeur", "To contact the developer and propose new features, you can send a private message to the bot."},
            {"Commande sans paramètres", "You can use a command without parameters for further explanation of the command"},
            {"Inviter le Bot", "To invite the bot on your server: https://top.gg/bot/769887426930933761"},
            {"Créer Salon UpdateBot", "You can create a channel updatebot to display future updates."},
            {"Utiliser commande", "To use the command use:"},
            {"jours", "days remaining"},
            {"heures", "hours"},


            //Commandes
            {
                    "Commande calculerDate",
                    "=calculateDate {date of creation of the account} {date of creation of the domain} (Format: dd/mm/yyyy)\n" +
                            "example: =calculateDate 21/04/2020 18/08/2019 (Calculates how long it needs before you can take over the domain)"
            },
            {
                    "Commande de",
                    "**=dice {xxdxx} (ex : 1d6)**\n" +
                            "Enter the number of dice you want to roll and then the maximum value.\n" +
                            "Example : **=dice 2d6** will roll 2 6-sided dice"
            },

            //jours
            {"Lundi", "Monday"},
            {"Mardi", "Tuesday"},
            {"Mercredi", "Wednesday"},
            {"Jeudi", "Thursday"},
            {"Vendredi", "Friday"},
            {"Samedi", "Saturday"},
            {"Dimanche", "Sunday"},

            //Mois
            {"Janvier", "January"},
            {"Février", "February"},
            {"Mars", "March"},
            {"Avril", "April"},
            {"Mai", "May"},
            {"Juin", "June"},
            {"Juillet", "July"},
            {"Août", "August"},
            {"Septembre", "September"},
            {"Octobre", "October"},
            {"Novembre", "November"},
            {"Décembre", "December"}
    };
}
