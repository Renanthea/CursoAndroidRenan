package br.com.renan.projeto10_fragments01;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import br.com.renan.aula10_fragments01.R;

/**
 * Renan de Arimathea
 * https://github.com/Renanthea
 */

public class F02 extends Fragment {

    private ProgressBar f02_progressbar;
    private TextView f02_tv_valor;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.f02, container, false);

        f02_progressbar = (ProgressBar) view.findViewById(R.id.f02_progressbar);
        f02_progressbar.setMax(200);
        f02_progressbar.setProgress(20);

        f02_tv_valor = (TextView) view.findViewById(R.id.f02_tv_valor);
        return view;
    }

    public void alterarTexto(String texto){
        f02_tv_valor.setText(texto);
    }
}
