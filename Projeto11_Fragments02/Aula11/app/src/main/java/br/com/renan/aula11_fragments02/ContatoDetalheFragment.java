package br.com.renan.aula11_fragments02;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Renan de Arimathea
 * https://github.com/Renanthea
 */

public class ContatoDetalheFragment extends Fragment {

    private TextView tv_codigo;
    private TextView tv_nome;
    private TextView tv_telefone;
    private TextView tv_idade;

    private Contato contato;

    //método que funciona como um construtor, mas que pode ser
    //carregado em diversos pontos no ciclo de vida do fragmento
    public static ContatoDetalheFragment novoContato(Contato contato){
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constantes.CONTATO, contato);
        ContatoDetalheFragment fragment = new ContatoDetalheFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contato = (Contato) getArguments().getSerializable(Constantes.CONTATO);
        setHasOptionsMenu(true); //estabelece o vinculo entre a informação
                                 //(objeto Contato) com o fragmento
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contato,container,false);
        tv_codigo = (TextView) view.findViewById(R.id.tv_codigo);
        tv_nome = (TextView) view.findViewById(R.id.tv_nome);
        tv_telefone = (TextView) view.findViewById(R.id.tv_telefone);
        tv_idade = (TextView) view.findViewById(R.id.tv_idade);

        if(contato != null){
            tv_codigo.setText(String.valueOf(contato.getIdcontato()));
            tv_nome.setText(contato.getNome());
            tv_telefone.setText(contato.getTelefone());
            tv_idade.setText(String.valueOf(contato.getIdade()));
        }

        return view;
    }
}
