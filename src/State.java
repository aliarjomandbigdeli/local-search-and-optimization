/**
 * this class is the parent of all problems state you want to define with this interface
 *
 * @author Ali ArjomandBigdeli
 * @since 12.27.2018
 */
public abstract class State {
    protected State parent;
    protected int act;

    public State() {
        parent = null;
        act = -1;
    }

    public int getAct() {
        return act;
    }
}
