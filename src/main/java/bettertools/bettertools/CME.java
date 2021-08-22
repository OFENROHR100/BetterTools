package bettertools.bettertools;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class CME {

    public static HashMap<UUID, Double> cooldownsE;

    public static void setupCooldownE(){
        cooldownsE = new HashMap<>();
    }
    public static void setCooldownE(Player player, int seconds){
        double delay  = System.currentTimeMillis() + (seconds*1000);
        cooldownsE.put(player.getUniqueId(), delay);
    }

    public static boolean checkCooldownE(Player player){
        if(!cooldownsE.containsKey(player.getUniqueId()) || cooldownsE.get(player.getUniqueId()) <= System.currentTimeMillis()){
            return true;
        }
        return false;
    }

    public static Long getCooldownE(Player player){
        return (Math.round((cooldownsE.get(player.getUniqueId()) - System.currentTimeMillis()))/1000);
    }
}
