package me.rainnny.anticrash.check.impl;

import me.rainnny.anticrash.check.Check;
import me.rainnny.anticrash.data.DataPlayer;
import me.rainnny.api.protocol.Packet;
import me.rainnny.api.protocol.ProtocolHandler;
import me.rainnny.api.protocol.type.WrappedInCustomPayloadPacket;
import me.rainnny.api.util.MathUtils;
import me.rainnny.api.util.MiscUtils;
import net.minecraft.server.v1_8_R3.PacketPlayInCustomPayload;
import org.bukkit.Bukkit;

/*
 * Created by Braydon on 3/24/20 8:29 a.m.
 */
public class CustomPayload extends Check {
    public CustomPayload(DataPlayer data) {
        super(data);
    }

    @Override
    public boolean onPacket(Object packet, Packet.Client type, long timestamp) {
        if (type == Packet.Client.CUSTOM_PAYLOAD) {
            WrappedInCustomPayloadPacket wrappedPayload = new WrappedInCustomPayloadPacket((PacketPlayInCustomPayload) packet);
            if (wrappedPayload.getPayload().equals("MC|BSign") || wrappedPayload.getPayload().equals("MC|BEdit")) {
                MiscUtils.printToConsole(data.getPlayer().getName() + " was kicked for sending an illegal payload: " + wrappedPayload.getPayload());
                ProtocolHandler.kickPlayer(data.getPlayer());
                return true;
            }
        }
        return false;
    }
}