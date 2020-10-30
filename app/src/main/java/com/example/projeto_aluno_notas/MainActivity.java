package com.example.projeto_aluno_notas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Spinner sistemas;
    EditText mEditTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Declarando os valores do comboBox
        sistemas = (Spinner) findViewById(R.id.spinnerCalculoNota);

        // Declarando o contexto
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.cursos, android.R.layout.simple_gallery_item);
        sistemas.setAdapter(adapter);
        adapter.setDropDownViewResource(R.layout.spinner_item);
    }

    public void btnAvancarOnClick(View v) {
        Intent novaActivity = new Intent(this, DisciplinasActivity.class);

        // Passando os Parâmetros
        Bundle bundle = new Bundle();
        // Rgm
        mEditTxt = (EditText)findViewById(R.id.rgm);
        bundle.putString("rgm", mEditTxt.getText().toString());

        // Nome Completo
        mEditTxt = (EditText)findViewById(R.id.nomeCompleto);
        bundle.putString("nomeCompleto", mEditTxt.getText().toString());

        // Turma
        mEditTxt = (EditText)findViewById(R.id.turma);
        bundle.putString("turma", mEditTxt.getText().toString());

        // Cursos
        sistemas = (Spinner) findViewById(R.id.spinnerCalculoNota);

        if(Consistir()) {
            bundle.putString("curso", sistemas.getSelectedItem().toString());
            novaActivity.putExtras(bundle);
            startActivity(novaActivity);
        } else {
            Toast.makeText(getApplicationContext(), "Por favor preencha todos os campos!", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean Consistir(){
        // Verificando RGM
        mEditTxt = (EditText)findViewById(R.id.rgm);
        if(mEditTxt.getText().toString().isEmpty()) return false;
        // Nome Completo
        mEditTxt = (EditText)findViewById(R.id.nomeCompleto);
        if(mEditTxt.getText().toString().isEmpty()) return false;
        // Turma
        mEditTxt = (EditText)findViewById(R.id.turma);
        if(mEditTxt.getText().toString().isEmpty()) return false;
        return true;
    }
    public void btnDesistirOnClick(View v) {
        finish();
    }
}