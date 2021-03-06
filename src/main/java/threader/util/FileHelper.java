package threader.util;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.*;

public class FileHelper {
    public static File getFile(String path){
        File file = null;

        try{
            file = new File(path);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            if (file.createNewFile()){
                System.out.println("CREATED FILE  "+ file);
            }
        }catch (IOException e){
            System.out.println(e);
        }
        return file;

    }
    public static File getDirectory(String path){
        File file = null;

        file = new File(path);
        if (!file.exists() || !file.isDirectory()) {
            file.mkdir();
        }
        return file;

    }
    public static void WriteFile(String path, String toPrint){
        try{
            File file = FileHelper.getFile(path);
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(toPrint);
            writer.close();
        }catch (IOException e){
            System.out.println(e);
        }
    }
}
