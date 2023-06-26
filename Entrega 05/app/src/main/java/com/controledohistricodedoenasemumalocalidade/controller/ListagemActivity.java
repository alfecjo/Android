package com.controledohistricodedoenasemumalocalidade.controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.view.ActionMode;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Switch;

import com.controledohistricodedoenasemumalocalidade.adapter.DoencaAdapter;
import com.controledohistricodedoenasemumalocalidade.R;
import com.controledohistricodedoenasemumalocalidade.about.SobreActivity;
import com.controledohistricodedoenasemumalocalidade.apoioSharedPreferences.ApoioSharedPreferences;
import com.controledohistricodedoenasemumalocalidade.model.ControleDoencas;

import java.util.ArrayList;

public class ListagemActivity extends AppCompatActivity {

    private ListView listViewDoencas;

    private ArrayList<ControleDoencas> listaDoencas;

    private int posicaoSelecionada = -1;
    private ActionMode actionMode;
    private View viewSelecionada;

    private DoencaAdapter doencaAdapter;
    private Switch myswitch;
    private ApoioSharedPreferences apoioSharedPreferences = new ApoioSharedPreferences();

    private ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            verificaTema();
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

       boolean myBoolean = apoioSharedPreferences.lerSharedPreferences(this);

        if (myBoolean) {
            setTheme(R.style.darkTema);
        } else {
            setTheme(R.style.lightTema);
        }

        setContentView(R.layout.activity_listagem);

        listViewDoencas = findViewById(R.id.listViewControleHis);

        Switch myswitch = findViewById(R.id.switchTrocaTemaListagem);

        if (myBoolean) myswitch.setChecked(true);

        myswitch.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            int currentTheme = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                if (currentTheme == Configuration.UI_MODE_NIGHT_YES) {
                    setTheme(R.style.darkTema);
                }
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                setTheme(R.style.lightTema);

            }
            apoioSharedPreferences.escreveSharedPreferences(this, isChecked);

            recreate();
        });
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

                    viewSelecionada = view;

                    verificaTema();

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


        boolean myBoolean = apoioSharedPreferences.lerSharedPreferences(this);

        if (myBoolean) {
            setTheme(R.style.darkTema);
            // Defina a cor de fundo da barra de ação
//            ActionBar actionBar = getSupportActionBar();
//            // Obter o valor da cor "colorPrimary" definida no arquivo themes.xml
//            int primaryColor = ContextCompat.getColor(this, R.color.purple_200);
//            actionBar.setBackgroundDrawable(new ColorDrawable(primaryColor));
        } else {
            setTheme(R.style.lightTema);
//            // Defina a cor de fundo da barra de ação
//            ActionBar actionBar = getSupportActionBar();
//            // Obter o valor da cor "colorPrimary" definida no arquivo themes.xml
//            int primaryColor = ContextCompat.getColor(this, R.color.purple_200);
//            actionBar.setBackgroundDrawable(new ColorDrawable(primaryColor));
        }

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
//        recreate();
    }

    private void verificaTema() {
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.darkTema);
        } else {
            setTheme(R.style.lightTema);
        }
    }

//    private void updateActionBarColors(int backgroundColor, int textColor) {
//        // Obtenha a barra de ação
//        ActionBar actionBar = getSupportActionBar();
//
//        // Defina a cor de fundo da barra de ação
//        actionBar.setBackgroundDrawable(new ColorDrawable(backgroundColor));
//
//        // Defina a cor do texto da barra de ação
//        Spannable text = new SpannableString(actionBar.getTitle());
//        text.setSpan(new ForegroundColorSpan(textColor), 0, text.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
//        actionBar.setTitle(text);
//    }

}