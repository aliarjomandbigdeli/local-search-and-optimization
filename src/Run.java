import java.util.Scanner;

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
                geneticAlgorithm = new GeneticAlgorithm(problem, 100
                        , 5, 0.01, 50);
//                System.out.print("please enter the population size: ");
//                int populationSize = scanner.nextInt();
//                System.out.print("please enter the tornument size: ");
//                int tornumentSize = scanner.nextInt();
//                System.out.print("please enter the mutation rate: ");
//                double mutationRate = scanner.nextDouble();
//                System.out.print("please enter the number of generations: ");
//                int numberOfGenerations = scanner.nextInt();
//                geneticAlgorithm = new GeneticAlgorithm(problem, populationSize
//                        , tornumentSize, mutationRate, numberOfGenerations);
                showResultOfGeneticAlgorithm(geneticAlgorithm);
                break;
            default:
                search = new SearchHillClimbing(problem);
                break;
        }
        if (choice != 6) {
            search.execute();
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
        System.out.println("h(Number of conflict color): " + geneticAlgorithm.getProblem().h(geneticAlgorithm.getAnswer()));
    }
}
