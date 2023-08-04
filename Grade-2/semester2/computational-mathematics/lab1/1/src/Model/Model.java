package Model;

import exceptions.InputUnexpectedException;

import java.io.IOException;

public abstract class Model {
    protected Matrix matrix;

    /**
     * Stage of process.
     * @throws InputUnexpectedException Might be thrown cause input in wrong format or file format wrong and so on
     * @throws IOException
     */
    public abstract void execute() throws InputUnexpectedException, IOException;

    protected void printMatrix(Matrix matrix) {
        if (matrix == null) {
            throw new InputUnexpectedException("matrix is not defined\n");
        }
        for (int i = 0; i < matrix.getRows(); i++) {
            for (int j = 0; j < matrix.getColumns(); j++) {
                if (j == matrix.getColumns() - 2) {
                    System.out.print(matrix.getElement(i, j) + "|");
                } else {
                    System.out.print(matrix.getElement(i, j) + " ");
                }
            }
            System.out.print("\n");
        }
    }

    /**
     * An abstract method for reading a matrix. Realization can be different by requirement
     * @throws IOException
     * @throws InputUnexpectedException
     */
    protected abstract void readMatrix() throws IOException, InputUnexpectedException,NumberFormatException;

    protected void process() throws InputUnexpectedException{
        MatrixBuilder mb = new MatrixBuilder(matrix);
        Matrix resultMatrix = mb.preparedMainMatrixs();
        Matrix triangle = mb.getTriangle();
        System.out.print("\n");
        System.out.print("Determinant is:"+mb.getOp()+"\n");
        if(mb.getOp()==0.0){
            System.out.print("Gotten triangle is(include bn):\n");
            printMatrix(triangle);
            System.out.print("This system of linear equations has no or endless result\n");
        }else {
            double [] result = solver(resultMatrix);
            System.out.print("Gotten triangle is(include bn):\n");
            printMatrix(triangle);
            System.out.print("Result is:\n");
            for (int i = 0; i < result.length; i++) {
                if (i == result.length - 1) {
                    System.out.print("|\n");
                } else {
                    System.out.print(result[i] + " ");
                }
            }
            double[] productAX = Calculator.multi(matrix,result);
            double[] r = Calculator.mins(productAX,matrix.getColumn(matrix.getColumns()-1));
            System.out.print("Ax*-b:\n");
            for(double d:r){
                System.out.print(d+" ");
            }
            System.out.print("\n");
            System.out.print("\n");
        }
    }

    protected double[] solver(Matrix triangle) {
        int j = 0;
        for (int i = triangle.getRows() - 1; i >= 0; i--) {
            j++;
            for (int k = 0; k < j; k++) {
                if (j == 1) {
                    int targetColumn = triangle.getColumns() - j - 1;
                    triangle.setElement(i, targetColumn, triangle.getElement(i, triangle.getColumns() - 1) / triangle.getElement(i, targetColumn));
                } else {
                    double result = 0;
                    double b = triangle.getElement(i,triangle.getColumns()-1);
                    for(int z = 0 ; z < j; z++){
                        if(j-z == 1){
                            double coefficient = triangle.getElement(i,triangle.getColumns()-2-z);
                            result = b/coefficient;
                            triangle.setElement(triangle.getRows()-1, triangle.getColumns()-1-j, result);
                        }else {
                            double coefficient = triangle.getElement(i, triangle.getColumns() - 2 - z);
                            double number = triangle.getElement(triangle.getRows() - 1, triangle.getColumns() - 2 - z);
                            b = b - coefficient * number;
                        }
                    }
                }
            }
        }
        return triangle.getLine(triangle.getRows() - 1);
    }
}
