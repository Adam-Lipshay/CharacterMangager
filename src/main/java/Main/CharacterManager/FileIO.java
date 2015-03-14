package Main.CharacterManager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;


public class FileIO {
    static File Characters = new File("Characters.txt");

    public static void output(String Write) throws IOException, InterruptedException{
        FileWriter fw = new FileWriter(Characters,true);
        fw.write(Write + "\n");
        fw.close();
    }
    public static void input() throws IOException{
        FileReader fr = new FileReader(Characters);
        BufferedReader reader = new BufferedReader(fr);
        int i=0;
        String line;
        while((line = reader.readLine()) != null){
            Main.Characters[i] = line;
            i++;
        }
        reader.close();
    }


    public static void CharChange(String Char) throws IOException{
        try {
            FileInputStream fstream = new FileInputStream("Skyrim.ini");
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String strLine;
            StringBuilder fileContent = new StringBuilder();
            while ((strLine = br.readLine()) != null) {
                System.out.println(strLine);
                String tokens[] = strLine.split("=");
                if (tokens.length > 0) {
                    if (tokens[0].equals("SLocalSavePath")) {
                        tokens[1] = "/Saves/"+Char;
                        String newLine = tokens[0] + "=" + tokens[1];
                        fileContent.append(newLine);
                        fileContent.append("\n");
                    } else {
                        // update content as it is
                        fileContent.append(strLine);
                        fileContent.append("\n");
                    }
                }
            }
            FileWriter fstreamWrite = new FileWriter("Skyrim.ini");
            BufferedWriter out = new BufferedWriter(fstreamWrite);
            out.write(fileContent.toString());
            out.close();
            fstream.close();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
