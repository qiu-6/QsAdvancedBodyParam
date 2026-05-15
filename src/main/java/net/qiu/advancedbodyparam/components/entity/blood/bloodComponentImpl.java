package net.qiu.advancedbodyparam.components.entity.blood;

import net.minecraft.nbt.NbtCompound;

public class bloodComponentImpl implements bloodComponent {

    private int blood = 20;

    private static final String bloodKey = "q_blood_level";

    @Override
    public void setBlood(int blood) {
        this.blood = Math.min(blood, 20);
    }

    @Override
    public int getBlood() {
        return blood;
    }

    @Override
    public void readFromNbt(NbtCompound nbtCompound) {
        blood = nbtCompound.contains(bloodKey) ? nbtCompound.getInt(bloodKey) : 20;
    }

    @Override
    public void writeToNbt(NbtCompound nbtCompound) {
        nbtCompound.putInt(bloodKey, blood);
    }
}
