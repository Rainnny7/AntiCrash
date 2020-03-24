package me.rainnny.anticrash.listener;

import me.rainnny.anticrash.data.DataPlayer;
import me.rainnny.api.util.Init;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/*
 * Created by Braydon on 3/24/20 8:32 a.m.
 */
@Init
public class PlayerListener implements Listener {
    public PlayerListener() {
        Bukkit.getOnlinePlayers().forEach(DataPlayer::new);
    }

    @EventHandler
    private void onJoin(PlayerJoinEvent event) {
        new DataPlayer(event.getPlayer());
    }

    @EventHandler
    private void onQuit(PlayerQuitEvent event) {
        DataPlayer.get(event.getPlayer()).remove();
    }
}