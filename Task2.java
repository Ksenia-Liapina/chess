import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Stack;

import static java.lang.System.lineSeparator;

/**
 * Created by hp on 17.03.2017.
 */
public class Task2 {
    public static void main(String[] args) throws IOException {
        ReadFile aa = new ReadFile();
        int result[][] = aa.read();
        int[] kordOfHourse = aa.getKoordOfHourse();
        Stack<Pair> path = searchOfPath(result,kordOfHourse);
        String[] itog = correctOutput(path);
        String str = itog[itog.length-1];;
        for (int i=itog.length-2; i>=0; i--){
            str=str+lineSeparator();
            str= str+itog[i];
            //System.out.println(itog[i]);
        }
        //showMatrix(result);
        writeInFile("out.txt", str);

    }

    public static Stack<Pair> searchOfPath(int result[][], int[] kordOfHourse){
        Stack<Pair> stack= new Stack<>();
        Stack<Pair> path = new Stack<>();
        Pair hourse = new Pair();
        hourse.x = kordOfHourse[0]-1;
        hourse.y = kordOfHourse[1]-1;
        stack.push(hourse);
        path.push(hourse);
        while (!(stack.empty())){
            Pair pairCurrent = stack.pop();
            Pair newPair = isCorrect(pairCurrent,result);
            if (result[newPair.x][newPair.y]==0) result[newPair.x][newPair.y]=1;

            if (result[newPair.x][newPair.y] == 2){
                path.push(newPair);
                break;
            }
            if (result[newPair.x][newPair.y] == 3) {
                    if (stack.size() != 0) {
                        path.pop();
                        stack.pop();
                    }
                    else{
                        stack.push(hourse);
                    }
                }

            else {
                path.push(newPair);
                stack.push(newPair);
            }

        }
        return path;
    }

    public static String[] correctOutput(Stack<Pair> path){
        Pair[] notresult = new Pair[path.size()];
        String[] result = new String[path.size()];
        int size = path.size();
        for (int i=0; i<size; i++){
            notresult[i]= path.pop();
            String currentWord = String.valueOf(notresult[i].x);
            String oneKoord = nameOfWord(currentWord);
            int y = notresult[i].y+1;
            String itog = oneKoord+String.valueOf(y);
            result[i] = itog;
        }
    return result;
    }

    public static String nameOfWord(String str){
        int n = Integer.valueOf(str);
        switch (n) {
            case 0: str="a"; break;
            case 1: str="b"; break;
            case 2: str="c"; break;
            case 3: str="d"; break;
            case 4: str="e"; break;
            case 5: str="f"; break;
            case 6: str="g"; break;
            case 7: str="h"; break;
        }
        return str;
    }

    public static void writeInFile(String fileName, String text) {
        File file = new File(fileName);
        try {
            if(!file.exists()){
                file.createNewFile();
            }
            PrintWriter out = new PrintWriter(file.getAbsoluteFile());
            try {
                out.print(text);
            } finally {
                out.close();
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static Pair isCorrect(Pair pair, int result[][]){
        Pair newPair = new Pair();
        if (((pair.x - 2) > 0) && ((pair.y + 1) < 7) &&((result[pair.x - 2][pair.y + 1]!=1)) &&(result[pair.x - 2][pair.y + 1]!=3)){
            newPair.x = pair.x - 2;
            newPair.y = pair.y + 1;
        }
        //1
        else {
            if (((pair.x - 2) > 0) && ((pair.y - 1) > 0)&&((result[pair.x - 2][pair.y - 1]!=1)) &&(result[pair.x - 2][pair.y - 1]!=3)){
                newPair.x = pair.x - 2;
                newPair.y = pair.y - 1;
            }
            //2

            else {
                if (((pair.x - 1) > 0) && ((pair.y - 2) >0) &&((result[pair.x - 1][pair.y - 2]!=1)) &&(result[pair.x - 1][pair.y - 2]!=3)){
                    newPair.x = pair.x - 1;
                    newPair.y = pair.y - 2;
                } //3
                else {
                    if (((pair.x + 1) <7) && ((pair.y - 2) >0)&&((result[pair.x + 1][pair.y - 2]!=1)) &&(result[pair.x + 1][pair.y - 2]!=3)) {
                        newPair.x = pair.x + 1;
                        newPair.y = pair.y - 2;
                    }

                    //4
                    else {
                        if (((pair.x +2) <7) && ((pair.y - 1) > 0) &&((result[pair.x +2][(pair.y - 1)]!=1)) &&(result[pair.x+2][(pair.y - 1)]!=3)){
                            newPair.x = pair.x +2;
                            newPair.y = pair.y - 1;
                        }

                        //5
                        else {
                            if (((pair.x + 2) < 7) && ((pair.y + 1) < 7)&&((result[pair.x + 2][pair.y + 1]!=1)) &&(result[pair.x + 2][pair.y + 1]!=3)){
                                newPair.x = pair.x + 2;
                                newPair.y = pair.y + 1;
                            }

                            //6
                            else {
                                if (((pair.x +1) <7) && ((pair.y +2) < 7) &&((result[pair.x +1][(pair.y +2)]!=1)) &&(result[pair.x+2][(pair.y - 1)]!=3)){
                                    newPair.x = pair.x +1;
                                    newPair.y = pair.y +2;
                                }
                                //7
                                else{
                                    if (((pair.x -1) >0) && ((pair.y +2) <7) &&((result[pair.x -1][(pair.y +2)]!=1)) &&(result[pair.x+2][(pair.y - 1)]!=3)){
                                        newPair.x = pair.x -1;
                                        newPair.y = pair.y +2;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return newPair;
    }




    static class Pair
    {
        public int x = 0;
        public int y = 0;
        public Pair(){};

        public Pair(int x, int y)
        {
            this.x = x;
            this.y = y;
        }
    }

    public static void showMatrix(int[][] matrixA){
        for (int i = 0; i < matrixA.length; i++) {
            for (int j = 0; j < matrixA.length; j++) {
                System.out.print(matrixA[i][j] + "\t");
            }
            System.out.println();
        }
    }
}
