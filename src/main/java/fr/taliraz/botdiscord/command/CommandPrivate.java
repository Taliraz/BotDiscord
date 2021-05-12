package fr.taliraz.botdiscord.command;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Emote;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.internal.entities.EmoteImpl;

import java.awt.*;
import java.util.List;

public class CommandPrivate {

    PrivateMessageReceivedEvent event;
    User user, developpeur;

    public CommandPrivate(PrivateMessageReceivedEvent event, User user, User developpeur) {
        this.event = event;
        this.user = user;
        this.developpeur = developpeur;
    }

    public void commandUser(User user, User developpeur, PrivateMessageReceivedEvent event) {
        EmbedBuilder builder = new EmbedBuilder();
        String avatarUrl = user.getAvatarUrl() == null ? null : user.getAvatarUrl() + "?size=256";
        builder.setAuthor(user.getName(), null, avatarUrl);
        builder.setTitle("Message au développeur");
        builder.setDescription(event.getMessage().getContentDisplay());
        builder.setColor(Color.GREEN);
        user.openPrivateChannel().queue((channel) -> {
            channel.sendMessage("Bonjour " + user.getName() + "! Votre message a été envoyé au développeur").queue();
            channel.sendMessage(builder.build()).queue();
        });
        developpeur.openPrivateChannel().queue((channel) -> {
            builder.setFooter(user.getId() + "\n" + user.getAsTag());
            channel.sendMessage(builder.build()).queue();
        });
    }

    public void block(List<String> banList) {
        String id = event.getMessage().getContentDisplay().substring(6);
        banList.add(id);
        developpeur.openPrivateChannel().queue((privateChannel -> {
            privateChannel.sendMessage("L'utilisateur a été bloqué !").queue();
        }));
    }

    public void unblock(List<String> banList) {
        String id = event.getMessage().getContentDisplay().substring(8);
        banList.remove(id);
        developpeur.openPrivateChannel().queue((privateChannel -> {
            privateChannel.sendMessage("L'utilisateur a été débloqué !").queue();
        }));
    }

    public void banlist(List<String> banList) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String id : banList) {
            stringBuilder.append(id).append("\n");
        }
        assert developpeur != null;
        developpeur.openPrivateChannel().queue((privateChannel -> {
            if (banList.size() != 0) {
                privateChannel.sendMessage(stringBuilder.toString()).queue();
            } else {
                privateChannel.sendMessage("Aucun utilisateur banni").queue();
            }
        }));
    }

    public void send() {
        String[] requete = event.getMessage().getContentDisplay().split(" ");
        event.getJDA().retrieveUserById(requete[1]).queue(userToSend -> {
            StringBuilder builder = new StringBuilder();
            builder.append("**Développeur : **");
            for (int i = 2; i < requete.length; i++) {
                builder.append(requete[i]).append(" ");
            }
            userToSend.openPrivateChannel().queue(privateChannel -> {
                privateChannel.sendMessage(builder.toString()).queue();
                event.getMessage().addReaction("✔").queue();
            });
        });
    }

    public void guildList() {
        List<Guild> guilds = event.getJDA().getGuilds();
        StringBuilder stringBuilder = new StringBuilder();
        for (Guild guild : guilds) {
            stringBuilder.append("- ").append(guild.getName()).append("\n");
        }
        developpeur.openPrivateChannel().queue((privateChannel -> {
            privateChannel.sendMessage(stringBuilder.toString()).queue();
        }));
    }

    public void update() {
        String[] requete = event.getMessage().getContentDisplay().split("::");
        EmbedBuilder embedBuilder = createBotMessage(":flag_fr: Mise à jour : " + requete[1], requete[2]);
        sendEmbedBuilderToGuilds(embedBuilder);
        embedBuilder = createBotMessage(":flag_us: Update : " + requete[1], requete[3]);
        sendEmbedBuilderToGuilds(embedBuilder);
    }

    public void news() {
        String[] requete = event.getMessage().getContentDisplay().split("::");
        sendMessageToGuilds(":flag_fr:" + requete[1]);
        sendMessageToGuilds(":flag_us:" + requete[2]);
    }


    public EmbedBuilder createBotMessage(String titre, String description) {
        User bot = event.getJDA().getSelfUser();
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setColor(Color.WHITE);
        embedBuilder.setTitle(titre);
        embedBuilder.setAuthor(bot.getName());
        embedBuilder.setImage(bot.getAvatarUrl());
        embedBuilder.appendDescription(description);
        return embedBuilder;
    }

    public void sendEmbedBuilderToGuilds(EmbedBuilder embedBuilder) {
        List<Guild> guilds = event.getJDA().getGuilds();
        for (Guild guild : guilds) {
            List<TextChannel> channelUpdates = guild.getTextChannelsByName("updateBot", true);
            TextChannel textChannel;
            if (channelUpdates.isEmpty()) {
                textChannel = guild.getTextChannels().get(0);
            } else {
                textChannel = channelUpdates.get(0);
            }
            try {
                textChannel.sendMessage(embedBuilder.build()).queue();
                developpeur.openPrivateChannel().queue((channel) -> {
                    channel.sendMessage(
                            textChannel.getGuild().getName() + " ("
                                    + textChannel.getName() + ") :white_check_mark:"
                    ).queue();
                });
            } catch (Exception e) {
                developpeur.openPrivateChannel().queue((channel) -> {
                    channel.sendMessage(textChannel.getGuild().getName() + " ("
                            + textChannel.getName() + ") :x:"
                    ).queue();
                });
            }
        }
    }

    public void sendMessageToGuilds(String message) {
        List<Guild> guilds = event.getJDA().getGuilds();
        for (Guild guild : guilds) {
            List<TextChannel> channelUpdates = guild.getTextChannelsByName("updateBot", true);
            TextChannel textChannel;
            if (channelUpdates.isEmpty()) {
                textChannel = guild.getTextChannels().get(0);
            } else {
                textChannel = channelUpdates.get(0);
            }
            try {
                textChannel.sendMessage(message).queue();
                developpeur.openPrivateChannel().queue((channel) -> {
                    channel.sendMessage(
                            textChannel.getGuild().getName() + " ("
                                    + textChannel.getName() + ") :white_check_mark:"
                    ).queue();
                });
            } catch (Exception e) {
                developpeur.openPrivateChannel().queue((channel) -> {
                    channel.sendMessage(textChannel.getGuild().getName() + " ("
                            + textChannel.getName() + ") :x:"
                    ).queue();
                });
            }
        }
    }
}
