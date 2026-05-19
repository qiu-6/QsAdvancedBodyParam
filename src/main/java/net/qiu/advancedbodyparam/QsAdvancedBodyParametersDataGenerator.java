package net.qiu.advancedbodyparam;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.entity.damage.DamageScaling;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;
import net.qiu.advancedbodyparam.datagen.DamageTypeProvider;
import net.qiu.advancedbodyparam.datagen.DamageTypeTagProvider;
import net.qiu.advancedbodyparam.datagen.LanguageProvider;
import net.qiu.advancedbodyparam.util.tags.DamageTypes;

public class QsAdvancedBodyParametersDataGenerator implements DataGeneratorEntrypoint {

	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {

		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		QsAdvancedBodyParameters.LOGGER.info("Datagen for language provider");
		pack.addProvider(LanguageProvider::new);
		QsAdvancedBodyParameters.LOGGER.info("Datagen for damage type provider");
		pack.addProvider(DamageTypeProvider::new);
		QsAdvancedBodyParameters.LOGGER.info("Datagen for damage type tag provider");
		pack.addProvider(DamageTypeTagProvider::new);
	}

	@Override
	public void buildRegistry(RegistryBuilder registryBuilder) {

		registryBuilder.addRegistry(RegistryKeys.DAMAGE_TYPE, entries ->
				entries.register(DamageTypes.HEMORRHAGE, new DamageType("hemorrhage", DamageScaling.NEVER, 0)));
	}
}
