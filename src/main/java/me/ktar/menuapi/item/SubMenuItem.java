package me.ktar.menuapi.item;
/*
 * Copyright (C) 2013-Current Carter Gale (Ktar5) <buildfresh@gmail.com>
 * 
 * This file is part of MenuApi.
 * 
 * MenuApi can not be copied and/or distributed without the express
 * permission of the aforementioned owner.
 */

import me.ktar.menuapi.menu.Menu;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public abstract class SubMenuItem extends MenuItem {

    protected SubMenuItem(ItemStack stack) {
        super(stack);
    }

    public abstract Menu getMenu(Player player, ClickType clickType);

    @Override
    public void act(Player player, ClickType clickType) {
        if (player.getOpenInventory() != null) {
            player.closeInventory();
        }
        new BukkitRunnable() {
            @Override
            public void run() {
                getMenu(player, clickType).show(player);
            }
        }.runTaskLater(JavaPlugin.getProvidingPlugin(this.getClass()), 2L);
    }
}
