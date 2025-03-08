package dev.discordMusicBot.bot;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

import dev.discordMusicBot.commands.*;

public class EventListener extends ListenerAdapter {

    public String prefix = "!";

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getPrefix() {
        return prefix;
    }

    private final Map<String, Command> commands = new HashMap<>();

    public EventListener() {
        commands.put("greet", new Greet());
        commands.put("setprefix", new SetPrefix(this));
        commands.put("avatar", new Avatar());
        commands.put("userinfo", new UserInfo());
        // ToDo: Add commands left
    }

    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split(" ");
        if(args[0].startsWith(prefix)) {
            String command = args[0].substring(1).toLowerCase(); // Remove '!' with substring(index)
            if(commands.containsKey(command))
                commands.get(command).execute(event, args);
            else
                event.getChannel().sendMessage("Unknown command: " + command).queue();
        }
    }
}