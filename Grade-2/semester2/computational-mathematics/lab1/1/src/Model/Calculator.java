package Model;

import exceptions.InputUnexpectedException;

/**
 * @author x1761
 */
public class Calculator {
    /**
     * Get Ax*. Remember that r is a array, whose the last element is not of importance
     * From r[0]-r[r.length-1] is all the results that we need
     * @param a A
     * @param r x*
     * @return double [] Ax*
     */
    static public double [] multi(Matrix a,double[]r){
        if(a.getColumns()!=r.length){
            throw new InputUnexpectedException("Matrix and r is unsuitable to each other\n");
        }
        double [] result = new double[r.length-1];
        for(int i = 0; i < a.getRows(); i++){
            double num = 0;
            for(int j = 0 ; j < a.getColumns() - 1; j++){
                double element = a.getElement(i,j);
                double dou = r[j];
                num = num +dou*element;
            }
            result[i] = num;
        }
        return result;
    }

    /**
     * Get Ax*-b. After method multi, Ax* and b have the same length
     * @param a Ax*
     * @param b b
     * @return double [] Ax*-b
     */
    static public double[] mins(double[] a,double[] b){
        if(a.length!=b.length){
            throw new InputUnexpectedException("Length of two arrays should be the same\n");
        }
        double [] result = new double[a.length];
        for(int i = 0 ; i < result.length;i++){
            result[i] = a[i] - b[i];
        }
        return result;
    }
}
