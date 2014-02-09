package com.dippoakabob.suntan.listeners;

import com.dippoakabob.suntan.utilities.SunUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

/**
 * Created by dippoakabob.
 */
public class MoveListener implements Listener {

	@EventHandler
	public void onMove(PlayerMoveEvent event){
		Player player = event.getPlayer();
		if(!SunUtils.isBurning(player) && SunUtils.shouldBurn(player)){
			SunUtils.burn(player);
		}
	}

}
