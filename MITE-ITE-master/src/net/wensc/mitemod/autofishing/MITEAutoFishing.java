package net.wensc.mitemod.autofishing;


import net.wensc.mitemod.autofishing.trans.TransMarker;
import net.xiaoyu233.fml.AbstractMod;


import net.xiaoyu233.fml.classloading.Mod;
import net.xiaoyu233.fml.config.InjectionConfig;
import org.spongepowered.asm.mixin.MixinEnvironment;

@Mod(MixinEnvironment.Side.SERVER)
public class MITEAutoFishing extends AbstractMod {

    public MITEAutoFishing() {
    }

    public void preInit() {
    }


    @Override
    public InjectionConfig getInjectionConfig() {
        return InjectionConfig.Builder.of("mite-auto-fishing", TransMarker.class.getPackage(), MixinEnvironment.Phase.INIT).setRequired().build();
    }


    public void postInit() {
    }


    public String modId() {
        return "mite-auto-fishing";
    }

    public int modVerNum() {
        return 1;
    }

    public String modVerStr() {
        return "0.0.1";
    }
}
