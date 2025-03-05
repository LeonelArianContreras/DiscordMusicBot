package dev.discordMusicBot.bot;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import java.util.EnumSet;

import javax.security.auth.login.LoginException;

public class MusicBot {
    public static void main(String[] args) throws LoginException {
        JDABuilder jda = JDABuilder.createDefault(ERROR,
                EnumSet.of(GatewayIntent.GUILD_MESSAGES, GatewayIntent.MESSAGE_CONTENT));
        jda.setActivity(Activity.listening("<< Musica >>"));
        jda.setStatus(OnlineStatus.ONLINE);
        jda.addEventListeners(new EventListener());
        jda.build();
    }
}
