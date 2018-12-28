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
     * heuristic function/objective function
     */
    public double h(State state) {
        return 0;
    }

    /**
     * fitness function
     * you should override it based on your problem to use genetic algorithm
     */
    public double fitness(State state) {
        return 0;
    }

    /**
     * you should override it based on your problem to use genetic algorithm
     */
    public State generateRandomState() {
        return null;
    }
}
