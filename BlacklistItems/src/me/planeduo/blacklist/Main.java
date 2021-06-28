package me.planeduo.blacklist;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener{
	
	public ArrayList<ItemStack> items = new ArrayList<ItemStack>();
	
	@Override
	public void onEnable() {
		this.saveDefaultConfig();
		this.getServer().getPluginManager().registerEvents(this, this);
		for (String msg : this.getConfig().getStringList("items")) {
			items.add(new ItemStack(Material.getMaterial(msg.toUpperCase())));
		}
	}

	@Override
	public void onDisable() {

	}
	
	
	@EventHandler
	public void interation(PlayerInteractEvent event) {
		if (items.contains(event.getItem())) {
			event.setCancelled(true);
			return;
		}
		return;
	}
	
}
