package Model;

public class MatrixBuilder {
    private final Matrix matrixSource = new Matrix();
    private final Matrix triangle = new Matrix();
    private final Matrix matrixProduct = new Matrix();
    int k = 0;
    public MatrixBuilder(Matrix matrix){
        matrixSource.init(matrix);
        matrixProduct.init(matrixSource);
    }

    public Matrix getMatrixProduct() {
        return matrixProduct;
    }
    public Matrix getMatrixSource(){
        return this.matrixSource;
    }

    public void reset(){
        matrixProduct.init(matrixSource);
        k=0;
    }

    public Matrix getTriangle() {
        return triangle;
    }

    /**
     * One of the most important function. He performs the process of choosing main elements and getting triangle
     * @return Matrix
     */
    public Matrix preparedMainMatrixs(){
        for(int i = 0; i<matrixProduct.getColumns()-1; i++){
            //choose main elements
            for(int j=i+1;j< matrixProduct.getRows();j++){
                if(matrixProduct.getElement(i,i)<matrixProduct.getElement(j,i)){
                    matrixProduct.exchangeLine(i,j);
                    k++;
                }
            }
            double[] origin = matrixProduct.getLine(i);
            //get triangle
            for(int j=i+1;j< matrixProduct.getRows();j++){
                matrixProduct.mulLine(-matrixProduct.getElement(j,i)/matrixProduct.getElement(i,i),i );
                matrixProduct.addLine(i,j);
                matrixProduct.setLine(origin,i);
            }
        }
        triangle.init(matrixProduct);
        return this.matrixProduct;
    }

    public double getOp(){
        double dK = Double.parseDouble(k+"");
        double result = 1;
        for(int i = 0;i< matrixProduct.getRows();i++){
            result = result*matrixProduct.getElement(i,i);
        }
        result = result*Math.pow(-1,dK);
        return result;
    }
}
