import code.*;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        // Variáveis declaradas
        String input, output;
        String[] lineStriped = null;
        Scanner scan = new Scanner(System.in);

        // Criação dos Objetos
        DirectoryManager dm = new DirectoryManager();
        FileManager fm = new FileManager(dm);
        CommandHandler cm = new CommandHandler(dm, fm);
        TerminalHandler terminal = new TerminalHandler(cm);
        Log log = new Log(dm);

        terminal.initializeCommandMap(); // Inicialização do HASH
        log.createLog(); // Cria uma pasta de LOG, se não houver, e um arquivo de LOG
        log.createFileLog(); // Cria o arquivo de log

        // Loop de execução do programa
        while (lineStriped == null || !lineStriped[0].equalsIgnoreCase("exit")) {

            // Input
            System.out.print(dm.actualDir + "> ");
            input = scan.nextLine();

            // Tratamento de comando e salvamento de histórico
            lineStriped = terminal.lineStrip(input);
            cm.arguments = lineStriped;
            cm.historyInput.add(input);

            // Rodar comando e salvar no log as saídas e entradas
            output = terminal.runCommand(lineStriped);
            log.writeLog(input, "[Input] ");
            log.writeLog(output, "[Output] ");
            log.writeLog("", "");

            // Limpar todas as saídas
            cm.output.clear();
        }

        scan.close();
    }
}
