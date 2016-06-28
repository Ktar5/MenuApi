package me.ktar.menuapi.item;
/*
 * Copyright (C) 2013-Current Carter Gale (Ktar5) <buildfresh@gmail.com>
 * 
 * This file is part of MenuApi.
 * 
 * MenuApi can not be copied and/or distributed without the express
 * permission of the aforementioned owner.
 */

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class SubMenuItem extends MenuItem {

    private final String server;

    protected SubMenuItem(ItemStack stack, String server) {
        super(stack);
        this.server = server;
    }

    //TODO
    @Override
    public void act(Player player, ClickType clickType) {

    }
}
