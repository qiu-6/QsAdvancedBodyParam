package net.qiu.advancedbodyparam.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;

import static net.qiu.advancedbodyparam.QsAdvancedBodyParameters.MOD_ID;
import static net.qiu.advancedbodyparam.QsAdvancedBodyParameters.MOD_NAME;

public class LanguageProvider extends FabricLanguageProvider {

    public LanguageProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    private static String deathMessageKey(String path) {
        return "death.attack." + path;
    }
    private static String commandKey(String path) {
        return "commands." + MOD_ID + "." + path;
    }
    private static String configKey(String path) {
        return MOD_ID + ".midnightconfig." + path;
    }
    private static String commandErrorKey(String path) {
        return MOD_ID + ".commands.error." + path;
    }
    private static String disconnectKey(String path) {
        return MOD_ID + ".disconnect." + path;
    }

    @Override
    public void generateTranslations(TranslationBuilder translationBuilder) {

        // Death message
        translationBuilder.add(deathMessageKey("hemorrhage"), "%s bleed to death");

        // Commands
        translationBuilder.add(commandKey("sourceerror"), "This command can only be run by a player when no target is specified");
        translationBuilder.add(commandKey("getblood"), "Blood level of %1$s is %2$d");
        translationBuilder.add(commandKey("setblood"), "Blood level of %1$s is set to %2$d");
        translationBuilder.add(commandKey("setmultipleblood"), "Blood level of %1$d players are set to %2$d");
        translationBuilder.add(commandKey("componenterror"), "Could not find %1$s component on %2$s");
        translationBuilder.add(commandKey("getstatusempty"), "There is no status on %1$s of %2$s");
        translationBuilder.add(commandKey("getstatus"), "Status on %1$s of %2$s: ");
        translationBuilder.add(commandKey("getstatuslist"), "Status: %1$s, Duration: %2$d, Intensity: %3$d");

        // Disconnect player
        translationBuilder.add(disconnectKey("no_component"), "You don't have %s component registered");

        // Command error
        translationBuilder.add(commandErrorKey("invalid_body_enum"), "Invalid body part %s");

        // Config
        translationBuilder.add(configKey("title"), MOD_NAME);

        translationBuilder.add(configKey("disable_status_in_peaceful"), "Disable status in peaceful");

        translationBuilder.add(configKey("fractured_comment"), "Bone fracture");

        translationBuilder.add(configKey("fracture_fall_comment"), "Thresholds of falling to trigger potential bone fracture");
        translationBuilder.add(configKey("mild_fall_threshold"), "Threshold to cause non-displaced fracture");
        translationBuilder.add(configKey("mid_fall_threshold"), "Threshold to cause displaced fracture");
        translationBuilder.add(configKey("severe_fall_threshold"), "Threshold to cause open fracture");
    }
}
