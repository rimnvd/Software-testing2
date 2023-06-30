import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LogFunctionTest {
    private static final double PRECISION = 0.01;

    @DisplayName("Test log with ln Mock")
    @ParameterizedTest
    @MethodSource("provideArgsForLog")
    public void integrateTestLog(double x, double base, double expected) {
        LnFunction ln = Mockito.spy(LnFunction.class);
        Mockito.when(ln.calculate(1)).thenReturn(0d);
        Mockito.when(ln.calculate(2)).thenReturn(0.693);
        Mockito.when(ln.calculate(32)).thenReturn(3.466);
        Mockito.when(ln.calculate(0.5)).thenReturn(-0.693);
        Mockito.when(ln.calculate(0.125)).thenReturn(-2.079);
        Mockito.when(ln.calculate(7)).thenReturn(1.946);
        Mockito.when(ln.calculate(49)).thenReturn(3.892);
        Mockito.when(ln.calculate(1d / 7d)).thenReturn(-1.945);
        Mockito.when(ln.calculate(1d / 49d)).thenReturn(-3.892);
        LogFunction log = new LogFunction(ln);
        assertEquals(expected, log.log(x, base), PRECISION);
    }

    private static Stream<Arguments> provideArgsForLog() {
        return Stream.of(
                Arguments.of(1, 2, 0),
                Arguments.of(2, 2, 1),
                Arguments.of(32, 2, 5),
                Arguments.of(0.5, 2, -1),
                Arguments.of(0.125, 2, -3),
                Arguments.of(2, 1, Double.NaN),
                Arguments.of(7, 7, 1),
                Arguments.of(49, 7, 2),
                Arguments.of(1d / 7, 7, -1),
                Arguments.of(1d / 49, 7, -2),
                Arguments.of(-4, 5, Double.NaN),
                Arguments.of(-0.00001, 12, Double.NaN)
        );
    }
}
