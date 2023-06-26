package com.controledohistricodedoenasemumalocalidade;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText editTextDoenca;
    private RadioGroup radioGroupFormadeContagio;
    private CheckBox checkBoxPodeLevarAObto;
    private Spinner spinnerFormaDeContagio;
    private String msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextDoenca = findViewById(R.id.editTextTextPersonNameDoenca);
        radioGroupFormadeContagio = findViewById(R.id.radioGroupFormaDeContagio);
        checkBoxPodeLevarAObto = findViewById(R.id.checkBoxPodeLevarAObto);
        spinnerFormaDeContagio = findViewById(R.id.spinnerFormaDeContagio);
        editTextDoenca = findViewById(R.id.editTextTextPersonNameDoenca);
        popularSpiner();
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

    public void cadastro(View view) {

        this.msg = getString(R.string.doencaInformada) + editTextDoenca.getText().toString() + "\n";

        switch (radioGroupFormadeContagio.getCheckedRadioButtonId()) {
            case R.id.radioButtonContagioSimples:
                this.msg += getString(R.string.radiobuttonContagioSimples).toString() + "\n";
                break;
            case R.id.radioButtonContagioComplexo:
                this.msg += getString(R.string.radiobuttonContagioComplexo).toString() + "\n";
                break;
        }

        if (checkBoxPodeLevarAObto.isChecked()) {
            msg += getString(R.string.doencaPodeLevarAObito) + "\n";
        } else {
            msg += getString(R.string.doencaNaoLevaAObto) + "\n";
        }

        msg += (String) spinnerFormaDeContagio.getSelectedItem();

        if (editTextDoenca.getText().toString().equals("")) {
            Toast.makeText(this, (R.string.doencaDeveSerPreenchida), Toast.LENGTH_SHORT).show();
            editTextDoenca.requestFocus();

        } else if ((radioGroupFormadeContagio.getCheckedRadioButtonId() != R.id.radioButtonContagioSimples) &&
                (radioGroupFormadeContagio.getCheckedRadioButtonId() != R.id.radioButtonContagioComplexo)) {
            Toast.makeText(this, (R.string.TipoDeContágioDeveSerFornecido), Toast.LENGTH_SHORT).show();
            radioGroupFormadeContagio.requestFocus();
        } else {
            Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
        }
    }

    public void limparCampos(View view) {
        this.msg = "";
        editTextDoenca.setText(msg);
        radioGroupFormadeContagio.clearCheck();
        if(checkBoxPodeLevarAObto.isChecked()) checkBoxPodeLevarAObto.setChecked(false);
        Toast.makeText(this, R.string.todosControlesForamReInicializados, Toast.LENGTH_LONG).show();
    }
}