import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by hp on 17.03.2017.
 */
public class ReadFile {
    int hourseOneDigit = 0;
    int hourseTwo = 0;
    int peshkaOneDigit = 0;
    int peshkaTwo = 0;
    int[][] matrixA;
    public int[][] read() throws IOException {
        FileInputStream fstream = new FileInputStream("in.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
        String strLine;
        String koordOfHourse = br.readLine();
        char hourseOne = koordOfHourse.charAt(0);
        hourseTwo = Character.getNumericValue(koordOfHourse.charAt(1));
        String koordOfPeshka = br.readLine();
        char peshkaOne = koordOfPeshka.charAt(0);
        peshkaTwo = Character.getNumericValue(koordOfPeshka.charAt(1));
        hourseOneDigit = AlphabeticCharactering(hourseOne);
        peshkaOneDigit = AlphabeticCharactering(peshkaOne);


        matrixA = new int[8][8];
        matrixA[peshkaOneDigit-1][peshkaTwo-1] = 2;
        checkDeadPlaces(peshkaOneDigit, peshkaTwo, matrixA);
        matrixA[hourseOneDigit-1][hourseTwo-1] = 1;


    return matrixA;
    }

    public int[] getKoordOfHourse(){
        int[] koord = new int[2];
        koord[0] = hourseOneDigit;
        koord[1] = hourseTwo;
        return koord;
    }

    public char AlphabeticCharactering(char n){
        switch (n) {
            case 'a': n=1; break;
            case 'b': n=2; break;
            case 'c': n=3; break;
            case 'd': n=4; break;
            case 'e': n=5; break;
            case 'f': n=6; break;
            case 'g': n=7; break;
            case 'h': n=8; break;
        }
        return n;
    }

    public  static void checkDeadPlaces(int peshkaOneDigit, int peshkaTwo, int[][] matrixA){
        if (((peshkaOneDigit-1)>0)&&(peshkaTwo-1)>0){
            matrixA[peshkaOneDigit-1-1][peshkaTwo-1-1]=3;
        }
        if (((peshkaOneDigit+1)<8)&&((peshkaTwo-1)>0)){
            matrixA[peshkaOneDigit+1-1][peshkaTwo-1-1]=3;
        }
        if (((peshkaOneDigit-1)>0)&&((peshkaTwo+1)<8)){
            matrixA[peshkaOneDigit-1-1][peshkaTwo+1-1]=3;
        }
        if (((peshkaOneDigit+1)<8)&&((peshkaTwo+1)<8)){
            matrixA[peshkaOneDigit+1-1][peshkaTwo+1-1]=3;
        }
    }
}
