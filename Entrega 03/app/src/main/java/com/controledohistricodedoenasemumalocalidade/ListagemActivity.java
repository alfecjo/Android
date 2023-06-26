package com.controledohistricodedoenasemumalocalidade;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListagemActivity extends AppCompatActivity {

    private ListView listViewDoencas;
    private ArrayList<ControleDoencas> doencas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listagem);

        listViewDoencas = findViewById(R.id.listViewControleHis);

        listViewDoencas.setOnItemClickListener(
                (parent, view, position, id) -> {
                    ControleDoencas controleDoencas = (ControleDoencas) listViewDoencas.getItemAtPosition(position);

                    Toast.makeText(getApplicationContext()
                            , getString(R.string.doenca) + controleDoencas.getDoenca() + "\n"
                                    + getString(R.string.Contagio) + controleDoencas.getContagio() + "\n"
                                    + getString(R.string.checkboxPodeLevarAObto) + controleDoencas.getTipoPodeLevarAObto() + "\n"
                                    + getString(R.string.primeirosSocorros) + controleDoencas.getPrimeirosSocorros() + "\n"
                                    + getString(R.string.medicamentos) + controleDoencas.getMedicamento() + "\n"
                                    + getString(R.string.localidade) + controleDoencas.getLocalidade()
                            , Toast.LENGTH_SHORT).show();
                });
        popularLista();
    }

    private void popularLista() {
        doencas = new ArrayList<>();
        DoencaAdapter doencaAdapter = new DoencaAdapter(this, doencas);
        listViewDoencas.setAdapter(doencaAdapter);
    }

    public void sobre(View view) {
        SobreActivity.sobre(this);
    }

    public void cadastroDoencas(View view) {
        CadastroActivity.novaDoenca(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            Bundle bundle = data.getExtras();

            ControleDoencas controleDoencas = new ControleDoencas(bundle.getString(CadastroActivity.DOENCA));
            controleDoencas.setContagio(bundle.getString(CadastroActivity.CONTAGIO));
            controleDoencas.setTipoPodeLevarAObto(bundle.getString(CadastroActivity.TIPOPODELEVARAOBTO));
            controleDoencas.setPrimeirosSocorros(bundle.getString(CadastroActivity.TIPOPRIMEIROSSOCORROS));
            controleDoencas.setMedicamento(bundle.getString(CadastroActivity.MEDICAMENTOS));
            controleDoencas.setLocalidade(bundle.getString(CadastroActivity.LOCALIDADE));
            doencas.add(controleDoencas);

            DoencaAdapter doencaAdapter = new DoencaAdapter(this, doencas);

            listViewDoencas.setAdapter(doencaAdapter);
        }
    }
}