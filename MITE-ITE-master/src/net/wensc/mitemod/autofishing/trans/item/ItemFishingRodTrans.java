package net.wensc.mitemod.autofishing.trans.item;

import net.minecraft.EntityPlayer;
import net.minecraft.IDamageableItem;
import net.minecraft.Item;
import net.minecraft.ItemFishingRod;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = ItemFishingRod.class, priority = 2000)
public class ItemFishingRodTrans extends Item implements IDamageableItem {

    @Shadow
    public int getNumComponentsForDurability() {
        return 0;
    }

    @Inject(method = "onItemRightClick", at=@At("RETURN"))
    public void onItemRightClick(EntityPlayer player, float partial_tick, boolean ctrl_is_down, CallbackInfoReturnable<Boolean> callbackInfoReturnable) {
        if(callbackInfoReturnable.getReturnValue()) {
            if(!player.worldObj.isRemote) {
                if(ctrl_is_down) {
                    player.isOpenAutoFishing = !player.isOpenAutoFishing;
                    player.addChatMessage("已" + (player.isOpenAutoFishing ? "开启" : "关闭") + "自动钓鱼");
                }
            }
        }

    }
}
