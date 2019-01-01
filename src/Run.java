import java.util.Scanner;

/**
 * this class is used to solve a problem by running one of the algorithms
 *
 * @author Ali ArjomandBigdeli
 * @since 12.31.2018
 */
public class Run {

    public static void main(String[] args) {
        Problem problem = new MapColoringProblem();
        Search search = new SearchHillClimbing(problem);
        GeneticAlgorithm geneticAlgorithm;
        System.out.println("Local Search, optimization Algorithms: ");
        System.out.println("1. Hill Climbing \n2. Stochastic Hill Climbing\n3. First Choice Hill Climbing\n" +
                "4. Random Restart Hill Climbing\n5. Simulated Annealing\n6. Genetic Algorithm");
        System.out.print("please choose your algorithm number: ");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                search = new SearchHillClimbing(problem);
                break;
            case 2:
                search = new SearchStochasticHillClimbing(problem);
                break;
            case 3:
                search = new SearchFirstChoiceHillClimbing(problem);
                break;
            case 4:
                System.out.print("please enter the number of iteration: ");
                int num = scanner.nextInt();
                search = new SearchRandomRestartHillClimbing(problem, num);
                break;
            case 5:
                search = new SimulatedAnnealing(problem);
                break;
            case 6:
                System.out.print("please enter the population size: ");
                int populationSize = scanner.nextInt();
                System.out.print("please enter the tournament size: ");
                int tournamentSize = scanner.nextInt();
                System.out.print("please enter the mutation rate: ");
                double mutationRate = scanner.nextDouble();
                System.out.print("please enter the number of generations: ");
                int numberOfGenerations = scanner.nextInt();
                System.out.print("please enter the number of crossOver point: ");
                int numberOfCrossOverPoint = scanner.nextInt();
                geneticAlgorithm = new GeneticAlgorithm(problem, populationSize
                        , tournamentSize, mutationRate, numberOfGenerations, numberOfCrossOverPoint);
                showResultOfGeneticAlgorithm(geneticAlgorithm);
                break;
            default:
                search = new SearchHillClimbing(problem);
                break;
        }
        if (choice != 6) {
            search.search();
            showResultOfSearch(search);
        }

    }

    public static void showResultOfSearch(Search search) {
        System.out.println("Result of the " + search.getClass().getSimpleName());
        System.out.print("path: ");
        for (int i = search.getPath().size() - 2; i >= 0; i--) {
            System.out.print(search.getPath().get(i) + " ");
        }
        System.out.println();
        System.out.print("answer: ");
        for (int i : ((MapColorState) search.answer).getColors()) {
            System.out.print(i + " ");
        }
        System.out.println("\nh(Number of conflict color): " + search.problem.h(search.answer));
        System.out.println("Depth of the result: " + search.getNodeExpand());
        System.out.println("Number of node that has been seen: " + search.getNodeSeen());
        System.out.println("Number of node that has been expanded: " + search.getNodeExpand());
        System.out.println("Maximum memory used: " + search.getMaxNodeKeptInMemory());
    }

    public static void showResultOfGeneticAlgorithm(GeneticAlgorithm geneticAlgorithm) {
        geneticAlgorithm.execute();
        System.out.print("\nanswer: ");
        for (int i : ((MapColorState) geneticAlgorithm.getAnswer()).getColors()) {
            System.out.print(i + " ");
        }
        System.out.println("\nfitness: " + geneticAlgorithm.getProblem().fitness(geneticAlgorithm.getAnswer()));
        System.out.println("bests: ");
        for (Double aDouble : geneticAlgorithm.getBestFitnessOfGenerations()) {
            System.out.print(aDouble + ", ");
        }
        System.out.println("\nworst: ");
        for (Double aDouble : geneticAlgorithm.getWorstFitnessOfGenerations()) {
            System.out.print(aDouble + ", ");
        }
        System.out.println("\naverage: ");
        for (Double aDouble : geneticAlgorithm.getAvgFitnessOfGenerations()) {
            System.out.print(aDouble + ", ");
        }
    }
}
