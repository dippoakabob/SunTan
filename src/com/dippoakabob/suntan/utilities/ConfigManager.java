package com.dippoakabob.suntan.utilities;

import com.dippoakabob.suntan.SunTan;
import org.bukkit.configuration.file.FileConfiguration;

/**
 * Created by dippoakabob.
 */
public class ConfigManager {

	public static boolean burnInNight, instaKill, burnEffect;
	public static double burnRate;

	public void configInit(){
		SunTan.getInstance().saveConfig();
		SunTan.getInstance().reloadConfig();

		addIfNotContains("burn-in-night", true);
		addIfNotContains("insta-kill", false);
		addIfNotContains("burn-effect.enabled", true);
		addIfNotContains("damage-rate", 3D);

		SunTan.getInstance().saveConfig();

		update();
	}

	public void addIfNotContains(String path, Object def){
		if(!SunTan.getInstance().getConfig().contains(path)){
			SunTan.getInstance().getConfig().set(path, def);
		}
	}

	public void update(){
		burnInNight = SunTan.getInstance().getConfig().getBoolean("burn-in-night");
		instaKill = SunTan.getInstance().getConfig().getBoolean("insta-kill");
		burnEffect = SunTan.getInstance().getConfig().getBoolean("burn-effect.enabled");

		burnRate = SunTan.getInstance().getConfig().getDouble("damage-rate");
	}

}
