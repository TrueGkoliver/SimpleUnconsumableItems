package com.gkoliver.simpleunconsumableitems.core;

import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.util.InputMappings;
import net.minecraftforge.client.settings.KeyConflictContext;

public class Keybind {
    public static final KeyBinding KEYBIND_ACTIVATE = new KeyBinding("key.simpleunconsumableitems.activate", KeyConflictContext.UNIVERSAL, InputMappings.getInputByName("key.keyboard.p"), "key.categories.gameplay");
}
