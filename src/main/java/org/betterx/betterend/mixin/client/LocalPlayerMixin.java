package org.betterx.betterend.mixin.client;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.protocol.game.ServerboundPlayerCommandPacket;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.ProfilePublicKey;
import net.minecraft.world.item.ElytraItem;
import net.minecraft.world.item.ItemStack;

import com.mojang.authlib.GameProfile;
import org.betterx.betterend.interfaces.FallFlyingItem;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import org.jetbrains.annotations.Nullable;

@Mixin(LocalPlayer.class)
public abstract class LocalPlayerMixin extends AbstractClientPlayer {
    @Final
    @Shadow
    public ClientPacketListener connection;

    public LocalPlayerMixin(ClientLevel clientLevel,
                            GameProfile gameProfile,
                            @Nullable ProfilePublicKey profilePublicKey) {
        super(clientLevel, gameProfile, profilePublicKey);
    }

    @Inject(method = "aiStep", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/LocalPlayer;getItemBySlot(Lnet/minecraft/world/entity/EquipmentSlot;)Lnet/minecraft/world/item/ItemStack;", shift = Shift.AFTER))
    public void be_aiStep(CallbackInfo info) {
        ItemStack itemStack = getItemBySlot(EquipmentSlot.CHEST);
        if (itemStack.getItem() instanceof FallFlyingItem && ElytraItem.isFlyEnabled(itemStack) && tryToStartFallFlying()) {
            connection.send(new ServerboundPlayerCommandPacket(
                    LocalPlayer.class.cast(this),
                    ServerboundPlayerCommandPacket.Action.START_FALL_FLYING
            ));
        }
    }
}
