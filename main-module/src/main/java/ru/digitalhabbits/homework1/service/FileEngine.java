package ru.digitalhabbits.homework1.service;

import javax.annotation.Nonnull;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static java.util.Arrays.stream;

public class FileEngine {
    private static final String RESULT_FILE_PATTERN = "results-%s.txt";
    private static final String RESULT_DIR = "results";
    private static final String RESULT_EXT = "txt";

    public boolean writeToFile(@Nonnull String text, @Nonnull String pluginName) {
        String resultName = RESULT_FILE_PATTERN.replace("%s", pluginName);

        String currentDir = System.getProperty("user.dir");
        File resultDir = new File(currentDir + "/" + RESULT_DIR + "/" + resultName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(resultDir))) {
            writer.write(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public void cleanResultDir() {
        final String currentDir = System.getProperty("user.dir");
        final File resultDir = new File(currentDir + "/" + RESULT_DIR);
        stream(resultDir.list((dir, name) -> name.endsWith(RESULT_EXT)))
                .forEach(fileName -> new File(resultDir + "/" + fileName).delete());
    }
}
