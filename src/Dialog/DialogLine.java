package Dialog;

public class DialogLine {

    private final String line, response;
    private final int[] nextDialogs;
    private final boolean removeAfterUsed;
    private boolean read;

    public DialogLine(String line, String response, int[] nextDialogs, boolean removeAfterUsed) {
        this.line = line;
        this.response=response;
        this.nextDialogs = nextDialogs;
        this.removeAfterUsed = removeAfterUsed;
    }

    public String getLine() {
        return line;
    }

    public String getResponse() {
        return response;
    }

    public int[] getNextDialogs() {
        return nextDialogs;
    }

    public boolean isRemoveAfterUsed() {
        return removeAfterUsed;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead() {
        this.read = true;
    }
}
