package Fight.Statuses;

public class StatusException extends Exception {
    public static final int DEATH = 0;
    public static final int STUN = 1;

    public final int code;

    public StatusException(int code) {
        this.code = code;
    }
}
