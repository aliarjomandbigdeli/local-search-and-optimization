import java.util.LinkedList;

public class GeneticAlgorithm {
    private Problem problem;
    private int populationSize;
    private LinkedList<State> population;

    public GeneticAlgorithm(Problem problem, int populationSize) {
        this.problem = problem;
        this.populationSize = populationSize;
    }

    private void initializePopulation() {
        population = new LinkedList<>();
        for (int i = 0; i < populationSize; i++) {
            population.add(problem.generateRandomState());
        }
    }
}
