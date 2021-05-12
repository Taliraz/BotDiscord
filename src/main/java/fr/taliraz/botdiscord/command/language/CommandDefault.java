package fr.taliraz.botdiscord.command.language;

import fr.taliraz.botdiscord.BotDiscord;
import fr.taliraz.botdiscord.command.Command;
import fr.taliraz.botdiscord.command.SimpleCommand;
import fr.taliraz.botdiscord.util.Util;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;

import java.awt.*;
import java.util.Collection;
import java.util.Date;
import java.util.Random;

public class CommandDefault {
    private final BotDiscord botDiscord;
    private final String DEBUT_ERREUR = "Utiliser commande";

    public CommandDefault(BotDiscord botDiscord) {
        this.botDiscord = botDiscord;
    }

    public void help(User user, MessageChannel channel, JDA jda, String locale) {
        User bot = jda.getSelfUser();
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setColor(Color.WHITE);
        embedBuilder.setTitle(Util.traduction("Aide"));
        embedBuilder.setAuthor(bot.getName());
        embedBuilder.setImage(bot.getAvatarUrl());
        StringBuilder stringBuilder = new StringBuilder();
        Collection<SimpleCommand> commands = botDiscord.getCommandMap().getCommands(locale);
        stringBuilder.append(Util.traduction("Contacter le développeur")).append("\n\n");
        for (SimpleCommand command : commands) {
            if (command.getExecutorType().equals(Command.ExecutorType.USER) || command.getExecutorType().equals(Command.ExecutorType.ALL)) {
                stringBuilder.append("=").append(command.getName()).append(" ").append(command.getDescription()).append("\n");
            }
        }
        stringBuilder.append("\n").append(Util.traduction("Commande sans paramètres")).append("\n");
        stringBuilder.append(Util.traduction("Inviter le Bot")).append("\n");
        stringBuilder.append(Util.traduction("Créer Salon UpdateBot"));
        embedBuilder.appendDescription(stringBuilder.toString());
        channel.sendMessage(embedBuilder.build()).complete();
    }

    public void calculerDate(User user, MessageChannel messageChannel) {
        messageChannel.sendMessage( Util.dateFormat(new Date())).queue();

    }

    public void de(MessageChannel messageChannel, String[] args) {
        String messageErreur = Util.traduction(DEBUT_ERREUR) + "\n" + Util.traduction("Commande de");
        if (args.length == 1 && args[0].matches("\\b[0-9]*d[0-9]*\\b")) {
            Random random = new Random();
            int nbDe = Integer.parseInt(args[0].split("d")[0]);
            int valeurDe = Integer.parseInt(args[0].split("d")[1]);
            StringBuilder stringBuilder = new StringBuilder();
            int total = 0;
            for (int i = 0; i < nbDe; i++) {
                int num = random.nextInt(valeurDe) + 1;
                total += num;
                stringBuilder.append(num).append(" ");
            }
            stringBuilder.append("\n").append("Total : ").append(total);
            messageChannel.sendMessage(stringBuilder.toString()).queue();
        } else {
            messageChannel.sendMessage(messageErreur).queue();
        }
    }
}
