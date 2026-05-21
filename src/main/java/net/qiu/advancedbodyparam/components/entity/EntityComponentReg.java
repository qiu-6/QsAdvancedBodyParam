package net.qiu.advancedbodyparam.components.entity;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import dev.onyxstudios.cca.api.v3.entity.RespawnCopyStrategy;
import net.minecraft.util.Identifier;
import net.qiu.advancedbodyparam.components.entity.blood.BloodComponent;
import net.qiu.advancedbodyparam.components.entity.blood.BloodComponentImpl;
import net.qiu.advancedbodyparam.components.entity.status.StatusComponent;
import net.qiu.advancedbodyparam.components.entity.status.StatusComponentImpl;

import static net.qiu.advancedbodyparam.QsAdvancedBodyParameters.MOD_ID;

public class EntityComponentReg implements EntityComponentInitializer {

    public static final ComponentKey<BloodComponent> BLOOD_COMPONENT =
            ComponentRegistry.getOrCreate(
                    new Identifier(MOD_ID, "blood"),
                    BloodComponent.class
            );

    public static final ComponentKey<StatusComponent> STATUS_COMPONENT =
            ComponentRegistry.getOrCreate(
                    new Identifier(MOD_ID, "status"),
                    StatusComponent.class
            );

    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry entityComponentFactoryRegistry) {

        entityComponentFactoryRegistry.registerForPlayers(
                BLOOD_COMPONENT,
                player -> new BloodComponentImpl(),
                RespawnCopyStrategy.LOSSLESS_ONLY
        );

        entityComponentFactoryRegistry.registerForPlayers(
                STATUS_COMPONENT,
                StatusComponentImpl::new,
                RespawnCopyStrategy.LOSSLESS_ONLY
        );
    }
}
