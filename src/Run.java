
public class Run {

    public static void main(String[] args) {

        Problem problem = new MapColoringProblem();
        Search search=new SearchHillClimbing(false);
        search.setProblem(problem);
        search.execute();
        System.out.print("path: ");
        for (int i = search.getPath().size() - 2; i >= 0; i--) {
            System.out.print(search.getPath().get(i) + " ");
        }
    }
}
