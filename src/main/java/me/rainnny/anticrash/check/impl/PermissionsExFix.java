package me.rainnny.anticrash.check.impl;

import me.rainnny.anticrash.check.Check;
import me.rainnny.anticrash.data.DataPlayer;
import me.rainnny.api.protocol.ProtocolHandler;
import me.rainnny.api.util.MiscUtils;
import me.rainnny.api.util.PlayerUtils;
import me.rainnny.api.util.TaskUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

/*
 * Created by Braydon on 3/24/20 10:28 a.m.
 */

// Simple fix that patches an exploit in PermissionsEx that crashes
// a server if you spam /pex promote a a
public class PermissionsExFix extends Check {
    public PermissionsExFix(DataPlayer data) {
        super(data);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    private void onCommand(PlayerCommandPreprocessEvent event) {
        if ((event.getMessage().toLowerCase().startsWith("/pex promote ")
                || event.getMessage().toLowerCase().startsWith("/pex demote "))
                && !event.getPlayer().hasPermission("anticrash.pex")) {
            MiscUtils.printToConsole(event.getPlayer().getName() + " attempted to crash the server with PermissionsEx: " + event.getMessage());
            ProtocolHandler.kickPlayer(event.getPlayer());
            event.setCancelled(true);
        }
    }
}