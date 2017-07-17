package br.com.renan.aula15_http_get;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


//@SuppressWarnings("deprecated")
public class MainActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_main);

        new DownloadJsonAsyncTask().execute("http://www.emiliocelso.com.br/api/android");
    }


    class DownloadJsonAsyncTask extends AsyncTask<String,Void,List<Contato>> {

        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = ProgressDialog.show(MainActivity.this,"Aguarde","Executando download dos Contatos");
        }

        @Override
        protected List<Contato> doInBackground(String... params)
        {
            String urlString = params[0];

            try{
                URL url = new URL(urlString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                InputStream stream = connection.getInputStream();
                String json = getStringFromInputStream(stream);
                List<Contato> contatos = listarContatos(json);

                stream.close();
                return contatos;

            }catch (Exception e)
            {
                e.printStackTrace();
                return new ArrayList<>();
            }

        }

        private String getStringFromInputStream(InputStream in){
            BufferedReader reader = null;
            StringBuilder sb = null;

            String linha;
            try{
                sb = new StringBuilder();
                reader = new BufferedReader(new InputStreamReader(in));
                while ((linha = reader.readLine()) != null)
                {
                    sb.append(linha);
                }

            }catch (Exception e)
            {
                e.printStackTrace();
            }
            return sb.toString();
        }

        private  List<Contato> listarContatos(String jsonString){
            List<Contato> contatos = new ArrayList<>();

            try{
                JSONArray contatosJson = new JSONArray(jsonString);
                JSONObject contato;

                for(int i = 0; i < contatosJson.length();i++)
                {

                    contato = new JSONObject(contatosJson.getString(i));

                    Contato objContato = new Contato();
                    objContato.setId(contato.getInt("Id"));
                    objContato.setNome(contato.getString("Nome"));
                    objContato.setTelefone(contato.getString("Telefone"));
                    objContato.setDataNascimento(contato.getString("DataNascimento"));

                    contatos.add(objContato);
                }

            }catch (Exception e)
            {
                e.printStackTrace();
            }
            return contatos;

        }
        @Override
        protected void onPostExecute(List<Contato> contatos) {
            super.onPostExecute(contatos);
            dialog.dismiss();

            if(contatos.size() > 0){
                ArrayAdapter<Contato> adapter = new ContatoListAdapter(MainActivity.this,
                                                                        contatos);
                setListAdapter(adapter);
            }else{
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this)
                                                    .setTitle("Erro")
                                                    .setMessage("Não foi possivel acessar os dados")
                                                    .setPositiveButton("OK",null);
                builder.create().show();
            }
        }
    }
}
