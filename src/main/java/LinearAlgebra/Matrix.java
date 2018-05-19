package LinearAlgebra;


public class Matrix {

    /* Library for basic linear algebra calculations.
       Library contains:
       -constructors:
        1. Empty square matrix with specified size.
        2. Square matrix from 2D array with specified size.
        3. Square matrix from 1D array with specified size.
       -methods:
        1. fillMatrix() - for filling matrix with random numbers;
        2. printMatrix() - for printing matrix to stdout.
        3. identity() - for overwriting current matrix by identity matrix
        4. clear() - for clearing current matrix (overwriting by 0);
        5. transpose() - for transposing matrix
        6. MultiplyByScalar(int scalar)
        ...
     */


   /*------------------------------------
    Class variables
    ------------------------------------*/

    int size;
    public double[][] matrix;

    public int getSize() {
        return size;
    }

    public double[][] getMatrix() {
        return matrix;
    }

    /*------------------------------------
    Constructors
    ------------------------------------*/

    //Empty square matrix constructor
    public Matrix( int size) {
        if( size < 1 ) throw new IllegalArgumentException("Size must be integer >=1!");
        this.size = size;
        matrix = new double[size][size];
    }

    //Matrix from a 2D array
    public Matrix ( double[][] m, int size ){
        if( size < 1 ) throw new IllegalArgumentException("Size must be integer >=1!");
        this.size = size;
        for(int i = 0; i < m.length;i++)
        {
            if(m.length != m[i].length | m.length != size) throw new IllegalArgumentException("Array size,dimensions not equal");
        }
        matrix =  myutils.copyMatrix(m);
    }

    //Matrix from 1D array
    public Matrix (double[] m, int size)
    {
        if( size < 1 | m.length != size*size ) throw new IllegalArgumentException("Size must be integer >=1, size^2 must be equal to 1d array count!");
        this.size = size;
        int iters = 0;
        matrix = new double[size][size];
        for(int i = 0 ; i < size ; i++)
        {
            for(int j = 0 ; j < size ; j++)
            {
                matrix[i][j]=m[iters];
                iters++;
            }
        }
    }

    /*------------------------------------
    Methods
    --------------------------------------*/
    // fill matrix: for test purposes
    public void FillMatrix() {
        double A[][] = { {5, -2, 2, 7},
            {1, 0, 0, 3},
            {-3, 1, 5, 0},
            {3, -1, -9, 4}};
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j)
                matrix[i][j] = A[i][j];
        }
    }

    //print matrix to stdout
    public void printMatrix() {
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j)
                System.out.printf("%5.2f ", matrix[i][j]);
            System.out.print("\n");
        }
        System.out.print("\n");
    }

    //fill matrix: identity matrix
    public void identity() {
        for (int i = 0; i < matrix.length; ++i) {
            for (int j = 0; j < matrix[i].length; ++j) {
                matrix[i][j] = 0;
                if (i == j)
                    matrix[i][j] = 1;
            }
        }
    }

    //clear matrix
    public void clear() {
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                matrix[i][j] = 0;
            }
        }
    }

    //method for transposing the matrix
    public void transpose() {
        for(int i = 0; i < size; ++i) {
            for(int j = i+1; j < size; ++j) {
                double temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }

    //method for multiplying matrix elements by a scalar
    public void MultiplyByScalar (double scalar)  {
        for(int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                matrix[i][j] = matrix[i][j]*scalar;
            }
        }
    }

    //method for multiplying two matrices
    public static Matrix Multiply(Matrix matrix1, Matrix matrix2, int size) {
        Matrix result = new Matrix(size);
        if (matrix2.size == size) {
            for(int i = 0; i < size; ++i) {
                for (int j = 0; j < size; ++j) {
                    for (int k = 0; k < size; ++k) {
                        result.matrix[i][j] += matrix1.matrix[i][k] * matrix2.matrix[k][j];
                    }
                }
            }
            return result;
        }
        else {
            System.out.println("Wrong matrix size!");
            return null;
        }
    }

    //method for array multiplication (element with corresponding element)
    public void ArrayMultiplication(Matrix matrix1) {
        for(int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                this.matrix[i][j] *=matrix1.matrix[i][j];
            }
        }
    }

    //method for calculating determinant
    public static double Determinant( Matrix matrix, int size) {
        int result = 0;
        if (size==1) return matrix.matrix[0][0];
        Matrix tempMatrix = new Matrix(size-1);
        int sign = 1;
        for (int i = 0; i < size; ++i) {
            Cofactor(matrix.matrix,tempMatrix.matrix,0,i,size);
            result+= sign*matrix.matrix[0][i]*Determinant(tempMatrix, size-1);
            sign = - sign;
        }
        return result;
    }

    //private method for calculating cofactor matrix
    private static void Cofactor( double matrix[][], double[][] temp, int p, int q,int size) {
        int i = 0, j = 0;
        for(int row = 0; row < size; ++row) {
            for(int col = 0; col < size; ++col) {
                if(row !=p && col != q) {
                    temp[i][j++] = matrix[row][col];
                    if(j == size -1) {
                        j = 0;
                        i++;
                    }
                }
            }
        }
    }

    //method for calculating adjugate matrix
    public static Matrix Adjugate(Matrix matrix, int size) {
        Matrix temp = new Matrix(matrix.size-1);
        Matrix adj = new Matrix(matrix.size);
        int sign = 1;
        if(size == 1) {
            adj.matrix[0][0] = 1;
            return adj;
        }
        for(int i = 0; i < size; ++i) {
            for(int j = 0; j < size; ++j) {
                Cofactor(matrix.matrix,temp.matrix,i,j,size);
                sign = ((i+j)%2 == 0)? 1 :-1 ;
                adj.matrix[j][i] = sign*Determinant(temp, size-1);
            }
        }
        return adj;
    }

    //method for calculating inverse matrix
    public static Matrix Inverse(Matrix matrix, int size) {
        double det = Determinant(matrix, size);
        if (det == 0) {
            System.out.println("Singular matrix");
            return null;
        }
        Matrix adjoint = Adjugate(matrix, size);
        Matrix inverse = new Matrix(size);
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                inverse.matrix[i][j] = adjoint.matrix[i][j] / det;
            }
        }
        return inverse;
    }

    /*------------------------------------
     Decompositions
     ------------------------------------*/

    public LUDecomposition lu()
    {
        return new LUDecomposition(this);
    }




}
