package bettertools.bettertools;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEvent implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        event.getPlayer().setResourcePack("https://www.dropbox.com/sh/jb2u5ehru4dxluq/AABHOwYionPMfFrwn0-pyB8qa?dl=1");
    }
}
