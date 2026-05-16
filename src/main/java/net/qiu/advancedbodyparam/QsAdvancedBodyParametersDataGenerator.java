package net.qiu.advancedbodyparam;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.entity.damage.DamageScaling;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;
import net.qiu.advancedbodyparam.datagen.damageTypeProvider;
import net.qiu.advancedbodyparam.datagen.languageProvider;
import net.qiu.advancedbodyparam.util.tags.damageTypes;

public class QsAdvancedBodyParametersDataGenerator implements DataGeneratorEntrypoint {

	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {

		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		QsAdvancedBodyParameters.LOGGER.info("Datagen for language provider");
		pack.addProvider(languageProvider::new);
		QsAdvancedBodyParameters.LOGGER.info("Datagen for damage types provider");
		pack.addProvider(damageTypeProvider::new);
	}

	@Override
	public void buildRegistry(RegistryBuilder registryBuilder) {

		registryBuilder.addRegistry(RegistryKeys.DAMAGE_TYPE, entries ->
				entries.register(damageTypes.HEMORRHAGE, new DamageType("hemorrhage", DamageScaling.NEVER, 0)));
	}
}
