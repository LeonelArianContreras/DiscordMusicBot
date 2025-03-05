package dev.discordMusicBot.bot;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class EventListener extends ListenerAdapter {

    public char prefix = '!';

    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split(" ");
        if(args[0].startsWith(prefix + "greet")) {
            event.getMessage().reply("¡Ey! My name is OnTheBeat, nice to meet you!").queue();
        }
    }
}