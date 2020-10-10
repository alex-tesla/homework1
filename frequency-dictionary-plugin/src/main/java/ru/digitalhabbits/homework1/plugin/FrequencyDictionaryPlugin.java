package ru.digitalhabbits.homework1.plugin;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class FrequencyDictionaryPlugin
        implements PluginInterface {

    private static final String REGEX = "\\b[a-zA-Z][a-zA-Z0-9\\/.-]*\\b";

    @Nullable
    @Override
    public String apply(@Nonnull String text) {
        List<String> wordList = new ArrayList<>();

        String plainText = text.replaceAll("\\\\n", "\n").toLowerCase();
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(plainText);

        while (matcher.find()) {
            wordList.add(matcher.group());
        }

        Map<String, Long> wordFrequencyMap = wordList.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        Map<String, Long> sortedWordFrequencyMap = new TreeMap<>(wordFrequencyMap);

        return sortedWordFrequencyMap.entrySet()
                .stream()
                .map(entry -> entry.getKey() + " " + entry.getValue())
                .collect(Collectors.joining("\n"));
    }
}
