package br.com.renan.aula02_eventos01;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Context context;

    private EditText et_nome;
    private Button btn_ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal);

        context = getBaseContext();

        et_nome = (EditText) findViewById(R.id.et_nome);
        btn_ok = (Button) findViewById(R.id.btn_ok);

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //obtendo o valor da caixa de textos
                String nome = et_nome.getText().toString();
                if(nome.equals("")){
                    Toast.makeText(context,
                            R.string.msg_erro,
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context,
                            "Seja bem vindo, " + nome,
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
