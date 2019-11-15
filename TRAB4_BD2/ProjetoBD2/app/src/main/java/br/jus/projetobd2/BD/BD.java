package br.jus.projetobd2.BD;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.jus.projetobd2.Beans.Produto;

public class BD {
    private SQLiteDatabase bd;

    public BD(Context context){
        BDCore auxBd = new BDCore(context);
        bd = auxBd.getWritableDatabase();
    }

    public void inserir(Produto p){
        ContentValues valores = new ContentValues();
        valores.put("nome", p.getNome());
        valores.put("preco", p.getPreco());
        valores.put("descricao", p.getDescricao());

        //insere no banco de dados.
        bd.insert("lista_produtos", null, valores);
    }

    public void atualizar(Produto p){
        ContentValues valores = new ContentValues();
        valores.put("nome", p.getNome());
        valores.put("preco", p.getPreco());
        valores.put("descricao", p.getDescricao());

        //insere no banco de dados.
        bd.update("lista_produtos", valores, "_id = ?", new String[]{""+p.getId()});
    }

    public void deletar(Produto p){
        //deleta do banco de dados.
        bd.delete("lista_produtos", "_id = " + +p.getId(), null);
    }

    public List<Produto> buscar(){
        List<Produto> lista = new ArrayList<Produto>();
        String [] colunas = new String[]{"_id", "nome", "preco", "descricao"};
        //deleta do banco de dados.
        Cursor cursor = bd.query("lista_produtos", colunas, null, null, null, null, "nome ASC");

        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            do{
                int id = cursor.getInt(0);
                String nome = cursor.getString(1);
                float preco = cursor.getFloat(2);
                String desc = cursor.getString(3);

                Produto p = new Produto(id, nome, preco, desc);

                lista.add(p);
            } while(cursor.moveToNext());
        }
        return lista;
    }

    public Produto busca_item(int cod){
        String [] campos = new String[]{"_id", "nome", "preco", "descricao"};
        Cursor cursor = bd.query("lista_produtos", campos, "_id = " + cod, null, null, null, null);

        if(cursor.getCount()>0){
            cursor.moveToFirst();
            int id = cursor.getInt(0);
            String nome = cursor.getString(1);
            float preco = cursor.getFloat(2);
            String desc = cursor.getString(3);

            Produto p = new Produto(id, nome, preco, desc);
            return p;
        } else {
            return null;
        }
    }
}
