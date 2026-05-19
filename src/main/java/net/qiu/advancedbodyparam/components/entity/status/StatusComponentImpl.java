package net.qiu.advancedbodyparam.components.entity.status;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.qiu.advancedbodyparam.util.status.Status;
import net.qiu.advancedbodyparam.util.status.StatusHelper;

import java.util.EnumMap;

public class StatusComponentImpl implements StatusComponent {

    private EnumMap<Status, StatusHelper> statusMap;

    private final PlayerEntity player;

    public StatusComponentImpl(PlayerEntity player) {
        this.player = player;
    }

    @Override
    public void readFromNbt(NbtCompound nbtCompound) {

    }

    @Override
    public void writeToNbt(NbtCompound nbtCompound) {

    }
}
