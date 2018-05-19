package LinearAlgebra;

import static org.junit.Assert.*;

public class myutilsTest {


    @org.junit.Test
    public void copyTest1()
    {
        double[][] test = new double[][]{{12,21,31},{443.43,5.443,6},{7,8.6654,9}};
        double[][] test1 = myutils.copyMatrix(test);
        assertEquals(test[0][0],test1[0][0], 0);
        assertEquals(test[1][0],test1[1][0], 0);
        assertEquals(test[2][0],test1[2][0], 0);
        assertEquals(test[0][1],test1[0][1], 0);
        assertEquals(test[1][1],test1[1][1], 0);
        assertEquals(test[2][1],test1[2][1], 0);
        assertEquals(test[0][2],test1[0][2], 0);
        assertEquals(test[1][2],test1[1][2], 0);
        assertEquals(test[2][2],test1[2][2], 0);
    }

    @org.junit.Test
    public void copyTest2()
    {
        double[][] test = new double[][]{{111.1,52.1,32.1},{4,51.2,6},{0.7,0.8,9}};
        double[][] test1 = myutils.copyMatrix(test);
        assertEquals(test[0][0],test1[0][0], 0);
        assertEquals(test[1][0],test1[1][0], 0);
        assertEquals(test[2][0],test1[2][0], 0);
        assertEquals(test[0][1],test1[0][1], 0);
        assertEquals(test[1][1],test1[1][1], 0);
        assertEquals(test[2][1],test1[2][1], 0);
        assertEquals(test[0][2],test1[0][2], 0);
        assertEquals(test[1][2],test1[1][2], 0);
        assertEquals(test[2][2],test1[2][2], 0);
    }
}