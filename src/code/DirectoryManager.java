package code;

import java.io.File;

// Manipula os diretórios
public class DirectoryManager {

    public static String actualDir = System.getProperty("user.dir");

    public static String[] getActualItens(){

        File files = new File(actualDir);
        File[] allItens = files.listFiles();
        int countFiles = 0;
        int countDir = 0;

        assert allItens != null;
        String[] fileString = new String[allItens.length+2];

        for (int i = 0; i < allItens.length; i++) {
            if (allItens[i].isDirectory()) {

                fileString[i] = "<DIR>\t" + allItens[i].getName();
                countDir++;

            } else {

                fileString[i] = "     \t" + allItens[i].getName();
                countFiles++;

            }
        }

        fileString[allItens.length] = String.format("\t%d arquivo(s)", countFiles);
        fileString[allItens.length+1] = String.format("\t%d pasta(s)", countDir);

        return fileString;
    }

}
