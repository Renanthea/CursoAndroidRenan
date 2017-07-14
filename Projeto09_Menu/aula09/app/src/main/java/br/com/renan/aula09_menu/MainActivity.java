package br.com.renan.aula09_menu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tv_texto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal);

        tv_texto = (TextView) findViewById(R.id.tv_texto);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.mnu_reset:
                tv_texto.setText("Menu RESET ativado");
                break;
            case R.id.mnu_cadastro:
                tv_texto.setText("Menu CADASTRO ativado");
                break;
            case R.id.mnu_sobre:
                tv_texto.setText("Menu SOBRE O APP ativado");
                break;
            case R.id.mnu_sair:
                finish();
                break;
            default:
                tv_texto.setText("Item a ser implementado");
        }

        return true;
    }
}
