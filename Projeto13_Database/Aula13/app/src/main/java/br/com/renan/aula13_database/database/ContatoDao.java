package br.com.renan.aula13_database.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import br.com.renan.aula13_database.model.Contato;

/**
 * Renan de Arimathea
 * https://github.com/Renanthea
 */

public class ContatoDao extends Dao {

    public ContatoDao(Context context) {
        super(context);
    }

    //metodo para inserir um novo contato
    public void inserirContato(Contato contato){
        try {
            abrirConexao();
            ContentValues cv = new ContentValues();
            cv.put(Constantes.IDCONTATO, contato.getIdcontato());
            cv.put(Constantes.NOME, contato.getNome());
            cv.put(Constantes.TELEFONE, contato.getTelefone());
            cv.put(Constantes.IDADE, contato.getIdade());

            db.insert(Constantes.TABELA, null, cv);

        } catch (Exception e){
            throw e;
        } finally {
            fecharConexao();
        }
    }

    //metodo para atualizar o contato
    public void atualizarContato(Contato contato){
        try {
            abrirConexao();
            String filtro = " idcontato = ? ";
            String[] argumentos = { String.valueOf(contato.getIdcontato())};

            ContentValues cv = new ContentValues();
            //cv.put(Constantes.IDCONTATO, contato.getIdcontato());
            cv.put(Constantes.NOME, contato.getNome());
            cv.put(Constantes.TELEFONE, contato.getTelefone());
            cv.put(Constantes.IDADE, contato.getIdade());

            db.update(Constantes.TABELA, cv, filtro, argumentos);
        } catch (Exception e){
            throw e;
        } finally {
            fecharConexao();
        }
    }

    //metodo para remover um contato pelo id
    public void removerContato(long idcontato){
        try {
            abrirConexao();
            String filtro = " idcontato = ? ";
            String[] argumentos = { String.valueOf(idcontato)};
            db.delete(Constantes.TABELA, filtro, argumentos);
        } catch (Exception e){
            throw e;
        } finally {
            fecharConexao();
        }
    }

    //metodo para obter um contato pelo id
    public Contato obterContatoPeloId(long idcontato){
        Contato contato = null;
        Cursor cursor = null;
        try {
            abrirConexao();
            String[] argumentos = { String.valueOf(idcontato)};
            String sql = " select * from " + Constantes.TABELA + " where idcontato = ? ";

            cursor = db.rawQuery(sql, argumentos);
            if(cursor.moveToNext()){
                contato = new Contato();

                contato.setIdcontato(cursor.getLong(cursor.getColumnIndex(Constantes.IDCONTATO)));
                contato.setNome(cursor.getString(cursor.getColumnIndex(Constantes.NOME)));
                contato.setTelefone(cursor.getString(cursor.getColumnIndex(Constantes.TELEFONE)));
                contato.setIdade(cursor.getInt(cursor.getColumnIndex(Constantes.IDADE)));
            }
        } catch (Exception e){
            throw e;
        } finally {
            fecharConexao();
        }
        return contato;
    }

    //metodo para listar todos os contatos
    public List<Contato> listarContatos(){

        List<Contato> contatos = new ArrayList<>();
        Cursor cursor = null;

        try {
            abrirConexao();
            String sql = " select * from " + Constantes.TABELA;
            cursor = db.rawQuery(sql, null);

            while (cursor.moveToNext()){
                Contato contato = new Contato();

                contato.setIdcontato(cursor.getLong(cursor.getColumnIndex(Constantes.IDCONTATO)));
                contato.setNome(cursor.getString(cursor.getColumnIndex(Constantes.NOME)));
                contato.setTelefone(cursor.getString(cursor.getColumnIndex(Constantes.TELEFONE)));
                contato.setIdade(cursor.getInt(cursor.getColumnIndex(Constantes.IDADE)));

                contatos.add(contato);
            }
        } catch (Exception e){
            throw e;
        } finally {
            fecharConexao();
        }
        return contatos;
    }

    //metodo para obter o proximo id valido para o contato
    public long proximoId(){
        long proId = 1;
        Cursor cursor = null;
        try{
            String sql = " select max(idcontato) + 1 as id from " + Constantes.TABELA;
            cursor = db.rawQuery(sql, null);
            if(cursor.moveToNext()){
                proId = cursor.getLong(cursor.getColumnIndex("id"));
                /*if(proId == 0){
                    proId = 1;
                }*/
            }
        } catch (Exception e){
            throw e;
        } finally {
            fecharConexao();
        }
        return proId;
    }
}
