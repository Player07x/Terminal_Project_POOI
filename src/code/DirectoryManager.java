package code;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

// Manipula os diretórios
public class DirectoryManager {

    public String actualDir = System.getProperty("user.dir");
    public String logDir = actualDir + "\\" + "log";

    // Exibe uma lista de itens dentro de uma pasta
    public String[] getActualItens(){

        File files = new File(actualDir);
        File[] allItens = files.listFiles();
        int countFiles = 0;
        int countDir = 0;

        assert allItens != null; // Evita que 'allItens' produza um 'NullPointerException'
        String[] fileString = new String[allItens.length+2];

        // Para cada pasta ou arquivo, exibe e soma ao contador
        for (int i = 0; i < allItens.length; i++) {
            if (allItens[i].isDirectory()) {

                fileString[i] = "<DIR>\t" + allItens[i].getName();
                countDir++;

            } else {

                fileString[i] = "     \t" + allItens[i].getName();
                countFiles++;

            }
        }

        // No fim da linha, adiciona a quantidade de arquivos e pastas
        fileString[allItens.length] = String.format("\t%d arquivo(s)", countFiles);
        fileString[allItens.length+1] = String.format("\t%d pasta(s)", countDir);

        return fileString;
    }


    // Verifica a existencia de um diretório
    public boolean dirExist(String dir) {

        File file = new File(dir);

        return file.exists();
    }


    // Verifica a existencia de um arquivo
    public boolean fileExist(String dir) {

        File file = new File(dir);

        return file.isFile();
    }


    // Corrige a formatação de um PATH
    public void fixRealDir() {

        Path dir = Paths.get(actualDir);

        try {
            actualDir = dir.toRealPath().toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    // Retorna um ou mais diretórios
    public boolean returnDir(String command) {

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

            // Verifica se terá casos como "\..."
            if((i == 0 && (commandChar[i] == '/' || commandChar[i] == '\\')
                    || (i != 0 && commandChar[i] == '.'))) {

                isRoot++;
            }

        }

        // Se o comando "\." for digitado, ir até ROOT
        if (isRoot == commandChar.length) {

            Path root = Paths.get(actualDir);
            actualDir = root.getRoot().toString();

            return true;
        }

        // Se for ".../" voltar o número de "."
        else if (isReturn == commandChar.length) {

            // Para cada "." volta um diretório, até o limite do diretório raiz
            while (isReturn != 2 && !Paths.get(actualDir).getRoot().toString().equals(actualDir)) {

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
