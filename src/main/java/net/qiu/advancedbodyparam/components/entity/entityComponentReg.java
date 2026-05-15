package net.qiu.advancedbodyparam.components.entity;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import dev.onyxstudios.cca.api.v3.entity.RespawnCopyStrategy;
import net.minecraft.util.Identifier;
import net.qiu.advancedbodyparam.components.entity.blood.bloodComponent;
import net.qiu.advancedbodyparam.components.entity.blood.bloodComponentImpl;

import static net.qiu.advancedbodyparam.QsAdvancedBodyParameters.MOD_ID;

public class entityComponentReg implements EntityComponentInitializer {

    public static final ComponentKey<bloodComponent> BLOOD_COMPONENT =
            ComponentRegistry.getOrCreate(
                    new Identifier(MOD_ID, "blood"),
                    bloodComponent.class
            );

    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry entityComponentFactoryRegistry) {

        entityComponentFactoryRegistry.registerForPlayers(
                BLOOD_COMPONENT,
                player -> new bloodComponentImpl(),
                RespawnCopyStrategy.LOSSLESS_ONLY
        );
    }
}
