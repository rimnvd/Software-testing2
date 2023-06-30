import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static java.lang.Math.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SinFunctionTest {

    private static final double PRECISION = 0.00001;

    SinFunction sinFunction = new SinFunction();

    @ParameterizedTest
    @MethodSource("provideArgsForSin")
    void positiveArgsTest(double x, double expected) {
        assertEquals(expected, sinFunction.calculate(x), PRECISION);
    }

    @ParameterizedTest
    @MethodSource("provideArgsForSin")
    void negativeArgsTest(double x, double expected) {
        assertEquals(-expected, sinFunction.calculate(-x), PRECISION);
    }

    @Test
    void specialValuesTest() {
        assertEquals(Double.NaN, sinFunction.calculate(Double.NaN));
        assertEquals(Double.NaN, sinFunction.calculate(Double.POSITIVE_INFINITY));
        assertEquals(Double.NaN, sinFunction.calculate(Double.NEGATIVE_INFINITY));
    }

    private static Stream<Arguments> provideArgsForSin() {
        return Stream.of(
                Arguments.of(0, 0),
                Arguments.of(PI / 6, 0.5),
                Arguments.of(PI / 4, sqrt(2) / 2),
                Arguments.of(PI / 3, sqrt(3) / 2),
                Arguments.of(PI / 2, 1),
                Arguments.of(2 * PI / 3, sqrt(3) / 2),
                Arguments.of(3 * PI / 4, sqrt(2) / 2),
                Arguments.of(5 * PI / 6, 0.5),
                Arguments.of(PI, 0),
                Arguments.of(PI / 6 + 4 * PI, 0.5),
                Arguments.of(PI / 3 + 7 * PI, -sqrt(3) / 2),
                Arguments.of(3 * PI, 0),
                Arguments.of(4 * PI, 0)
        );
    }
}
