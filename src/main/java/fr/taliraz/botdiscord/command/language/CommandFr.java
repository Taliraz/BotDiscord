package fr.taliraz.botdiscord.command.language;

import fr.taliraz.botdiscord.BotDiscord;
import fr.taliraz.botdiscord.command.Command;
import fr.taliraz.botdiscord.command.Command.ExecutorType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;

public class CommandFr {

    private final BotDiscord botDiscord;
    private final CommandDefault commandDefault;

    public CommandFr(BotDiscord botDiscord) {
        this.commandDefault = new CommandDefault(botDiscord);
        this.botDiscord = botDiscord;
    }

    @Command(name = "stop", type = ExecutorType.CONSOLE)
    private void stop() {
        botDiscord.setRunning(false);
    }

    @Command(name = "aide", type = ExecutorType.USER, description = "")
    private void help(User user, MessageChannel channel, JDA jda) {
        commandDefault.help(user, channel, jda, "fr");
    }

    @Command(name = "calculerDate", type = ExecutorType.USER, description = "")
    private void calculerDate(User user, MessageChannel messageChannel) {
        commandDefault.calculerDate(user, messageChannel);
    }

    @Command(name = "de", type = ExecutorType.USER, description = "{xxdxx} (ex : 1d6)")
    private void de(MessageChannel messageChannel, String[] args) {
        commandDefault.de(messageChannel,args);
    }
}
