mport java.util.*;
public class ElectionSimulator {
    public static final int NUM_SIMS = 5;
    public static final int NUM_DISTS = 10;
    public static final double POLL_AVG = 0.52;
    public static final double POLL_ERR = 0.07;

    public static void main(String[] args) {
        System.out.println("Welcome to the Election Simulator!");
        System.out.println("Running " + NUM_SIMS + " simulations of " + NUM_DISTS +  " districts.");
        System.out.println("Our candidate is polling at " + (POLL_AVG * 100) + "% with a " +
                (POLL_ERR * 100) + "% margin of error.");
        System.out.println();
        Random rand = new Random();
        simulationOne(rand);
    }

    //getting results from simulation - either candidate wins or losses election
    public static void simulationOne(Random rand) {
        int voteYes = 0;
        int voteNo = 0;
        double totalPercent = 0.0;
        for (int t = 0; t < NUM_SIMS; t++) { //number of simulations being run
            for (int d = 0; d < NUM_DISTS; d++) { // adding votes of the 10 districts
                int voteTotal = 1 + rand.nextInt(1000);
                double error = rand.nextGaussian() * 0.5 * POLL_ERR;
                double percent = (error + POLL_AVG);
                int i = (int) (voteTotal * percent);
                voteYes += i;
                voteNo += voteTotal - i;
         }
            boolean win = voteYes > voteNo;
            double yesPercent = 100.0 *  voteYes / (voteYes + voteNo);
            double noPercent = 100.0 * voteNo / (voteYes + voteNo);
            System.out.println("Running simulation #" + (t+1) + ":");
            System.out.println("  Win? " + win);
            System.out.println("  Results: " + voteYes + " (" + yesPercent + "%) - " +
                    voteNo + " (" + noPercent + "%)");
            System.out.print("  Visualization: ");
            for (int i = 0; i < (voteYes/100); i++) {
                System.out.print("+");
         }
            System.out.println();
            System.out.print("                 ");
            for (int j = 0; j < (voteNo/100); j++) {
                System.out.print("-");
         }
            System.out.println();
            voteYes = 0;
            voteNo = 0;
            totalPercent += yesPercent;
      }
        System.out.println();
        System.out.println("Average vote percentage: " + totalPercent / NUM_SIMS + "%");
    }
}
