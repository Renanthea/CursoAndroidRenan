package br.com.renan.aula14_http_post;

import java.io.Serializable;

/**
 * Renan de Arimathea
 * https://github.com/Renanthea
 */

public class Contato implements Serializable {
    private int id;
    private String nome;
    private String telefone;
    private String dataNascimento; //private Date dataNascimento

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}
