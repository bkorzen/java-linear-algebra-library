import LinearAlgebra.LUDecomposition;
import LinearAlgebra.Matrix;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI implements ActionListener {

    private static int size;
    private static double[][] arrayMatrix;
    private static Matrix matrixObject;
    private static double[][] tempMatrix;
    private static JTextField[][] inputField;
    private static int result;
    private static JButton inverseBtn,
            multiplyBtn, scalarMultiplyBtn, determinantBtn,
            showMatrixBtn, transposingBtn, newMatrixBtn,
            identityBtn, luDecompositionBtn;
    private static JPanel[] choosePanel = new JPanel[8];

    GUI() {
        double[][] m = new double[4][4];
        matrixObject = new Matrix(m, m.length);
        matrixObject.FillMatrix();
        arrayMatrix = matrixObject.getMatrix();
        size = matrixObject.getSize();
//        arrayMatrix = new double[0][0];
        ChooseOperation();
    }

    private static void showMatrix(double[][] matrix, String title) {
        int temp, temp1;             //temprature variable

        JPanel choosePanel[] = new JPanel[matrix.length + 1];
        choosePanel[0] = new JPanel();
        choosePanel[0].add(new JLabel(title));

        for (temp = 1; temp <= matrix.length; temp++) {
            choosePanel[temp] = new JPanel();


            for (temp1 = 0; temp1 < matrix[0].length; temp1++) {
                if (matrix[temp - 1][temp1] == -0) {
                    matrix[temp - 1][temp1] = 0;
                }
                choosePanel[temp].add(new JLabel(String.format("%.3f", matrix[temp - 1][temp1])));

                if (temp1 < matrix[0].length - 1) {
                    choosePanel[temp].add(Box.createHorizontalStrut(15)); // a spacer
                }

            }//end col loop

        }//end row loop

        if (size == 0) {
            JOptionPane.showMessageDialog(null, "You haven't entered any matrixObject");
        } else {

            JOptionPane.showMessageDialog(null, choosePanel, null,
                    JOptionPane.PLAIN_MESSAGE, null);
        }
    } // ?????????????????????????????????

    private static void guiGetDeterminant() {
        if (arrayMatrix.length < 1) {
            JOptionPane.showMessageDialog(null, "You haven't entered any matrixObject");
        } else {
            double result = getDeterminant();

            JOptionPane.showMessageDialog(null, String.format("det = %.3f",
                    result, null,
                    JOptionPane.PLAIN_MESSAGE, null));
        }
    }

    private static double getDeterminant() {
        return Matrix.Determinant(matrixObject, matrixObject.getSize());
    }

    private static void guiTransposing(double[][] matrix) {
        if (arrayMatrix.length < 1) {
            JOptionPane.showMessageDialog(null, "You haven't entered any matrixObject");
            return;
        }

        double[][] transMatrix = new double[matrix[0].length][matrix.length];

        transMatrix = transposing(matrix);

        showMatrix(transMatrix, "Transposing Matrix");
    }

    private static double[][] transposing(double[][] matrix) {
        Matrix m = new Matrix(matrix, matrix.length);
        m.transpose();
        double[][] transposedMatrix = m.getMatrix();

        return transposedMatrix;
    }

    private static void inverse() {
        double[][] inverseMatrix = Matrix.Inverse(matrixObject, matrixObject.getSize()).getMatrix();
        showMatrix(inverseMatrix, "Inversing Matrix");
    }

    private static void identity() {
        Matrix temp = new Matrix(arrayMatrix, arrayMatrix.length);
        temp.identity();
        double[][] identityMatrix = temp.getMatrix();
        showMatrix(identityMatrix, "Identity Matrix");
    }

    private static void guiMultiplyByScalar() {

        double[][] results;
        double x;
        String tempString;

        if (arrayMatrix.length < 1) {
            JOptionPane.showMessageDialog(null, "You haven't entered any matrixObject");
            return;
        }

        tempString = JOptionPane.showInputDialog(null,
                "Enter the scaler number for multiplying");

        if (tempString == null) //cancel option
        {
            return;
        } else if (!tempString.equals(""))
            x = Double.parseDouble(tempString);
        else {
            JOptionPane.showMessageDialog(null, "You haven't entered a scaler");
            return;
        }
        results = multiplyByScalar(x);
        showMatrix(results, "Multiplication Result");

    }

    private static double[][] multiplyByScalar(double scalar) {
        Matrix temp = new Matrix(arrayMatrix, arrayMatrix.length);
        temp.MultiplyByScalar(scalar);
        double[][] m = temp.getMatrix();

        return m;
    }

    private static void multiplyByMatrix() {

        Matrix matrix2;
        Matrix multiplicationResult;

        JTextField wField = new JTextField(5); //col field
        int col2 = 0;

        if (arrayMatrix.length < 1) {
            JOptionPane.showMessageDialog(null, "You haven't entered any matrixObject");
        } else {

            //design input line
            JPanel choosePanel[] = new JPanel[2];
            choosePanel[0] = new JPanel();
            choosePanel[1] = new JPanel();

            choosePanel[0].add(new JLabel("Enter Dimensions"));

            choosePanel[1].add(new JLabel("Rows:"));
            choosePanel[1].add(new JLabel("" + size));
            choosePanel[1].add(Box.createHorizontalStrut(15)); // a spacer
            choosePanel[1].add(new JLabel("Cols:"));
            choosePanel[1].add(wField);


            result = JOptionPane.showConfirmDialog(null, choosePanel,
                    null, JOptionPane.PLAIN_MESSAGE,
                    JOptionPane.PLAIN_MESSAGE);

            if (result == 0) {
                if (wField.getText().equals("")) {
                    col2 = 0;
                } else {
                    if (isInt(wField.getText())) {
                        col2 = Integer.parseInt(wField.getText());

                    }
                }

                double[][] m2 = new double[size][col2];
                if (setElements(m2, "Fill multiplying matrixObject")) {

//                    for (int i = 0; i < row; i++) {
//                        for (int j = 0; j < col2; j++) {
//                            sum = 0;
//                            for (int k = 0; k < col; k++) {
//                                sum += arrayMatrix[i][k] * m2[k][j];
//                            }
//
//                            results[i][j] = sum;
//
//                        }
//                    }
                    matrix2 = new Matrix(m2, m2.length);
                    multiplicationResult = Matrix.Multiply(matrixObject, matrix2, matrixObject.getSize());

                    showMatrix(multiplicationResult.getMatrix(), "Mulitiplication Result");
                }//elements checking
            }//dimensions checking
            else
                return;
        }//end else of checking
    }

    private static void LUDecomposition() {
        LUDecomposition lu = new LUDecomposition(matrixObject);
        showMatrix(lu.getL().getMatrix(), "Matrix L");
        showMatrix(lu.getU().getMatrix(), "Matrix U");
    }

    private static boolean isInt(String str) {
        int temp;
        if (str.length() == '0')
            return false;

        for (temp = 0; temp < str.length(); temp++) {
            if (str.charAt(temp) != '+' && str.charAt(temp) != '-'
                    && !Character.isDigit(str.charAt(temp))) {
                return false;
            }
        }
        return true;
    } // ?????????????????????????????????

    private static boolean isDouble(String str) {
        int temp;
        if (str.length() == '0')
            return false;

        for (temp = 0; temp < str.length(); temp++) {
            if (str.charAt(temp) != '+' && str.charAt(temp) != '-'
                    && str.charAt(temp) != '.'
                    && !Character.isDigit(str.charAt(temp))
                    ) {
                return false;
            }
        }
        return true;
    } // ??????????????????????????????????????

    private static void getDimension() {
        JTextField sizeField = new JTextField(5); //lenght field

        //design input line
        JPanel choosePanel[] = new JPanel[2];
        choosePanel[0] = new JPanel();
        choosePanel[1] = new JPanel();
        choosePanel[0].add(new JLabel("Enter Dimensitions"));
        choosePanel[1].add(new JLabel("Size:"));
        choosePanel[1].add(sizeField);
        choosePanel[1].add(Box.createHorizontalStrut(15)); // a spacer

        result = JOptionPane.showConfirmDialog(null, choosePanel,
                null, JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);

        //save last dimensions
        int lastSize = size;

        //ok option
        if (result == 0) {

            if (sizeField.getText().equals(""))
                size = 0;
            else {
                if (isInt(sizeField.getText())) {
                    size = Integer.parseInt(sizeField.getText());
                } else {
                    JOptionPane.showMessageDialog(null, "Wrong Dimension");
                    size = lastSize;
                    return;
                }
            }
            if (size < 1) {
                JOptionPane.showConfirmDialog(null, "You entered wrong dimension",
                        "Error", JOptionPane.PLAIN_MESSAGE);
                size = lastSize;
            } else {
                tempMatrix = arrayMatrix;
                arrayMatrix = new double[size][size];
                if (!setElements(arrayMatrix, "Fill your new matrixObject")) //filling the new matrixObject
                {
                    //backup

                    arrayMatrix = tempMatrix;
                }
            }
        } else if (result == 1) {
            size = lastSize;
        }
    } // ??????????????????????????????????????????

    private static void newMatrix() {
        getDimension();
    } // ???????????????????????????????????????

    private static boolean setElements(double matrix[][], String title) {
        int temp, temp1;             //temporary variables
        String tempString;

        JPanel choosePanel[] = new JPanel[size + 2];
        choosePanel[0] = new JPanel();
        choosePanel[0].add(new Label(title));
        choosePanel[choosePanel.length - 1] = new JPanel();
        choosePanel[choosePanel.length - 1].add(new Label("consider space field as zeros"));
        inputField = new JTextField[matrix.length][matrix[0].length];


        //length loop
        for (temp = 1; temp <= matrix.length; temp++) {
            choosePanel[temp] = new JPanel();


            for (temp1 = 0; temp1 < matrix[0].length; temp1++) {
                inputField[temp - 1][temp1] = new JTextField(3);
                choosePanel[temp].add(inputField[temp - 1][temp1]);

                if (temp1 < matrix[0].length - 1) {
                    choosePanel[temp].add(Box.createHorizontalStrut(15)); // a spacer
                }

            }//end col loop

        }//end row loop

        result = JOptionPane.showConfirmDialog(null, choosePanel,
                null, JOptionPane.OK_OPTION, JOptionPane.PLAIN_MESSAGE);

        int lastSize = size;

        if (result == 0) {
            checkTextField(inputField);
            for (temp = 0; temp < matrix.length; temp++) {
                for (temp1 = 0; temp1 < matrix[0].length; temp1++) {
                    tempString = inputField[temp][temp1].getText();


                    if (isDouble(tempString)) {
                        matrix[temp][temp1] = Double.parseDouble(inputField[temp][temp1].getText());
                    } else {
                        JOptionPane.showMessageDialog(null, "You entered wrong elements");

                        //backup
                        size = lastSize;

                        return false;
                    }
                }
            }
            return true;
        } else
            return false;


    } // ???????????????????????

    private static void checkTextField(JTextField field[][]) {
        for (int temp = 0; temp < field.length; temp++) {
            for (int temp1 = 0; temp1 < field[0].length; temp1++) {
                if (field[temp][temp1].getText().equals(""))
                    field[temp][temp1].setText("0");
            }
        }
    } // ???????????????????????????????????

    public static void main(String[] args) {
        GUI m1 = new GUI();

    }

    private void ChooseOperation() {
        int temp;

        for (temp = 0; temp < choosePanel.length; temp++) {
            choosePanel[temp] = new JPanel();
        }

//        choosePanel[1].add(Box.createHorizontalStrut(15)); // a spacer

//        choosePanel[6].add(Box.createHorizontalStrut(15)); // a spacer

        showMatrixBtn = new JButton("Show Matrix");
        showMatrixBtn.setPreferredSize(new Dimension(175, 35));
        showMatrixBtn.addActionListener(this);
        choosePanel[2].add(showMatrixBtn);

        multiplyBtn = new JButton("Multiply by matrix");
        multiplyBtn.setPreferredSize(new Dimension(175, 35));
        multiplyBtn.addActionListener(this);
        choosePanel[2].add(multiplyBtn);

        scalarMultiplyBtn = new JButton("Multiply by scalar");
        scalarMultiplyBtn.setPreferredSize(new Dimension(175, 35));
        scalarMultiplyBtn.addActionListener(this);
        choosePanel[2].add(scalarMultiplyBtn);

        transposingBtn = new JButton("Transposing");
        transposingBtn.setPreferredSize(new Dimension(175, 35));
        transposingBtn.addActionListener(this);
        choosePanel[3].add(transposingBtn);


        determinantBtn = new JButton("Determinant");
        determinantBtn.setPreferredSize(new Dimension(175, 35));
        determinantBtn.addActionListener(this);
        choosePanel[3].add(determinantBtn);

        inverseBtn = new JButton("Inversing");
        inverseBtn.setPreferredSize(new Dimension(175, 35));
        inverseBtn.addActionListener(this);
        choosePanel[3].add(inverseBtn);


        newMatrixBtn = new JButton("New Matrix");
        newMatrixBtn.setPreferredSize(new Dimension(175, 35));
        newMatrixBtn.addActionListener(this);
        choosePanel[4].add(newMatrixBtn);

        identityBtn = new JButton("Identity");
        identityBtn.setPreferredSize(new Dimension(175, 35));
        identityBtn.addActionListener(this);
        choosePanel[4].add(identityBtn);

        luDecompositionBtn = new JButton("LU Decomposition");
        luDecompositionBtn.setPreferredSize(new Dimension(175, 35));
        luDecompositionBtn.addActionListener(this);
        choosePanel[4].add(luDecompositionBtn);

        JOptionPane.showConfirmDialog(null, choosePanel, null,
                JOptionPane.CLOSED_OPTION, JOptionPane.PLAIN_MESSAGE);

    } // ??????????????????????????????????????????????

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == showMatrixBtn) {
            showMatrix(arrayMatrix, "Your Matrix");
        }

        if (e.getSource() == multiplyBtn) {
            multiplyByMatrix();
        } else if (e.getSource() == inverseBtn) {
            inverse();
        } else if (e.getSource() == scalarMultiplyBtn) {
            guiMultiplyByScalar();
        } else if (e.getSource() == transposingBtn) {
            guiTransposing(arrayMatrix);
        } else if (e.getSource() == determinantBtn) {
            guiGetDeterminant();
        } else if (e.getSource() == newMatrixBtn) {
            newMatrix();
        } else if (e.getSource() == identityBtn) {
            identity();
        } else if (e.getSource() == luDecompositionBtn) {
            LUDecomposition();
        }
    } // ????????????????????????????????????????

}
