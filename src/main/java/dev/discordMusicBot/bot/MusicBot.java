package dev.discordMusicBot.bot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.requests.GatewayIntent;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.EnumSet;
import io.github.cdimascio.dotenv.Dotenv;

import javax.security.auth.login.LoginException;

import static dev.discordMusicBot.persistence.DatabaseManager.connect;

public class MusicBot {

    private final String token;

    public MusicBot() {
        Dotenv dotenv = Dotenv.load();  // Load .env
        this.token = dotenv.get("TOKEN");  // Search "TOKEN" in the file and save the encripted value into token
    }

    public static void main(String[] args) throws LoginException {
        MusicBot bot = new MusicBot();

        try(Connection connection = connect()){
            System.out.println("Connected to database");
        } catch(SQLException e) {
            System.err.println("Not connected to database" + e.getMessage());
            e.printStackTrace();
        }

        JDABuilder jda = JDABuilder.createDefault(bot.token,
                EnumSet.of(
                        GatewayIntent.GUILD_MESSAGES,
                        GatewayIntent.MESSAGE_CONTENT,
                        GatewayIntent.GUILD_MEMBERS,
                        GatewayIntent.GUILD_VOICE_STATES
                ));
        jda.setStatus(OnlineStatus.ONLINE);
        JDA jdaInstance = jda.build();
        jdaInstance.addEventListener(new EventListener(jdaInstance));
    }
}
