package labs.lab0;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class Variant1TestJupiter {

    @ParameterizedTest
    @MethodSource
    public void arrayTest(double[] array, int[] result) {
        assertArrayEquals(result, new Variant1().arrayTask(array));
    }

    private static Stream<Arguments> arrayTest() {
        return Stream.of(
                Arguments.of(new double[]{2, 3, 6}, new int[]{0}),
                Arguments.of(new double[]{12, 6, 3}, new int[]{0,1,2})
        );
    }


}
