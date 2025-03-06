package code;

import java.util.Scanner;
import code.CommandsHash;


public class TerminalHandler {

    // USA O INPUT DO SISTEMA E SEPARA CADA PALAVRA EM UMA STRING
    public static String[] lineStrip(String comando) {

        String regex = "[,\\s]";

        return comando.trim().split(regex);
    }


    // DETECTA CADA COMANDO E ANALISA QUAL FUNÇÃO CHAMAR, SE NÃO, RETORNA ERRO
    public static String runCommand(String[] line) {

        // Verifica foi digitado algo na linha
        if (line.length > 0) {

            // Verifica se o comando existe
            if (CommandsHash.commandMap.containsKey(line[0])) {

                Runnable command = CommandsHash.commandMap.get(line[0]);
                command.run();

                return CommandHandler.output;
            }
            else if (!line[0].trim().isEmpty()) {
                return ErrorManager.commandNotIdentify(line[0]);
            }
        }

        return "";
    }

}
