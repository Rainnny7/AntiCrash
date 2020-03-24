package me.rainnny.anticrash;

import me.rainnny.api.util.AutoLoad;
import org.bukkit.plugin.java.JavaPlugin;

/*
 * Created by Braydon on 3/23/20 5:39 a.m.
 */
@AutoLoad
public class AntiCrash extends JavaPlugin {
    public static AntiCrash INSTANCE;

    @Override
    public void onEnable() {
        INSTANCE = this;
    }
}