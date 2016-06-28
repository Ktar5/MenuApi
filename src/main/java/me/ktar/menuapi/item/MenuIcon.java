package me.ktar.menuapi.item;
/*
 * Copyright (C) 2013-Current Carter Gale (Ktar5) <buildfresh@gmail.com>
 * 
 * This file is part of MenuApi.
 * 
 * MenuApi can not be copied and/or distributed without the express
 * permission of the aforementioned owner.
 */

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class MenuIcon extends MenuItem {

    public MenuIcon(ItemStack stack) {
        super(stack);
    }

    public MenuIcon(Material material)
    {
        super(material);
    }

    @Override
    public void act(Player player, ClickType clickType) {
        //Does nothing because its an icon, icons don't do crap
    }
}
