package br.com.renan.aula11_fragments02;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity
        implements ContatoListFragment.ISelecionaContato{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_contatos);
    }

    @Override
    public void selecionarContato(Contato contato) {
        Intent intent = new Intent(getBaseContext(),ContatoDetalheActivity.class);
        intent.putExtra(Constantes.CONTATO, contato);
        startActivity(intent);
    }
}
