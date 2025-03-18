package code;

public class ErrorManager {

    // Identifica se o comando é válido
    public static String commandNotIdentify(String command) {

        return String.format("""
                '%s' não é reconhecido como um comando interno
                ou externo, um programa operável ou um arquivo em lotes.
                """, command);
    }


    // Erro se o caminho não existe
    public static String noPath() {

        return "O sistema não pode encontrar o caminho especificado.\n";
    }


    // Valida o caminho
    public static String pathValidator(String path) {
        char[] charPath = path.toCharArray();
        int validator = 0;

        for (char c : charPath) {
            if (c == '.' || c == '/' || c == '\\') {
                validator++;
            }
        }
        if (validator == charPath.length)  path = "";

        return path;
    }


    // Retorna um erro por não encontrar um arquivo
    public static String cantFind(String path, String actualDir) {

        return String.format("Não foi possível encontrar %s\\%s.\n", actualDir,path);
    }


    // Retorna um erro por não encontrar uma pasta
    public static String cantFind(String path) {

        return String.format("Não foi possível encontrar %s.\n", path);
    }


    // Retorna um erro de sintaxe
    public static String sintaxiError() {

        return "A sintaxe do comando está incorreta.\n";
    }


    // Retorna um erro de pasta não vazia
    public static String noEmptyFolder() {

        return "A pasta não está vazia.\n";
    }


    // Retorna um erro por não poder criar um arquivo
    public static String fileFail() {

        return "Não foi possível criar o arquivo.\n";
    }
}
