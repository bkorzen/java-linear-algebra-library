package LinearAlgebra;

import static org.junit.Assert.*;

public class MatrixTest {


    @org.junit.Test

    //Matrix(int) constructor
    public void correctSize(){
        Matrix m = new Matrix(10);
        assertEquals(10,m.size, 0);
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void tooSmallSize1()
    {
        Matrix m =new Matrix(0) ;
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void minusSize()
    {
        Matrix m =new Matrix(-1) ;
    }

    //Matrix(double[][], int)

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void tooSmallSize2()
    {
        double[][] test = new double[][]{{1,2,3},{4,5,6},{7,8,9}};
        Matrix m =new Matrix(test,-1) ;
    }
    @org.junit.Test(expected = IllegalArgumentException.class)
    public void sizeNotEqualToArrayDimensions()
    {
        double[][] test = new double[][]{{1,2,3},{4,5,6},{7,8,9}};
        Matrix m =new Matrix(test,4) ;
    }
    @org.junit.Test(expected = IllegalArgumentException.class)
    public void notEqualArrayDimensions()
    {
        double[][] test = new double[][]{{1,2,3},{4,5,6}};
        Matrix m =new Matrix(test,3) ;
    }
    @org.junit.Test
    public void matrixArrayEqualToPassed()
    {
        double[][] test = new double[][]{{1,2,3},{4,5,6},{7,8,9}};
        Matrix m = new Matrix(test,3) ;
        //test[1][1] = 15;
        assertEquals(test[0][0],m.matrix[0][0], 0);
        assertEquals(test[1][0],m.matrix[1][0], 0);
        assertEquals(test[2][0],m.matrix[2][0], 0);
        assertEquals(test[0][1],m.matrix[0][1], 0);
        assertEquals(test[1][1],m.matrix[1][1], 0);
        assertEquals(test[2][1],m.matrix[2][1], 0);
        assertEquals(test[0][2],m.matrix[0][2], 0);
        assertEquals(test[1][2],m.matrix[1][2], 0);
        assertEquals(test[2][2],m.matrix[2][2], 0);
    }
    @org.junit.Test
    public void matrixArrayNotEqualToChanged()
    {
        double[][] test = new double[][]{{1,2,3},{4,5,6},{7,8,9}};
        Matrix m = new Matrix(test,3) ;
        test[0][0] = 15;
        assertFalse(test[0][0] == m.matrix[0][0]);
    }

    //Matrix(double[] m , int size)
    @org.junit.Test(expected = IllegalArgumentException.class)
    public void tooSmallSize3()
    {   double[] test = new double[]{1,2,3,4,5,6,7,8,9};
        Matrix m = new Matrix(test,0) ;
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void sizeSquareNotEqualToArrayCount()
    {
        double[] test = new double[]{1,2,3,4,5,6,7,8};
        Matrix m = new Matrix(test,5) ;
    }

    @org.junit.Test
    public void array1DtoMatrixEquals()
    {
        double[] test = new double[]{0,1,2,3,4,9,6,8,11};
        Matrix m = new Matrix(test,3) ;
        assertEquals(0.0,m.matrix[0][0], 0);
        assertEquals(1.0,m.matrix[0][1], 0);
        assertEquals(2.0,m.matrix[0][2], 0);
        assertEquals(3.0,m.matrix[1][0], 0);
        assertEquals(4.0,m.matrix[1][1], 0);
        assertEquals(9.0,m.matrix[1][2], 0);
        assertEquals(6.0,m.matrix[2][0], 0);
        assertEquals(8.0,m.matrix[2][1], 0);
        assertEquals(11.0,m.matrix[2][2], 0);
    }
}