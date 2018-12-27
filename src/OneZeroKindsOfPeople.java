import java.util.Arrays;
import java.util.Scanner;

public class OneZeroKindsOfPeople {


    private char[][] map;
    private char mark;
    private int r1, c1, r2, c2;
    private int cmax, rmax;
    private int[][] visited;
    private Scanner scanner;

    public void solve() {

        scanner = new Scanner(System.in);
        getMap();

        scanner.reset();
        int n = scanner.nextInt();

        for (int i = 0; i < n; i++) {
            mark = '0';
            getPositions();

            if (checkSamePosition()) {
                if (map[r1][c1] == '0') System.out.println("binary");
                else System.out.println("decimal");
            } else {
                createVisited();
                if (checkRoute()) {
                    System.out.println("binary");
                } else {
                    createVisited();
                    mark = '1';
                    if (checkRoute()) System.out.println("decimal");
                    else System.out.println("neither");
                }
            }
        }
        scanner.close();
    }

    private boolean checkSamePosition() {
        return r1 == r2 && c1 == c2;
    }

    private void createVisited() {
        visited = new int[rmax][cmax];
        for (int i = 0; i < rmax; i++) {
            Arrays.fill(visited[i], 0);
        }
    }

    private boolean checkRoute() {
        return checkRouteREC(r1, c1);
    }

    private boolean checkRouteREC(int r, int c) {
        if (r < 0 || r >= rmax || c < 0 || c >= cmax) return false;
        if (r == r2 && c == c2 && map[r][c] == mark) return true;
        if (visited[r][c] == 1) return false;
        if (map[r][c] != mark) return false;

        visited[r][c] = 1;
        return checkRouteREC(r + 1, c) || checkRouteREC(r - 1, c) || checkRouteREC(r, c + 1) || checkRouteREC(r, c - 1);
    }

    private void getPositions() {
        scanner.reset();
        scanner = new Scanner(System.in);

        r1 = scanner.nextInt() - 1;
        c1 = scanner.nextInt() - 1;
        r2 = scanner.nextInt() - 1;
        c2 = scanner.nextInt() - 1;


    }

    private void getMap() {

        rmax = scanner.nextInt();
        cmax = scanner.nextInt();

        map = new char[rmax][cmax];

        for (int i = 0; i < rmax; i++) {
            map[i] = scanner.next().toCharArray();
        }
    }

    public static void main(String[] args) {
        OneZeroKindsOfPeople problem = new OneZeroKindsOfPeople();
        problem.solve();
    }
}
