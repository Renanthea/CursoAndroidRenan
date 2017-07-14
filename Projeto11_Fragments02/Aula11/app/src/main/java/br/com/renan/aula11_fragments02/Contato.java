package br.com.renan.aula11_fragments02;

import java.io.Serializable;

/**
 * Renan de Arimathea
 * https://github.com/Renanthea
 */

public class Contato implements Serializable {

    private long idcontato;
    private String nome;
    private String telefone;
    private int idade;

    public Contato(long idcontato, String nome, String telefone, int idade) {
        this.idcontato = idcontato;
        this.nome = nome;
        this.telefone = telefone;
        this.idade = idade;
    }

    public Contato(){}

    public long getIdcontato() {
        return idcontato;
    }

    public void setIdcontato(long idcontato) {
        this.idcontato = idcontato;
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

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    @Override
    public String toString() {
        return this.getIdcontato() + ": " + this.getNome();
    }
}
