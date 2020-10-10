package ru.digitalhabbits.homework1.plugin;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CounterPlugin
        implements PluginInterface {
    private static final String REGEX = "\\b[a-zA-Z][a-zA-Z0-9\\/.-]*\\b";

    @Nullable
    @Override
    public String apply(@Nonnull String text) {
        String plainText = text.replaceAll("\\\\n", "\n").toLowerCase();
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(plainText);

        int lineCount = plainText.split("\n").length;

        int wordCount = 0;
        while (matcher.find()) {
            wordCount++;
        }

        int letterCount = plainText.length();

        return lineCount + ";" + wordCount + ";" + letterCount;
    }

}
