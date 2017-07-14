package br.com.renan.projeto10_fragments01;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import br.com.renan.aula10_fragments01.R;

public class MainActivity extends AppCompatActivity {

    private Context context;
    private F01 f01;
    private F02 f02;
    private FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal);

        iniciarComponentes();
        executarAcao();
    }

    private void iniciarComponentes() {
        context = getBaseContext();
        fm = getSupportFragmentManager();

        f01 = (F01) fm.findFragmentById(R.id.principal_f01);
        f02 = (F02) fm.findFragmentById(R.id.principal_f02);
    }

    private void executarAcao() {
        f01.setOnAcionarMudancaListener(new F01.IF01() {
            @Override
            public void acionarMudanca(String texto) {
                f02.alterarTexto(texto);
            }
        });
    }
}










