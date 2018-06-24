package threader.util;

import java.io.File;
import java.io.IOException;

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
}
