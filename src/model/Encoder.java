package model;

import java.util.Random;

public class Encoder {

    //Attributes
    private int[][] matrix;

    private Random rd;
    //Builder
    public Encoder() {
        matrix = new int[6][6];
        rd = new Random();
        matrixGenerator();
    }

    /**
     * <b>Name:matrixGenerator</b><br>
     * This method allows you to generate a matrix.
     * <b>Post:</b>The matrix was generated correctly<br>
     */
    private void matrixGenerator() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = rd.nextInt(9);
            }
        }
    }
    /**
     * <b>Name:showMatrix</b><br>
     * This method allows you to show all the rows and columns of the matrix
     * <b>Post:</b>The matrix was shown correctly<br>
     * @return a string with the organized matrix
     */
    public String showMatrix() {
        String message = "";
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                message += matrix[i][j]+" ";

            }
            message += "\n";
        }
        return message;
    }
    /**
     * <b>Name:codeN</b><br>
     * This method allows you to generate a code by going through the matrix in an N form.
     * <b>Post:</b>codeN method was operated correctly<br>
     * @return a String with the generated code 
     */
    public String codeN() {
        String subCode1 = "";
        String subCode2 = "";
        String subCode3 = "";
        for (int i = 0; i < matrix.length; i++) {
            subCode1 += ""+matrix[matrix.length-(i+1)][0];
            if (i > 0 && i < matrix.length-1) subCode2 += ""+matrix[i][i];
            subCode3 += ""+matrix[matrix.length-(i+1)][matrix.length-1];
        }
        return showMatrix() +"\n"+ subCode1+subCode2+subCode3;
    }
    /**
     * <b>Name:codeT</b><br>
     * This method allows you to generate a code by going through the matrix in a T form.
     * <b>Post:</b>codeT method was operated correctly<br>
     * @return a String with the generated code 
     */
    public String codeT() {
        String subCode1 = "";
        String subCode2 = "";
        String subCode3 = "";
        String subCode4 = "";
        for (int i = 0; i < matrix.length; i++) {
            if (i < 2) subCode1 += ""+matrix[0][i];
            subCode2 += ""+matrix[i][2];
            subCode3 += ""+matrix[matrix.length-(i+1)][3];
            if (i > 3) subCode4 += ""+matrix[0][i];
        }
        return showMatrix() +"\n"+ subCode1+subCode2+subCode3+subCode4;
    }
    /**
     * <b>Name:codeModifiedChessBoard</b><br>
     * This method allows you to generate a code by going through the matrix in a chess form form.
     * <b>Post:</b>codeModifiedChessBoard method was operated correctly<br>
     * @return a String with the generated code 
     */
    public String codeModifiedChessBoard() {
        String code = "";
        for (int i = matrix.length-1; i > -1; i--) {
            for (int j = matrix[0].length-1; j > -1; j--) {
                if (i+j > 1) {
                    if (i%2 != 0){
                        if (j%2 == 0) code += ""+matrix[i][j];
                    } else if (j%2 != 0) code += ""+matrix[i][j];
                }
            }
        }
        return showMatrix() +"\n"+ code;
    }
}
