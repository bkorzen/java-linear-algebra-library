import LinearAlgebra.LUDecomposition;
import LinearAlgebra.Matrix;

public class Main {

    public static void main(String[] args) {
	// write your code here
        //Matrix matrix = new Matrix(4);
        //matrix.FillMatrix();
       /* matrix.transpose();
        System.out.println();
        matrix.matrix[0][0] = 21;
        matrix.matrix[0][2] = 10;
        matrix.printMatrix();
        Matrix matrix1 = new Matrix(4);
        matrix1.FillMatrix();
        matrix1.transpose();
        System.out.println();
        matrix1.matrix[0][0] = 21;
        matrix1.matrix[0][2] = 10;
        Matrix resultMult = Matrix.Multiply(matrix,matrix1,4);
        resultMult.printMatrix();
        double det = Matrix.Determinant(matrix,3);
        Matrix matrix2 = Matrix.Adjugate(matrix,3);
        matrix2.printMatrix();
        System.out.println("Determinant: " + det);*/
        //matrix = Matrix.Inverse(matrix,4);
        //matrix.printMatrix();


        /*------------------------------------
        LUDecomposition usage cases
        ------------------------------------*/
        double[][] arr = new double[][]{{0,3,1},{4,0,1},{1,8,0}};
        Matrix matrix = new Matrix( arr  ,3);
        //Matrix object
        Matrix L = matrix.lu().getL();
        Matrix U = matrix.lu().getU();
        L.printMatrix();
        U.printMatrix();
        //LUDecomposition object
        LUDecomposition LU = new LUDecomposition(matrix);
        LU.getL().printMatrix();
        LU.getU().printMatrix();
        /*-----------------------------------*/

    }
}
