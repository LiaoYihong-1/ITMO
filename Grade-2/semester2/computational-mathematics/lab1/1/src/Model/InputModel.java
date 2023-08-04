package Model;

import exceptions.InputUnexpectedException;

import java.io.IOException;
import java.util.Scanner;

/**
 * With this model we can input a matrix by ourselves
 * @author Ляо Ихун P3211
 */
public class InputModel extends Model{
    @Override
    public void execute() throws InputUnexpectedException,IOException{
        readMatrix();
        process();
    }

    @Override
    protected void readMatrix() throws InputUnexpectedException,NumberFormatException{
        //check rows and columns
        System.out.print("Input model running:\n");
        System.out.print("Input rank:\n");
        String r = new Scanner(System.in).nextLine();
        int rank = 0;
        try {
            rank = Integer.parseInt(r);
            if(rank>20){
                throw new InputUnexpectedException("Rank should be not bigger than 20\n");
            }
        }catch (NumberFormatException e){
            System.out.print("Input a number please:\n");
        }
        //set matrix
        matrix = new Matrix(rank,rank+1);
        for(int i = 0; i<matrix.getRows();i++){
            for(int j = 0; j<matrix.getColumns();j++){
                if(j== matrix.getColumns()-1){
                    System.out.print("Please input your b"+i+":\n");
                    String e = new Scanner(System.in).nextLine();
                    matrix.setElement(i, j, Double.parseDouble(e));
                }else {
                    System.out.print("Please input element in the " + i + " row " + "and " + j + " column\n");
                    String e = new Scanner(System.in).nextLine();
                    matrix.setElement(i, j, Double.parseDouble(e));
                }
            }
        }
    }
}
