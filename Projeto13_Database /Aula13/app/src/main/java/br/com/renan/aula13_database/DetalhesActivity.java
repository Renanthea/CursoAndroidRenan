package br.com.renan.aula13_database;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
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

    private TextView tv_usuario;

    private EditText et_codigo;
    private EditText et_nome;
    private EditText et_telefone;
    private EditText et_idade;

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

        if (idContatoAux != -1) {
            Contato cAux = contatoDao.obterContatoPeloId(idContatoAux);
            et_codigo.setText(String.valueOf(cAux.getIdContato()));
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
        btn_salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validacao()) {
                    inserirAtualizacao();
                } else {
                    alertaMensagem("Validação", mensagemErro);
                }
            }
        });
        btn_excluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               excluir();
            }
        });
    }

    private boolean validacao() {
        String nome = et_nome.getText().toString().trim();
        String telefone = et_telefone.getText().toString().trim();
        int idade = converterIdade(et_idade.getText().toString().trim());

        if (nome.length() == 0) {
            mensagemErro = "ERRO: Nome obrigatório";
            return false;
        }

        if (telefone.length() == 0) {
            mensagemErro = "ERRO: Telefone obrigatório";
            return false;
        }

        if (idade == -1) {
            mensagemErro = "ERRO: Idade inválida";
            return false;
        }

        return true;
    }

    private int converterIdade(String idade) {
        try {
            int idadeInt = Integer.parseInt(idade);
            if (idadeInt < 0) {
                throw new Exception();
            }
            return idadeInt;
        } catch (Exception e) {
            return -1;
        }
    }

    private void inserirAtualizacao() {
        Contato cAux = new Contato();

        try {
            cAux.setNome(et_nome.getText().toString());
            cAux.setTelefone(et_telefone.getText().toString());
            cAux.setIdade(Integer.parseInt(et_idade.getText().toString()));


            if (idContatoAux != -1) {
                cAux.setIdContato(idContatoAux);
                contatoDao.atualizarContato(cAux);
            } else {
                idContatoAux = contatoDao.proximoId();
                cAux.setIdContato(idContatoAux);
                contatoDao.inserirContato(cAux);

                et_codigo.setText(String.valueOf(idContatoAux));
                btn_excluir.setVisibility(View.VISIBLE);
            }
            alertaMensagem("Contato", "Contato gravado com sucesso");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void excluir(){
        try{
            if(idContatoAux != -1){
                contatoDao.removerContato(idContatoAux);
                alertaMensagem("Contato", "Contato excluído com sucesso");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private void alertaMensagem(String titulo, String texto) {
        AlertDialog.Builder alerta = new AlertDialog.Builder(DetalhesActivity.this);
        alerta.setTitle(titulo);
        alerta.setMessage(texto);
        alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                voltarPrincipal();
            }
        });
        alerta.show();
    }

    private void voltarPrincipal() {
        Intent intent = new Intent(context, MainActivity.class);
        startActivity(intent);
        finish();
    }

}
