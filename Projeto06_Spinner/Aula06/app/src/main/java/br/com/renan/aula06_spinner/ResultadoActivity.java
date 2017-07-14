package br.com.renan.aula06_spinner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;

public class ResultadoActivity extends AppCompatActivity {

    private TextView tv_nome;
    private TextView tv_produto;
    private TextView tv_produto_hm;
    private Button btn_ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultado);

        iniciarComponentes();
        executarAcao();
    }

    private void iniciarComponentes() {
        tv_nome = (TextView) findViewById(R.id.tv_nome);
        tv_produto = (TextView) findViewById(R.id.tv_produto);
        tv_produto_hm = (TextView) findViewById(R.id.tv_produto_hm);
        btn_ok = (Button) findViewById(R.id.btn_ok);

        Intent intent = getIntent();

        String nome = intent.getStringExtra("nome");
        tv_nome.setText(nome);

        Produto produto = (Produto) intent.getSerializableExtra("produto");
        tv_produto.setText(produto.toString());

        HashMap<String,String> auxiliar = (HashMap<String, String>)
                intent.getSerializableExtra("produto_hm");
        tv_produto_hm.setText(auxiliar.toString());
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
