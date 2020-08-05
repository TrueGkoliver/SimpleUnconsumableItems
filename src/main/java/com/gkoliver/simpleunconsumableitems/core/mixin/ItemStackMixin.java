package com.gkoliver.simpleunconsumableitems.core.mixin;

import com.gkoliver.simpleunconsumableitems.SimpleUnconsumableItems;
import com.gkoliver.simpleunconsumableitems.core.SwapModePacket;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.IItemProvider;
import net.minecraftforge.common.capabilities.CapabilityProvider;
import net.minecraftforge.common.extensions.IForgeItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;
import java.util.Random;

@Mixin(ItemStack.class)
public class ItemStackMixin {
    //This is going to go into Simple Unbreakable Tools because why not lol
    //public boolean attemptDamageItem(int amount, Random rand, @Nullable ServerPlayerEntity damager) { return false; }
    @Inject(method = "net.minecraft.item.ItemStack.shrink(I)V", at = @At("HEAD"), cancellable = true, remap=false)
    public void check(CallbackInfo ci) {
        if (Minecraft.getInstance().player!=null) {
            boolean dead = Minecraft.getInstance().player.getPersistentData().getBoolean(SwapModePacket.id);
        }
        if (SimpleUnconsumableItems.isThisForForgeQuestionMark) {
            ci.cancel();
        }
        return;
    }
}