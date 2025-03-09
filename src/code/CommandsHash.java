package code;
import java.util.HashMap;
import java.util.Map;


public class CommandsHash {
    public Map<String, Runnable> commandMap = new HashMap<>();
    private final CommandHandler cm;

    public CommandsHash(CommandHandler cm) {
        this.cm = cm;
    }


    // Cria um hashmap com todos os comandos aceitos
    public void initializeCommandMap() {
        commandMap.put("pwd", cm::pwd);
        commandMap.put("ls", cm::ls);
        commandMap.put("cd", cm::cd);
        commandMap.put("mkdir", cm::mkdir);
        commandMap.put("touch", cm::touch);
        commandMap.put("rm", cm::rm);
        commandMap.put("cat", cm::cat);
        commandMap.put("echo", cm::echo);
        commandMap.put("history", cm::history);
        commandMap.put("exit", cm::exit);
    }

}
