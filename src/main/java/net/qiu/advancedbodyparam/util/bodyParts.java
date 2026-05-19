package net.qiu.advancedbodyparam.util;

public enum bodyParts {

    HEAD(8, 8),
    TORSO(20, 20),
    LEFT_ARM(36, 52),
    RIGHT_ARM(44, 20),
    LEFT_LEG(20, 52),
    RIGHT_LEG(4, 20);

    private final int u;
    private final int v;


    bodyParts(int u, int v) {
        this.u = u;
        this.v = v;
    }

    public int getV() {
        return v;
    }

    public int getU() {
        return u;
    }

    public int getRegionHeight() {
        return this.equals(HEAD) ? 8 : 12;
    }

    public int getRegionWidth() {
        return switch (this) {
            case HEAD, TORSO -> 8;
            case LEFT_ARM, RIGHT_ARM, LEFT_LEG, RIGHT_LEG -> 4;
        };
    }
}
