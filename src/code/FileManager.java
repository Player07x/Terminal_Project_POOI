package code;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

// Manipula os arquivos
public class FileManager {

    DirectoryManager dm;

    public FileManager(DirectoryManager dm) {
        this.dm = dm;
    }

    // Cria uma pasta
    public String createFolder(String name) {

        String dir = dm.actualDir + "\\" + name;
        if (!dm.dirExist(dir)) {
            File folder = new File(dir);
            folder.mkdir();

        } else {
            ErrorManager.folderExits(name);
        }

        return dm.actualDir;
    }

    // Cria um arquivo
    public String createFile(String name) {
        String dir = dm.actualDir + "\\" + name;

        if (!dm.dirExist(dir)) {
            Path path = Paths.get(dir);

            try {
                Files.createFile(path);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        } else {
            deleteFile(name);
            createFile(name);
        }

        return dm.actualDir;
    }

    public void deleteFile(String path) {
        Path file = Paths.get(path);

        if (dm.dirExist(path)) {
            try {
                Files.delete(file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            ErrorManager.canFind(path, dm.actualDir);
        }
    }
}
