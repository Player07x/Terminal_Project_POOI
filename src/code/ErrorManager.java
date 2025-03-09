package code;

public class ErrorManager {

    public static String commandNotIdentify(String command) {

        String error =  String.format("'%s' não é reconhecido como um comando interno\n" +
                "ou externo, um programa operável ou um arquivo em lotes.\n", command);

        System.out.println(error);

        return error;
    }

    public static String noPath() {
        String error = "O sistema não pode encontrar o caminho especificado.\n";
        System.out.println(error);

        return error;
    }

    public static String pathValidator(String path) {
        char[] charPath = path.toCharArray();
        int validator = 0;
        int isReturn = 0;

        for(int i = 0; i < charPath.length; i++) {

            if(charPath[i] == '.' || charPath[i] == '/' || charPath[i] == '\\') {
                validator++;

                if (i == 0 && (charPath[i] == '/' || charPath[i] == '\\') ||
                        (i != 0 && charPath[i] == '.')) {
                    isReturn++;
                }

            }
        }

        if (isReturn == charPath.length) ;
        if (validator == charPath.length)  path = "";

        return path;
    }

    public static void folderExits(String name) {
        System.out.printf("Já existe uma subpasta ou um arquivo %s.\n\n", name);
    }

    public static void canFind(String path, String actualDir) {
        System.out.printf("Não foi possível encontrar %s\\%s.\n\n", actualDir,path);
    }

    public static void sintaxiError() {
        System.out.println("A sintaxe do comando está incorreta.\n");
    }
}
