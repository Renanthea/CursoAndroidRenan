package br.com.renan.aula06_spinner;

import java.io.Serializable;

/**
 * Renan de Arimathea
 * https://github.com/Renanthea
 */

public class Produto implements Serializable {
    private long idproduto;
    private String descricao;
    private double valor;
    private String barcode;

    public long getIdproduto() {
        return idproduto;
    }

    public void setIdproduto(long idproduto) {
        this.idproduto = idproduto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    @Override
    public String toString() {
        return this.getDescricao() + " - " + this.getValor();
    }
}
