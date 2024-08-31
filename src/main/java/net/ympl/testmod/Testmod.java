package net.ympl.testmod;

import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import net.ympl.testmod.effects.ModMobEffectData;
import net.ympl.testmod.util.MobEffectsUtil;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Testmod.MODID)
public class Testmod {

    // Define mod id in a common place for everything to reference
    public static final String MODID = "testmod";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public Testmod() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register the commonSetup method for modloading
        bus.addListener(this::commonSetup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        ModMobEffectData data;

        data = new ModMobEffectData("fun_effect", MobEffectCategory.NEUTRAL, 0);
        data.addOnStart((entity, amplifier) -> {
            entity.sendSystemMessage(Component.literal("EFFECT STARTED " + amplifier));
        });
        data.addOnTick((entity, amplifier) -> {
            entity.teleportTo(entity.getX(), entity.getY() + amplifier + 1, entity.getZ());
        });
        data.addOnEnd((entity, amplifier) -> {
            entity.sendSystemMessage(Component.literal("EFFECT ENDED"));
        });

        data.setTickDelay(20);
        MobEffectsUtil.createEffect(data);

        data = new ModMobEffectData("fun_effect_2", MobEffectCategory.NEUTRAL, 16777215);
        data.addOnTick((entity, amplifier) -> {
            ItemStack item = entity.getMainHandItem();
            if (item.isEmpty()) return;
            item.setDamageValue(item.getDamageValue()+1+amplifier);
        });
        MobEffectsUtil.createEffect(data);

        MobEffectsUtil.register(bus);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        // Some common setup code
        LOGGER.info("HELLO FROM COMMON SETUP");
        LOGGER.info("DIRT BLOCK >> {}", ForgeRegistries.BLOCKS.getKey(Blocks.DIRT));
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            // Some client setup code
            LOGGER.info("HELLO FROM CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
        }
    }
}
