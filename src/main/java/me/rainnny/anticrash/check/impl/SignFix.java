package me.rainnny.anticrash.check.impl;

import me.rainnny.anticrash.check.Check;
import me.rainnny.anticrash.data.DataPlayer;
import me.rainnny.api.protocol.ProtocolHandler;
import me.rainnny.api.util.MiscUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.SignChangeEvent;

import java.util.Arrays;
import java.util.Optional;

/*
 * Created by Braydon on 3/24/20 9:05 a.m.
 */
public class SignFix extends Check {
    public SignFix(DataPlayer data) {
        super(data);
    }

    @EventHandler
    private void onSign(SignChangeEvent event) {
        Optional<String> littleShit = Arrays.stream(event.getLines()).filter(line -> line.length() >= 20).findFirst();
        if (littleShit.isPresent()) {
            MiscUtils.printToConsole(event.getPlayer().getName() + " was kicked for having a big ass sign line: " + littleShit.get().length());
            ProtocolHandler.kickPlayer(event.getPlayer());
            event.setCancelled(true);
        }
    }
}