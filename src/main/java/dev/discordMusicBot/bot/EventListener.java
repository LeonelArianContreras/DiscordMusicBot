package dev.discordMusicBot.bot;

import dev.discordMusicBot.commands.databaseCommand.ChangePassword;
import dev.discordMusicBot.commands.databaseCommand.ChangeUsername;
import dev.discordMusicBot.commands.databaseCommand.Register;
import dev.discordMusicBot.commands.infoCommand.Avatar;
import dev.discordMusicBot.commands.infoCommand.UserInfo;
import dev.discordMusicBot.utils.LeoEvent;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRemoveEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

import dev.discordMusicBot.service.*;
import dev.discordMusicBot.commands.*;

public class EventListener extends ListenerAdapter {

    public String prefix = "!";
    private final long CHANNEL_ID = 1348851832159735809L;

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
        commands.put("hug", new GifCommand("hug"));
        commands.put("kiss", new GifCommand("kiss"));
        commands.put("kick", new GifCommand("kick"));
        commands.put("love", new Love(new LoveService()));
        commands.put("say", new Say());
        commands.put("register", new Register());
        commands.put("changepassword", new ChangePassword());
        commands.put("changeusername", new ChangeUsername());
        // ToDo: Add commands left
    }

    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        LeoEvent eventUtils = new LeoEvent(event);
        String[] args = event.getMessage().getContentRaw().split(" ");
        if(args[0].startsWith(prefix)) {
            String command = args[0].substring(1).toLowerCase(); // Remove '!' with substring(index)
            if(commands.containsKey(command))
                commands.get(command).execute(eventUtils);
            else
                eventUtils.sendBasicMessage("Unknown command: " + command);
        }
    }

    private void sendWelcomeOrFarewellMessage(Guild guild, String message) {
        TextChannel channel = guild.getChannelById(TextChannel.class, CHANNEL_ID);
        channel.sendMessage(message).queue();
    }

    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event) {
        sendWelcomeOrFarewellMessage(event.getGuild(), "\uD83C\uDF89 Welcome to our Discord, " +
                event.getMember().getAsMention() + "! \uD83C\uDF89");
    }

    @Override
    public void onGuildMemberRemove(GuildMemberRemoveEvent event) {
        sendWelcomeOrFarewellMessage(event.getGuild(), "\uD83D\uDC94 Bye bye, " +
                event.getUser().getAsMention() + "! We'll miss u \uD83D\uDC94");
    }


}