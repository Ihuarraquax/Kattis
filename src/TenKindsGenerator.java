import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

public class TenKindsGenerator {

    ThreadLocalRandom rand = ThreadLocalRandom.current();

    public void generateTestData(int r, int c, int n) {


        File file = new File("test.txt");

        try (FileWriter writer = new FileWriter(file)) {
            BufferedWriter bw = new BufferedWriter(writer);

            String line = r + " " + c;
            bw.write(line);
            bw.newLine();

            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    stringBuilder.append(rand.nextInt(2));

                }
                bw.write(stringBuilder.toString());
                bw.newLine();
                stringBuilder = new StringBuilder();
            }

            bw.write(String.valueOf(n));
            bw.newLine();
            stringBuilder = new StringBuilder();
            for (int i = 0; i < n; i++) {
                stringBuilder.append(rand.nextInt(r)+1);
                stringBuilder.append(" ");
                stringBuilder.append(rand.nextInt(c)+1);
                stringBuilder.append(" ");
                stringBuilder.append(rand.nextInt(r)+1);
                stringBuilder.append(" ");
                stringBuilder.append(rand.nextInt(c)+1);

                bw.write(stringBuilder.toString());
                stringBuilder = new StringBuilder();
                bw.newLine();
            }
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
