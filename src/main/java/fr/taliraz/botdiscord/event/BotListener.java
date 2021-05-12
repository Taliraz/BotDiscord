package fr.taliraz.botdiscord.event;

import fr.taliraz.botdiscord.command.CommandMap;
import fr.taliraz.botdiscord.command.CommandPrivate;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.EventListener;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BotListener implements EventListener {

    private final CommandMap commandMap;

    private final List<String> banList;

    public BotListener(CommandMap commandMap) {
        this.commandMap = commandMap;
        banList = new ArrayList<>();
    }

    @Override
    public void onEvent(GenericEvent event) {
        System.out.println(event);
        if (event instanceof MessageReceivedEvent) {
            onMessage((MessageReceivedEvent) event);
        }
        if (event instanceof PrivateMessageReceivedEvent) {
            onPrivateMessage((PrivateMessageReceivedEvent) event);
        }
    }

    private void onMessage(MessageReceivedEvent event) {
        if (event.getAuthor().equals(event.getJDA().getSelfUser())) {
            return;
        }
        String message = event.getMessage().getContentDisplay();
        if (message.startsWith(commandMap.getTag())) {
            message = message.replaceFirst(commandMap.getTag(), "");
            if (commandMap.commandUser(event.getAuthor(), message, event.getMessage())) {

            }
        }
    }

    private void onPrivateMessage(PrivateMessageReceivedEvent event) {
        if (event.getAuthor().isBot()) {
            return;
        }
        event.getJDA().retrieveUserById("203804867284828160").queue((developpeur -> {
            User user = event.getAuthor();
            CommandPrivate commandPrivate = new CommandPrivate(event, user, developpeur);
            if (!user.getId().equals("203804867284828160") && !isBanned(user) && !event.getMessage().getContentDisplay().startsWith("=")) {
                commandPrivate.commandUser(user, developpeur, event);
            } else if (isBanned(user)) {
                user.openPrivateChannel().queue((channel) -> {
                    channel.sendMessage("Vous avez été bloqué ! :smile: ").queue();
                });
            } else {
                if (event.getMessage().getContentDisplay().startsWith("block")) {
                    commandPrivate.block(banList);
                } else if (event.getMessage().getContentDisplay().startsWith("unblock")) {
                    commandPrivate.unblock(banList);
                } else if (event.getMessage().getContentDisplay().startsWith("banlist")) {
                    commandPrivate.banlist(banList);
                } else if (event.getMessage().getContentDisplay().startsWith("send")) {
                    commandPrivate.send();
                } else if (event.getMessage().getContentDisplay().startsWith("guildlist")) {
                    commandPrivate.guildList();
                } else if (event.getMessage().getContentDisplay().startsWith("update")) {
                    commandPrivate.update();
                } else if (event.getMessage().getContentDisplay().startsWith("news")) {
                    commandPrivate.news();
                }
            }
        }));
    }

    private boolean isBanned(User user) {
        return banList.contains(user.getId());
    }
}
