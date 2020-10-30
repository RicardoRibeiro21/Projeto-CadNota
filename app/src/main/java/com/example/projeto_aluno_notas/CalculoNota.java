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

import java.util.ArrayList;

public class CalculoNota extends AppCompatActivity {

    Intent novoTexto;
    Bundle parametros;
    ArrayList<String> disciplinas = null;
    Spinner spin;
    ArrayAdapter adapter = null;
    ArrayList<Disciplina> arrayDisciplinas = new ArrayList<Disciplina>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculo_nota);

        // Recuperando os parâmetros passados
         novoTexto = getIntent();
        parametros = novoTexto.getExtras();
        disciplinas = parametros.getStringArrayList("disciplinasArrayListString");

        spin = (Spinner) findViewById(R.id.spinnerCalculoNota);
        // Declarando o contexto
         adapter = new ArrayAdapter(this, android.R.layout.simple_gallery_item, disciplinas);
        spin.setAdapter(adapter);
        adapter.setDropDownViewResource(R.layout.spinner_item);
    }

    public void btnCalcularNotas(View v) {
        if(Consistir()) {
            EditText edtNota1 = (EditText) findViewById(R.id.edtNota1);
            EditText edtNota2 = (EditText) findViewById(R.id.edtNotaA2);
            TextView txtResultado = (TextView) findViewById(R.id.txtResultado);
            Spinner spin = (Spinner) findViewById(R.id.spinnerCalculoNota);
            float nota = Float.parseFloat(edtNota1.getText().toString()) + Float.parseFloat(edtNota2.getText().toString());

            addToArrayDisciplina(spin.getSelectedItem().toString(), nota);
            if(Resultado(nota)){
                Toast.makeText(getApplicationContext(), "Parabéns você foi aprovado!!!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Infelizmente você precisa fazer AF", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "Informe as duas notas", Toast.LENGTH_SHORT).show();
        }
    }

    public void addToArrayDisciplina(String disciplina, float nota){
        Disciplina objDisciplina = new Disciplina();
        objDisciplina.setDisciplina(disciplina);
        objDisciplina.setNota(nota);

        if(arrayDisciplinas.size() == 0){
            arrayDisciplinas.add(objDisciplina);
        } else {
            if(arrayDisciplinas.contains(disciplina)) {
                arrayDisciplinas.remove(disciplina);
            } else {
                for(int i = 0; i < arrayDisciplinas.size(); i++) {
                    if(arrayDisciplinas.get(i).disciplina != disciplina){
                        arrayDisciplinas.add(objDisciplina);
                    } else {
                        arrayDisciplinas.remove(i);
                        arrayDisciplinas.add(objDisciplina);
                    }
                }
            }
        }
    }

    public Boolean Resultado (float nota) {
        if (nota < 6) {
            return false;
        }
        return true;
    }

    public boolean Consistir () {
        EditText edtNota1 = (EditText) findViewById(R.id.edtNota1);
        EditText edtNota2 = (EditText) findViewById(R.id.edtNotaA2);
        if(edtNota1.getText().toString().isEmpty() || edtNota2.getText().toString().isEmpty()) return false;
        return true;
    }

    public void btnCalcularAf(View v){
        ArrayList<Disciplina> novoArray = arrayDisciplinas;

        // Passando os Parâmetros
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("lista", novoArray);

        Intent calculoNotaActivity = new Intent(CalculoNota.this, ResultadosFinais.class);
        calculoNotaActivity.putExtras(bundle);
        startActivity(calculoNotaActivity);
    }
}