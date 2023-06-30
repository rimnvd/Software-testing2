import lombok.AllArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.IOException;
import java.io.PrintWriter;

@AllArgsConstructor
public class FunctionSystem {
    private final LogFunction logF;
    private final TrigonometricFunction trF;

    public double calculate(double x) {
        if (x <= 0) {
            return (((((Math.pow((((((((((Math.pow(Math.pow(trF.sin(x) - trF.cot(x), 2), 2)) / trF.tan(x)) - trF.sec(x)) + (Math.pow(trF.csc(x), 2))) +
                    ((trF.csc(x) - trF.csc(x)) * (Math.pow(Math.pow(trF.tan(x), 2), 2)))) * (trF.tan(x) * (trF.sin(x) / (trF.cos(x) +
                    (trF.sec(x) / trF.csc(x)))))) + trF.cos(x)) / ((trF.cot(x) / ((trF.csc(x) * trF.cos(x)) - trF.cot(x))) * trF.csc(x))) *
                    trF.sec(x)) + trF.cot(x), 2)) - trF.tan(x)) * (trF.tan(x) + (((trF.cot(x) * ((trF.cos(x) - trF.sin(x)) + trF.sec(x))) *
                    (trF.csc(x) - trF.sec(x))) / (Math.pow(trF.csc(x), 2))))) - ((trF.cos(x) * ((((trF.tan(x) + (((trF.csc(x) / trF.sec(x)) - trF.sec(x)) *
                    trF.cos(x))) - ((trF.cot(x) + trF.cos(x)) + trF.cot(x))) / ((trF.sin(x) + (Math.pow(Math.pow(trF.tan(x), 3), 2))) - (Math.pow(trF.sec(x) - (trF.sin(x) *
                    (trF.csc(x) + (trF.cos(x) * ((trF.cos(x) / trF.csc(x)) / trF.sin(x))))), 2)))) / trF.cos(x))) * (Math.pow(((trF.sec(x) * trF.sec(x)) -
                    trF.tan(x)) + (trF.cos(x) / trF.sec(x)), 2)))) + (((Math.pow(Math.pow((trF.cos(x) * trF.cot(x)) * ((trF.sin(x) + trF.csc(x)) - ((trF.cot(x) -
                    trF.sec(x)) * (trF.sin(x) - (trF.tan(x) * trF.cot(x))))), 3), 3)) - (Math.pow(trF.sin(x) * trF.cos(x), 3))) + (((Math.pow((Math.pow(trF.sin(x), 3)) /
                    trF.cos(x), 2) + (((trF.cot(x) / (trF.tan(x) / (trF.tan(x) / trF.tan(x)))) * trF.cot(x)) + trF.cot(x))) - trF.sec(x)))));
        }
        return ((Math.pow(((logF.log(x, 10) - logF.log(x, 10)) * logF.log(x, 5)) * logF.log(x, 5), 2)) - (Math.pow(logF.log(x, 3), 2)));
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
