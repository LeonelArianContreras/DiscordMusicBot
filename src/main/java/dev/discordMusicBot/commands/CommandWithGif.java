package dev.discordMusicBot.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.io.File;
import java.util.Random;

import static net.dv8tion.jda.api.utils.FileUpload.fromData;

public class CommandWithGif implements Command {

    private final Random random = new Random();
    private final String command;

    public CommandWithGif(String command) {
        this.command = command;
    }

    public void execute(MessageReceivedEvent event, String[] args) {

        if(args.length < 2) {
            event.getChannel().sendMessage("Usage: `!" + command + " <someone>").queue();
            return;
        }

        Member huggedMember = event.getMessage().getMentions().getMembers().get(0);

        File gifFolder = new File("src/main/resources/gifs/" + command);
        if(!gifFolder.exists()) {
            System.out.println(gifFolder.getAbsolutePath());
            return;
        }

        File[] gifFiles = gifFolder.listFiles((dir, name) -> name.endsWith(".gif"));
        if(!gifFolder.exists()) {
            System.out.println("gifFolder does not exist");
            return;
        }

        File randomGif = gifFiles[random.nextInt(gifFiles.length)]; // Choose random gif

        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle(event.getAuthor().getEffectiveName() + " has given a " + command + " to " + huggedMember.getEffectiveName());
        eb.setColor(0xFF69B4); // Pink colour
        event.getChannel().sendMessageEmbeds(eb.build()).addFiles(fromData(randomGif)).queue();

    }
}
