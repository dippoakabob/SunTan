package com.dippoakabob.suntan;

import com.dippoakabob.suntan.utilities.ConfigManager;
import com.dippoakabob.suntan.listeners.MoveListener;
import com.dippoakabob.suntan.utilities.SunUtils;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by dippoakabob.
 */
public class SunTan extends JavaPlugin {

	private static SunTan instance;

	private ConfigManager manager;

	public void onEnable() {
		instance = this;

		SunUtils.init();

		this.getConfig().options().copyDefaults(true);
		manager = new ConfigManager();
		manager.configInit();

		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new MoveListener(), this);
	}

	public static SunTan getInstance(){
		return instance;
	}

}
