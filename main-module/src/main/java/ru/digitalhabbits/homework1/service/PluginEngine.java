package ru.digitalhabbits.homework1.service;

import ru.digitalhabbits.homework1.plugin.PluginInterface;

import javax.annotation.Nonnull;

public class PluginEngine {

    @Nonnull
    public  <T extends PluginInterface> String applyPlugin(@Nonnull Class<T> cls, @Nonnull String text) {
        String result = "";
        try {
            PluginInterface plugin = cls.getConstructor().newInstance();
            result = plugin.apply(text);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
