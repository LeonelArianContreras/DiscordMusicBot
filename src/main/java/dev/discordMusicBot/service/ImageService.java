package dev.discordMusicBot.service;

import dev.discordMusicBot.service.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class ImageService {

    private final String directory;

    public ImageService(String directory) {
        this.directory = directory;
    }

    public BufferedImage getImage() {
        File folder = getFolder();
        folderVerification(folder);
        File[] files = getFilesInFolder();
        File imageFile = files[new Random().nextInt(files.length)];

        try {
            return ImageIO.read(imageFile);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public File getFolder() {
        return new File("src/main/resources/pictures/" + directory);
    }

    public File[] getFilesInFolder() {
        return getFolder().listFiles((dir, name) -> name.endsWith(".png"));
    }

    public void folderVerification(File folder) {
        if(!folder.exists() || folder.listFiles() == null)
            System.err.println("Love images folder not found: " + folder.getAbsolutePath());
    }

}