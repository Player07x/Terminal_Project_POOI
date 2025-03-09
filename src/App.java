import code.*;

import java.awt.*;
import java.awt.event.WindowStateListener;
import java.io.File;
import java.util.Scanner;


public class App {

    public static void main(String[] args) throws Exception {

        // Variáveis declaradas
        String line, command = "";
        String[] lineStriped = null;
        Scanner scan = new Scanner(System.in);

        // Criação dos Objetos
        DirectoryManager dm = new DirectoryManager();
        FileManager fm = new FileManager(dm);
        CommandHandler cm = new CommandHandler(dm, fm);
        CommandsHash cHash = new CommandsHash(cm);
        TerminalHandler terminal = new TerminalHandler(cm);

        terminal.initializeCommandMap(); // Inicialização do HASH

        // Loop de execução do programa
        while (lineStriped == null || !lineStriped[0].equalsIgnoreCase("exit")) {

            System.out.print(dm.actualDir + "> ");
            line = scan.nextLine();
            lineStriped = terminal.lineStrip(line);
            cm.arguments = lineStriped;
            command = terminal.runCommand(lineStriped);

        }

        scan.close();
    }
}
