package br.com.renan.aula13_database.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class Dao {

    private Context context;

    protected SQLiteDatabase db;

    public Dao(Context context) {
        this.context = context;
    }

    //metodo para abrir a conexão com o banco de dados
    public void abrirConexao() {
        SQLiteHelper helper = new SQLiteHelper(
                context,
                Constantes.DATABASE,
                null,
                Constantes.VERSAO);

        db = helper.getWritableDatabase();
    }

    public void fecharConexao (){
        if(db != null)
            db.close();
    }
}
