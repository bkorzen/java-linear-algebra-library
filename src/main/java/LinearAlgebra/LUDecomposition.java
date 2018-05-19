package LinearAlgebra;
import static java.util.stream.IntStream.range;


public class LUDecomposition {

    /*LU decomposition class. Based on Crout's algorithm.
    Problems with dividing by zero eliminated by pivoting
     */

    /*------------------------------------
    Class variables
    ------------------------------------*/
    private Matrix L;
    private Matrix U;

    public Matrix getL(){ return L; }
    public Matrix getU(){ return U; }

     /*------------------------------------
     Constructor
     ------------------------------------*/
    public LUDecomposition (Matrix A) {
        int n = A.size;
        L = new Matrix(n);
        U = new Matrix(n);
        double[][] P = pivotize(A.matrix);
        Matrix A2 = Matrix.Multiply(new Matrix(P,n), A, n);

        for (int j = 0; j < n; j++) {
            L.matrix[j][j] = 1;
            for (int i = 0; i < j + 1; i++) {
                double s1 = 0;
                for (int k = 0; k < i; k++)
                    s1 += U.matrix[k][j] * L.matrix[i][k];
                U.matrix[i][j] = A2.matrix[i][j] - s1;
            }
            for (int i = j; i < n; i++) {
                double s2 = 0;
                for (int k = 0; k < j; k++)
                    s2 += U.matrix[k][j] * L.matrix[i][k];
                L.matrix[i][j] = (A2.matrix[i][j] - s2) / U.matrix[j][j];
            }
        }
    }

    /*------------------------------------
    Private methods
    ------------------------------------*/
    private double[][] pivotize(double[][] m) {
        int n = m.length;
        double[][] id = range(0, n).mapToObj(j -> range(0, n)
                .mapToDouble(i -> i == j ? 1 : 0).toArray())
                .toArray(double[][]::new);

        for (int i = 0; i < n; i++) {
            double maxm = m[i][i];
            int row = i;
            for (int j = i; j < n; j++)
                if (m[j][i] > maxm) {
                    maxm = m[j][i];
                    row = j;
                }
            if (i != row) {
                double[] tmp = id[i];
                id[i] = id[row];
                id[row] = tmp;
            }
        }
        return id;
    }
}
