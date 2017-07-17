package br.com.renan.aula14_http_post;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private Context context;

    private EditText et_nome;
    private EditText et_telefone;
    private EditText et_data;

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

        et_nome = (EditText) findViewById(R.id.et_nome);
        et_telefone = (EditText) findViewById(R.id.et_telefone);
        et_data = (EditText) findViewById(R.id.et_data);

        btn_enviar = (Button) findViewById(R.id.btn_enviar);
    }

    private void executarAcao() {
        btn_enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Contato contato = new Contato();
                contato.setNome(et_nome.getText().toString());
                contato.setTelefone(et_telefone.getText().toString());
                contato.setDataNascimento(et_data.getText().toString());

                try{
                    new IncludeJsonAsyncTask().execute(contato);
                } catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    class IncludeJsonAsyncTask extends AsyncTask<Contato, Void, Integer> {

        ProgressDialog dialog;
        String mensagem;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = ProgressDialog.show(MainActivity.this, "Aguarde", "Enviando dados");
        }

        @Override
        protected Integer doInBackground(Contato... params) {
            Contato contato = params[0];
            Integer codigo = -1;

            try{
                codigo = incluirContato(contato);
                mensagem = "Contato incluído com sucesso";
            } catch(Exception e){
                mensagem = "ERRO: " + e.getMessage();
                e.printStackTrace();
            }
            return codigo;
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            dialog.dismiss();
            AlertDialog.Builder builder = new AlertDialog.Builder(
                    MainActivity.this)
                        .setTitle("Mensagem")
                        .setMessage(mensagem)
                        .setPositiveButton("OK", null);
            builder.create().show();

        }

        //metodo que abre e mantem a conexao com o serviço
        private HttpURLConnection abrirConexao(String url, String metodo){
            HttpURLConnection conexao = null;
            try{
                URL urlCon = new URL(url);
                conexao = (HttpURLConnection) urlCon.openConnection();
                conexao.setReadTimeout(15000);
                conexao.setConnectTimeout(15000);
                conexao.setRequestMethod(metodo);
                conexao.setDoInput(true);
                conexao.setDoOutput(true);

                conexao.addRequestProperty("Content-Type", "application/json");

            } catch(Exception e){
                e.printStackTrace();
            }
            return conexao;
        }

        //metodo que converte um fluxo de bytes da resposta em String
        private String streamToString(InputStream is){
            try{
                byte[] bytes = new byte[1024];
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                int bytesLidos;

                while((bytesLidos = is.read(bytes)) > 0){
                    stream.write(bytes, 0, bytesLidos);
                }
                return new String(stream.toByteArray());
            } catch(Exception e){
                e.printStackTrace();
                return null;
            }
        }
        //metodo que gera o fluxo de bytes a partir do objeto
        private byte[] getContatoJson(Contato contato){
            try{
                JSONObject json = new JSONObject();
                json.put("Nome", contato.getNome());
                json.put("Telefone", contato.getTelefone());
                json.put("DataNascimento", contato.getDataNascimento());
                String jsonString = json.toString();
                return jsonString.getBytes();

            } catch (Exception e){
                e.printStackTrace();
                return null;
            }
        }

        private Integer incluirContato(Contato contato) {
            int codigo = -1;

            try {
                String url = "http://www.emiliocelso.com.br/api/android";

                byte[] bytes = getContatoJson(contato);
                HttpURLConnection conexao = abrirConexao(url, "POST");
                conexao.setRequestProperty("Content-Length", String.valueOf(bytes.length));

                OutputStream os = conexao.getOutputStream();
                os.write(bytes);
                os.flush();
                os.close();

                int resposta = conexao.getResponseCode();
                if(resposta == HttpURLConnection.HTTP_OK){
                    InputStream is = conexao.getInputStream();
                    String s = streamToString(is);
                    is.close();

                    JSONObject json = new JSONObject(s);
                    contato.setId(json.getInt("Id"));
                    codigo = contato.getId();
                }
            } catch(Exception e){
                e.printStackTrace();
            }
            return codigo;
        }
    }


}

