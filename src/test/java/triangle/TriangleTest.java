package triangle;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import joinery.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;


import static org.junit.jupiter.api.Assertions.*;


public class TriangleTest {
    public static Triangle t;
    private static ArrayList<String> actualOutputs = new ArrayList<String>();
    private static DataFrame df = new DataFrame();

    @BeforeAll
    public static void init() throws Exception {
        t = new Triangle();
        df.add("id", "a", "b", "c", "expectedOutput", "actualOutput", "pass");
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/TriangleTest.csv", numLinesToSkip = 1)
    void testCheckType(int id, int slide1, int slide2, int slide3, String expectedOutput) {
        String str = t.checkType(slide1, slide2, slide3);
        if (expectedOutput.equals(str)) {
            df.append(Arrays.asList(id, slide1, slide2, slide3, expectedOutput, str, "True"));
        } else {
            df.append(Arrays.asList(id, slide1, slide2, slide3, expectedOutput, str, "False"));
        }
        //assertEquals(expectedOutput, t.checkType(slide1, slide2, slide3));
    }

    @AfterAll
    static void writeCSV() {
//        Set<Object> indexs = df.index();
//        Set<Object> columns = df.columns();
//        for(Object index:indexs)
//        {
//            for(Object column:columns)
//            {
//                System.out.print(df.get(index, column));
//                System.out.print("\t");
//            }
//            System.out.println();
//        }

        try {
            df.writeCsv("result/triangle.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}