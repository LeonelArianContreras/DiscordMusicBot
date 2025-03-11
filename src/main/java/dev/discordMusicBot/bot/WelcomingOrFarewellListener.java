package dev.discordMusicBot.bot;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRemoveEvent;

public class WelcomingOrFarewellListener extends ListenerAdapter {

    public void onGuildMemberJoin(GuildMemberJoinEvent event) {
        event.getGuild().getDefaultChannel().asTextChannel().
                sendMessage("Welcome to our Discord, " +
                        event.getMember().getAsMention() + "! \uD83D\uDC95").queue();
    }

    public void onGuildMemberRemove(GuildMemberRemoveEvent event) {
        event.getGuild().getDefaultChannel().asTextChannel().
                sendMessage("Bye bye, " +
                        event.getMember().getAsMention() + "! We will miss u \uD83D\uDC94").queue();
    }
}