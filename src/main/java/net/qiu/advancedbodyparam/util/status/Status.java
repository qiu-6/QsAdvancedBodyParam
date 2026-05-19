package net.qiu.advancedbodyparam.util.status;

public enum Status {

    FRACTURE(2);

    private final int maxDuration;
    private final boolean isPersistent;
    private final int maxIntensity;

    Status(int maxDuration, boolean isPersistent, int maxIntensity) {
        this.maxDuration = maxDuration;
        this.isPersistent = isPersistent;
        this.maxIntensity = maxIntensity;
    }

    Status(int maxIntensity) {
        this.maxIntensity = maxIntensity;
        this.maxDuration = 114514;
        this.isPersistent = true;
    }

    public boolean getIsPersistent() {
        return isPersistent;
    }

    public int getMaxDuration() {
        return maxDuration;
    }

    public int getMaxIntensity() {
        return maxIntensity;
    }
}
