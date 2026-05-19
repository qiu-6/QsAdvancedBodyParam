package net.qiu.advancedbodyparam.util.tags;

import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import static net.qiu.advancedbodyparam.QsAdvancedBodyParameters.MOD_ID;

public class DamageTypeTags {

    public static final TagKey<DamageType> CAUSE_FRACTURE = TagKey.of(
            RegistryKeys.DAMAGE_TYPE,
            Identifier.of(MOD_ID, "cause_fracture")
    );
}
