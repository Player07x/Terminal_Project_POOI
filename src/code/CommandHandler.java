package code;

/*
    PWD → EXIBE O DIRETÓRIO ATUAL.
    LS → LISTA OS ARQUIVOS E DIRETÓRIOS DO DIRETÓRIO ATUAL.
    CD <DIRETÓRIO> → NAVEGA ENTRE DIRETÓRIOS.
    MKDIR <NOME> → CRIA UM NOVO DIRETÓRIO.
    TOUCH <ARQUIVO> → CRIA UM NOVO ARQUIVO VAZIO.
    RM <ARQUIVO/DIRETÓRIO> → REMOVE UM ARQUIVO OU DIRETÓRIO.
    CAT <ARQUIVO> → EXIBE O CONTEÚDO DE UM ARQUIVO.
    ECHO <TEXTO> > <ARQUIVO> → ESCREVE TEXTO EM UM ARQUIVO.
    HISTORY → MOSTRA O HISTÓRICO DE COMANDOS DIGITADOS.
    EXIT → ENCERRA O PROGRAMA.
 */

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

    public static Runnable cd() {

        return null;
    }

    public static Runnable mkdir() {

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
