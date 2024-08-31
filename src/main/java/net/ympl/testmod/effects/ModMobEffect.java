package net.ympl.testmod.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.MobEffectEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.ympl.testmod.Testmod;

@Mod.EventBusSubscriber(modid= Testmod.MODID)
public class ModMobEffect extends MobEffect {
    public ModMobEffect(MobEffectCategory mobEffectCategory, int color) {
        super(mobEffectCategory, color);
    }

    public void effectEnd(LivingEntity entity, int amplifier) {}

    @SubscribeEvent
    public static void onEffectEndEvent(MobEffectEvent.Expired event) {
        if (event.getEffectInstance() == null) return;

        MobEffect effect = event.getEffectInstance().getEffect().get();

        if (effect instanceof ModMobEffect modEffect) {
            modEffect.effectEnd(event.getEntity(), event.getEffectInstance().getAmplifier());
        }
    }
}
