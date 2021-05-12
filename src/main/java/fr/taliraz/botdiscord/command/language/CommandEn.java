package fr.taliraz.botdiscord.command.language;

import fr.taliraz.botdiscord.BotDiscord;
import fr.taliraz.botdiscord.command.Command;
import fr.taliraz.botdiscord.command.Command.ExecutorType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;

public class CommandEn {

    private final BotDiscord botDiscord;
    private final CommandDefault commandDefault;

    public CommandEn(BotDiscord botDiscord) {
        this.commandDefault = new CommandDefault(botDiscord);
        this.botDiscord = botDiscord;
    }

    @Command(name = "help", type = ExecutorType.USER, description = "", locale = "en")
    private void help(User user, MessageChannel channel, JDA jda) {
        commandDefault.help(user, channel, jda, "en");
    }

    @Command(name = "calculateDate", type = ExecutorType.USER, description = "", locale = "en")
    private void calculerDate(User user, MessageChannel messageChannel) {
        commandDefault.calculerDate(user, messageChannel);
    }

    @Command(name = "dice", type = ExecutorType.USER, description = "{xxdxx} (ex : 1d6)", locale = "en")
    private void de(MessageChannel messageChannel, String[] args) {
        commandDefault.de(messageChannel,args);
    }
}
