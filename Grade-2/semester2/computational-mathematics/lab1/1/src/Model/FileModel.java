package Model;

import exceptions.FileFormatUnexpectedException;
import exceptions.InputUnexpectedException;

import java.io.*;
import java.util.Scanner;

/**
 * This class is to start a solution of a group of equations, which is presented by a matrix, with file input
 * @author Ляо Ихун P3211
 */
public class FileModel extends Model{
    @Override
    public void execute() throws InputUnexpectedException, IOException {
        readMatrix();
        process();
    }

    @Override
    protected void readMatrix() throws InputUnexpectedException, IOException{
        File file;
        String fileName;
        int rows = 0;
        int columns = 0;
        System.out.print("FileModel is running\n");
        System.out.print("Now input path to your file:\n");
        fileName = new Scanner(System.in).nextLine();
        file = new File(fileName);
        FileReader fileReader =new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String s;
        bufferedReader.mark((int)file.length()+1);
        //check rows and columns
        if((s=bufferedReader.readLine())!=null) {
            String[] row = s.split(" ");
            columns = row.length;
            rows++;
        }
        while(bufferedReader.readLine()!=null){
            rows++;
        }

        if(rows>20) {
            throw new InputUnexpectedException("Rank should be not bigger than 20\n");
        }
        //matrix should be a square
        if(columns-1!=rows) {
            throw new FileFormatUnexpectedException("Matrix from coefficients of unknown should be a square\n");
        }
        //set matrix
        matrix = new Matrix(rows,columns);
        bufferedReader.reset();
        for(int i = 0; i<rows; i++){
            s = bufferedReader.readLine();
            if(s.split(" ").length< matrix.getColumns()) {
                throw new FileFormatUnexpectedException("Make sure your matrix format is assessable");
            }
            String[] nums = s.split(" ");
            for(int j = 0;j<columns;j++){
                matrix.setElement(i,j,Double.parseDouble(nums[j]));
            }
        }
    }
}
