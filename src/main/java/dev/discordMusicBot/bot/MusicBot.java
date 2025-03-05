package dev.discordMusicBot.bot;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import java.util.EnumSet;
import io.github.cdimascio.dotenv.Dotenv;

import javax.security.auth.login.LoginException;

public class MusicBot {

    private final String token;

    public MusicBot() {
        Dotenv dotenv = Dotenv.load();  // Load .env
        this.token = dotenv.get("TOKEN");  // Search "TOKEN" in the file and save the encripted value into token
    }

    public static void main(String[] args) throws LoginException {
        MusicBot bot = new MusicBot();

        JDABuilder jda = JDABuilder.createDefault(bot.token,
                EnumSet.of(GatewayIntent.GUILD_MESSAGES, GatewayIntent.MESSAGE_CONTENT));
        jda.setActivity(Activity.listening("<< Musica >>"));
        jda.setStatus(OnlineStatus.ONLINE);
        jda.addEventListeners(new EventListener());
        jda.build();
    }
}
