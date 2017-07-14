package br.com.renan.aula05_intent;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Context context;

    private EditText et_curso;
    private EditText et_ch;
    private Button btn_ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal);

        iniciarComponentes();
        executarAcao();
    }

    private void iniciarComponentes() {
        context = getBaseContext();

        et_curso = (EditText) findViewById(R.id.et_curso);
        et_ch = (EditText) findViewById(R.id.et_ch);

        btn_ok = (Button) findViewById(R.id.btn_ok);
    }

    private void executarAcao() {
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                    - receber os valores das caixas de texto
                    - criar um objeto da classe Intent
                    - armazenar as informações lidas no Intent
                    - chamar a nova (segunda) activity através do Intent
                 */
                Intent intent = new Intent(context, SegundoActivity.class);

                intent.putExtra(Constantes.DESCRICAO, et_curso.getText().toString());
                intent.putExtra(Constantes.CARGAHORARIA, et_ch.getText().toString());
                startActivity(intent);
            }
        });
    }
}
