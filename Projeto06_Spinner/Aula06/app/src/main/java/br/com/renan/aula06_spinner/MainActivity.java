package br.com.renan.aula06_spinner;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Context context;

    private Spinner sp_nomes;
    private ArrayList<String> nomes;
    private ArrayAdapter<String> adapter_nomes;

    private Spinner sp_produtos;
    private ArrayList<Produto> produtos;
    private ArrayAdapter<Produto> adapter_produtos;

    private Spinner sp_produtos_hm;
    private ArrayList<HMAuxiliar> produtos_hm;
    private ArrayAdapter<HMAuxiliar> adapter_produtos_hm;


    private Button btn_enviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal);

        iniciarComponentes();
        executarAcao();
    }

    private void iniciarComponentes() {
        context = getBaseContext();

        //Para a lista de nomes
        sp_nomes = (Spinner) findViewById(R.id.sp_nomes);
        gerarNomes(25); //este método instancia a lista "nomes" (ArrayList)
        adapter_nomes = new ArrayAdapter<String>(context,
                android.R.layout.simple_list_item_1,
                nomes);
        adapter_nomes.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        sp_nomes.setAdapter(adapter_nomes);



        //Para a lista de produtos
        sp_produtos = (Spinner) findViewById(R.id.sp_produtos);
        gerarProdutos(25); //instancia a lista "produtos"
        adapter_produtos = new ArrayAdapter<Produto>(context,
                android.R.layout.simple_list_item_1,
                produtos);
        adapter_produtos.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        sp_produtos.setAdapter(adapter_produtos);


        //Para a lista de produtos - HM
        sp_produtos_hm = (Spinner) findViewById(R.id.sp_produtos_hm);
        gerarProdutosHM(25);
        adapter_produtos_hm = new ArrayAdapter<HMAuxiliar>(context,
                android.R.layout.simple_list_item_1,
                produtos_hm);
        adapter_produtos_hm.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        sp_produtos_hm.setAdapter(adapter_produtos_hm);

        btn_enviar = (Button) findViewById(R.id.btn_enviar);
    }

    private void gerarProdutosHM(int quantidade) {
        produtos_hm = new ArrayList<>();
        HMAuxiliar auxiliar = new HMAuxiliar();
        auxiliar.put(HMAuxiliar.ID, String.valueOf(-1));
        auxiliar.put(HMAuxiliar.DESCRICAO, "<Selecione o produto>");
        produtos_hm.add(auxiliar);

        for (int i = 1; i <= quantidade; i++){
            HMAuxiliar item = new HMAuxiliar();
            item.put(HMAuxiliar.ID, String.valueOf(i));
            item.put(HMAuxiliar.DESCRICAO, "Produto HM: " + i);
            produtos_hm.add(item);
        }

    }

    private void gerarProdutos(int quantidade) {
        produtos = new ArrayList<>();
        for (int i = 1; i <= quantidade; i++){
            Produto produto = new Produto();
            produto.setIdproduto(i);
            produto.setDescricao("[Produto: " + i + "]");
            produto.setValor(0.5 * i);
            produto.setBarcode("Barcode - " + i);

            produtos.add(produto);
        }
    }

    private void gerarNomes(int quantidade) {
        nomes = new ArrayList<>();
        for (int i = 1; i <= quantidade; i++){
            nomes.add("Nome: " + i);
        }
    }

    private void executarAcao() {
        btn_enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nome = (String) sp_nomes.getSelectedItem();
                Produto produto = (Produto) sp_produtos.getSelectedItem();
                HMAuxiliar produto_hm = (HMAuxiliar) sp_produtos_hm.getSelectedItem();

                Intent intent = new Intent(context, ResultadoActivity.class);
                intent.putExtra("nome", nome);
                intent.putExtra("produto", produto);
                intent.putExtra("produto_hm", produto_hm);
                startActivity(intent);
            }
        });
    }
}
