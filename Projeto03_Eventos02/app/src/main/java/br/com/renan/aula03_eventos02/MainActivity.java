package br.com.renan.aula03_eventos02;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Context context;

    private CheckBox cb_android;
    private CheckBox cb_ios;

    private RadioGroup rg_sexo;
    private RadioButton rb_masculino;
    private RadioButton rb_feminino;

    private Button btn_verificar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal);

        iniciarComponentes();
        executarAcao();
    }

    private void iniciarComponentes() {
        context = getBaseContext();

        cb_android = (CheckBox) findViewById(R.id.cb_android);
        cb_ios = (CheckBox) findViewById(R.id.cb_ios);

        rg_sexo = (RadioGroup) findViewById(R.id.rg_sexo);

        rb_masculino = (RadioButton) findViewById(R.id.rb_masculino);
        rb_feminino = (RadioButton) findViewById(R.id.rb_feminino);

        btn_verificar = (Button) findViewById(R.id.btn_verificar);
    }

    private void executarAcao() {
        cb_android.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String mensagem = isChecked ? "Curso Android Ativado" : "Curso Android Desativado";
                Toast.makeText(context, mensagem, Toast.LENGTH_SHORT).show();
            }
        });

        cb_ios.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String mensagem = isChecked ? "Curso iOS Ativado" : "Curso iOS Desativado";
                Toast.makeText(context, mensagem, Toast.LENGTH_SHORT).show();
            }
        });

        rg_sexo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                String mensagem;
                switch(checkedId){
                    case R.id.rb_masculino: mensagem = "Selecionado: Masculino";break;
                    case R.id.rb_feminino: mensagem = "Selecionado: Feminino"; break;
                    default: mensagem = "Sexo não informado";
                }
                Toast.makeText(context, mensagem, Toast.LENGTH_SHORT).show();
            }
        });

        btn_verificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mensagem = "Curso(s): ";
                String curso = "Nenhum selecionado";

                if(cb_android.isChecked() && !cb_ios.isChecked()){
                    curso = "Android ";
                } else if(!cb_android.isChecked() && cb_ios.isChecked()){
                    curso = "iOS ";
                } else if (cb_android.isChecked() && cb_ios.isChecked()){
                    curso = "Android, iOS";
                }

                Toast.makeText(context, mensagem + curso,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
