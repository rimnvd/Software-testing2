import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Main {
    public static void main(String[] args) {
        FunctionSystem functionSystem = new FunctionSystem(new LogFunction(), new TrigonometricFunction());
        String out = "fs_big.csv";
        PrintWriter CSVOut;
        try {
            CSVOut = new PrintWriter(out);
            for (double x = -300; x < 300; x = x + 0.001) {
                functionSystem.writeCSV(x, CSVOut);
            }
            CSVOut.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
