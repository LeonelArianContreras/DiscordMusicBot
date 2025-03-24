package dev.discordMusicBot.bot;

import dev.discordMusicBot.commands.basicCommand.*;
import dev.discordMusicBot.commands.databaseCommand.ChangePassword;
import dev.discordMusicBot.commands.databaseCommand.ChangeUsername;
import dev.discordMusicBot.commands.databaseCommand.Register;
import dev.discordMusicBot.commands.infoCommand.Avatar;
import dev.discordMusicBot.commands.infoCommand.UserInfo;
import dev.discordMusicBot.commands.musicCommand.Play;
import dev.discordMusicBot.service.music.MusicManager;
import dev.discordMusicBot.utils.LeoEvent;
import lavalink.client.io.Link;
import lavalink.client.io.jda.JdaLavalink;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRemoveEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import dev.discordMusicBot.service.*;
import dev.discordMusicBot.commands.*;
import dev.discordMusicBot.commands.databaseCommand.*;

public class EventListener extends ListenerAdapter {

    public String prefix = "!";
    private final long CHANNEL_ID = 1348851832159735809L;
    private final Map<String, Command> commands = new HashMap<>();
    private static JdaLavalink lavalink = null;

    public EventListener(JDA jda) {
        lavalink = new JdaLavalink("BotName", jda.getShardInfo().getShardTotal(), shardId -> jda);

        try {
            lavalink.addNode(URI.create("http://localhost:2333"), "Necroja2005");
        } catch (Exception e) {
            System.err.println("❌ Lavalink connection failed: " + e.getMessage());
        }
        MusicManager musicManager = new MusicManager(lavalink);

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
        commands.put("unregister", new Unregister());
        commands.put("play", new Play(musicManager));
        // ToDo: Add commands left
    }

    public String getPrefix() {
        return prefix;
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


    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    private static class LinkLavalink extends Link {
        public LinkLavalink(JdaLavalink lavalink, String guildId) {
            super(lavalink, guildId);
        }

        @Override
        protected void removeConnection() {
            // JDA handles this for us
        }


        @Override
        protected void queueAudioDisconnect() {
            Guild g = lavalink.getJdaFromSnowflake(String.valueOf(guild)).getGuildById(guild);
            if (g != null) {
                lavalink.getJdaFromSnowflake(String.valueOf(guild)).getDirectAudioController().disconnect(g);
            } else {
                System.out.println("Attempted to disconnect, but guild was not found");
            }
        }

        @Override
        protected void queueAudioConnect(long channelId) {
            VoiceChannel vc = lavalink.getJdaFromSnowflake(String.valueOf(guild)).getVoiceChannelById(channelId);
            if (vc != null) {
                lavalink.getJdaFromSnowflake(String.valueOf(guild)).getDirectAudioController().connect(vc);
            } else {
                System.out.println("Attempted to connect, but voice channel {} was not found");
            }
        }
    }
}

