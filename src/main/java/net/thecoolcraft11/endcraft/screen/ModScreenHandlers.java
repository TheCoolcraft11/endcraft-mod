package net.thecoolcraft11.endcraft.screen;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.thecoolcraft11.endcraft.Endcraft;

public class ModScreenHandlers {
    public static final ScreenHandlerType<EnderForgeConvertingScreenHandler> ENDER_FORGE_CONVERTING_SCREEN_HANDLER
            = Registry.register(Registries.SCREEN_HANDLER, new Identifier(Endcraft.MOD_ID, "ender_forge_converting"),
            new ExtendedScreenHandlerType<>((EnderForgeConvertingScreenHandler::new)));
    public static final ScreenHandlerType<EssenceAltarScreenHandler> ESSENCE_ALTAR_SCREEN_HANDLER
            = Registry.register(Registries.SCREEN_HANDLER, new Identifier(Endcraft.MOD_ID, "essence_altar"),
            new ExtendedScreenHandlerType<>((EssenceAltarScreenHandler::new)));
    public static final ScreenHandlerType<EnderChargerScreenHandler> ENDER_CHARGER_SCREEN_HANDLER
            = Registry.register(Registries.SCREEN_HANDLER, new Identifier(Endcraft.MOD_ID, "ender_charger"),
            new ExtendedScreenHandlerType<>((EnderChargerScreenHandler::new)));
    public static final ScreenHandlerType<ModTableScreenHandler> MOD_TABLE_SCREEN_HANDLER
            = Registry.register(Registries.SCREEN_HANDLER, new Identifier(Endcraft.MOD_ID, "mod_table"),
            new ExtendedScreenHandlerType<>((ModTableScreenHandler::new)));
    public static final ScreenHandlerType<EnderStaffConfigurationScreenHandler> ENDER_STAFF_CONFIGURATION_SCREEN_HANDLER
            = Registry.register(Registries.SCREEN_HANDLER, new Identifier(Endcraft.MOD_ID, "ender_staff_configuration"),
            new ExtendedScreenHandlerType<>((EnderStaffConfigurationScreenHandler::new)));

    public static void registerScreenHandlers() {
        Endcraft.LOGGER.info("Registering ScreenHandlers for " + Endcraft.MOD_ID);
    }
}
