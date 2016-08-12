package me.ktar.menuapi.menu;
/*
 * Copyright (C) 2013-Current Carter Gale (Ktar5) <buildfresh@gmail.com>
 * 
 * This file is part of MenuApi.
 * 
 * MenuApi can not be copied and/or distributed without the express
 * permission of the aforementioned owner.
 */

import me.ktar.menuapi.ItemFactory;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.function.Function;

public class DynamicMenu extends Menu<DynamicMenu> {

    public DynamicMenu(String name, Size size) {
        super(name, size);
    }

    public DynamicMenu(String name, int size) {
        super(name, size);
    }

    @Override
    public void show(Player player) {
        player.openInventory(inventory);
    }

    @Override
    public DynamicMenu getThis() {
        return this;
    }

    public InventoryRefresher modify(Player player) {
        return new InventoryRefresher(player, this);
    }

    public InventoryRefresher modify() {
        return modify(null);
    }

    public class InventoryRefresher {
        private final Player player;

        InventoryRefresher(Player player, DynamicMenu menu) {
            this.player = player;
        }

        public InventoryRefresher change(int index, Function<ItemFactory, ItemFactory> function) {
            if (index < inventory.getSize()) {
                ItemStack item = function.apply(new ItemFactory(inventory.getItem(index))).getItemStack();
                items().get(index).stack(item);
                inventory.setItem(index, item);
            }

            return this;
        }

        public InventoryRefresher change(int x, int y, Function<ItemFactory, ItemFactory> function) {
            return change(toIndex(x, y), function);
        }

        public InventoryRefresher set(int x, int y, Function<ItemFactory, ItemFactory> function) {
            int index = toIndex(x, y);
            if (index < inventory.getSize()) {
                ItemStack item = function.apply(new ItemFactory(Material.AIR)).getItemStack();
                items().get(index).stack(item);
                inventory.setItem(index, item);
            }
            return this;
        }

        public InventoryRefresher set(int x, int y, ItemStack stack) {
            int index = toIndex(x, y);
            if (index < inventory.getSize()) {
                items().get(index).stack(stack);
                inventory.setItem(index, stack);
            }
            return this;
        }

    }

}
