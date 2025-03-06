package code;
import java.util.HashMap;
import java.util.Map;


public class CommandsHash {
    public static Map<String, Runnable> commandMap = new HashMap<>();

    static {

        // Cria um hashmap com todos os comandos aceitos
        commandMap.put("pwd", CommandHandler::pwd);
        commandMap.put("ls", CommandHandler::ls);
        commandMap.put("cd", CommandHandler::cd);
        commandMap.put("mkdir", CommandHandler::mkdir);
        commandMap.put("touch", CommandHandler::touch);
        commandMap.put("rm", CommandHandler::rm);
        commandMap.put("cat", CommandHandler::cat);
        commandMap.put("echo", CommandHandler::echo);
        commandMap.put("history", CommandHandler::history);
        commandMap.put("exit", CommandHandler::exit);

    }
}
