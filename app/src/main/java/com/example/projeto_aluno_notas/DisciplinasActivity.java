package com.example.projeto_aluno_notas;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.le.AdvertiseData;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class DisciplinasActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disciplinas);

        ListView list = (ListView) findViewById(R.id.listDisciplinas);

        list.smoothScrollToPosition(list.getCount());
        //list.isNestedScrollingEnabled();
        list.setTranscriptMode(0);
        list.setStackFromBottom(false);

        // Recuperando os parâmetros passados
        Intent novoTexto = getIntent();
        Bundle parametros = novoTexto.getExtras();
        String sNome = parametros.getString("nomeCompleto");
    }

    public void btnGravarDisciplina(View v) {
        EditText edtTxt = (EditText) findViewById(R.id.disciplina);
        adicionarItemLista(edtTxt.getText().toString());
        edtTxt.setText(null);
    }

    public void adicionarItemLista(String disciplina) {
        if(!disciplina.isEmpty()) {
            ListView list = (ListView) findViewById(R.id.listDisciplinas);
            List<String> disciplinas = new ArrayList<String>();
            for (int i = 0; i < list.getCount();i++) {
                String a = list.getItemAtPosition(i).toString();
                disciplinas.add(a);
            }
            disciplinas.add(disciplina);
            ArrayAdapter adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, disciplinas);
            list.setAdapter(adaptador);
        } else {
            Toast.makeText(getApplicationContext(), "Insira uma disciplina válida!", Toast.LENGTH_SHORT).show();
        }

    }
    public ArrayList<String> ConvertToArrayString(ListView l)
    {
        ArrayList<String> list = new ArrayList<String>();

        for (int i = 0; i < l.getCount(); i++) {
            list.add(l.getItemAtPosition(i).toString());
        }
        return list;
    }

    public void btnAvancarDisciplinaOnClick (View v) {
        if(Consistir()) {
            Intent calculoNotaActivity = new Intent(this, CalculoNota.class);

            // Passando os Parâmetros
            Bundle bundle = new Bundle();
            ListView list = (ListView) findViewById(R.id.listDisciplinas);
            ArrayList<String> arrayList = ConvertToArrayString(list);

            bundle.putStringArrayList("disciplinasArrayListString", arrayList);
            calculoNotaActivity.putExtras(bundle);
            startActivity(calculoNotaActivity);
        } else {
            Toast.makeText(getApplicationContext(), "Por favor, cadastre às disciplinas!", Toast.LENGTH_SHORT).show();
        }

    }

    public void btnVoltarOnClick (View v) {
       finish();
    }

    public Boolean Consistir () {
        ListView list = (ListView) findViewById(R.id.listDisciplinas);
        if(list.getCount() <= 0) return false;
        return true;
    }
}