package net.qiu.advancedbodyparam.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.entity.damage.DamageScaling;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryWrapper;
import net.qiu.advancedbodyparam.util.tags.DamageTypes;

import java.util.concurrent.CompletableFuture;

public class DamageTypeProvider extends FabricDynamicRegistryProvider {

    public DamageTypeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup, Entries entries) {

        entries.add(DamageTypes.HEMORRHAGE, new DamageType("hemorrhage", DamageScaling.NEVER, 0));
    }

    @Override
    public String getName() {
        return "Damage Types Provider";
    }
}
