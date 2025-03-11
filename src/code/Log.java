package code;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Log extends FileManager {

    DirectoryManager dm;
    String logName;

    public Log(DirectoryManager dm) {
        super(dm);
        this.dm = dm;
    }

    public void createLog() {
        File logDir = new File(dm.logDir);

        logDir.mkdir();
    }

    public void createFileLog() {
        LocalDate data = LocalDate.now();
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH-mm-ss");
        String formattedTime = LocalTime.now().format(timeFormatter);
        String name = logName;

        if (logName == null) {
            name = "log-" + data + "-" + formattedTime + ".txt";
        }

        logName = name;

        Path logFilePath = Paths.get(dm.logDir, logName);

        if (!Files.exists(logFilePath)) {
            try {
                Files.createFile(logFilePath);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            deleteFile(logName, false);
            createFile(logName);
        }
    }


    public void writeLog(String logEntry, String id) {

        File fileLog = new File(dm.logDir + "\\" + logName);
        String text = id + logEntry + "\n";

        // Adicionar informação ao log se ele existir
        if (dm.dirExist(dm.logDir)) createLog();

        try {
            Files.write(Paths.get(fileLog.toString()), text.getBytes(), StandardOpenOption.WRITE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            return;
        }

    }
}
