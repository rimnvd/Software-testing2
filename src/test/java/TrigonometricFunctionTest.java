import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

import java.util.stream.Stream;

import static java.lang.Math.sqrt;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static java.lang.Math.*;


public class TrigonometricFunctionTest {

    private static final double PRECISION = 0.00001;

    static SinFunction sinMock = Mockito.spy(SinFunction.class);

    static {
        Mockito.when(sinMock.calculate(0)).thenReturn(0d);
        Mockito.when(sinMock.calculate(PI / 6)).thenReturn(0.5);
        Mockito.when(sinMock.calculate(PI / 4)).thenReturn(sqrt(2) / 2);
        Mockito.when(sinMock.calculate(PI / 3)).thenReturn(sqrt(3) / 2);
        Mockito.when(sinMock.calculate(PI / 2)).thenReturn(1d);
        Mockito.when(sinMock.calculate(2 * PI / 3)).thenReturn(sqrt(3) / 2);
        Mockito.when(sinMock.calculate(3 * PI / 4)).thenReturn(sqrt(2) / 2);
        Mockito.when(sinMock.calculate(5 * PI / 6)).thenReturn(0.5);
        Mockito.when(sinMock.calculate(PI)).thenReturn(0d);
        Mockito.when(sinMock.calculate(PI / 6 + 4 * PI)).thenReturn(0.5);
        Mockito.when(sinMock.calculate(PI / 3 + 7 * PI)).thenReturn(-sqrt(3) / 2);
        Mockito.when(sinMock.calculate(3 * PI)).thenReturn(0d);
        Mockito.when(sinMock.calculate(4 * PI)).thenReturn(0d);
        Mockito.when(sinMock.calculate(7 * PI / 6)).thenReturn(-0.5);
        Mockito.when(sinMock.calculate(5 * PI / 4)).thenReturn(-sqrt(2) / 2);
        Mockito.when(sinMock.calculate(4 * PI / 3)).thenReturn(-sqrt(3) / 2);
        Mockito.when(sinMock.calculate(7 * PI / 6 + PI / 2)).thenReturn(-sqrt(3) / 2);
        Mockito.when(sinMock.calculate(5 * PI / 4 + PI / 2)).thenReturn(-sqrt(2) / 2);
        Mockito.when(sinMock.calculate(4 * PI / 3 + PI / 2)).thenReturn(-0.5);
        Mockito.when(sinMock.calculate(3 * PI / 2)).thenReturn(-1d);
    }

    @DisplayName("Test cos with sin Mock")
    @ParameterizedTest
    @MethodSource("provideArgsForCos")
    public void testCosWithSinMock(double x, double expected) {
        TrigonometricFunction tr = new TrigonometricFunction(sinMock);
        assertEquals(expected, tr.cos(x), PRECISION);
        assertEquals(expected, tr.cos(-x), PRECISION);
    }

    @DisplayName("Test cos without any mocks")
    @ParameterizedTest
    @MethodSource("provideArgsForCos")
    public void testCos(double x, double expected) {
        TrigonometricFunction tr = new TrigonometricFunction();
        assertEquals(expected, tr.cos(x), PRECISION);
        assertEquals(expected, tr.cos(-x), PRECISION);
    }

    private static Stream<Arguments> provideArgsForCos() {
        return Stream.of(
                Arguments.of(0, 1),
                Arguments.of(PI / 6, sqrt(3) / 2),
                Arguments.of(PI / 4, sqrt(2) / 2),
                Arguments.of(PI / 3, 0.5),
                Arguments.of(PI / 2, 0),
                Arguments.of(2 * PI / 3, -0.5),
                Arguments.of(3 * PI / 4, -sqrt(2) / 2),
                Arguments.of(5 * PI / 6, -sqrt(3) / 2),
                Arguments.of(PI, -1),
                Arguments.of(PI / 6 + 4 * PI, sqrt(3) / 2),
                Arguments.of(PI / 3 + 7 * PI, -0.5),
                Arguments.of(3 * PI, -1),
                Arguments.of(4 * PI, 1)
        );
    }

    @DisplayName("Test tan with sin Mock")
    @ParameterizedTest
    @MethodSource("provideArgsForTan")
    public void testTanWithSinMock(double x, double expected) {
        TrigonometricFunction tr = new TrigonometricFunction(sinMock);
        assertEquals(expected, tr.tan(x), PRECISION);
        assertEquals(-expected, tr.tan(-x), PRECISION);
    }

    @DisplayName("Test tan without any mocks")
    @ParameterizedTest
    @MethodSource("provideArgsForTan")
    public void testTan(double x, double expected) {
        TrigonometricFunction tr = new TrigonometricFunction();
        assertEquals(expected, tr.tan(x), PRECISION);
        assertEquals(-expected, tr.tan(-x), PRECISION);
    }

    private static Stream<Arguments> provideArgsForTan() {
        return Stream.of(
                Arguments.of(0, 0),
                Arguments.of(PI / 6, sqrt(3) / 3),
                Arguments.of(PI / 4, 1),
                Arguments.of(PI / 3, sqrt(3)),
                Arguments.of(PI / 2, Double.POSITIVE_INFINITY),
                Arguments.of(PI, 0),
                Arguments.of(5 * PI / 6, -sqrt(3) / 3),
                Arguments.of(PI / 6 + 4 * PI, sqrt(3) / 3),
                Arguments.of(PI / 3 + 7 * PI, sqrt(3)),
                Arguments.of(3 * PI, 0),
                Arguments.of(-5 * PI, 0),
                Arguments.of(11 * PI / 2, Double.POSITIVE_INFINITY)
        );
    }

    @DisplayName("Test cot with sin Mock")
    @ParameterizedTest
    @MethodSource("provideArgsForCot")
    public void testCotWithSinMock(double x, double expected) {
        TrigonometricFunction tr = new TrigonometricFunction(sinMock);
        assertEquals(expected, tr.cot(x), PRECISION);
        assertEquals(-expected, tr.cot(-x), PRECISION);
        assertEquals(Double.POSITIVE_INFINITY, tr.cot(0));
    }

    @DisplayName("Test cot without any mocks")
    @ParameterizedTest
    @MethodSource("provideArgsForCot")
    public void testCot(double x, double expected) {
        TrigonometricFunction tr = new TrigonometricFunction();
        assertEquals(expected, tr.cot(x), PRECISION);
        assertEquals(-expected, tr.cot(-x), PRECISION);
        assertEquals(Double.POSITIVE_INFINITY, tr.cot(0));
    }

    private static Stream<Arguments> provideArgsForCot() {
        return Stream.of(
                Arguments.of(PI / 6, sqrt(3)),
                Arguments.of(PI / 4, 1),
                Arguments.of(PI / 3, sqrt(3) / 3),
                Arguments.of(PI / 2, 0),
                Arguments.of(PI, Double.POSITIVE_INFINITY),
                Arguments.of(5 * PI / 6, -sqrt(3)),
                Arguments.of(PI / 6 + 4 * PI, sqrt(3)),
                Arguments.of(PI / 3 + 7 * PI, sqrt(3) / 3),
                Arguments.of(3 * PI, Double.POSITIVE_INFINITY),
                Arguments.of(4 * PI, Double.POSITIVE_INFINITY)
        );
    }

    @DisplayName("Test sec with sin Mock")
    @ParameterizedTest
    @MethodSource("provideArgsForSec")
    public void testSecWithSinMock(double x, double expected) {
        TrigonometricFunction tr = new TrigonometricFunction(sinMock);
        assertEquals(expected, tr.sec(x), PRECISION);
        assertEquals(expected, tr.sec(-x), PRECISION);
    }

    @DisplayName("Test sec without any mocks")
    @ParameterizedTest
    @MethodSource("provideArgsForSec")
    public void testSec(double x, double expected) {
        TrigonometricFunction tr = new TrigonometricFunction();
        assertEquals(expected, tr.sec(x), PRECISION);
        assertEquals(expected, tr.sec(-x), PRECISION);
    }

    private static Stream<Arguments> provideArgsForSec() {
        return Stream.of(
                Arguments.of(0, 1),
                Arguments.of(PI / 3, 2),
                Arguments.of(PI / 4, sqrt(2)),
                Arguments.of(PI / 6, 2 / sqrt(3)),
                Arguments.of(2 * PI / 3, -2),
                Arguments.of(3 * PI / 4, -sqrt(2)),
                Arguments.of(5 * PI / 6, -2 / sqrt(3)),
                Arguments.of(PI, -1),
                Arguments.of(7 * PI / 6, -2 / sqrt(3)),
                Arguments.of(4 * PI / 3, -2),
                Arguments.of(5 * PI / 4, -sqrt(2))
        );
    }

    @DisplayName("Test csc with sin Mock")
    @ParameterizedTest
    @MethodSource("provideArgsForCsc")
    public void testCscWithSinMock(double x, double expected) {
        TrigonometricFunction tr = new TrigonometricFunction(sinMock);
        assertEquals(expected, tr.csc(x), PRECISION);
        assertEquals(-expected, tr.csc(-x), PRECISION);
    }

    @DisplayName("Test csc without any mocks")
    @ParameterizedTest
    @MethodSource("provideArgsForCsc")
    public void testCsc(double x, double expected) {
        TrigonometricFunction tr = new TrigonometricFunction();
        assertEquals(expected, tr.csc(x), PRECISION);
        assertEquals(-expected, tr.csc(-x), PRECISION);
    }

    private static Stream<Arguments> provideArgsForCsc() {
        return Stream.of(
                Arguments.of(PI / 2, 1),
                Arguments.of(PI / 3, 2 / sqrt(3)),
                Arguments.of(PI / 4, sqrt(2)),
                Arguments.of(PI / 6, 2),
                Arguments.of(2 * PI / 3, 2 / sqrt(3)),
                Arguments.of(3 * PI / 4, sqrt(2)),
                Arguments.of(5 * PI / 6, 2),
                Arguments.of(7 * PI / 6, -2),
                Arguments.of(4 * PI / 3, -2 / sqrt(3)),
                Arguments.of(5 * PI / 4, -sqrt(2)),
                Arguments.of(7 * PI / 6 + PI / 2, -2 / sqrt(3)),
                Arguments.of(4 * PI / 3 + PI / 2, -2),
                Arguments.of(5 * PI / 4 + PI / 2, -sqrt(2)),
                Arguments.of(3 * PI / 2, -1),
                Arguments.of(PI, Double.POSITIVE_INFINITY)
        );
    }
}
