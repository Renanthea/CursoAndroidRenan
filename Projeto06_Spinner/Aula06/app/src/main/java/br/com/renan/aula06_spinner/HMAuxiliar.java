package br.com.renan.aula06_spinner;

import java.util.HashMap;

/**
 * Renan de Arimathea
 * https://github.com/Renanthea
 */

public class HMAuxiliar extends HashMap<String, String> {
    public static final String ID = "id";
    public static final String DESCRICAO = "descricao";

    @Override
    public String toString() {
        return get(DESCRICAO);
    }
}
