import lombok.AllArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.IOException;
import java.io.PrintWriter;

@AllArgsConstructor
public class LogFunction {
    private final LnFunction ln;

    public LogFunction() {
        ln = new LnFunction();
    }

    public double ln(double x) {
        return ln.calculate(x);
    }

    public double log(double x, double base) {
        if (base == 1) return Double.NaN;
        return ln(x) / ln(base);
    }

    public void writeCSV(double x, PrintWriter out) {
        double res = log(x, 5);
        try {
            CSVFormat csvFormat = CSVFormat.DEFAULT.withDelimiter(',');
            CSVPrinter printer = new CSVPrinter(out, csvFormat);
            printer.printRecord(x, res);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
