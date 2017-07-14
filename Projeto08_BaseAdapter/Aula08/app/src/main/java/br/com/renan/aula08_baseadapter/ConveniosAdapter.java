package br.com.renan.aula08_baseadapter;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

/**
 * Renan de Arimathea
 * https://github.com/Renanthea
 */

public class ConveniosAdapter extends BaseAdapter {

    private Context context;
    private List<Convenio> convenios;

    public ConveniosAdapter(Context context, List<Convenio> convenios) {
        this.context = context;
        this.convenios = convenios;
    }

    @Override
    public int getCount() {
        return convenios.size();
    }

    @Override
    public Object getItem(int position) {
        return convenios.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Convenio convenio = (Convenio) getItem(position);

        View view = LayoutInflater.from(context).inflate(R.layout.celula, null);

        ImageView iv_logo = (ImageView) view.findViewById(R.id.celula_iv_logo);
        TextView tv_descricao = (TextView) view.findViewById(R.id.celula_tv_descricao);
        TextView tv_data = (TextView) view.findViewById(R.id.celula_tv_data);

        Resources resources = context.getResources();
        TypedArray logos = resources.obtainTypedArray(R.array.logos);

        iv_logo.setImageDrawable(logos.getDrawable(convenio.getLogo()));
        tv_descricao.setText(convenio.getDescricao());


        DateFormat fmt = new SimpleDateFormat("EEEE - dd/MM/yyyy", new Locale("pt","BR"));

        tv_data.setText("Data Fund.: " + fmt.format(convenio.getFundacao()));

        return view;
    }
}
