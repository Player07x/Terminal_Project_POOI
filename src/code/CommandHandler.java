package code;

/*
    X PWD → EXIBE O DIRETÓRIO ATUAL.
    X LS → LISTA OS ARQUIVOS E DIRETÓRIOS DO DIRETÓRIO ATUAL.
    X CD <DIRETÓRIO> → NAVEGA ENTRE DIRETÓRIOS.
    X MKDIR <NOME> → CRIA UM NOVO DIRETÓRIO.
    X TOUCH <ARQUIVO> → CRIA UM NOVO ARQUIVO VAZIO.
    X RM <ARQUIVO/DIRETÓRIO> → REMOVE UM ARQUIVO OU DIRETÓRIO.
    CAT <ARQUIVO> → EXIBE O CONTEÚDO DE UM ARQUIVO.
    ECHO <TEXTO> > <ARQUIVO> → ESCREVE TEXTO EM UM ARQUIVO.
    HISTORY → MOSTRA O HISTÓRICO DE COMANDOS DIGITADOS.
    X EXIT → ENCERRA O PROGRAMA.
 */

import java.util.Objects;

// GERENCIADOR DE COMANDOS
public class CommandHandler {

    public String[] arguments = null;
    public String output = null;
    public DirectoryManager dm;
    public FileManager fm;

    public CommandHandler(DirectoryManager dm, FileManager fm) {
        this.dm = dm;
        this.fm = fm;
    }

    public void pwd() {
        output = dm.actualDir + "\n";
        System.out.println(output);


    }

    public void ls() {

        for (String file : dm.getActualItens()) {
            System.out.println(file);
        }

        System.out.println();

    }


    // CD <DIRETÓRIO> → NAVEGA ENTRE DIRETÓRIOS
    public void cd() {

        // Se existe argumentos
        if (arguments.length > 1) {

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
                ErrorManager.noPath(); // Mensagem de erro
            }


        } else {
            System.out.printf(dm.actualDir + "\n\n");
        }

    }

    public void mkdir() {
        fm.createFolder(arguments[1]);

    }

    public void touch() {
        fm.createFile(arguments[1]);
        System.out.println(arguments[1] + "\n");

    }

    public void rm() {
        fm.deleteFile(arguments[1]);


    }

    public void cat() {
    }

    public void echo() {
    }

    public void history() {
    }

    public void exit() {
    }
}
