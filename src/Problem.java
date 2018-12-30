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

    public void setInitialState(State initialState) {
        this.initialState = initialState;
    }

    abstract public ArrayList<Integer> actions(State state);

    abstract public State nextState(State state, int action);

    abstract public double stepCost(State firstState, int action, State secondState);

    abstract public double pathCost(List<Integer> path);

    /**
     * heuristic function/objective function
     * heuristic function should return lower values for better states
     */
    public double h(State state) {
        return 0;
    }

    /**
     * fitness function/objective function
     * fitness function should return higher values for better states
     * <p>
     * NOTE, you should override it based on your problem to use genetic algorithm
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

    //######################## genetic algorithm methods ############################

    /**
     * you should override it based on your problem to use genetic algorithm
     */
    public State crossover(State parent1, State parent2) {
        return crossover(parent1, parent2, 1);
    }


    public State crossover(State parent1, State parent2, int numberOfCrossoverPoint) {
        return null;
    }

    public int getNumberOfGenomes() {
        return 0;
    }

    public State mutation(State state, int index) {
        return null;
    }
}
