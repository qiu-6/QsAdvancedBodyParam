package net.qiu.advancedbodyparam.util.tags;

import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import static net.qiu.advancedbodyparam.QsAdvancedBodyParameters.MOD_ID;

public class damageTypes {

    public static final RegistryKey<DamageType> HEMORRHAGE = RegistryKey.of(
            RegistryKeys.DAMAGE_TYPE,
            new Identifier(MOD_ID, "blood_death")
    );
}
