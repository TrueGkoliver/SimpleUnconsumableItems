package com.gkoliver.simpleunconsumableitems.core.event;

import com.gkoliver.simpleunconsumableitems.SimpleUnconsumableItems;
import com.gkoliver.simpleunconsumableitems.core.Keybind;
import com.gkoliver.simpleunconsumableitems.core.SwapModePacket;
import com.google.common.eventbus.Subscribe;
import net.minecraft.client.Minecraft;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import sun.java2d.pipe.SpanShapeRenderer;

@Mod.EventBusSubscriber(modid=SimpleUnconsumableItems.MOD_ID)
public class CommonEvent {
    @SubscribeEvent
    public static void onInput(InputEvent.KeyInputEvent event) {
        if (Keybind.KEYBIND_ACTIVATE.isKeyDown()) {
            SimpleUnconsumableItems.handler.sendToServer(new SwapModePacket(Minecraft.getInstance().player.getUniqueID()));
        }
    }
}
