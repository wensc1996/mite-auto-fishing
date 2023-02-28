package net.wensc.mitemod.autofishing.trans.entity;

import net.minecraft.*;
import net.xiaoyu233.fml.util.ReflectHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = EntityPlayer.class, priority = 2000)
public abstract class EntityPlayerTrans extends EntityLiving implements ICommandListener {
   @Shadow
   public EntityFishingHook fishEntity;

   public boolean isOpenAutoFishing = false;
   public int fishingStatus = 0;
   public EntityPlayerTrans(World par1World) {
      super(par1World);
   }

   @Inject(method = "onLivingUpdate",
           at = @At(value = "INVOKE",
                   target = "Lnet/minecraft/EntityLiving;onLivingUpdate()V",
                   shift = At.Shift.AFTER))
   private void injectTick(CallbackInfo c){

      if(!this.worldObj.isRemote) {
         if(isOpenAutoFishing) {
            if(this.getHeldItem() instanceof ItemFishingRod) {
               if(this.fishingStatus == 0) {
                  if(this.fishEntity == null) {
                     this.fishingStatus = 1;
                     this.onEntityRightClicked(ReflectHelper.dyCast(EntityPlayer.class,this), this.getHeldItemStack());
                     this.getHeldItem().onItemRightClick(ReflectHelper.dyCast(EntityPlayer.class, this),1F, false);
                  }
               }else if(this.fishingStatus == 2) {
                  if(this.fishEntity != null) {
                     this.fishingStatus = 0;
                     this.getHeldItem().onItemRightClick(ReflectHelper.dyCast(EntityPlayer.class, this), 1F, false);
                  }
               }
            } else {
               this.fishingStatus = 0;
            }
         } else {
            this.fishingStatus = 0;
         }
      }
   }
}
