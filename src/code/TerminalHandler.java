package code;

public class TerminalHandler extends CommandsHash {

    CommandHandler ch;

    public TerminalHandler(CommandHandler ch) {
        super(ch);
        this.ch = ch;
    }

    // USA O INPUT DO SISTEMA E SEPARA CADA PALAVRA EM UMA STRING
    public String[] lineStrip(String comando) {

        String regex = "[,\\s]";

        return comando.trim().split(regex);
    }


    // DETECTA CADA COMANDO E ANALISA QUAL FUNÇÃO CHAMAR, SE NÃO, RETORNA ERRO
    public String runCommand(String[] line) {

        // Verifica foi digitado algo na linha
        if (line.length > 0) {

            // Verifica se o comando existe
            if (this.commandMap.containsKey(line[0])) {

                Runnable command = this.commandMap.get(line[0]);
                command.run();

                return ch.output;
            }
            else if (!line[0].trim().isEmpty()) {
                return ErrorManager.commandNotIdentify(line[0]);
            }
        }

        return "";
    }
}
