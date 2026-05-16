package net.qiu.advancedbodyparam;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.qiu.advancedbodyparam.command.command;
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

		CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> command.register(dispatcher));
	}
}