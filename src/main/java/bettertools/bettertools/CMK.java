package bettertools.bettertools;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class CMK {

    public static HashMap<UUID, Double> cooldownsK;

    public static void setupCooldownK(){
        cooldownsK = new HashMap<>();
    }
    public static void setCooldownK(Player player, int seconds){
        double delay  = System.currentTimeMillis() + (seconds*1000);
        cooldownsK.put(player.getUniqueId(), delay);
    }

    public static boolean checkCooldownK(Player player){
        if(!cooldownsK.containsKey(player.getUniqueId()) || cooldownsK.get(player.getUniqueId()) <= System.currentTimeMillis()){
            return true;
        }
        return false;
    }

    public static Long getCooldownK(Player player){
        return (Math.round((cooldownsK.get(player.getUniqueId()) - System.currentTimeMillis()))/1000);
    }
}