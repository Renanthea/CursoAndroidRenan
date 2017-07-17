package br.com.renan.aula13_database.model;

public class Contato {
    private long idContato;
    private String nome;
    private String telefone;
    private int idade;

    public long getIdContato() {
        return idContato;
    }

    public void setIdContato(long idContato) {
        this.idContato = idContato;
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
        return this.getIdContato() + ": " + this.getNome();
    }
}
