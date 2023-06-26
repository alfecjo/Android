package com.controledohistricodedoenasemumalocalidade.controller;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.controledohistricodedoenasemumalocalidade.R;
import com.controledohistricodedoenasemumalocalidade.model.ControleDoencas;

import java.util.ArrayList;

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
    public static final int VAZIO = -1;
    private EditText editTextDoenca;
    private RadioGroup radioGroupFormadeContagio;
    private CheckBox checkBoxPodeLevarAObto;
    private Spinner spinnerFormaDeContagio;
    private EditText editTextMedicamentos;
    private EditText editTextLocalidade;
    private String msg;

    public static void novaDoenca(AppCompatActivity activity) {
        Intent intent = new Intent(activity, CadastroActivity.class);
        intent.putExtra(MODO, NOVO);
        activity.startActivityForResult(intent, NOVO);
    }

    public static void alterarDoenca(AppCompatActivity activity, ControleDoencas doenca) {

        Intent intent = new Intent(activity, CadastroActivity.class);

        intent.putExtra(MODO, ALTERAR);
        intent.putExtra(DOENCA, doenca.getDoenca());
        intent.putExtra(CONTAGIO, doenca.getContagio());
        intent.putExtra(TIPOPODELEVARAOBTO, doenca.getTipoPodeLevarAObto());
        intent.putExtra(TIPOPRIMEIROSSOCORROS, doenca.getPrimeirosSocorros());
        intent.putExtra(MEDICAMENTOS, doenca.getMedicamento());
        intent.putExtra(LOCALIDADE, doenca.getLocalidade());

        activity.startActivityForResult(intent, ALTERAR);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        editTextDoenca = findViewById(R.id.editTextTextPersonNameDoenca);
        radioGroupFormadeContagio = findViewById(R.id.radioGroupFormaDeContagio);
        checkBoxPodeLevarAObto = findViewById(R.id.checkBoxPodeLevarAObto);
        spinnerFormaDeContagio = findViewById(R.id.spinnerFormaDeContagio);
        editTextMedicamentos = findViewById(R.id.editTextTextPersonNameMedicamento);
        editTextLocalidade = findViewById(R.id.editTextTextPersonNameLocalidade);
        popularSpiner();

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null) {
            if (bundle.getInt(MODO, NOVO) == NOVO) {
                setTitle("Novo");
            } else {
                setTitle("Alterar");
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
        }
    }

    private void cancelar(){
        setResult(Activity.RESULT_CANCELED);
        finish();
    }

    @Override
    public void onBackPressed() {
        cancelar();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.doencas_cadastro, menu);
        return true;
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
        Intent intent = getIntent();

        this.msg = editTextDoenca.getText().toString();
        intent.putExtra(DOENCA, this.msg);

        this.msg = "";

        switch (radioGroupFormadeContagio.getCheckedRadioButtonId()) {
            case R.id.radioButtonContagioSimples:
                this.msg = getString(R.string.radiobuttonContagioSimples);
                break;
            case R.id.radioButtonContagioComplexo:
                this.msg = getString(R.string.radiobuttonContagioComplexo);
                break;
        }
        intent.putExtra(CONTAGIO, this.msg);

        if (checkBoxPodeLevarAObto.isChecked()) {
            this.msg = getString(R.string.sim);
        } else {
            this.msg = getString(R.string.nao);
        }
        intent.putExtra(TIPOPODELEVARAOBTO, this.msg);

        this.msg = (String) spinnerFormaDeContagio.getSelectedItem();
        intent.putExtra(TIPOPRIMEIROSSOCORROS, this.msg);

        this.msg = editTextMedicamentos.getText().toString();
        intent.putExtra(MEDICAMENTOS, this.msg);

        this.msg = editTextLocalidade.getText().toString();
        intent.putExtra(LOCALIDADE, this.msg);


        if (editTextDoenca.getText().toString().equals("")) {
            Toast.makeText(this, (R.string.doencaDeveSerPreenchida), Toast.LENGTH_SHORT).show();
            editTextDoenca.requestFocus();

        } else if ((radioGroupFormadeContagio.getCheckedRadioButtonId() != R.id.radioButtonContagioSimples) &&
                (radioGroupFormadeContagio.getCheckedRadioButtonId() != R.id.radioButtonContagioComplexo)) {
            Toast.makeText(this, (R.string.TipoDeContágioDeveSerFornecido), Toast.LENGTH_SHORT).show();
            radioGroupFormadeContagio.requestFocus();
        } else if (editTextMedicamentos.getText().toString().equals("")) {
            Toast.makeText(this, (R.string.medicamentosDeveSerPreenchida), Toast.LENGTH_SHORT).show();
            editTextMedicamentos.requestFocus();
        } else if (editTextLocalidade.getText().toString().equals("")) {
            Toast.makeText(this, (R.string.localidadeDeveSerPreenchida), Toast.LENGTH_SHORT).show();
            editTextLocalidade.requestFocus();
        } else {
            msg = getString(R.string.salvoComSucesso);
            Toast.makeText(this, this.msg, Toast.LENGTH_LONG).show();
        }

        if ((!(editTextDoenca.getText().toString().equals("")) && (!(radioGroupFormadeContagio.getCheckedRadioButtonId() == VAZIO))) &&
                (!(editTextMedicamentos.getText().toString().equals("")) && (!(editTextLocalidade.getText().toString().equals(""))))) {
            setResult(Activity.RESULT_OK, intent);
        } else {
            setResult(Activity.RESULT_CANCELED);
        }
        finish();
    }

    private void popularSpiner() {
        ArrayList<String> lista = new ArrayList<>();

        lista.add(getString(R.string.dozeHoras));
        lista.add(getString(R.string.vinteEQuatroHoras));
        lista.add(getString(R.string.acimaDeVinteQuatroHoras));

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this,
                        android.R.layout.simple_list_item_1,
                        lista);

        spinnerFormaDeContagio.setAdapter(adapter);
    }
}