import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.concurrent.ThreadLocalRandom;

public class TenKindsOfPeopleFileRead {
    private BufferedReader br;
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

    public static void main(String[] args) throws IOException {
        TenKindsOfPeopleFileRead problem = new TenKindsOfPeopleFileRead();

        ThreadLocalRandom rand = ThreadLocalRandom.current();
        TenKindsGenerator generator = new TenKindsGenerator();

        problem.solve();
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


    private void getPositions() throws IOException {


        String input = br.readLine();

        stringTokenizer = new StringTokenizer(input);

        r1 = Integer.parseInt(stringTokenizer.nextToken()) - 1;
        c1 = Integer.parseInt(stringTokenizer.nextToken()) - 1;
        r2 = Integer.parseInt(stringTokenizer.nextToken()) - 1;
        c2 = Integer.parseInt(stringTokenizer.nextToken()) - 1;


    }

    private void getMap() throws IOException {

        String input = br.readLine();

        stringTokenizer = new StringTokenizer(input);

        rmax = Integer.parseInt(stringTokenizer.nextToken());
        cmax = Integer.parseInt(stringTokenizer.nextToken());

        map = new int[rmax][cmax];

        for (int i = 0; i < rmax; i++) {
            input = br.readLine();
            for (int j = 0; j < cmax; j++) {
                map[i][j] = Character.getNumericValue(input.charAt(j));
            }
        }

    }

    private boolean checkSamePosition() {
        return r1 == r2 && c1 == c2;
    }

    public void solve() throws IOException {


        File file = new File("C:\\Users\\Hubii\\Documents\\Projects\\gs-accessing-data-mysql-master\\Kattis\\sample-00.in");
        br = new BufferedReader(new FileReader(file));


        getMap();

        int n = 0;
        try {
            n = Integer.parseInt(br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }

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
    }

}
