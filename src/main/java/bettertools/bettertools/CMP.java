package bettertools.bettertools;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class CMP {

    public static HashMap<UUID, Double> cooldownsP;

    public static void setupCooldownP(){
        cooldownsP = new HashMap<>();
    }
    public static void setCooldownP(Player player, int seconds){
        double delay  = System.currentTimeMillis() + (seconds*1000);
        cooldownsP.put(player.getUniqueId(), delay);
    }

    public static boolean checkCooldownP(Player player){
        if(!cooldownsP.containsKey(player.getUniqueId()) || cooldownsP.get(player.getUniqueId()) <= System.currentTimeMillis()){
            return true;
        }
        return false;
    }

    public static Long getCooldownP(Player player){
        return (Math.round((cooldownsP.get(player.getUniqueId()) - System.currentTimeMillis()))/1000);
    }
}

