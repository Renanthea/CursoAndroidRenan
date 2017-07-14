package br.com.renan.aula07_simpleadapter;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Context context;
    private ListView lv_nomes;
    private ArrayList<HMAuxiliar> nomes;
    private SimpleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal);

        iniciarComponentes();
        executarAcao();
    }

    private void iniciarComponentes() {
        context = getBaseContext();
        lv_nomes = (ListView) findViewById(R.id.lv_nomes);

        gerarDados(25);

        String[] origem  = {HMAuxiliar.NOME, HMAuxiliar.TELEFONE};
        int[] destino = {R.id.celula_tv_nome, R.id.celula_tv_telefone};

        adapter = new SimpleAdapter(context,
                nomes,
                R.layout.celula,
                origem,
                destino);
        lv_nomes.setAdapter(adapter);
    }

    private void gerarDados(int quantidade) {
        nomes = new ArrayList<>();
        for (int i = 1; i <= quantidade; i++){
            HMAuxiliar item = new HMAuxiliar();
            item.put(HMAuxiliar.ID, String.valueOf(i));
            item.put(HMAuxiliar.NOME, "Nome: " + i);
            item.put(HMAuxiliar.TELEFONE, "Telefone: " + i);
            nomes.add(item);
        }
    }

    private void executarAcao() {
        lv_nomes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HMAuxiliar item = (HMAuxiliar)parent.getItemAtPosition(position);
                Toast.makeText(context, item.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
