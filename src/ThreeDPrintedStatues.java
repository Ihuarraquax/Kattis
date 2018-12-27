import java.util.Scanner;

public class ThreeDPrintedStatues {

    private int printers;
    private int statues;
    private int moves;
    private int goal;

    public void solve() {
        Scanner scanner = new Scanner(System.in);
        solve(scanner.nextInt());
    }

    public void solve(int goal) {
        printers = 1;
        statues = 0;
        moves = 0;


        this.goal = goal;

        createEnoughtPrintersToPrintStatuesInOneMove();
        System.out.println(moves);
    }

    private void createEnoughtPrintersToPrintStatuesInOneMove() {
        while (printers < goal) createAllPrinters();
        createAllStatues();
    }


    private void createAllPrinters() {
        printers *= 2;
        moves++;
    }

    private void createAllStatues() {
        statues += printers;
        moves++;
    }

    private void createPrinters(int amount) {
        if (amount >= printers) {
            createAllPrinters();
        } else {
            statues += printers - amount;
            printers += amount;
        }
        moves++;
    }

    private void createStatues(int amount) {
        if (amount >= printers) {
            createAllStatues();
        } else {
            statues += amount;
            printers += printers - amount;
        }
        moves++;

    }


    public static void main(String[] args) {

        ThreeDPrintedStatues problem = new ThreeDPrintedStatues();

        problem.solve();


    }
}
