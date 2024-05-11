package Fight;

public abstract class Tagable {
    private final Tags[] tags;

    public Tagable(Tags[] tags) {
        this.tags = tags;
    }

    public boolean haveTag(Tags tag){
        for(Tags t:tags){
            if(t==tag)
                return true;
        }
        return false;
    }
}
