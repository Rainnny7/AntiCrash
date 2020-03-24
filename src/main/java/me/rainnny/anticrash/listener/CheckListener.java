package me.rainnny.anticrash.listener;

import me.rainnny.anticrash.data.DataPlayer;
import me.rainnny.api.event.AtlasListener;
import me.rainnny.api.event.Listen;
import me.rainnny.api.event.ListenerPriority;
import me.rainnny.api.protocol.event.ClientPacketEvent;
import me.rainnny.api.util.Init;

/*
 * Created by Braydon on 3/24/20 8:38 a.m.
 */
@Init
public class CheckListener implements AtlasListener {
    @Listen(priority = ListenerPriority.HIGHEST)
    public void onPacket(ClientPacketEvent event) {
        DataPlayer.get(event.getPlayer()).getChecks().forEach(check -> {
            event.setCancelled(check.onPacket(event.getPacketClass(), event.getPacket(), event.getTimestamp()));
        });
    }
}