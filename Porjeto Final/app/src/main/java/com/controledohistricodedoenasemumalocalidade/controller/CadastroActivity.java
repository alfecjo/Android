package com.controledohistricodedoenasemumalocalidade.controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.controledohistricodedoenasemumalocalidade.R;
import com.controledohistricodedoenasemumalocalidade.apoioSharedPreferences.ApoioSharedPreferences;
import com.controledohistricodedoenasemumalocalidade.model.ControleDoencas;
import com.controledohistricodedoenasemumalocalidade.persiste.ControleDoencasDatabase;
import com.controledohistricodedoenasemumalocalidade.utils.UtilsGui;

import java.util.Arrays;
import java.util.List;

public class CadastroActivity extends AppCompatActivity {
    public static final String MODO = "MODO";
    public static final String DOENCA = "DOENCA";
    public static String CONTAGIO = "Simples";
    public static String TIPOPODELEVARAOBTO = "Sim";
    public static String TIPOPRIMEIROSSOCORROS = "até 12 H!";
    public static final String MEDICAMENTOS = "MEDICAMENTOS";
    public static final String LOCALIDADE = "LOCALIDADE";
    public static final int NOVO = 1;
    public static final int ALTERAR = 2;
    private EditText editTextDoenca;
    private RadioGroup radioGroupFormadeContagio;
    private CheckBox checkBoxPodeLevarAObto;
    private Spinner spinnerFormaDeContagio;
    private EditText editTextMedicamentos;
    private EditText editTextLocalidade;
    private String msg;
    private static int id;
    private ControleDoencas controleDoencas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        boolean myBoolean = verificaTema();

        setContentView(R.layout.activity_cadastro);

        Switch myswitch = findViewById(R.id.switchTrocaTema);

        trocaTema(myBoolean, myswitch);

        escutaSwitch(myswitch);

        habilitaActionBar();

        carregaInstancias();

        carregaSpinner();

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null) {
            controleDoencas = new ControleDoencas("", "", "",
                    "", "", "");
            if (bundle.getInt(MODO, NOVO) == NOVO) {
                setTitle(getString(R.string.novo));
            } else{
                setTitle(getString(R.string.alterar));
                carregaDadosPreenchidos(bundle);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.doencas_cadastro, menu);
        return true;
    }

    public static void novaDoenca(AppCompatActivity activity) {
        Intent intent = new Intent(activity, CadastroActivity.class);
        intent.putExtra(MODO, NOVO);

        activity.startActivityForResult(intent, NOVO);
    }

    public static void alterarDoenca(AppCompatActivity activity, ControleDoencas doenca) {
        Intent intent = new Intent(activity, CadastroActivity.class);
        intent.putExtra(MODO, ALTERAR);
        id = doenca.getId();

        intent.putExtra(DOENCA, doenca.getDoenca());
        intent.putExtra(CONTAGIO, doenca.getContagio());
        intent.putExtra(TIPOPODELEVARAOBTO, doenca.getTipoPodeLevarAObto());
        intent.putExtra(TIPOPRIMEIROSSOCORROS, doenca.getPrimeirosSocorros());
        intent.putExtra(MEDICAMENTOS, doenca.getMedicamento());
        intent.putExtra(LOCALIDADE, doenca.getLocalidade());

        activity.startActivityForResult(intent, ALTERAR);
    }

    private void carregaDadosPreenchidos(Bundle bundle) {
        editTextDoenca.setText(bundle.getString(DOENCA));

        RadioButton simples = findViewById(R.id.radioButtonContagioSimples);
        RadioButton complexo = findViewById(R.id.radioButtonContagioComplexo);
        if (getIntent().getStringExtra(CONTAGIO).equals(getString(R.string.radiobuttonContagioSimples))) {
            simples.setChecked(true);
        } else if (getIntent().getStringExtra(CONTAGIO).equals(getString(R.string.radiobuttonContagioComplexo))) {
            complexo.setChecked(true);
        }

        if (getIntent().getStringExtra(TIPOPODELEVARAOBTO).equals(getString(R.string.sim))) {
            checkBoxPodeLevarAObto.setChecked(true);
        } else if (getIntent().getStringExtra(TIPOPODELEVARAOBTO).equals(getString(R.string.nao))) {
            checkBoxPodeLevarAObto.setChecked(false);
        }

        if (getIntent().getStringExtra(TIPOPRIMEIROSSOCORROS).equals(getString(R.string.dozeHoras))) {
            spinnerFormaDeContagio.setSelection(0);
        } else if (getIntent().getStringExtra(TIPOPRIMEIROSSOCORROS).equals(getString(R.string.vinteEQuatroHoras))) {
            spinnerFormaDeContagio.setSelection(1);
        } else if (getIntent().getStringExtra(TIPOPRIMEIROSSOCORROS).equals(getString(R.string.acimaDeVinteQuatroHoras))) {
            spinnerFormaDeContagio.setSelection(2);
        }

        editTextMedicamentos.setText(bundle.getString(MEDICAMENTOS));
        editTextLocalidade.setText(bundle.getString(LOCALIDADE));
    }

    private void carregaSpinner() {
        Spinner meuSpinner = findViewById(R.id.spinnerFormaDeContagio);
        List<String> items = Arrays.asList(getString(R.string.dozeHoras),
                getString(R.string.vinteEQuatroHoras),
                getString(R.string.acimaDeVinteQuatroHoras));

        CustomSpinnerAdapter adapter = new CustomSpinnerAdapter(this, items);
        meuSpinner.setAdapter(adapter);
    }

    private void carregaInstancias() {
        editTextDoenca = findViewById(R.id.editTextTextPersonNameDoenca);
        radioGroupFormadeContagio = findViewById(R.id.radioGroupFormaDeContagio);
        checkBoxPodeLevarAObto = findViewById(R.id.checkBoxPodeLevarAObto);
        spinnerFormaDeContagio = findViewById(R.id.spinnerFormaDeContagio);
        editTextMedicamentos = findViewById(R.id.editTextTextPersonNameMedicamento);
        editTextLocalidade = findViewById(R.id.editTextTextPersonNameLocalidade);
    }

    private void habilitaActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void escutaSwitch(Switch myswitch) {
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
            escreveSharedPreferences(isChecked);

            recreate();
        });
    }

    private void trocaTema(boolean myBoolean, Switch myswitch) {
        if (myBoolean) myswitch.setChecked(true);
    }

    private boolean verificaTema() {
        ApoioSharedPreferences apoioSharedPreferences = new ApoioSharedPreferences();
        boolean myBoolean = apoioSharedPreferences.lerSharedPreferences(this);

        if (myBoolean) {
            setTheme(R.style.darkTema);
        } else {
            setTheme(R.style.lightTema);
        }
        return myBoolean;
    }

    private void escreveSharedPreferences(boolean isChecked) {
        SharedPreferences sharedPreferences = getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("myBoolean", isChecked);
        editor.commit();
    }

    private void cancelar() {
        setResult(Activity.RESULT_CANCELED);
        finish();
    }

    @Override
    public void onBackPressed() {
        cancelar();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.menuItemSalvar:
                gravar();
                return true;

            case R.id.menuItemLimpar:
                limpar();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void limpar() {
        this.msg = "";
        editTextDoenca.setText(msg);
        radioGroupFormadeContagio.clearCheck();
        if (checkBoxPodeLevarAObto.isChecked()) checkBoxPodeLevarAObto.setChecked(false);
        editTextMedicamentos.setText(msg);
        editTextLocalidade.setText(msg);
        editTextDoenca.requestFocus();
        Toast.makeText(this, R.string.todosControlesForamReInicializados, Toast.LENGTH_LONG).show();
    }

    private void gravar() {
        String verificaValorVazio;
        Intent intent = getIntent();

        if (!(getTitle() == (getString(R.string.novo)))) {
            ControleDoencasDatabase controleDoencasDatabase = ControleDoencasDatabase.getDatabase(this);
            controleDoencas = controleDoencasDatabase.controleDoencasDao().queryForId(id);
        }

        verificaValorVazio = UtilsGui.validaCampoTexto(this, editTextDoenca,
                (R.string.doencaDeveSerPreenchida));
        if (verificaValorVazio == null || verificaValorVazio == "") {
            return;
        } else {
            controleDoencas.setDoenca(verificaValorVazio);
            intent.putExtra(DOENCA, verificaValorVazio);
        }

        this.msg = "";
        switch (radioGroupFormadeContagio.getCheckedRadioButtonId()) {
            case R.id.radioButtonContagioSimples:
                this.msg = getString(R.string.radiobuttonContagioSimples);
                break;
            case R.id.radioButtonContagioComplexo:
                this.msg = getString(R.string.radiobuttonContagioComplexo);
                break;
        }

        verificaValorVazio = UtilsGui.validaCampoString(this, msg, (R.string.TipoDeContágioDeveSerFornecido));
        if (verificaValorVazio == null || verificaValorVazio == "") {
            checkBoxPodeLevarAObto.requestFocus();
            return;
        } else {
            controleDoencas.setContagio(verificaValorVazio);
            intent.putExtra(CONTAGIO, verificaValorVazio);
        }

        if (checkBoxPodeLevarAObto.isChecked()) {
            this.msg = getString(R.string.sim);
        } else {
            this.msg = getString(R.string.nao);
        }
        controleDoencas.setTipoPodeLevarAObto(this.msg);
        intent.putExtra(TIPOPODELEVARAOBTO, this.msg);

        this.msg = (String) spinnerFormaDeContagio.getSelectedItem();
        controleDoencas.setPrimeirosSocorros(this.msg);
        intent.putExtra(TIPOPRIMEIROSSOCORROS, this.msg);

        this.msg = editTextMedicamentos.getText().toString();
        verificaValorVazio = UtilsGui.validaCampoTexto(this, editTextMedicamentos, (R.string.medicamentosDeveSerPreenchida));
        if (verificaValorVazio == null || verificaValorVazio == "") return;
        controleDoencas.setMedicamento(this.msg);
        intent.putExtra(MEDICAMENTOS, this.msg);

        this.msg = editTextLocalidade.getText().toString();
        verificaValorVazio = UtilsGui.validaCampoTexto(this, editTextLocalidade, (R.string.localidadeDeveSerPreenchida));
        if (verificaValorVazio == null || verificaValorVazio == "") return;
        controleDoencas.setLocalidade(this.msg);
        intent.putExtra(LOCALIDADE, this.msg);

        ControleDoencasDatabase controleDoencasDatabase = ControleDoencasDatabase.getDatabase(this);
        if (!(getTitle() == (getString(R.string.novo))))  {
            controleDoencasDatabase.controleDoencasDao().update(controleDoencas);
        } else {
            controleDoencasDatabase.controleDoencasDao().insert(controleDoencas);
        }
        setResult(Activity.RESULT_OK, intent);
        finish();
    }
}