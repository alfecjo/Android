package com.controledohistricodedoenasemumalocalidade;

public class ControleDoencas {
    private String doenca;
    private String contagio;
    private TipoPodeLevarAObto tipoPodeLevarAObto;
    private TipoPrimeirosSocorros primeirosSocorros;

    public ControleDoencas(String doenca) {
        this.doenca = doenca;
    }

    public String getDoenca() {
        return doenca;
    }

    public void setDoenca(String doenca) {
        this.doenca = doenca;
    }

    public String getContagio() {
        return contagio;
    }

    public void setContagio(String contagio) {
        this.contagio = contagio;
    }

    public TipoPodeLevarAObto getTipoPodeLevarAObto() {
        return tipoPodeLevarAObto;
    }

    public void setTipoPodeLevarAObto(TipoPodeLevarAObto tipoPodeLevarAObto) {
        this.tipoPodeLevarAObto = tipoPodeLevarAObto;
    }

    public TipoPrimeirosSocorros getPrimeirosSocorros() {
        return primeirosSocorros;
    }

    public void setPrimeirosSocorros(TipoPrimeirosSocorros primeirosSocorros) {
        this.primeirosSocorros = primeirosSocorros;
    }
}
