package br.com.renan.aula11_fragments02;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Renan de Arimathea
 * https://github.com/Renanthea
 */

public class ContatoListFragment extends ListFragment {

    private List<Contato> contatos;
    private ArrayAdapter<Contato> adapter;

    //metodo do cliclo de vida que será executado quando o activity
    //tiver sido criado

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        contatos = listarContatos();
        adapter = new ArrayAdapter<Contato>(getActivity(),
                android.R.layout.simple_list_item_1,
                contatos);
        setListAdapter(adapter);
    }

    private List<Contato> listarContatos() {
        List<Contato> lista = new ArrayList<>();
        lista.add(new Contato(10, "Jose","3254-2200", 23));
        lista.add(new Contato(20, "Faria","2091-0099", 19));
        lista.add(new Contato(30, "Gabriel","84587-2154", 37));
        lista.add(new Contato(40, "Adelaide","2584-8788", 65));

        return lista;
    }

    public interface ISelecionaContato {
        void selecionarContato(Contato contato);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Activity activity = getActivity();
        if(activity instanceof ISelecionaContato){
            Contato contato = (Contato) l.getItemAtPosition(position);
            ISelecionaContato delegate = (ISelecionaContato) activity;
            delegate.selecionarContato(contato);
        }
    }
}
