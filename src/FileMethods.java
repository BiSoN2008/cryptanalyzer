import java.io.*;

public class FileMethods {
    public static String readFromFile(String src) {
        BufferedReader reader = null;
        StringBuilder message = new StringBuilder();
        try {
            String strCurrentLine;
            reader = new BufferedReader(new FileReader(src));
            while ((strCurrentLine = reader.readLine()) != null) {
                message.append(strCurrentLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null)
                    reader.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return message.toString();
    }

    public static void writeToFile(String scr, String data) {
        BufferedWriter br = null;
        try {
            br = new BufferedWriter(new FileWriter(new File(scr)));
            br.write(data);
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
