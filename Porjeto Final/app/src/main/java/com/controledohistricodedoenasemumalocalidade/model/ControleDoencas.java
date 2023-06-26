package com.controledohistricodedoenasemumalocalidade.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class ControleDoencas {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @NonNull
    private String doenca;
    @NonNull
    private String contagio;
    private String tipoPodeLevarAObto;
    private String primeirosSocorros;

    @NonNull
    private String medicamento;

    @NonNull
    private String localidade;

    public ControleDoencas(String doenca, String contagio, String tipoPodeLevarAObto, String primeirosSocorros, String medicamento, String localidade) {
        this.doenca = doenca;
        this.contagio = contagio;
        this.tipoPodeLevarAObto = tipoPodeLevarAObto;
        this.primeirosSocorros = primeirosSocorros;
        this.medicamento = medicamento;
        this.localidade = localidade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    //    necessario para carregar valores de forma correta "return getDoenca()"
    @Override
    public String toString() {
        return getDoenca();
    }
}
