import Model.*;
import exceptions.InputUnexpectedException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Matrix {
    public static void main(String[] args) throws IOException {
        while(true) {
            Model model;
            String choice;
            System.out.print("Choose the module you want(input/file):\n");
            choice = new Scanner(System.in).nextLine();
            if("file".equalsIgnoreCase(choice)){
                model = new FileModel();
                try {
                    model.execute();
                } catch (FileNotFoundException f) {
                    System.out.print("No such file\n");
                } catch (InputUnexpectedException e) {
                    System.out.print(e.getMessage());
                } catch (NumberFormatException e) {
                    System.out.print("Make sure that what your input or content in file only is number\n");
                }
            }else if("input".equalsIgnoreCase(choice)) {
                try {
                    model = new InputModel();
                    model.execute();
                }catch (NumberFormatException n){
                    System.out.print("Input a number please\n");
                }
            }else if("exit".equalsIgnoreCase(choice)){
                System.exit(0);
            }
        }
    }
}
