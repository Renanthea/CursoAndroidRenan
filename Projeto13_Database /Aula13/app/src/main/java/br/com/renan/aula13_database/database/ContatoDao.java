package br.com.renan.aula13_database.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import br.com.renan.aula13_database.model.Contato;

public class ContatoDao extends Dao {
    public ContatoDao(Context context) {
        super(context);
    }

    //metodo para inserir um novo contato
    public void inserirContato(Contato contato) {
        try {
            abrirConexao();
            ContentValues cv = new ContentValues();

            cv.put(Constantes.IDCONTATO, contato.getIdContato());
            cv.put(Constantes.NOME, contato.getNome());
            cv.put(Constantes.TELEFONE, contato.getTelefone());
            cv.put(Constantes.IDADE, contato.getIdade());

            db.insert(Constantes.TABELA, null, cv);

        } catch (Exception e) {
            throw e;
        } finally {
            fecharConexao();
        }
    }

    //método para atualizar o contato
    public void atualizarContato(Contato contato) {
        try {
            abrirConexao();

            String filtro = Constantes.IDCONTATO + " = ? ";
            String[] argumentos = {String.valueOf(contato.getIdContato())};

            ContentValues cv = new ContentValues();

            //cv.put(Constantes.IDCONTATO, contato.getIdContato());
            cv.put(Constantes.NOME, contato.getNome());
            cv.put(Constantes.TELEFONE, contato.getTelefone());
            cv.put(Constantes.IDADE, contato.getIdade());

            db.update(Constantes.TABELA, cv, filtro, argumentos);

        } catch (Exception e) {
            throw e;
        } finally {
            fecharConexao();
        }
    }

    //metodo para remover um contato pelo id
    public void removerContato(long id) {
        try {
            abrirConexao();

            String filtro = Constantes.IDCONTATO + " = ? ";
            String[] argumentos = {String.valueOf(id)};

            db.delete(Constantes.TABELA, filtro, argumentos);

        } catch (Exception e) {
            throw e;
        } finally {
            fecharConexao();
        }
    }

    //metodo para obter um contato pelo id
    public Contato obterContatoPeloId(long idContato) {
        Contato contato = null;
        Cursor cursor = null;

        try {
            abrirConexao();
            String[] argumentos = {String.valueOf(idContato)};
            String sql = "SELECT * FROM " + Constantes.TABELA + " WHERE idcontato = ?";

            cursor = db.rawQuery(sql, argumentos);
            if (cursor.moveToNext()) {
                contato = new Contato();

                contato.setIdContato(cursor.getLong(cursor.getColumnIndex(Constantes.IDCONTATO)));
                contato.setNome(cursor.getString(cursor.getColumnIndex(Constantes.NOME)));
                contato.setTelefone(cursor.getString(cursor.getColumnIndex(Constantes.TELEFONE)));
                contato.setIdade(cursor.getInt(cursor.getColumnIndex(Constantes.IDADE)));
            }

        } catch (Exception e) {
            throw e;
        } finally {
            fecharConexao();
        }

        return contato;
    }

    //método para listar todos os contatos
    public List<Contato> listarContatos() {
        List<Contato> contatos = new ArrayList<>();
        Cursor cursor = null;

        try {
            abrirConexao();
            String sql = "SELECT * FROM " + Constantes.TABELA;

            cursor = db.rawQuery(sql, null);
            while (cursor.moveToNext()) {
                Contato contato = new Contato();

                contato.setIdContato(cursor.getLong(cursor.getColumnIndex(Constantes.IDCONTATO)));
                contato.setNome(cursor.getString(cursor.getColumnIndex(Constantes.NOME)));
                contato.setTelefone(cursor.getString(cursor.getColumnIndex(Constantes.TELEFONE)));
                contato.setIdade(cursor.getInt(cursor.getColumnIndex(Constantes.IDADE)));

                contatos.add(contato);
            }

        } catch (Exception e) {
            throw e;
        } finally {
            fecharConexao();
        }

        return contatos;
    }

    //metodo para obter o próximo id válido para o contato
    public long proximoId(){
        long proId = 1;
        Cursor cursor = null;

        try{
            abrirConexao();

            String sql = "SELECT MAX(idcontato) + 1 AS id FROM "+ Constantes.TABELA;
            cursor = db.rawQuery(sql,null);

            if(cursor.moveToNext()){
                proId = cursor.getInt(cursor.getColumnIndex("id"));
            }

        }
        catch(Exception e){
            throw e;
        }
        finally {
            fecharConexao();
        }

        return proId;
    }

}
