package br.com.renan.aula13_database;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import br.com.renan.aula13_database.database.Constantes;
import br.com.renan.aula13_database.database.ContatoDao;
import br.com.renan.aula13_database.model.Contato;

public class MainActivity extends AppCompatActivity {

    private Context context;
    private ListView lv_contatos;
    private Button btn_criarUsuario;

    private ContatoDao contatoDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal);

        iniciarComponentes();
        executarAcao();
    }

    private void iniciarComponentes() {
        context = getBaseContext();

        contatoDao = new ContatoDao(context);

        lv_contatos = (ListView) findViewById(R.id.lv_contatos);

        lv_contatos.setAdapter(new ArrayAdapter<>(
                context,
                android.R.layout.simple_list_item_1,
                contatoDao.listarContatos()));

        btn_criarUsuario = (Button) findViewById(R.id.btn_criarusuario);
    }

    private void executarAcao() {

        lv_contatos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Contato contato = (Contato) parent.getItemAtPosition(position);
                chamarDetalhes(contato.getIdContato());
            }
        });

        btn_criarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UsuarioActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.action_incluir){
            chamarDetalhes(-1L);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void chamarDetalhes(long idContato) {
        SharedPreferences preferences = getSharedPreferences(Constantes.APP_PREFS, MODE_PRIVATE);
        String usuario = preferences.getString(Constantes.USERNAME_KEY, null);
        String senha = preferences.getString(Constantes.PASSWORD_KEY, null);

        if (usuario == null || usuario.trim().length() == 0 ||
                senha == null || senha.trim().length() == 0) {
            Intent intent = new Intent(context, UsuarioActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(context, DetalhesActivity.class);
            intent.putExtra(Constantes.PARAMETRO_ID, idContato);
            startActivity(intent);
            finish();
        }

    }


}
