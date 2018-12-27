import java.util.Scanner;

public class Game2048 {


    private int[][] map;
    private Scanner scanner;
    private int[] helpfulArray;
    private int move;

    public void solve() {
        generateField();

        move = scanner.nextInt();

        buildHelpfulArraysForDirection(move);


    }

    private void buildHelpfulArraysForDirection(int move) {

        switch (move) {
            case 0:

                for (int x = 0; x < 4; x++) {

                    helpfulArray = new int[4];
                    for (int y = 0; y < 4; y++) {
                        helpfulArray[y] = map[y][x];
                    }

                    playOnArray(helpfulArray);

                    for (int y = 0; y < 4; y++) {
                        map[y][x] = helpfulArray[y];
                    }

                }


                break;
            case 1:
            case 2:
            case 3:

        }
    }

    private void playOnArray(int[] helpfulArray) {

        for (int i = 0; i < 3; i++) {
            if (i + 1 < 4 && helpfulArray[i] != 0) {
                int next = i + 1;
                while (next < 3 && helpfulArray[next] == 0) {
                    next++;
                }
                if (helpfulArray[i] == helpfulArray[next]) {
                    helpfulArray[i] *= 2;
                    helpfulArray[next] = 0;
                }
            }



        }
        moveAllWay(helpfulArray);

    }


    private void moveAllWay(int[] helpfulArray) {
        for (int j = 0; j < 4; j++) {
            for (int i = 3; i > 0; i--) {
                if (helpfulArray[i] != 0 && helpfulArray[i - 1] == 0) {
                    helpfulArray[i - 1] = helpfulArray[i];
                    helpfulArray[i] = 0;
                }
            }
        }

    }


    private void generateField() {
        scanner = new Scanner(System.in);
        map = new int[4][4];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                map[j][i] = scanner.nextInt();
            }
        }

    }


    public static void main(String[] args) {
        Game2048 problem = new Game2048();

        problem.solve();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(problem.map[j][i] + " ");
            }
            System.out.println();
        }
    }
}
