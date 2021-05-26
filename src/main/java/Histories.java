import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class Histories {
    private static PrintWriter listen;
    public static String nameFileHistoryes(String login){
        return "hisroties+ " + login + "txt";
    }
    public static void gogo(String login){
        try {
            listen = new PrintWriter(new FileOutputStream(nameFileHistoryes(login), true ), true);
//                    ставил лишнюю скобку и он мне автофлаш сразу передавал
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }
}
