import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class GeneticAlgorithm {
    private Problem problem;
    private int populationSize;
    private LinkedList<State> population;
    private int tornumentSize;
    private LinkedList<State> parents;
    private double mutationRate;
    private int numberOfGenerations;

    public GeneticAlgorithm(Problem problem, int populationSize,
                            int tornumentSize, double mutationRate, int numberOfGenerations) {
        this.problem = problem;
        this.populationSize = populationSize;
        this.tornumentSize = tornumentSize;
        this.mutationRate = mutationRate;
        this.numberOfGenerations = numberOfGenerations;
        initializePopulation();

    }

    private void initializePopulation() {
        population = new LinkedList<>();
        for (int i = 0; i < populationSize; i++) {
            population.add(problem.generateRandomState());
        }
    }

    /**
     * tornument selection or parent selection
     * it select the best chromosome form tornumentSize that were chosen randomly
     */
    private void tornumentSelection() {
        parents = new LinkedList<>();
        int num = populationSize / tornumentSize;
        int[] choices = new int[tornumentSize];
        Random random = new Random();
        for (int i = 0; i < num; i++) {
            for (int j = 0; j < tornumentSize; j++) {
                choices[j] = random.nextInt(populationSize);
            }
            parents.add(population.get(bestChoice(choices)));
        }
    }

    private int bestChoice(int[] choices) {
        int bestChoice = 0;
        for (int choice : choices) {
            if (problem.fitness(population.get(choice)) >
                    problem.fitness(population.get(bestChoice))) {
                bestChoice = choice;
            }
        }
        return bestChoice;
    }
}
