package com.example.examplemod;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Random;

@Mod(PersianSnacks.MOD_ID)
public class PersianSnacks {
    public static final String MOD_ID = "persiansnacks";

    private static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);

    private static final DeferredRegister<CreativeModeTab> CREATIVE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MOD_ID);

    private static final Random RANDOM = new Random();

    // ============================================
    // SNACKS
    // ============================================

    // OLIVIEH
    public static final RegistryObject<Item> OLIVIEH = ITEMS.register("olivieh",
            () -> new Item(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(12)
                            .saturationMod(1.6F)
                            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 400, 2), 1.0F)
                            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 120, 1), 0.8F)
                            .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, 200, 0), 0.5F)
                            .build())
                    .stacksTo(16)));

    // MOEINI POOR CAKE
    public static final RegistryObject<Item> MOEINI_POOR = ITEMS.register("moeini_poor",
            () -> new Item(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(10)
                            .saturationMod(1.8F)
                            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 200, 1), 1.0F)
                            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 300, 1), 0.8F)
                            .build())
                    .stacksTo(16)));

    // RANGARANG PACKAGED
    public static final RegistryObject<Item> RANGARANG_PACKAGED = ITEMS.register("rangarang_packaged",
            () -> new Item(new Item.Properties()
                    .stacksTo(64)) {
                @Override
                public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
                    ItemStack stack = player.getItemInHand(hand);
                    
                    if (!level.isClientSide) {
                        String[] messages = {
                            "§e رنگارنگ باز شد! وای چه بوی خوبی!",
                            "§6 رنگارنگ = خوشمزه‌ترین وافری که خوردی!",
                            "§a وایسا ببینم چیه! آهان رنگارنگ!",
                            "§e رنگارنگ وافری پر از عشق و انرژی!",
                            "§6 با رنگارنگ رنگین کمان رو ببین!",
                            "§a رنگارنگ = خنده + انرژی!",
                            "§e این وافره یا بهشته؟! رنگارنگ!",
                            "§6 رنگارنگ رو باز کن تا دنیا رنگین بشه!",
                            "§a یه رنگارنگ بخور قشنگ بشو!",
                            "§e رنگارنگ = طعم خونه، طعم ایران!"
                        };
                        String randomMessage = messages[level.random.nextInt(messages.length)];
                        player.displayClientMessage(
                            Component.literal(randomMessage), 
                            true
                        );
                        
                        ItemStack openedRangarang = new ItemStack(RANGARANG_UNPACKED.get(), 1);
                        player.getInventory().add(openedRangarang);
                        stack.shrink(1);
                    }
                    
                    return InteractionResultHolder.sidedSuccess(stack, level.isClientSide);
                }
            });

    // RANGARANG UNPACKED
    public static final RegistryObject<Item> RANGARANG_UNPACKED = ITEMS.register("rangarang_unpacked",
            () -> new Item(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(5)
                            .saturationMod(0.8F)
                            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 100, 1), 1.0F)
                            .effect(() -> new MobEffectInstance(MobEffects.DIG_SPEED, 80, 0), 0.5F)
                            .build())
                    .stacksTo(64)));

    // POFAK NAMAKI PACKAGED
    public static final RegistryObject<Item> POFAK_NAMAKI_PACKAGED = ITEMS.register("pofak_namaki_packaged",
            () -> new Item(new Item.Properties()
                    .stacksTo(64)) {
                @Override
                public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
                    ItemStack stack = player.getItemInHand(hand);
                    
                    if (!level.isClientSide) {
                        String[] messages = {
                            "§e پفک نمکی = انرژی!",
                            "§6 پفک نمکی خوشمزه!",
                            "§a چقدر پفک داری میخوری؟!",
                            "§e پفک نمکی = زندگی!",
                            "§6 با پفک نمکی پرواز کن!",
                            "§a پفک نمکی = قدرت!",
                            "§e شکم سیر نمیشه با پفک؟!",
                            "§6 پفک نمکی = عشق!"
                        };
                        String randomMessage = messages[level.random.nextInt(messages.length)];
                        player.displayClientMessage(
                            Component.literal(randomMessage), 
                            true
                        );
                        
                        int randomAmount = 4 + RANDOM.nextInt(2);
                        ItemStack openedPofak = new ItemStack(POFAK_NAMAKI_UNPACKED.get(), randomAmount);
                        player.getInventory().add(openedPofak);
                        stack.shrink(1);
                    }
                    
                    return InteractionResultHolder.sidedSuccess(stack, level.isClientSide);
                }
            });

    // POFAK NAMAKI UNPACKED
    public static final RegistryObject<Item> POFAK_NAMAKI_UNPACKED = ITEMS.register("pofak_namaki_unpacked",
            () -> new Item(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(8)
                            .saturationMod(1.2F)
                            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 300, 2), 1.0F)
                            .effect(() -> new MobEffectInstance(MobEffects.DIG_SPEED, 300, 2), 1.0F)
                            .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, 300, 1), 0.8F)
                            .effect(() -> new MobEffectInstance(MobEffects.JUMP, 200, 1), 0.7F)
                            .effect(() -> new MobEffectInstance(MobEffects.WEAKNESS, 60, 0), 0.2F)
                            .build())
                    .stacksTo(64)));

    // TITAP PACKAGED
    public static final RegistryObject<Item> TITAP_PACKAGED = ITEMS.register("titap_packaged",
            () -> new Item(new Item.Properties()
                    .stacksTo(64)) {
                @Override
                public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
                    ItemStack stack = player.getItemInHand(hand);
                    
                    if (!level.isClientSide) {
                        String[] messages = {
                            "§e مگه رژیم نداری؟",
                            "§6 تیتاپ = قدرت!",
                            "§6 چقدر میخوری؟!",
                            "§e شکم سیر نمیشه؟!",
                            "§6 بازم میخوای؟!",
                            "§a تیتاپ = انرژی!",
                            "§a تیتاپ = زندگی!",
                            "§b با تیتاپ پرواز کن!",
                            "§6 سلطان تیتاپ!"
                        };
                        String randomMessage = messages[level.random.nextInt(messages.length)];
                        player.displayClientMessage(
                            Component.literal(randomMessage), 
                            true
                        );
                        
                        ItemStack openedTitap = new ItemStack(TITAP_UNPACKED.get(), 1);
                        player.getInventory().add(openedTitap);
                        stack.shrink(1);
                    }
                    
                    return InteractionResultHolder.sidedSuccess(stack, level.isClientSide);
                }
            });

    // TITAP UNPACKED
    public static final RegistryObject<Item> TITAP_UNPACKED = ITEMS.register("titap_unpacked",
            () -> new Item(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(20)
                            .saturationMod(2.0F)
                            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 600, 4), 1.0F)
                            .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, 600, 4), 1.0F)
                            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 600, 4), 1.0F)
                            .effect(() -> new MobEffectInstance(MobEffects.JUMP, 600, 4), 1.0F)
                            .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 600, 3), 1.0F)
                            .effect(() -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 600, 0), 1.0F)
                            .effect(() -> new MobEffectInstance(MobEffects.NIGHT_VISION, 600, 0), 1.0F)
                            .effect(() -> new MobEffectInstance(MobEffects.ABSORPTION, 600, 10), 1.0F)
                            .effect(() -> new MobEffectInstance(MobEffects.DIG_SPEED, 600, 4), 1.0F)
                            .effect(() -> new MobEffectInstance(MobEffects.SLOW_FALLING, 600, 0), 1.0F)
                            .effect(() -> new MobEffectInstance(MobEffects.WATER_BREATHING, 600, 0), 1.0F)
                            .effect(() -> new MobEffectInstance(MobEffects.HEALTH_BOOST, 600, 10), 1.0F)
                            .alwaysEat()
                            .build())
                    .stacksTo(64)) {
                @Override
                public UseAnim getUseAnimation(ItemStack stack) {
                    return UseAnim.EAT;
                }
            });

    // ============================================
    // DRINKS
    // ============================================

    // ZAMZAM COLA
    public static final RegistryObject<Item> ZAMZAM_COLA = ITEMS.register("zamzam_cola",
            () -> new Item(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(2)
                            .saturationMod(0.2F)
                            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 100, 1), 1.0F)
                            .effect(() -> new MobEffectInstance(MobEffects.DIG_SPEED, 100, 1), 0.8F)
                            .alwaysEat()
                            .build())
                    .stacksTo(16)) {
                @Override
                public UseAnim getUseAnimation(ItemStack stack) {
                    return UseAnim.DRINK;
                }
            });

    // ZAMZAM FANTA
    public static final RegistryObject<Item> ZAMZAM_FANTA = ITEMS.register("zamzam_fanta",
            () -> new Item(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(2)
                            .saturationMod(0.2F)
                            .effect(() -> new MobEffectInstance(MobEffects.JUMP, 200, 1), 1.0F)
                            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 80, 0), 0.5F)
                            .alwaysEat()
                            .build())
                    .stacksTo(16)) {
                @Override
                public UseAnim getUseAnimation(ItemStack stack) {
                    return UseAnim.DRINK;
                }
            });

    // ZAMZAM LEMONADE
    public static final RegistryObject<Item> ZAMZAM_LEMONADE = ITEMS.register("zamzam_lemonade",
            () -> new Item(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(2)
                            .saturationMod(0.2F)
                            .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 200, 0), 1.0F)
                            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 100, 0), 0.7F)
                            .alwaysEat()
                            .build())
                    .stacksTo(16)) {
                @Override
                public UseAnim getUseAnimation(ItemStack stack) {
                    return UseAnim.DRINK;
                }
            });

    // DOOGH AB ALI
    public static final RegistryObject<Item> DOOGH_AB_ALI = ITEMS.register("doogh_ab_ali",
            () -> new Item(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(3)
                            .saturationMod(0.5F)
                            .effect(() -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 300, 0), 1.0F)
                            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 80, 0), 0.5F)
                            .alwaysEat()
                            .build())
                    .stacksTo(16)) {
                @Override
                public UseAnim getUseAnimation(ItemStack stack) {
                    return UseAnim.DRINK;
                }
            });

    // ============================================
    // CREATIVE TABS
    // ============================================

    public static final RegistryObject<CreativeModeTab> SNACKS_TAB = CREATIVE_TABS.register("snacks_tab",
            () -> CreativeModeTab.builder()
                    .title(Component.literal("Persian Snacks (اسنک های ایرانی)"))
                    .icon(() -> new ItemStack(OLIVIEH.get()))
                    .displayItems((params, output) -> {
                        output.accept(OLIVIEH.get());
                        output.accept(MOEINI_POOR.get());
                        output.accept(RANGARANG_PACKAGED.get());
                        output.accept(RANGARANG_UNPACKED.get());
                        output.accept(POFAK_NAMAKI_PACKAGED.get());
                        output.accept(POFAK_NAMAKI_UNPACKED.get());
                        output.accept(TITAP_PACKAGED.get());
                        output.accept(TITAP_UNPACKED.get());
                    })
                    .build());

    public static final RegistryObject<CreativeModeTab> DRINKS_TAB = CREATIVE_TABS.register("drinks_tab",
            () -> CreativeModeTab.builder()
                    .title(Component.literal("Persian Drinks (نوشیدنی های ایرانی)"))
                    .icon(() -> new ItemStack(ZAMZAM_COLA.get()))
                    .displayItems((params, output) -> {
                        output.accept(ZAMZAM_COLA.get());
                        output.accept(ZAMZAM_FANTA.get());
                        output.accept(ZAMZAM_LEMONADE.get());
                        output.accept(DOOGH_AB_ALI.get());
                    })
                    .build());

    @SuppressWarnings("deprecation")
    public PersianSnacks() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ITEMS.register(modEventBus);
        CREATIVE_TABS.register(modEventBus);
        MinecraftForge.EVENT_BUS.register(this);
    }
}
