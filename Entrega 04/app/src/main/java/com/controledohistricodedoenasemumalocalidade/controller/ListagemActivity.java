package com.controledohistricodedoenasemumalocalidade.controller;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.controledohistricodedoenasemumalocalidade.adapter.DoencaAdapter;
import com.controledohistricodedoenasemumalocalidade.R;
import com.controledohistricodedoenasemumalocalidade.about.SobreActivity;
import com.controledohistricodedoenasemumalocalidade.model.ControleDoencas;

import java.util.ArrayList;

public class ListagemActivity extends AppCompatActivity {

    private ListView listViewDoencas;

    private ArrayList<ControleDoencas> listaDoencas;

    private int posicaoSelecionada = -1;
    private ActionMode actionMode;
    private View viewSelecionada;

    private DoencaAdapter doencaAdapter;

    private ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            MenuInflater inflate = actionMode.getMenuInflater();
            inflate.inflate(R.menu.doencas_edicao_cadastro, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.menuItemAlterar:
                    alterarDoenca();
                    actionMode.finish();
                    return true;

                case R.id.menuItemExcluir:
                    excluirDoenca();
                    actionMode.finish();
                    return true;

                default:
                    return false;
            }
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            if (viewSelecionada != null) {
                viewSelecionada.setBackgroundColor(Color.TRANSPARENT);
            }

            actionMode = null;
            viewSelecionada = null;

            listViewDoencas.setEnabled(true);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listagem);

        listViewDoencas = findViewById(R.id.listViewControleHis);

        listViewDoencas.setOnItemClickListener(
                (parent, view, position, id) -> {

                    posicaoSelecionada = position;
                    alterarDoenca();
                });

        listViewDoencas.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        listViewDoencas.setOnItemLongClickListener(
                (parent, view, position, id) -> {
                    if (actionMode != null) {
                        return false;
                    }

                    posicaoSelecionada = position;

                    view.setBackgroundColor(Color.LTGRAY);

                    viewSelecionada = view;

                    listViewDoencas.setEnabled(false);

                    actionMode = startSupportActionMode(mActionModeCallback);

                    return true;
                });
        popularLista();
    }

    private void popularLista() {
        listaDoencas = new ArrayList<>();
        doencaAdapter = new DoencaAdapter(this, listaDoencas);
        listViewDoencas.setAdapter(doencaAdapter);
    }

    private void excluirDoenca() {
        listaDoencas.remove(posicaoSelecionada);
        listViewDoencas.setAdapter(doencaAdapter);
    }

    private void alterarDoenca() {
        ControleDoencas controleDoencas = listaDoencas.get(posicaoSelecionada);
        CadastroActivity.alterarDoenca(this, controleDoencas);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.doencas_listagem, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.menuItemNovo:
                CadastroActivity.novaDoenca(this);
                return true;

            case R.id.menuItemAbout:
                SobreActivity.sobre(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            Bundle bundle = data.getExtras();
            if (requestCode == CadastroActivity.ALTERAR) {

                ControleDoencas controleDoencas = listaDoencas.get(posicaoSelecionada);
                controleDoencas.setDoenca(bundle.getString(CadastroActivity.DOENCA));
                controleDoencas.setContagio(bundle.getString(CadastroActivity.CONTAGIO));
                controleDoencas.setTipoPodeLevarAObto(bundle.getString(CadastroActivity.TIPOPODELEVARAOBTO));
                controleDoencas.setPrimeirosSocorros(bundle.getString(CadastroActivity.TIPOPRIMEIROSSOCORROS));
                controleDoencas.setMedicamento(bundle.getString(CadastroActivity.MEDICAMENTOS));
                controleDoencas.setLocalidade(bundle.getString(CadastroActivity.LOCALIDADE));

                posicaoSelecionada = -1;
            } else {
                listaDoencas.add(new ControleDoencas(bundle.getString(CadastroActivity.DOENCA),
                        bundle.getString(CadastroActivity.CONTAGIO),
                        bundle.getString(CadastroActivity.TIPOPODELEVARAOBTO),
                        bundle.getString(CadastroActivity.TIPOPRIMEIROSSOCORROS),
                        bundle.getString(CadastroActivity.MEDICAMENTOS),
                        bundle.getString(CadastroActivity.LOCALIDADE)));
            }

            DoencaAdapter doencaAdapter = new DoencaAdapter(this, listaDoencas);

            listViewDoencas.setAdapter(doencaAdapter);
        }
    }
}