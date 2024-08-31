package net.ympl.effectlib.util;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.ympl.effectlib.effects.ModMobEffect;
import net.ympl.effectlib.effects.ModMobEffectData;
import org.jetbrains.annotations.NotNull;

public class MobEffectsUtil {
    public static DeferredRegister<MobEffect> EFFECTS;

    public static void initialize(String modid) {
        EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, modid);
    }

    public static void createEffect(ModMobEffectData data) {
        EFFECTS.register(data.getName(), () -> new ModMobEffect(data.getCategory(), data.getColor()) {
            @Override
            public void effectEnd(LivingEntity entity, int amplifier) {
                data.applyOnEnd(entity, amplifier);
            }

            @Override
            public void onEffectStarted(@NotNull LivingEntity entity, int amplifier) {
                data.applyOnStart(entity, amplifier);
            }

            @Override
            public boolean applyEffectTick(@NotNull LivingEntity entity, int amplifier) {
                return data.applyOnTick(entity, amplifier);
            }

            private final int delay = data.getTickDelay();
            private int counter = 0;
            @Override
            public boolean shouldApplyEffectTickThisTick(int d, int a) {
                counter++;
                if (counter >= delay) {
                    counter = 0;
                    return true;
                }
                return false;
            }
        });
    }

    public static void register(IEventBus bus) {
        EFFECTS.register(bus);
    }
}