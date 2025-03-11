package code;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

// Manipula os arquivos
public class FileManager {

    DirectoryManager dm;

    // Método construtor
    public FileManager(DirectoryManager dm) {
        this.dm = dm;
    }


    // Escreve algo em um arquivo já existente
    public void writeFile(String name, String text) {
        String dir = dm.actualDir + "\\" + name;

        if (!dm.dirExist(dir)) return;

        Path path = Paths.get(dir);

        try {
            Files.write(path, text.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            ErrorManager.cantFind(dir);
        }
    }

    // Cria um arquivo
    public String createFile(String name) {
        String dir = dm.actualDir + "\\" + name;

        // Verifica se o arquivo não existe
        if (!dm.dirExist(dir)) {
            Path path = Paths.get(dir);

            try {
                Files.createFile(path);
            } catch (IOException e) {
                return ErrorManager.fileFail();
            }

        // Se existe, delete e crie um novo
        } else {
            deleteFile(name, true);
            createFile(name);
        }

        return dm.actualDir;
    }


    // Deleta um arquivo
    public void deleteFile(String path, boolean message) {
        Path file = Paths.get(path);

        // Se o caminho existe...
        if (dm.dirExist(path)) {

            try {
                Files.delete(file); // ... deleta o arquivo
            } catch (IOException e) {
                if (message) ErrorManager.noEmptyFolder();
            }

        } else {
            if (message) ErrorManager.cantFind(path, dm.actualDir);
        }
    }


    // Lê um arquivo
    public void readFile(String filePath) {
        // Cria um leitor para o arquivo
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line); // Exibe cada linha do arquivo
            }
            System.out.println();

        } catch (IOException e) {
            ErrorManager.cantFind(filePath); // Exibe mensagem de erro se o arquivo não for encontrado
        }
    }
}
