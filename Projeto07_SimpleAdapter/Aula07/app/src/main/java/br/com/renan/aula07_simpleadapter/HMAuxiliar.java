package br.com.renan.aula07_simpleadapter;

import java.util.HashMap;

/**
 * Renan de Arimathea
 * https://github.com/Renanthea
 */

public class HMAuxiliar extends HashMap<String,String> {
    public static final String ID = "id";
    public static final String NOME = "nome";
    public static final String TELEFONE = "telefone";

    @Override
    public String toString() {
        return "[" + get(ID) + "][" + get(NOME) + "][" + get(TELEFONE) + "]";
    }
}
