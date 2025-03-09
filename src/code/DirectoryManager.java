package code;

import javax.sound.midi.Soundbank;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

// Manipula os diretórios
public class DirectoryManager {

    public static String actualDir = System.getProperty("user.dir");


    // Cria uma pasta
    public static String createFolder(String name) {

        String dir = actualDir + "\\" + name;
        if (!DirectoryManager.dirExist(dir)) {
            File folder = new File(dir);
            folder.mkdir();

        } else {
            ErrorManager.folderExits(name);
        }

        return actualDir;
    }


    // Exibe uma lista de itens dentro de uma pasta
    public static String[] getActualItens(){

        File files = new File(actualDir);
        File[] allItens = files.listFiles();
        int countFiles = 0;
        int countDir = 0;

        assert allItens != null; // Evita que 'allItens' produza um 'NullPointerException'
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


    // Verifica a existencia de um diretório
    public static boolean dirExist(String dir) {

        File file = new File(dir);

        return file.exists();
    }


    // Corrige a formatação de um PATH
    public static void fixRealDir() {
        Path dir = Paths.get(actualDir);
        try {
            actualDir = dir.toRealPath().toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    // Retorna um ou mais diretórios
    public static boolean returnDir(String command) {
        int isReturn = 0;
        int isRoot = 0;
        int commandSize = command.length();
        char[] commandChar = command.toCharArray();

        // Lê toda a linha de comando
        for(int i = 0; i < commandChar.length; i++) {

            // Verifica se terá casos como ".../"
            if((i == commandSize-1 && (commandChar[i] == '/' || commandChar[i] == '\\')
                    || (i != commandSize-1 && commandChar[i] == '.'))) {

                isReturn++;
            }

            if((i == 0 && (commandChar[i] == '/' || commandChar[i] == '\\')
                    || (i != 0 && commandChar[i] == '.'))) {

                isRoot++;
            }

        }

        if (isRoot == commandChar.length) {

            Path root = Paths.get(actualDir);
            actualDir = root.getRoot().toString();

            return true;
        }
        else if (isReturn == commandChar.length) {

            // Para cada "." volta um diretório, até o limite do diretório raiz
            while (isReturn != 2) {

                File file = new File(actualDir);
                File parentFile = file.getParentFile();

                if (parentFile.exists()) {
                    actualDir = parentFile.getAbsolutePath();

                } else {
                    isReturn = 0;
                }

                isReturn--;
            }

            return true;
        }
        return false;
    }

}
