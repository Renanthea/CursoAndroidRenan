package br.com.renan.projeto10_fragments01;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import br.com.renan.aula10_fragments01.R;

/**
 * Renan de Arimathea
 * https://github.com/Renanthea
 */

public class F01 extends Fragment {

    private Context context;
    private Button f01_btn_mostrar;
    private CheckBox f01_cb_info;

    //1. Definição da interface
    public interface IF01 {
       public void acionarMudanca(String texto);
    }

    //2. Definição de um atributo do tipo da interface
    private IF01 delegate;

    //3. Definição do métod que será executado como evento
    public void setOnAcionarMudancaListener(IF01 delegate){
        this.delegate = delegate;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.f01, container, false);
        context = getActivity();
        f01_btn_mostrar = (Button) view.findViewById(R.id.f01_btn_mostrar);
        f01_cb_info = (CheckBox) view.findViewById(R.id.f01_cb_info);

        f01_btn_mostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,
                        "Evento do botao no Fragment 01",
                        Toast.LENGTH_SHORT).show();

                //acesso ao metodo da interface IF01
                if(f01_cb_info.isChecked()){
                    if(delegate != null){
                        delegate.acionarMudanca("Acesso vindo de F01");
                    }
                }
            }
        });

        f01_cb_info.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(context,
                        isChecked ? "Marcado" : "Desmarcado",
                        Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}
