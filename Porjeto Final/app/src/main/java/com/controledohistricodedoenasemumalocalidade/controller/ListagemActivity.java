package com.controledohistricodedoenasemumalocalidade.controller;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
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
import com.controledohistricodedoenasemumalocalidade.persiste.ControleDoencasDatabase;
import com.controledohistricodedoenasemumalocalidade.utils.UtilsGui;

import java.util.ArrayList;
import java.util.List;

public class ListagemActivity extends AppCompatActivity {

    private ListView listViewDoencas;
    private List<ControleDoencas> listaDoencas;
    private int posicaoSelecionada = -1;
    private ActionMode actionMode;
    private View viewSelecionada;
    private ControleDoencas controleDoencas;
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

        myswitch = findViewById(R.id.switchTrocaTemaListagem);

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

        listViewDoencas.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        listViewDoencas.setOnItemLongClickListener(
                (parent, view, position, id) -> {
                    posicaoSelecionada = position;
                    viewSelecionada = view;
                    verificaTema();
                    listViewDoencas.setEnabled(false);
                    actionMode = startSupportActionMode(mActionModeCallback);
                    return true;
                });

        listViewDoencas.setOnItemClickListener(
                (parent, view, position, id) -> {
                    posicaoSelecionada = position;
                    alterarDoenca();
                });
        popularLista();
    }

    private void popularLista() {
        ControleDoencasDatabase database = ControleDoencasDatabase.getDatabase(this);
        listaDoencas = database.controleDoencasDao().queryAll();
        doencaAdapter = new DoencaAdapter(this, listaDoencas);
        listViewDoencas.setAdapter(doencaAdapter);
    }

    private void excluirDoenca() {
        controleDoencas = listaDoencas.get(posicaoSelecionada);
        String msg = getString(R.string.titulo_dialog_deletar) + "\n" + controleDoencas.getDoenca();

        DialogInterface.OnClickListener listener =
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        switch (which) {
                            case DialogInterface.BUTTON_POSITIVE:
                                ControleDoencasDatabase controleDoencasDatabase =
                                        ControleDoencasDatabase.getDatabase(ListagemActivity.this);

                                controleDoencasDatabase.controleDoencasDao().delete(controleDoencas);

                                listaDoencas.remove(posicaoSelecionada);
                                listViewDoencas.setAdapter(doencaAdapter);
                                recreate();
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                break;
                        }
                    }
                };
        UtilsGui utilsGui = new UtilsGui();
        utilsGui.confirmaAcao(this, msg, listener);
    }

    private void alterarDoenca() {
        controleDoencas = listaDoencas.get(posicaoSelecionada);
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
        if (myBoolean) myswitch.setChecked(true);

        if (myBoolean) {
            setTheme(R.style.darkTema);
        } else {
            setTheme(R.style.lightTema);
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

            popularLista();
            verificaTema();
        }
    }

    private void verificaTema() {
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.darkTema);
        } else {
            setTheme(R.style.lightTema);
        }
    }
}