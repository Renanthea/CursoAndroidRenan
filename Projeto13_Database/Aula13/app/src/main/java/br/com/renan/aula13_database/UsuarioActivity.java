package br.com.renan.aula13_database;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.renan.aula13_database.database.Constantes;

import static br.com.renan.aula13_database.R.id.usuario_btn_salvar;

public class UsuarioActivity extends AppCompatActivity {
    private Context context;
    private EditText et_nome;
    private EditText et_senha;

    private Button btn_salvar;
    private Button btn_cancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.usuario);

        iniciarComponentes();
        executarAcao();
    }

    private void iniciarComponentes() {
        context = getBaseContext();
        et_nome = (EditText) findViewById(R.id.usuario_et_nome);
        et_senha = (EditText) findViewById(R.id.usuario_et_senha);
        btn_salvar = (Button) findViewById(usuario_btn_salvar);
        btn_cancelar = (Button) findViewById(R.id.usuario_btn_cancelar);
    }

    private void executarAcao() {
        btn_salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences =
                        getSharedPreferences(Constantes.APP_PREFS, MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();

                editor.putString(Constantes.USERNAME_KEY, et_nome.getText().toString());
                editor.putString(Constantes.PASSWORD_KEY, et_senha.getText().toString());

                editor.commit();
                finish();
            }
        });

        btn_cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
