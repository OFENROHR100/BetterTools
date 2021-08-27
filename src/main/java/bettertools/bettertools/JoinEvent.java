package bettertools.bettertools;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEvent implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        event.getPlayer().setResourcePack("https://www.dropbox.com/sh/rw9b6l2dsfwmtb2/AACET6rZGy3cVEZI6iRRG-nca?dl=1");
    }
}
