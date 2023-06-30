import lombok.AllArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.IOException;
import java.io.PrintWriter;

@AllArgsConstructor
public class TrigonometricFunction {
    private final SinFunction sin;

    public TrigonometricFunction() {
        sin = new SinFunction();
    }

    public double sin(double x) {
        return sin.calculate(x);
    }

    public double getEps() {
        return sin.getEps();
    }

    public double cos(double x) {
        return sin(x + Math.PI / 2);
    }

    public double tan(double x) {
        if (isCosZero(x)) {
            if (x > 0) return Double.POSITIVE_INFINITY;
            if (x < 0) return Double.NEGATIVE_INFINITY;
        }
        return sin(x) / cos(x);
    }

    public double cot(double x) {
        if (isSinZero(x)) {
            if (x >= 0) return Double.POSITIVE_INFINITY;
            else return Double.NEGATIVE_INFINITY;
        }
        return cos(x) / sin(x);
    }

    public double sec(double x) {
        if (isCosZero(x)) return Double.POSITIVE_INFINITY;
        return 1 / cos(x);
    }

    public double csc(double x) {
        if (isSinZero(x)) {
            if (x >= 0) return Double.POSITIVE_INFINITY;
            else return Double.NEGATIVE_INFINITY;
        }
        return 1 / sin(x);
    }

    private boolean isSinZero(double x) {
        return Math.abs(sin(x) - 0) < getEps();
    }

    private boolean isCosZero(double x) {
        return Math.abs(cos(x) - 0) < getEps();
    }

    public void writeCSV(double x, PrintWriter out) {
        try {
            CSVFormat csvFormat = CSVFormat.DEFAULT.withDelimiter(',');
            CSVPrinter printer = new CSVPrinter(out, csvFormat);
            printer.printRecord(x, sin(x), cos(x), tan(x), sec(x), cot(x));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
