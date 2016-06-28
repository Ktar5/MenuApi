package me.ktar.menuapi;
/*
 * Copyright (C) 2013-Current Carter Gale (Ktar5) <buildfresh@gmail.com>
 * 
 * This file is part of MenuApi.
 * 
 * MenuApi can not be copied and/or distributed without the express
 * permission of the aforementioned owner.
 */

import lombok.NonNull;
import me.ktar.menuapi.menu.Menu;
import me.ktar.menuapi.menu.MenuHolder;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class MenuListener implements Listener {

    static{
        Bukkit.getServer().getPluginManager().registerEvents(new MenuListener(), JavaPlugin.getProvidingPlugin(MenuListener.class));
    }

    @EventHandler
    public void onExit(InventoryCloseEvent event) {
        if (event.getInventory().getHolder() instanceof MenuHolder) {
            MenuHolder holder = ((MenuHolder) event.getInventory().getHolder());
            @NonNull Menu menu = holder.getMenu();
            menu.call(event);
            if (menu.parent() != null) {
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        menu.parent().show(((Player) event.getPlayer()));
                    }
                }.runTaskLater(JavaPlugin.getProvidingPlugin(this.getClass()), 2L);
            }
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (event.getInventory().getHolder() instanceof MenuHolder) {
            MenuHolder<?> holder = ((MenuHolder) event.getInventory().getHolder());
            @NonNull Menu menu = holder.getMenu();
            boolean cancel = menu.call(event);

            if (event.getRawSlot() >= menu.inventory().getSize()) {
                event.setCancelled(cancel);
                return;
            }
            event.setCancelled(true);
            if (!menu.items().containsKey(event.getSlot())) {
                event.setCancelled(cancel);
                return;
            }
            menu.use(event.getSlot(), ((Player) event.getWhoClicked()), event.getClick());
        }
    }

    @EventHandler
    public void onDrag(InventoryDragEvent event) {
        if (event.getInventory().getHolder() instanceof MenuHolder) {
            event.setCancelled(true);
            MenuHolder holder = ((MenuHolder)event.getInventory().getHolder());
            @NonNull Menu menu = holder.getMenu();
            menu.call(event);
        }
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        if (event.getPlayer().getOpenInventory().getTopInventory().getHolder() instanceof MenuHolder) {
            event.setCancelled(true);
        }
    }


}
