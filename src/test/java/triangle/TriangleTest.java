package triangle;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.*;

class TriangleTest {
    public static Triangle t;

    @BeforeAll
    public static void init() throws Exception {
        t = new Triangle();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/TriangleTest.csv")
    void testCheckType(int slide1, int slide2, int slide3, String result) {
        assertEquals(result, t.checkType(slide1, slide2, slide3));
    }
}