package net.qiu.advancedbodyparam.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;

import static net.qiu.advancedbodyparam.QsAdvancedBodyParameters.MOD_ID;
import static net.qiu.advancedbodyparam.QsAdvancedBodyParameters.MOD_NAME;

public class languageProvider extends FabricLanguageProvider {

    public languageProvider(FabricDataOutput dataOutput) {
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

        // Config
        translationBuilder.add(configKey("title"), MOD_NAME);

        translationBuilder.add(configKey("fracturedComment"), "Bone fracture");
    }
}
