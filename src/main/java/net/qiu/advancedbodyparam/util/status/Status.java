package net.qiu.advancedbodyparam.util.status;

public enum Status {

    FRACTURE();

    private final int maxDuration;
    private final boolean isPersistent;

    Status(int maxDuration, boolean isPersistent) {
        this.maxDuration = maxDuration;
        this.isPersistent = isPersistent;
    }

    Status() {
        this.maxDuration = 114514;
        this.isPersistent = true;
    }

    public boolean getIsPersistent() {
        return isPersistent;
    }

    public int getMaxDuration() {
        return maxDuration;
    }
}
