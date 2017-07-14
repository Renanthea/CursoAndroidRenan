package br.com.renan.aula12_sharedpreferences;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Context context;
    private TextView tv_bemvindo;
    private Button btn_novo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal);

        iniciarComponentes();
    }

    private void iniciarComponentes() {
        context = getBaseContext();
        tv_bemvindo = (TextView) findViewById(R.id.tv_bemvindo);
        btn_novo = (Button) findViewById(R.id.btn_novo);
    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences preferences = getSharedPreferences(Constantes.APP_PREFS, MODE_PRIVATE);
        String username = preferences.getString(Constantes.USERNAME_KEY, null);

        if(username == null){
            tv_bemvindo.setText("Incluir seu nome aqui");
            btn_novo.setText("Adicionar");
        } else {
            tv_bemvindo.setText("Bem vindo, " + username);
            btn_novo.setText("Alterar Nome");
        }

        btn_novo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CadastroActivity.class);
                startActivity(intent);
            }
        });
    }
}
