import java.util.ArrayList;
import java.util.List;

/**
 * this class is the parent of all problems you want to define with this interface
 *
 * @author Ali ArjomandBigdeli
 * @since 12.27.2018
 */
public abstract class Problem {
    protected State initialState;

    public State getInitialState() {
        return initialState;
    }

    abstract public ArrayList<Integer> actions(State state);

    abstract public State nextState(State state, int action);

    abstract public double stepCost(State firstState, int action, State secondState);

    abstract public double pathCost(List<Integer> path);

    /**
     * heuristic function
     * you should override it based on your problem to use AStar search
     */
    public double h(State state){
        return 0;
    }

    public double f(){
        return 0;
    }
}
