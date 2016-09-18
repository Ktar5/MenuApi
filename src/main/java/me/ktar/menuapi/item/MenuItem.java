package me.ktar.menuapi.item;
/*
 * Copyright (C) 2013-Current Carter Gale (Ktar5) <buildfresh@gmail.com>
 *
 * This file is part of MenuApi.
 *
 * MenuApi can not be copied and/or distributed without the express
 * permission of the aforementioned owner.
 */

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import me.ktar.menuapi.menu.DynamicMenu;
import me.ktar.menuapi.menu.Menu;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.function.BiConsumer;
import java.util.function.BiPredicate;

public abstract class
MenuItem {
    @Getter
    @Setter
    @Accessors(fluent = true)
    ItemStack stack;

    protected MenuItem(ItemStack stack) {
        this.stack = stack;
    }

    protected MenuItem(Material material) {
        this(new ItemStack(material));
    }

    public static void test() throws InterruptedException {
        DynamicMenu menu = new DynamicMenu("YoloSwag2k16", Menu.Size.FIVE)
                .setItem(0, 1,
                        MenuItem.create(new ItemStack(Material.APPLE), (player, clickType) -> {
                            if (player.getName().equalsIgnoreCase("ktar5") && clickType == ClickType.LEFT) {
                                System.out.println("Ktar5 did a left click");
                            }
                        })
                ).setItem(0, 2, new MenuIcon(Material.WOOD))
                .setItem(0, 0, new MenuItem(new ItemStack(Material.EYE_OF_ENDER)) {
                    @Override
                    public void act(Player player, ClickType clickType) {
                        System.out.println("You have touched the enderchest, congratulations");
                    }
                });
        menu.show(Bukkit.getPlayer("Ktar5"));
        Thread.sleep(2000);
        menu.modify().change(0, 1, item -> item.setDisplayName("I have been changed"));
    }

    public static MenuItem create(ItemStack item, BiConsumer<Player, ClickType> onclick) {
        return new MenuItem(item) {
            @Override
            public void act(Player player, ClickType clickType) {
                onclick.accept(player, clickType);
            }
        };
    }

    public static MenuItem create(ItemStack item, BiConsumer<Player, ClickType> onclick, BiPredicate<Player, ClickType> canUse) {
        return new MenuItem(item) {
            @Override
            public boolean canUse(Player player, ClickType clickType) {
                return canUse.test(player, clickType);
            }

            @Override
            public void act(Player player, ClickType clickType) {
                onclick.accept(player, clickType);
            }
        };
    }

    public final boolean checkAct(Player player, ClickType type) {
        boolean used = canUse(player, type);
        if (used) {
            act(player, type);
        }
        return used;
    }

    public boolean canUse(Player player, ClickType type) {
        return true;
    }

    public abstract void act(Player player, ClickType clickType);
}
