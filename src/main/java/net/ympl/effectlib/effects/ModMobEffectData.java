package net.ympl.effectlib.effects;

import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

import java.util.function.BiConsumer;

public class ModMobEffectData {
    private final String name;
    private final MobEffectCategory category;
    private final int color;
    private BiConsumer<LivingEntity, Integer> onTick;
    private BiConsumer<LivingEntity, Integer> onStart;
    private BiConsumer<LivingEntity, Integer> onEnd;
    private int tickDelay;

    public ModMobEffectData(String name, MobEffectCategory category, int color) {
        this.name = name;
        this.category = category;
        this.color = color;
        this.onEnd = (entity, amp) -> {};
        this.onStart = (entity, amp) -> {};
        this.onTick = (entity, amp) -> {};
        this.tickDelay = 1;
    }

    public String getName() {
        return name;
    }

    public MobEffectCategory getCategory() {
        return category;
    }

    public int getColor() {
        return color;
    }

    public void addOnTick(BiConsumer<LivingEntity, Integer> onTick) {
        this.onTick = onTick;
    }

    public boolean applyOnTick(LivingEntity entity, int amplifier) {
        onTick.accept(entity, amplifier);
        return true;
    }

    public void addOnStart(BiConsumer<LivingEntity, Integer> onStart) {
        this.onStart = onStart;
    }

    public void applyOnStart(LivingEntity entity, int amplifier) {
        onStart.accept(entity, amplifier);
    }

    public void addOnEnd(BiConsumer<LivingEntity, Integer> onEnd) {
        this.onEnd = onEnd;
    }

    public void applyOnEnd(LivingEntity entity, int amplifier) {
        onEnd.accept(entity, amplifier);
    }

    public void setTickDelay(int n) {
        this.tickDelay = n;
    }

    public int getTickDelay() {
        return this.tickDelay;
    }
}