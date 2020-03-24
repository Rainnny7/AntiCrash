package me.rainnny.anticrash.check.impl;

import me.rainnny.anticrash.check.Check;
import me.rainnny.anticrash.data.DataPlayer;
import me.rainnny.api.protocol.Packet;
import me.rainnny.api.protocol.ProtocolHandler;
import me.rainnny.api.util.MathUtils;
import me.rainnny.api.util.MiscUtils;

/*
 * Created by Braydon on 3/24/20 8:29 a.m.
 */
public class PacketSpam extends Check {
    private long lastPacket;
    private int threshold;

    public PacketSpam(DataPlayer data) {
        super(data);
    }

    @Override
    public void onPacket(Object packet, Packet.Client type, long timestamp) {
        long difference = timestamp - lastPacket;
        if (difference <= 10 && MathUtils.elapsed(data.loginTime) >= 1500L) {
            if (threshold++ >= 50) {
                MiscUtils.printToConsole(data.getPlayer().getName() + " was kicked for spamming packets: " + difference);
                ProtocolHandler.kickPlayer(data.getPlayer());
            }
        } else threshold = 0;
        lastPacket = timestamp;
    }
}