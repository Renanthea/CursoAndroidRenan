package br.com.renan.aula15_http_get;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Renan de Arimathea
 * https://github.com/Renanthea
 */

public class ContatoListAdapter extends ArrayAdapter<Contato> {
    public ContatoListAdapter(@NonNull Context context,List<Contato> contatos) {
        super(context,0,contatos);
    }


    static class ViewLocal{
        TextView tv_id;
        TextView tv_nome;
        TextView tv_telefone;
        TextView tv_dataNascimento;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Contato contato = getItem(position);
        ViewLocal local;

        //if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_contato,null);

            local = new ViewLocal();
            local.tv_id = (TextView) convertView.findViewById(R.id.tv_id);
            local.tv_nome = (TextView) convertView.findViewById(R.id.tv_nome);
            local.tv_telefone = (TextView) convertView.findViewById(R.id.tv_telefone);
            local.tv_dataNascimento = (TextView) convertView.findViewById(R.id.tv_data);

            local.tv_id.setText(String.valueOf(contato.getId()));
            local.tv_nome.setText(contato.getNome());
            local.tv_telefone.setText(contato.getTelefone());
            local.tv_dataNascimento.setText(contato.getDataNascimento());

            convertView.setTag(local);
        //}
        //else{
            local= (ViewLocal) convertView.getTag();
        //}
        return convertView;
    }
}
