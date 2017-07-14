package br.com.renan.aula05_intent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SegundoActivity extends AppCompatActivity {

    private TextView tv_descricao;
    private TextView tv_ch;
    private Button btn_ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.segundo);

        iniciarComponentes();
        executarAcao();
    }

    private void iniciarComponentes() {
        tv_descricao = (TextView) findViewById(R.id.tv_descricao);
        tv_ch = (TextView) findViewById(R.id.tv_ch);
        btn_ok = (Button) findViewById(R.id.btn_ok);

        Intent intent = getIntent();
        String curso = intent.getStringExtra(Constantes.DESCRICAO);
        String ch = intent.getStringExtra(Constantes.CARGAHORARIA);

        tv_descricao.setText("Curso: " + curso);
        tv_ch.setText("Carga horária: " + ch);
    }

    private void executarAcao() {
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
