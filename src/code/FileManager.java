package code;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.*;

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

    // Cria um diretório
    public String createDirectory(String name) {
        Path path;
        try {
            // Normaliza o caminho para evitar problemas com barras
            path = Paths.get(name).normalize();
        } catch (InvalidPathException e) {
            String error = "Caminho inválido: " + name;
            System.out.println(error);
            return error;
        }

        // Verifica se o diretório já existe
        if (Files.exists(path)) {
            String error = ErrorManager.fileFail();
            System.out.println(error);
            return error;
        }

        try {
            Files.createDirectories(path); // Cria o diretório (e pais se necessário)
            return path.toString();
        } catch (IOException e) {
            String error = "Erro ao criar diretório: " + e.getMessage();
            System.out.println(error);
            return error;
        }
    }


    // Cria um arquivo
    public String createFile(String name) {
        String dir = dm.actualDir + "\\" + name;
        Path path = Paths.get(dir);

        // Verifica se é um diretório ou arquivo existente
        if (dm.dirExist(dir)) {
            ErrorManager.cantFind(dir); // Erro se for diretório
            return ErrorManager.fileFail();
        } else if (dm.fileExist(dir)) {
            deleteFile(dir, false); // Deleta se for arquivo existente
        }

        try {
            Files.createFile(path); // Cria o arquivo
            return dm.actualDir;
        } catch (IOException e) {
            return ErrorManager.fileFail();
        }
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
    public String readFile(String filePath) {
        StringBuilder output = new StringBuilder();

        // Cria um leitor para o arquivo
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n"); // Retorna cada linha do arquivo
            }

        } catch (IOException e) {
            return ErrorManager.cantFind(filePath); // Exibe mensagem de erro se o arquivo não for encontrado
        }

        return output.toString();
    }
}
