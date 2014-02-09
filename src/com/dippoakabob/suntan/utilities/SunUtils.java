package com.dippoakabob.suntan.utilities;

import com.dippoakabob.suntan.SunTan;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

/**
 * Created by dippoakabob.
 */
public class SunUtils {

	protected static ArrayList<String> burning = new ArrayList<String>();

	public static void init() {
		new BukkitRunnable() {
			public void run() {
				ArrayList<String> temp = (ArrayList<String>) burning.clone();
				for(String name : temp){
					if(Bukkit.getPlayer(name) != null && !Bukkit.getPlayer(name).isDead()){
						if(shouldBurn(Bukkit.getPlayer(name))){
							damage(Bukkit.getPlayer(name));
						}else{
							burning.remove(name);
						}
					}else{
						burning.remove(name);
					}
				}
			}
		}.runTaskTimer(SunTan.getInstance(), 20, 20);
	}

	public static boolean isBurning(Player player) {
		return burning.contains(player.getName()) && !player.isDead();
	}

	public static void burn(Player player) {
		burning.add(player.getName());
		damage(player);
	}

	private static void damage(Player player) {
			player.setFireTicks(30);
			player.damage(ConfigManager.burnRate*2);
			if(ConfigManager.burnEffect){
				player.getWorld().playSound(player.getLocation(), Sound.FIZZ, 1, 1);
				player.getWorld().playEffect(player.getLocation(), Effect.MOBSPAWNER_FLAMES, 10);
			}
	}

	public static boolean shouldBurn(Player player) {
		if (player.getGameMode() != GameMode.CREATIVE) {
			if (player.getWorld().getHighestBlockYAt(player.getLocation()) <= player.getLocation().getBlock().getY()+2) {
				if(ConfigManager.burnInNight || day()){
					if(!player.hasPermission("sun.lotion")){
						return true;
					}
				}
			}
		}
		return false;
	}

	public static boolean day() {
		long time = Bukkit.getWorld("world").getTime();
		return time < 12300 || time > 23850;
	}

}
