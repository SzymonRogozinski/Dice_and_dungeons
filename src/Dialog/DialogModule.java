package Dialog;

public class DialogModule {

    private NPCLines pointedNPC;
    private boolean refreshDialog;

    public DialogModule() {
    }

    public boolean isRefreshDialog() {
        return refreshDialog;
    }

    public void setRefreshDialog(boolean refreshDialog) {
        this.refreshDialog = refreshDialog;
    }

    public NPCLines getPointedNPC() {
        return pointedNPC;
    }

    public void setPointedNPC(NPCLines pointedNPC) {
        this.pointedNPC = pointedNPC;
        refreshDialog=true;
    }
}
