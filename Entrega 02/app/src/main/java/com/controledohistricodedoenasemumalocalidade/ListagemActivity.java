package com.controledohistricodedoenasemumalocalidade;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListagemActivity extends AppCompatActivity {

    private ListView listViewDoencas;
    private ArrayList<ControleDoencas> doencas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listagem);

        listViewDoencas = findViewById(R.id.listViewControleHis);

        listViewDoencas.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent,
                                            View view,
                                            int position,
                                            long id) {
                        ControleDoencas controleDoencas = (ControleDoencas) listViewDoencas.getItemAtPosition(position);

                        Toast.makeText(getApplicationContext()
                                , getString(R.string.doenca) + controleDoencas.getDoenca() + getString(R.string.foiClicada)
                                , Toast.LENGTH_SHORT).show();
                    }
                });
        popularLista();
    }

    private void popularLista() {
        String[] nomes = getResources().getStringArray(R.array.stringArrayDoenca);
        String[] contagio = getResources().getStringArray(R.array.stringArrayContagio);
        int[] arraytiposPodeLevarAObto = getResources().getIntArray(R.array.integerArraytipoPodeLevarAObto);
        int[] arrayprimeirosSocorros = getResources().getIntArray(R.array.integerArrayprimeirosSocorros);

        doencas = new ArrayList<>();

        ControleDoencas controleDoencas;

        TipoPodeLevarAObto[] tipoPodeLevarAObito = TipoPodeLevarAObto.values();

        TipoPrimeirosSocorros[] tipoPrimeirosSocorros = TipoPrimeirosSocorros.values();

        for (int cont = 0; cont < nomes.length; cont++) {
            controleDoencas = new ControleDoencas(nomes[cont]);
            controleDoencas.setContagio(contagio[cont]);
            controleDoencas.setTipoPodeLevarAObto(tipoPodeLevarAObito[arraytiposPodeLevarAObto[cont]]);
            controleDoencas.setPrimeirosSocorros(tipoPrimeirosSocorros[arrayprimeirosSocorros[cont]]);

            doencas.add(controleDoencas);
        }
        DoencaAdapter doencaAdapter = new DoencaAdapter(this, doencas);

        listViewDoencas.setAdapter(doencaAdapter);
    }
}