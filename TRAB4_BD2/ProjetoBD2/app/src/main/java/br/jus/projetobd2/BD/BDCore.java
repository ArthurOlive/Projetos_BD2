package br.jus.projetobd2.BD;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BDCore extends SQLiteOpenHelper {
    private static final String NOME_BD = "TRAB4_BD";
    private static final int VERSAO_BD = 6;

    public BDCore(Context ctx){
        super(ctx, NOME_BD, null, VERSAO_BD);
    }
    @Override
    public void onCreate(SQLiteDatabase bd) {
        bd.execSQL("create table lista_produtos(_id integer primary key autoincrement, nome text not null, preco float not null, descricao text not null);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase bd, int i, int i1) {
        bd.execSQL("drop table lista_produtos;");
        onCreate(bd);
    }
}
