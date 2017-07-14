package br.com.renan.aula13_database.model;

/**
 * Created by emilio on 01/07/2017.
 */

public class Contato {
    private long idcontato;
    private String nome;
    private String telefone;
    private int idade;

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
