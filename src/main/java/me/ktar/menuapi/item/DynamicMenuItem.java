package me.ktar.menuapi.item;
/*
 * Copyright (C) 2013-Current Carter Gale (Ktar5) <buildfresh@gmail.com>
 * 
 * This file is part of MenuApi.
 * 
 * MenuApi can not be copied and/or distributed without the express
 * permission of the aforementioned owner.
 */

import me.ktar.menuapi.ItemFactory;

public abstract class DynamicMenuItem extends MenuItem {

    protected DynamicMenuItem(ItemFactory def) {
        super(def.getItemStack());
    }

}
