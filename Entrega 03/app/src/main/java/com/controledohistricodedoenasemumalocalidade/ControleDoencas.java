package com.controledohistricodedoenasemumalocalidade;

public class ControleDoencas {
    private String doenca;
    private String contagio;
    private String tipoPodeLevarAObto;
    private String primeirosSocorros;

    private String medicamento;

    private String localidade;

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(String medicamento) {
        this.medicamento = medicamento;
    }

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

    public String getTipoPodeLevarAObto() {
        return tipoPodeLevarAObto;
    }

    public void setTipoPodeLevarAObto(String tipoPodeLevarAObto) {
        this.tipoPodeLevarAObto = tipoPodeLevarAObto;
    }

    public String getPrimeirosSocorros() {
        return primeirosSocorros;
    }

    public void setPrimeirosSocorros(String primeirosSocorros) {
        this.primeirosSocorros = primeirosSocorros;
    }
}
