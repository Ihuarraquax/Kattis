import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class ArcticNetwork {


    List<Outpost> outposts = new LinkedList<>();
    int n;
    double[][] distances;

    public void solve(int satelites) {
        calculateOutpostDistances();
        setClosestOutpost();

        LinkedList<Double> sortedList = new LinkedList<>();

        for (Outpost outpost : outposts) {
            sortedList.add(outpost.closestDistance);
        }
        sortedList.sort((x, y) -> y.compareTo(x));

        for (int i = 0; i < satelites; i++) {
            sortedList.pop();
        }
        System.out.printf("%.2f", sortedList.pop());
        System.out.println();

    }


    public void addOutpost(int x, int y) {
        outposts.add(new Outpost(x, y));
    }

    public void calculateOutpostDistances() {
        n = outposts.size();

        distances = new double[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                distances[i][j] = distanceBetween(outposts.get(i), outposts.get(j));
            }
        }
    }

    public void setClosestOutpost() {
        for (int i = 0; i < n; i++) {
            int closestOutpostIndex = Integer.MAX_VALUE;
            double distance = Double.MAX_VALUE;
            for (int j = 0; j < n; j++) {
                if (distances[i][j] < distance && distances[i][j] != 0.0) {
                    closestOutpostIndex = j;
                    distance = distances[i][j];
                }
            }
            outposts.get(i).closestOutpost = outposts.get(closestOutpostIndex);
            outposts.get(i).closestDistance = distance;
        }
    }

    private double distanceBetween(Outpost outpost1, Outpost outpost2) {
        double x1, x2, y1, y2;
        x1 = outpost1.cords.getWidth();
        x2 = outpost2.cords.getWidth();
        y1 = outpost1.cords.getHeight();
        y2 = outpost2.cords.getHeight();


        return Math.hypot(x1 - x2, y1 - y2);

    }

    public void print() {
        for (int i = 0; i < distances.length; i++) {
            for (int j = 0; j < distances.length; j++) {
                System.out.printf("%.2f", distances[i][j]);
                System.out.print("\t\t");
            }
            System.out.println();
        }
        for (int i = 0; i < distances.length; i++) {

            System.out.println("Closest outpost from " + i + " is " + outposts.indexOf(outposts.get(i).closestOutpost) + " at " + outposts.get(i).closestDistance);
        }
    }


    private class Outpost {
        Dimension cords = new Dimension();
        Outpost closestOutpost;
        double closestDistance;

        public Outpost(int x, int y) {
            cords.setSize(x, y);
        }

    }

    public static void main(String[] args) {
        ArcticNetwork problem = new ArcticNetwork();

        problem.addOutpost(0, 100);
        problem.addOutpost(0, 300);
        problem.addOutpost(0, 600);
        problem.addOutpost(150, 750);


        problem.solve(2);
        problem.print();

    }
}




