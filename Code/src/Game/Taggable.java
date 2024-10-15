package Game;

public abstract class Taggable {
    public final Tags[] tags;

    public Taggable(Tags[] tags) {
        this.tags = tags;
    }

    public boolean haveTag(Tags tag) {
        for (Tags t : tags) {
            if (t == tag)
                return true;
        }
        return false;
    }
}
