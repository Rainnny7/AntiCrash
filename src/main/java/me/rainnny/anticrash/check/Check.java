package me.rainnny.anticrash.check;

import me.rainnny.anticrash.AntiCrash;
import me.rainnny.anticrash.data.DataPlayer;
import me.rainnny.api.protocol.Packet;
import org.bukkit.event.Listener;

import static org.bukkit.Bukkit.getPluginManager;

/*
 * Created by Braydon on 3/24/20 8:29 a.m.
 */
public class Check implements Listener {
    public final DataPlayer data;

    public Check(DataPlayer data) {
        this.data = data;
        getPluginManager().registerEvents(this, AntiCrash.INSTANCE);
    }

    public void onPacket(Object packet, Packet.Client type, long timestamp) {}
}