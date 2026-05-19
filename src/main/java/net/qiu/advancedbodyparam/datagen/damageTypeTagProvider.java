package net.qiu.advancedbodyparam.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.qiu.advancedbodyparam.util.tags.damageTypeTags;

import java.util.concurrent.CompletableFuture;

public class damageTypeTagProvider extends FabricTagProvider<DamageType> {

    public damageTypeTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, RegistryKeys.DAMAGE_TYPE, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup lookup) {
        getOrCreateTagBuilder(damageTypeTags.CAUSE_FRACTURE)
                .add(DamageTypes.FALL);
    }
}
