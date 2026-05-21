package net.qiu.advancedbodyparam;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.command.v2.ArgumentTypeRegistry;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.command.argument.serialize.ConstantArgumentSerializer;
import net.minecraft.util.Identifier;
import net.qiu.advancedbodyparam.command.command;
import net.qiu.advancedbodyparam.config.ConfigManager;
import net.qiu.advancedbodyparam.util.argumentTypes.BodyPartsArgumentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QsAdvancedBodyParameters implements ModInitializer {
	public static final String MOD_ID = "qadvancedbodyparam";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final String MOD_NAME = "Q's advanced body parameters";

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Registering " + MOD_NAME);

		LOGGER.info("Registering custom command argument types for " + MOD_NAME);
		ArgumentTypeRegistry.registerArgumentType(
				new Identifier(MOD_ID, "body_part_argument"),
				BodyPartsArgumentType.class,
				ConstantArgumentSerializer.of(BodyPartsArgumentType::bodyPartsArgument)
		);

		CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> command.register(dispatcher));

		LOGGER.info("Loading config for " + MOD_NAME);
		ConfigManager.loadConfig();
	}
}