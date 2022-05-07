package saleCom;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import triangle.Triangle;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SaleComTest {
    public static SaleCom t;

    @BeforeAll
    public static void init() throws Exception {
        t = new SaleCom();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/SaleComTest.csv")
    void testCheckReward(int mainUnit, int screen, int perpheral, String result) {
        assertEquals(result, t.checkReward(mainUnit, screen, perpheral));
    }
}
