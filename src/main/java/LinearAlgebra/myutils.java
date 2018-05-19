package LinearAlgebra;

public class myutils {

    public static double[][] copyMatrix(double[][] matrix) {
        double[][] myDouble = new double[matrix.length][];
        for (int i = 0; i < matrix.length; i++)
            myDouble[i] = matrix[i].clone();
        return myDouble;
    }
}