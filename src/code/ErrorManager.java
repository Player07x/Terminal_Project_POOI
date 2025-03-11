package code;

public class ErrorManager {

    // Identifica se o comando é válido
    public static String commandNotIdentify(String command) {

        String error =  String.format("""
                '%s' não é reconhecido como um comando interno
                ou externo, um programa operável ou um arquivo em lotes.
                """, command);

        System.out.println(error);

        return error;
    }


    // Erro se o caminho não existe
    public static String noPath() {
        String error = "O sistema não pode encontrar o caminho especificado.\n";
        System.out.println(error);

        return error;
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
    public static void cantFind(String path, String actualDir) {
        String error = String.format("Não foi possível encontrar %s\\%s.\n", actualDir,path);
        System.out.println(error);

    }


    // Retorna um erro por não encontrar uma pasta
    public static void cantFind(String path) {
        String error = String.format("Não foi possível encontrar %s.\n", path);
        System.out.println(error);

    }


    // Retorna um erro de sintaxe
    public static String sintaxiError() {
        String error = "A sintaxe do comando está incorreta.\n";
        System.out.println(error);

        return error;
    }


    // Retorna um erro de pasta não vazia
    public static void noEmptyFolder() {
        String error = "A pasta não está vazia.\n";
        System.out.println(error);

    }


    // Retorna um erro por não poder criar um arquivo
    public static String fileFail() {
        String error = "Não foi possível criar o arquivo.\n";
        System.out.println(error);

        return error;
    }
}
