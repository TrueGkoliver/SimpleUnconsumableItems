package com.gkoliver.simpleunconsumableitems;

import com.gkoliver.simpleunconsumableitems.core.Keybind;
import com.gkoliver.simpleunconsumableitems.core.SwapModePacket;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(SimpleUnconsumableItems.MOD_ID)
@Mod.EventBusSubscriber(modid=SimpleUnconsumableItems.MOD_ID, bus= Mod.EventBusSubscriber.Bus.MOD)
public class SimpleUnconsumableItems
{



    //Fellas, never ask the Forge discord a question. Worst mistake of my life!



    public static boolean isThisForForgeQuestionMark = false;
    public static final String MOD_ID = "simple_unconsumable_items";
    private static final Logger LOGGER = LogManager.getLogger();
    public static SimpleChannel handler = NetworkRegistry.newSimpleChannel(new ResourceLocation(MOD_ID, "swap_mode"), ()->"1.16.1", predicate -> true, predicate->true);
    public SimpleUnconsumableItems() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        //eventBus.addListener(this::onTick);
    }
    /*private void onTick(final TickEvent event) {
        System.out.println("thank you");
        if (Keybind.KEYBIND_ACTIVATE.isKeyDown()) {
            SimpleUnconsumableItems.isThisForForgeQuestionMark = !SimpleUnconsumableItems.isThisForForgeQuestionMark;
            ITextComponent component = SimpleUnconsumableItems.isThisForForgeQuestionMark ? new TranslationTextComponent("sui.enabled") : new TranslationTextComponent("sui.disabled");
            Minecraft.getInstance().player.sendStatusMessage(component, true);
        }
    }*/
    @SubscribeEvent
    public static void setupClient(FMLClientSetupEvent event) {
        ClientRegistry.registerKeyBinding(Keybind.KEYBIND_ACTIVATE);
    }
    @SubscribeEvent
    public static void setup(FMLCommonSetupEvent event) {
        handler.registerMessage(1, SwapModePacket.class, SwapModePacket::write, SwapModePacket::read, SwapModePacket::work);
    }


}
