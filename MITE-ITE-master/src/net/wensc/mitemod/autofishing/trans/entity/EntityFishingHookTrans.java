package net.wensc.mitemod.autofishing.trans.entity;


import net.minecraft.EntityFishingHook;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = EntityFishingHook.class, priority = 2000)
public abstract class EntityFishingHookTrans extends net.minecraft.Entity {
   @Shadow
   public net.minecraft.EntityPlayer angler;

   public EntityFishingHookTrans(net.minecraft.World par1World) {
      super(par1World);
   }

   @Inject(method = "checkForBite",at = @At("RETURN"))
   public void AutoCheckForBite(CallbackInfoReturnable<Boolean> callback) {
      if(!worldObj.isRemote) {
         if(angler != null && callback.getReturnValue()) {
            angler.fishingStatus = 2;
         }
      }
   }

}