package code;

import java.util.ArrayList;
import java.util.Objects;


// GERENCIADOR DE COMANDOS
public class CommandHandler {

    // Inicialização de variáveis
    public String[] arguments = null;
    public ArrayList<String> historyInput;
    public ArrayList<String> output = new ArrayList<>();

    // Inicialização de objetos
    public DirectoryManager dm;
    public FileManager fm;


    // Método construtor
    public CommandHandler(DirectoryManager dm, FileManager fm) {
        this.dm = dm;
        this.fm = fm;
        this.historyInput = new ArrayList<>();
    }



    // pwd → Exibe o diretório atual.
    public void pwd() {
        this.output.add(dm.actualDir);
        System.out.println(dm.actualDir + "\n");
    }


    // ls → Lista os arquivos e diretórios do diretório atual.
    public void ls() {

        for (String file : dm.getActualItens()) {
            System.out.println(file);
            this.output.add(file);
        }

        System.out.println();

    }


    // cd <diretório> → Navega entre diretórios.
    public void cd() {

        if (arguments.length <= 1) {
            String command = dm.actualDir + "\n";
            System.out.println(command);
            this.output.add(command);

            return;
        }


        // Se for um retorno (../), então encerra essa função
        if (dm.returnDir(arguments[1])) {
            return;
        }

        // Valida se o caminho é válido
        arguments[1] = ErrorManager.pathValidator(arguments[1]);
        String dir = !Objects.equals(arguments[1], "") ?
                dm.actualDir + "\\" + arguments[1] : dm.actualDir;

        // Verifica se o diretório existe e é relativo
        if (dm.dirExist(dir)) {
            dm.actualDir = dir;
            dm.fixRealDir();
        }
        // Verifica se o diretório existe e é absoluto
        else if (dm.dirExist(arguments[1])) {
            dm.actualDir = arguments[1];
            dm.fixRealDir();
        }
        else {
            String error = ErrorManager.noPath();
            System.out.println(error);
            this.output.add(error); // Mensagem de erro
        }
    }


    // mkdir <nome> → Cria um novo diretório.
    public void mkdir() {
        if (arguments.length <= 1) {
            System.out.println(ErrorManager.sintaxiError());
            this.output.add(ErrorManager.sintaxiError());
            return;
        }

        // Verifica se o caminho é absoluto ou relativo
        String path = arguments[1];
        if (path.contains(":") || path.startsWith("\\")) {
            // Caminho absoluto
            fm.createDirectory(path);
        } else {
            // Caminho relativo
            path = dm.actualDir + "\\" + path;
            fm.createDirectory(path);
        }

        System.out.println(arguments[1] + "\n");
        this.output.add(arguments[1] + "\n");
    }


    // touch <arquivo> → Cria um novo arquivo vazio.
    public void touch() {
        if (arguments.length <= 1) {
            String error = ErrorManager.sintaxiError();
            System.out.println(error);
            this.output.add(error);
            return;
        }

        String result = fm.createFile(arguments[1]);

        if (result.equals(ErrorManager.fileFail())) {
            System.out.println(result);
            this.output.add(result);

            return;
        }
        System.out.println(arguments[1] + "\n");
        this.output.add(arguments[1] + "\n");
    }


    // rm <arquivo/diretório> → Remove um arquivo ou diretório.
    public void rm() {
        if (arguments.length <= 1) {
            String error = ErrorManager.sintaxiError();
            this.output.add(error);
            return;
        }

        fm.deleteFile(arguments[1], true);

    }


    // cat <arquivo> → Exibe o conteúdo de um arquivo.
    public void cat() {
        if (arguments.length <= 1) {
            String error = ErrorManager.sintaxiError();
            System.out.println(error);
            this.output.add(error);

            return;
        }

        String filePath = dm.actualDir + "\\" + arguments[1];
        if (dm.fileExist(filePath)) {
            String text = fm.readFile(filePath);
            System.out.println(text);
            this.output.add(text);
        }
        else {
            String error = ErrorManager.cantFind(filePath);
            System.out.println(error);
            this.output.add(error);
        }

    }


    // echo <texto> > <arquivo> → Escreve texto em um arquivo.
    public void echo() {

        int commandIndex = 0; // 1 representa o comando ECHO, ou seja, echo não possui ">"
        StringBuilder text = new StringBuilder();

        // Verificar se existe o comando ">" e salvar a posição
        for (String arg : arguments) {

            if (Objects.equals(arg, ">")) {
                break;
            }
            commandIndex++;
        }


        // Se não existir ">", então o código gerará um erro
        if (commandIndex == 0 || (commandIndex) >= arguments.length-1) {
            String error = ErrorManager.sintaxiError();
            this.output.add(error);
            return;
        }

        for (int i = 1; i < arguments.length; i++) {

            // Se "comando" é diferente de ">" ou i é diferente de commandIndex
            // Se "comando" é igual a ">" ou i é igual a command index
            if (!arguments[i].equals(">") && i != commandIndex+1) {
                text.append(arguments[i]).append(" ");
            }
        }

        fm.createFile(arguments[commandIndex+1]); // Cria um arquivo
        fm.writeFile(arguments[commandIndex+1], text.toString()); // Reescreve um arquivo já criado
        System.out.println();
    }


    // history → Mostra o histórico de comandos digitados.
    public void history() {
        for (int i = 0; i < historyInput.size(); i++) {
            String text = String.format("[%d] %s\n", i, historyInput.get(i));
            System.out.print(text);
            this.output.add(text);
        }
        System.out.println();
    }


    // exit → Encerra o programa.
    public void exit() {
    }
}
