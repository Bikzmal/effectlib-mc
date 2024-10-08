package net.ympl.effectlib.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.MobEffectEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.ympl.effectlib.Effectlib;

@Mod.EventBusSubscriber(modid=Effectlib.MODID)
public class ModMobEffect extends MobEffect {
    public ModMobEffect(MobEffectCategory mobEffectCategory, int color) {
        super(mobEffectCategory, color);
    }

    public void effectEnd(LivingEntity entity, int amplifier) {}
    public void effectRemoved(LivingEntity entity, int amplifier) {}

    @SubscribeEvent
    public static void onEffectEndEvent(MobEffectEvent.Expired event) {
        if (event.getEffectInstance() == null) return;

        MobEffect effect = event.getEffectInstance().getEffect().get();

        if (effect instanceof ModMobEffect modEffect) {
            modEffect.effectEnd(event.getEntity(), event.getEffectInstance().getAmplifier());
        }
    }

    @SubscribeEvent
    public static void onEffectRemoveEvent(MobEffectEvent.Remove event) {
        if (event.getEffectInstance() == null) return;

        MobEffect effect = event.getEffectInstance().getEffect().get();

        if (effect instanceof ModMobEffect modEffect) {
            modEffect.effectRemoved(event.getEntity(), event.getEffectInstance().getAmplifier());
        }
    }
}