import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class TenKindsOfPeople {


    private Scanner scanner;
    private StringTokenizer stringTokenizer;
    private int[][] map;
    private int mark;
    private int r1;
    private int c1;
    private int r2;
    private int c2;
    private int cmax;
    private int rmax;
    private int[][] visited;

    public void solve() {

        scanner = new Scanner(System.in);
        getMap();

        int n = scanner.nextInt();

        for (int i = 0; i < n; i++) {
            mark = 0;
            getPositions();

            if (checkSamePosition()) {
                if (map[r1][c1] == 0) System.out.println("binary");
                else System.out.println("decimal");
            } else {
                createVisited();
                if (checkRoute()) {
                    System.out.println("binary");
                } else {
                    createVisited();
                    mark = 1;
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
        scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        stringTokenizer = new StringTokenizer(input);

        r1 = Integer.parseInt(stringTokenizer.nextToken()) - 1;
        c1 = Integer.parseInt(stringTokenizer.nextToken()) - 1;
        r2 = Integer.parseInt(stringTokenizer.nextToken()) - 1;
        c2 = Integer.parseInt(stringTokenizer.nextToken()) - 1;


    }

    private void getMap() {

        String input = scanner.nextLine();

        stringTokenizer = new StringTokenizer(input);

        rmax = Integer.parseInt(stringTokenizer.nextToken());
        cmax = Integer.parseInt(stringTokenizer.nextToken());

        map = new int[rmax][cmax];

        for (int i = 0; i < rmax; i++) {
            input = scanner.nextLine();
            for (int j = 0; j < cmax; j++) {
                map[i][j] = Character.getNumericValue(input.charAt(j));
            }
        }

    }


    public static void main(String[] args) {
        TenKindsOfPeople problem = new TenKindsOfPeople();

        problem.solve();
    }
}
