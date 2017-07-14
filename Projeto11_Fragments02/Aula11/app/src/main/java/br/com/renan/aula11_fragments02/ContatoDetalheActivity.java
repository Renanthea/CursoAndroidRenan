package br.com.renan.aula11_fragments02;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ContatoDetalheActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contato);

        //para incluir o fragmento apenas uma vez
        if(savedInstanceState == null){
            Intent intent = getIntent();
            Contato contato = (Contato) intent.getSerializableExtra(Constantes.CONTATO);
            ContatoDetalheFragment fragment = ContatoDetalheFragment.novoContato(contato);

            //carregar o fragmento dinamicamente
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.layout_detalhe, fragment, "tagDetalhe");
            transaction.commit();
        }
    }
}
