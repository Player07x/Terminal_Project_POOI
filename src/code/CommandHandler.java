package code;

/*
    X PWD → EXIBE O DIRETÓRIO ATUAL.
    X LS → LISTA OS ARQUIVOS E DIRETÓRIOS DO DIRETÓRIO ATUAL.
    X CD <DIRETÓRIO> → NAVEGA ENTRE DIRETÓRIOS.
    X MKDIR <NOME> → CRIA UM NOVO DIRETÓRIO.
    TOUCH <ARQUIVO> → CRIA UM NOVO ARQUIVO VAZIO.
    RM <ARQUIVO/DIRETÓRIO> → REMOVE UM ARQUIVO OU DIRETÓRIO.
    CAT <ARQUIVO> → EXIBE O CONTEÚDO DE UM ARQUIVO.
    ECHO <TEXTO> > <ARQUIVO> → ESCREVE TEXTO EM UM ARQUIVO.
    HISTORY → MOSTRA O HISTÓRICO DE COMANDOS DIGITADOS.
    X EXIT → ENCERRA O PROGRAMA.
 */

import java.util.Objects;
import java.util.Scanner;

// GERENCIADOR DE COMANDOS
public class CommandHandler {

    public static String[] arguments = null;
    public static String output = null;


    public static Runnable pwd() {
        output = DirectoryManager.actualDir + "\n";
        System.out.println(output);


        return null;
    }

    public static Runnable ls() {

        for (String file : DirectoryManager.getActualItens()) {
            System.out.println(file);
        }

        System.out.println();

        return null;
    }


    // CD <DIRETÓRIO> → NAVEGA ENTRE DIRETÓRIOS
    public static Runnable cd() {

        // Se existe argumentos
        if (arguments.length > 1) {

            // Se for um retorno (../), então encerra essa função
            if (DirectoryManager.returnDir(arguments[1])) {
                return null;
            }

            // Valida se o caminho é válido
            arguments[1] = ErrorManager.pathValidator(arguments[1]);
            String dir = !Objects.equals(arguments[1], "") ?
                    DirectoryManager.actualDir + "\\" + arguments[1] : DirectoryManager.actualDir;

            // Verifica se o diretório existe e é relativo
            if (DirectoryManager.dirExist(dir)) {
                DirectoryManager.actualDir = dir;
                DirectoryManager.fixRealDir();

            }
            // Verifica se o diretório existe e é absoluto
            else if (DirectoryManager.dirExist(arguments[1])) {
                DirectoryManager.actualDir = arguments[1];
                DirectoryManager.fixRealDir();
            }
            else {
                ErrorManager.noPath(); // Mensagem de erro
            }


        } else {
            System.out.printf(DirectoryManager.actualDir + "\n\n");
        }

        return null;
    }

    public static Runnable mkdir() {
        DirectoryManager.createFolder(arguments[1]);

        return null;
    }

    public static Runnable touch() {
        return null;
    }

    public static Runnable rm() {
        return null;
    }

    public static Runnable cat() {
        return null;
    }

    public static Runnable echo() {
        return null;
    }

    public static Runnable history() {
        return null;
    }

    public static Runnable exit() {
        return null;
    }
}
