package me.planeduo.blacklist;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener{

    public ArrayList<Material> items = new ArrayList<>();

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        this.getServer().getPluginManager().registerEvents(this, this);
        for (String msg : this.getConfig().getStringList("items")) {
            items.add(Material.getMaterial(msg.toUpperCase()));
        }

    }

    @Override
    public void onDisable() {

    }


    @EventHandler
    public void interaction(PlayerInteractEvent event) {
        ItemStack item = event.getItem();
        assert item != null;
        Player player = event.getPlayer();
        Material material = item.getType();


        if (items.contains(material)) {
            assert item != null;
            event.setCancelled(true);
            player.getInventory().remove(material);
            player.updateInventory();

            return;
        }

    }
    @EventHandler
    public void dropItem(PlayerDropItemEvent event) {
        ItemStack item = event.getItemDrop().getItemStack();
        assert item != null;
        Player player = event.getPlayer();
        Material material = item.getType();
        if (items.contains(material)) {
            event.setCancelled(true);
            player.getInventory().removeItem(item);
            player.updateInventory();
           return;
        }
    }
}
