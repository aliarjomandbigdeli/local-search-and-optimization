import java.util.Comparator;
import java.util.LinkedList;

/**
 * Random Restart Hill Climbing search algorithm
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
    public void search() {
        SearchHillClimbing hillClimbing = new SearchHillClimbing(problem);
        for (int i = 0; i < numberOfIteration; i++) {
            problem.setInitialState(problem.generateRandomState());
            hillClimbing.search();
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
        createSolutionPath(temp);
    }

}


