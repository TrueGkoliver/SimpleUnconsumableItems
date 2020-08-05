package com.gkoliver.simpleunconsumableitems.core;

import com.gkoliver.simpleunconsumableitems.SimpleUnconsumableItems;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.server.permission.context.Context;

import java.util.UUID;
import java.util.function.Supplier;

public class SwapModePacket {
    public final UUID uuidPlayer;
    public SwapModePacket(UUID uuidIn) {
        this.uuidPlayer = uuidIn;
    }
    public static String id = "simpleunconsumableitems:unconsumable_on";
    //This is a stupid packet which does nothing.
    public static void write(SwapModePacket packetIn, PacketBuffer bufferIn) {
        bufferIn.writeUniqueId(packetIn.uuidPlayer);
    }
    public static SwapModePacket read(PacketBuffer bufferIn) {
        return new SwapModePacket(bufferIn.readUniqueId());
    }

    public static void work(SwapModePacket msg, Supplier<NetworkEvent.Context> ctx) {
        SimpleUnconsumableItems.isThisForForgeQuestionMark = !SimpleUnconsumableItems.isThisForForgeQuestionMark;
        execute(ctx.get().getSender());
    }
    public static void execute(PlayerEntity entityIn) {
        ITextComponent component = SimpleUnconsumableItems.isThisForForgeQuestionMark ? new TranslationTextComponent("sui.enabled") : new TranslationTextComponent("sui.disabled");
        Minecraft.getInstance().player.sendStatusMessage(component, true);
        entityIn.getPersistentData().putBoolean(id, !entityIn.getPersistentData().getBoolean(id));
    }
}
