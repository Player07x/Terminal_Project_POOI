package code;

public class ErrorManager {

    public static String commandNotIdentify(String command) {

        String error =  String.format("'%s' não é reconhecido como um comando interno\n" +
                "ou externo, um programa operável ou um arquivo em lotes.\n", command);

        System.out.println(error);

        return error;
    }

}
