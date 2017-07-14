package br.com.renan.aula13_database;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import br.com.renan.aula13_database.database.Constantes;
import br.com.renan.aula13_database.database.ContatoDao;
import br.com.renan.aula13_database.model.Contato;

public class DetalhesActivity extends AppCompatActivity {

    private Context context;
    private ContatoDao contatoDao;

    private EditText et_codigo;
    private EditText et_nome;
    private EditText et_telefone;
    private EditText et_idade;

    private TextView tv_usuario;

    private Button btn_excluir;
    private Button btn_salvar;

    private String mensagemErro;
    private long idContatoAux;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalhes);

        iniciarComponentes();
        executarAcao();
    }

    private void iniciarComponentes() {
        context = getBaseContext();
        contatoDao = new ContatoDao(context);

        idContatoAux = getIntent().getLongExtra(Constantes.PARAMETRO_ID, 0);

        et_codigo = (EditText) findViewById(R.id.detalhe_et_codigo);
        et_nome = (EditText) findViewById(R.id.detalhe_et_nome);
        et_telefone = (EditText) findViewById(R.id.detalhe_et_telefone);
        et_idade = (EditText) findViewById(R.id.detalhe_et_idade);

        tv_usuario = (TextView) findViewById(R.id.detalhe_tv_usuario);

        btn_excluir = (Button) findViewById(R.id.detalhe_btn_excluir);
        btn_salvar = (Button) findViewById(R.id.detalhe_btn_salvar);

        if(idContatoAux != -1){
            Contato cAux = contatoDao.obterContatoPeloId(idContatoAux);
            et_codigo.setText(String.valueOf(cAux.getIdcontato()));
            et_nome.setText(cAux.getNome());
            et_telefone.setText(cAux.getTelefone());
            et_idade.setText(String.valueOf(cAux.getIdade()));
        } else {
            btn_excluir.setVisibility(View.GONE);
        }

        SharedPreferences preferences = getSharedPreferences(Constantes.APP_PREFS, MODE_PRIVATE);
        String usuario = preferences.getString(Constantes.USERNAME_KEY, null);
        tv_usuario.setText("Usuario: " + usuario);
    }

    private void executarAcao() {

    }
}
