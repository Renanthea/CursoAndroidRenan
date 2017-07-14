package br.com.renan.aula08_baseadapter;

import java.util.Date;

/**
 * Renan de Arimathea
 * https://github.com/Renanthea
 */

public class Convenio {
    private int codigo;
    private String descricao;
    private Date fundacao;
    private int logo;

    public Convenio(int codigo, String descricao, Date fundacao, int logo) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.fundacao = fundacao;
        this.logo = logo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getFundacao() {
        return fundacao;
    }

    public void setFundacao(Date fundacao) {
        this.fundacao = fundacao;
    }

    public int getLogo() {
        return logo;
    }

    public void setLogo(int logo) {
        this.logo = logo;
    }
}
