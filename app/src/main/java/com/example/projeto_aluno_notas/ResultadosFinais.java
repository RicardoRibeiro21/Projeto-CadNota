package com.example.projeto_aluno_notas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ResultadosFinais extends AppCompatActivity {

    Spinner spin;
    ArrayAdapter<String> adapter;
    ArrayList<Disciplina> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados_finais);

        Bundle bundle =  getIntent().getExtras();
        if(bundle != null) {
            ArrayList<Disciplina> arrayBundle  = bundle.getParcelableArrayList("lista");
            arrayList = arrayBundle;
            Log.e("SavedInstance", arrayBundle.toString());

            ArrayList disciplinasAF = trataDados(arrayBundle);
            spin = (Spinner) findViewById(R.id.spinnerDisciplinasAF);
            // Declarando o contexto
            adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, disciplinasAF);
            spin.setAdapter(adapter);
            spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                    String  mselection= spin.getSelectedItem().toString();
                    spin = (Spinner) findViewById(R.id.spinnerDisciplinasAF);
                    TextView txtNotaSomada = (TextView) findViewById(R.id.txtNotaSomada);
                    int posicao = spin.getSelectedItemPosition();
                    txtNotaSomada.setText(arrayList.get(posicao).nota.toString());
                }
                @Override
                public void onNothingSelected(AdapterView<?> arg0) {
                    // TODO Auto-generated method stub
                    //
                }
            });
        }
    }

    // Métodos responsável por verificar quais disciplinas necessitam da nota AF
    public ArrayList trataDados(ArrayList<Disciplina> arrayDisciplinas) {
        ArrayList  retArrayDisciplinas = new ArrayList();
        for(int i = 0; i < arrayDisciplinas.size(); i++){
            if(arrayDisciplinas.get(i).nota < 6){
                retArrayDisciplinas.add(arrayDisciplinas.get(i).disciplina);
            } else arrayDisciplinas.remove(i);
        }
        return retArrayDisciplinas;
    }

    public boolean Consistir (){
        EditText edtTxt = (EditText) findViewById(R.id.edtNotaResultado);
        if(edtTxt.getText().toString().trim().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Insira uma nota válida", Toast.LENGTH_SHORT).show();
            return false;
        } return true;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void btnCalcularNotaFinal(View v){
        if(Consistir()) {
            EditText edtTxt = (EditText) findViewById(R.id.edtNotaResultado);
            float notaFinal = Float.parseFloat(edtTxt.getText().toString());
            if(notaFinal >= 6 ) {
                Toast.makeText(getApplicationContext(), "Parabéns! Você foi Aprovado!" , Toast.LENGTH_SHORT).show();
            } else if (notaFinal < 6){
                Toast.makeText(getApplicationContext(), "Infelizmente você foi reprovado!" , Toast.LENGTH_SHORT).show();
            }
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        getIntent().removeExtra("lista");
    }

}