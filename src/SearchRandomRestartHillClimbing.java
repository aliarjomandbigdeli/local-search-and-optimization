import java.util.Comparator;
import java.util.LinkedList;
import java.util.Random;

/**
 * First Choice Hill Climbing local search algorithm
 * First-choice hill climbing implements stochastic hill climbing by generating successors
 * randomly until one is generated that is better than the current state.
 *
 * @author Ali ArjomandBigdeli
 * @since 12.28.2018
 */
public class SearchRandomRestartHillClimbing extends Search {
    private int numberOfIteration;
    private LinkedList<State> answers;

    public SearchRandomRestartHillClimbing(Problem problem, int numberOfIteration) {
        super(problem);
        answers = new LinkedList<>();
        this.numberOfIteration = numberOfIteration;
    }

    @Override
    public void execute() {
        search();
        maxMemoryUse = (nodeSeen - nodeExpand) * nodeSize;
    }

    @Override
    public void search() {
        SearchHillClimbing hillClimbing = new SearchHillClimbing(problem);
        for (int i = 0; i < numberOfIteration; i++) {
            problem.setInitialState(problem.generateRandomState());
            hillClimbing.execute();
            answers.add(hillClimbing.answer);
        }
        answers.sort(new Comparator<State>() {
            @Override
            public int compare(State s1, State s2) {
                return ((Double) problem.h(s1)).compareTo(problem.h(s2));
            }
        });
        answer = answers.peek();
        State temp = answer;
        while (temp != null) {
            path.add(temp.act);
            temp = temp.parent;
        }

    }

}


