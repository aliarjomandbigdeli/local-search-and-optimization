
public class Run {

    public static void main(String[] args) {

        Problem problem = new MapColoringProblem();
//        Search search = new SearchHillClimbing(problem);
        Search search = new SimulatedAnnealing(problem);
        search.execute();
        System.out.print("path: ");
        for (int i = search.getPath().size() - 2; i >= 0; i--) {
            System.out.print(search.getPath().get(i) + " ");
        }
        System.out.print("\nanswer: ");
        for (int i : ((MapColorState) search.answer).getColors()) {
            System.out.print(i + " ");
        }
        System.out.println("\nh(Number of conflict color): "+problem.h(search.answer));

        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(problem,100,5,0.01,500);
        geneticAlgorithm.execute();
        System.out.print("\nanswer: ");
        for (int i : ((MapColorState) geneticAlgorithm.getAnswer()).getColors()) {
            System.out.print(i + " ");
        }
        System.out.println("\nfitness(Number of conflict color): "+problem.fitness(geneticAlgorithm.getAnswer()));
    }
}
