package br.com.renan.aula08_baseadapter;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Context context;
    private List<Convenio> convenios;
    private ConveniosAdapter adapter;
    private ListView lv_convenios;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        iniciarComponentes();
    }

    private void iniciarComponentes() {
        context = getBaseContext();
        lv_convenios = new ListView(context);

        setContentView(lv_convenios);

        convenios = new ArrayList<>();

        convenios.add(new Convenio(10, "Amil Saude", new Date(), 0));
        convenios.add(new Convenio(11, "Bio Vida", new Date(), 1));
        convenios.add(new Convenio(12, "Bradesco Dental", new Date(), 2));
        convenios.add(new Convenio(13, "Gama Saude", new Date(), 3));
        convenios.add(new Convenio(14, "Unimed Seguros", new Date(), 4));

        adapter = new ConveniosAdapter(context, convenios);

        lv_convenios.setAdapter(adapter);
    }
}
