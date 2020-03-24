package me.rainnny.anticrash.data;

import lombok.Getter;
import me.rainnny.anticrash.check.Check;
import me.rainnny.anticrash.check.impl.CustomPayload;
import me.rainnny.anticrash.check.impl.PacketSpam;
import me.rainnny.anticrash.check.impl.SignFix;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
 * Created by Braydon on 3/24/20 8:28 a.m.
 */
@Getter
public class DataPlayer {
    private static final HashMap<Player, DataPlayer> cache = new HashMap<>();

    private final Player player;
    private final List<Check> checks = new ArrayList<>();

    public long loginTime;

    public DataPlayer(Player player) {
        this.player = player;

        // Register Checks
        checks.add(new PacketSpam(this));
        checks.add(new SignFix(this));
        checks.add(new CustomPayload(this));

        loginTime = System.currentTimeMillis();
        cache.put(player, this);
    }

    public void remove() {
        cache.remove(player);
    }

    public static DataPlayer get(Player player) {
        if (!cache.containsKey(player))
            new DataPlayer(player);
        return cache.get(player);
    }
}