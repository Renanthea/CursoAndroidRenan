package br.com.renan.aula12_sharedpreferences;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CadastroActivity extends AppCompatActivity {

    private EditText et_username;
    private Button btn_incluir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro);

        iniciarCompontentes();
    }

    private void iniciarCompontentes() {
        et_username = (EditText) findViewById(R.id.et_username);
        btn_incluir = (Button) findViewById(R.id.btn_incluir);
    }

    @Override
    protected void onResume() {
        super.onResume();
        btn_incluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Lê para armazenar
                SharedPreferences preferences = getSharedPreferences(Constantes.APP_PREFS, MODE_PRIVATE);

                //informação a ser armazenada
                String username = et_username.getText().toString();

                //para armazenar, criamos um objeto do tipo Editor (classe interna de sharedpreferences)
                SharedPreferences.Editor editor = preferences.edit();
                //putString (chave, qual o valor para ser armazenado)
                editor.putString(Constantes.USERNAME_KEY,username);
                //efeitva a gravacao
                editor.commit();
                //fecha o activity
                finish();

                Toast.makeText(getBaseContext(),"Usuário atualizado", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
