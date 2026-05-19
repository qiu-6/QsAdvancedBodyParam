package net.qiu.advancedbodyparam.util.status;

public class StatusHelper {

    private final Status status;
    private int duration;
    private int intensity;

    public StatusHelper(Status status, int duration, int intensity) {
        this.status = status;
        this.duration = duration;
        this.intensity = intensity;
    }

    public boolean tick() {

        if (status.getIsPersistent()) return false;

        if (duration > 0) {
            duration--;
        }

        return duration <= 0;
    }
}
