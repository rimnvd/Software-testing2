import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.IOException;
import java.io.PrintWriter;

@NoArgsConstructor
public class SinFunction {

    @Getter
    private final double eps = 0.00000001;

    public double calculate(double arg) {
        if (Double.isNaN(arg) || Double.isInfinite(arg)) return Double.NaN;
        double result = 0;
        double previous = 0;
        double current = Double.MAX_VALUE;
        int n = 0;
        while (Math.abs(current - previous) >= eps) {
            previous = current;
            int sign = n % 2 == 0 ? 1 : -1;
            current = sign * Math.pow(arg, 2 * n + 1) / calcFactorial(2 * n + 1);
            result += current;
            n++;
        }
        return result;
    }

    private double calcFactorial(int n) {
        double result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    public void writeCSV(double x, PrintWriter out) {
        double res = calculate(x);
        try {
            CSVFormat csvFormat = CSVFormat.DEFAULT.withDelimiter(',');
            CSVPrinter printer = new CSVPrinter(out, csvFormat);
            printer.printRecord(x, res);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
