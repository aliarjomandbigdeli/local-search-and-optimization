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
    private int numberOfCrossoverPoint;
    private State answer;

    private ArrayList<Double> bestFitnessOfGenerations;
    private ArrayList<Double> avgFitnessOfGenerations;
    private ArrayList<Double> worstFitnessOfGenerations;

    public GeneticAlgorithm(Problem problem, int populationSize, int tornumentSize,
                            double mutationRate, int numberOfGenerations, int numberOfCrossoverPoint) {
        this.problem = problem;
        this.populationSize = populationSize;
        this.tornumentSize = tornumentSize;
        this.mutationRate = mutationRate;
        this.numberOfGenerations = numberOfGenerations;
        this.numberOfCrossoverPoint = numberOfCrossoverPoint;

        bestFitnessOfGenerations = new ArrayList<>();
        worstFitnessOfGenerations = new ArrayList<>();
        avgFitnessOfGenerations = new ArrayList<>();
    }

    public GeneticAlgorithm(Problem problem, int populationSize,
                            int tornumentSize, double mutationRate, int numberOfGenerations) {
        this(problem, populationSize, tornumentSize,
                mutationRate, numberOfGenerations, 1);


    }

    public State getAnswer() {
        return answer;
    }

    public Problem getProblem() {
        return problem;
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

    private LinkedList<State> newGeneration() {
        LinkedList<State> newGeneration = new LinkedList<>();
        Random random = new Random();
        for (int i = 0; i < populationSize; i++) {
            int x = random.nextInt(parents.size());
            int y = random.nextInt(parents.size());
            while (x == y) {
                y = random.nextInt(parents.size());
            }
            newGeneration.add(problem.crossover(parents.get(x), parents.get(y), numberOfCrossoverPoint));
        }
        return newGeneration;
    }

//    private LinkedList<State> mutation(LinkedList<State> generation) {
//        int mutatedGenomes = ((Double) (populationSize * problem.getNumberOfGenomes() * mutationRate)).intValue();
//        for (State state : generation) {
//            problem.mutation(state, mutatedGenomes);
//        }
//        return generation;
//    }

    private LinkedList<State> mutation(LinkedList<State> generation) {
        Random random = new Random();
        int mutatedGenomes = ((Double) (populationSize * problem.getNumberOfGenomes() * mutationRate)).intValue();
        for (int i = 0; i < mutatedGenomes; i++) {
            problem.mutation(generation.get(random.nextInt(generation.size())),
                    random.nextInt(problem.getNumberOfGenomes()));
        }
        return generation;
    }

    public void execute() {
        initializePopulation();
        calculateFitnessOfGeneration();
        for (int i = 0; i < numberOfGenerations; i++) {
            tornumentSelection();
            LinkedList<State> newGeneration = newGeneration();
            mutation(newGeneration);
            population = newGeneration;
            calculateFitnessOfGeneration();
        }
        answer = population.get(0);
        for (State state : population) {
            if (problem.fitness(state) > problem.fitness(answer)) {
                answer = state;
            }
        }
    }

    private void calculateFitnessOfGeneration() {
        double best = problem.fitness(population.get(0));
        double worst = problem.fitness(population.get(0));
        double sum = 0;
        double temp = 0;
        for (State state : population) {
            temp = problem.fitness(state);
            if (best < temp)
                best = temp;
            if (worst > temp)
                worst = temp;
            sum += temp;
        }
        bestFitnessOfGenerations.add(best);
        worstFitnessOfGenerations.add(worst);
        avgFitnessOfGenerations.add(sum / population.size());

    }
}
