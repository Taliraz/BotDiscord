package fr.taliraz.botdiscord;

import fr.taliraz.botdiscord.command.CommandMap;
import fr.taliraz.botdiscord.event.BotListener;
import fr.taliraz.botdiscord.languages.TradFr;
import fr.taliraz.botdiscord.util.Util;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.util.ResourceBundle;
import java.util.Scanner;

public class BotDiscord implements Runnable {

    private final JDA jda;
    private final CommandMap commandMap = new CommandMap(this);
    private boolean running;
    private final Scanner scanner = new Scanner(System.in);
    private ResourceBundle language;


    public BotDiscord() throws LoginException, IOException {
        language = new TradFr();
        Util.init(this);
        //BDD.init();
        jda = JDABuilder.createDefault(Util.getProperties().get("token").toString()).build();
        jda.addEventListener(new BotListener(commandMap));
        jda.getPresence().setActivity(Activity.listening("=help/=aide"));
        System.out.println("Bot connecté");
    }

    public static void main(String[] args) {
        BotDiscord botDiscord = null;
        try {
            botDiscord = new BotDiscord();
            new Thread(botDiscord, "bot").start();
        } catch (LoginException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        running = true;

        while (running) {
            if (scanner.hasNextLine()) {
                commandMap.commandConsole(scanner.nextLine());
            }
        }
        scanner.close();
        System.out.println("Bot deconnecté");
        jda.shutdown();
        System.exit(0);
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public JDA getJda() {
        return jda;
    }

    public CommandMap getCommandMap() {
        return commandMap;
    }

    public ResourceBundle getLanguage() {
        return this.language;
    }

    public void setLanguage(ResourceBundle language) {
        this.language = language;
    }
}
