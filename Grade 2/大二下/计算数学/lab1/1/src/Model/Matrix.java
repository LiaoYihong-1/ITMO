package Model;

import exceptions.InputUnexpectedException;

public class Matrix {
    private double[][] matrix;
    private int rows;
    private int columns;

    public Matrix(){

    };
    public Matrix(int rows,int columns){
        this.matrix = new double[rows][columns];
        this.rows = rows;
        this.columns = columns;
    }

    public int getColumns() {
        return columns;
    }

    public int getRows() {
        return rows;
    }

    public double getElement(int row,int column) throws InputUnexpectedException{
        if(row>=rows||column>=columns) {
            throw new InputUnexpectedException("No such element\n");
        }
        return this.matrix[row][column];
    }

    public void setElement(int row,int column, double newElement) throws InputUnexpectedException{
        if(row>=rows||column>=columns){
            throw new InputUnexpectedException("No such element\n");
        }
        this.matrix[row][column] = newElement;
    }
    public void exchangeElement(int r1,int r2,int c1,int c2){
        double buffer = getElement(r1,c1);
        setElement(r1,c1,getElement(r2,c2));
        setElement(r2,c2,buffer);
    }

    public void exchangeLine(int L1,int L2){
        for(int i = 0; i < columns; i++){
            exchangeElement(L1,L2,i,i);
        }
    }

    public void addLine(int value,int target){
        for(int i = 0; i<columns;i++){
            matrix[target][i] = matrix[target][i] + matrix[value][i];
        }
    }

    public void mulLine(double mul,int target){
        for(int i=0;i<columns;i++){
            matrix[target][i] = matrix[target][i]*mul;
        }
    }
    public double[] getLine(int value){
        double [] result = new double[columns];
        for(int i=0;i<columns;i++){
            result[i] = matrix[value][i];
        }
        return result;
    }
    public void setLine(double [] newLine,int target){
        for(int i = 0; i<columns;i++){
            matrix[target][i] = newLine[i];
        }
    }

    public double[] getColumn(int target){
        double [] result = new double[getRows()];
        for(int i = 0; i < getRows(); i++){
            result[i] = matrix[i][target];
        }
        return result;
    }

    public void printLine(int target){
        for(int i=0;i<columns;i++){
            if(i==columns-2){
                System.out.print(matrix[target][i] + "|");
            }else {
                System.out.print(matrix[target][i] + " ");
            }
        }
        System.out.print("\n");
    }

    public void printColumn(int target){
        for(int i = 0; i<rows; i++){
            System.out.print(matrix[i][target]+" ");
        }
        System.out.print("\n");
    }

    public void init(Matrix matrix){
        this.matrix = new double[matrix.getRows()][matrix.getColumns()];
        this.columns = matrix.getColumns();
        this.rows = matrix.getRows();
        for(int i = 0; i < matrix.getRows();i++){
            setLine(matrix.getLine(i), i);
        }
    }
}
