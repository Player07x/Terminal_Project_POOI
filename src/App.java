import code.*;

import java.awt.*;
import java.awt.event.WindowStateListener;
import java.util.Scanner;


public class App {

    public static void main(String[] args) throws Exception {

        // Variáveis declaradas
        String line, command = "";
        String[] lineStriped = null;
        Scanner scan = new Scanner(System.in);

        // Loop de execução do programa
        while (lineStriped == null || !lineStriped[0].equalsIgnoreCase("exit")) {

            System.out.print(DirectoryManager.actualDir + "> ");
            line = scan.nextLine();
            lineStriped = TerminalHandler.lineStrip(line);
            CommandHandler.arguments = lineStriped;
            command = TerminalHandler.runCommand(lineStriped);

        }

        scan.close();

    }

}
