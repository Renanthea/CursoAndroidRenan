package br.com.renan.aula04_orientacaotela;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private Context context;

    private EditText et_curso;
    private EditText et_ch;
    private Button btn_incluir;
    private ListView lv_cursos;

    ArrayList<String> cursos;
    ArrayAdapter<String> adapter;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putStringArrayList("minhalista", cursos);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal);

        iniciarComponentes(savedInstanceState);
        executarAcao();
    }

    private void iniciarComponentes(Bundle savedInstanceState) {
        context = getBaseContext();

        et_curso = (EditText) findViewById(R.id.et_curso);
        et_ch = (EditText) findViewById(R.id.et_ch);
        btn_incluir = (Button) findViewById(R.id.btn_incluir);
        lv_cursos = (ListView) findViewById(R.id.lv_cursos);

        //cursos = new ArrayList<>();
        if(savedInstanceState != null){
            cursos = savedInstanceState.getStringArrayList("minhalista");
        } else {
            cursos = new ArrayList<>();
        }

        adapter = new ArrayAdapter<String>(
                context,
                android.R.layout.simple_list_item_1,
                cursos);

        lv_cursos.setAdapter(adapter);
    }

    private void executarAcao() {
        btn_incluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cursos.add(et_curso.getText().toString() + " - " + et_ch.getText().toString());
                adapter.notifyDataSetChanged();

                et_curso.setText("");
                et_ch.setText("");
                et_curso.requestFocus();
            }
        });
    }
}
