package br.jus.projetobd2.App;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.jus.projetobd2.BD.BD;
import br.jus.projetobd2.Adapter.ListaAdapterItem;
import br.jus.projetobd2.Beans.Produto;
import br.jus.projetobd2.R;

public class MainActivity extends AppCompatActivity {

    private final int COD_TELA_ADD = 1;
    private final int COD_TELA_ALT = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        atualizarLista();
    }

    public void atualizarLista(){
        BD bd = new BD(this); //Recebe a conexão do banco de dados
        List<Produto> p = bd.buscar();//Recebe a lista dos objetos de produto

        ArrayList<Produto> lista = new ArrayList<Produto>();

        int x = 0;
        while(!p.isEmpty()){
            Produto produto = p.get(x);
            p.remove(x);
            lista.add(produto);
        }

        ListaAdapterItem listaAdapter = new ListaAdapterItem(this, lista);

        /*
        Para o hashmap utiliza-se
        String [] chaves = new String[]{"Nome", "Valor"};

        int nativo_layout = android.R.layout.two_line_list_item;

        int[] to = new int[]{android.R.id.text1, android.R.id.text2};

        //Recebe as variaveis dos botões
        listaItens = (ListView) findViewById(R.id.lv);
        listaItens.setAdapter(new SimpleAdapter(this, lista, nativo_layout, chaves, to));
         */

        //Recebe as variaveis dos botões
        ListView listaItens = (ListView) findViewById(R.id.lv);
        listaItens.setAdapter(listaAdapter);
    }

    public void removeItem(View v){
        ListView listaItens = (ListView) findViewById(R.id.lv);
        int id = v.getId(); //Codigo do botão vinculado na hora da definição.
        System.out.println(id);
        final Produto p =  (Produto) listaItens.getAdapter().getItem(id); //Produto que será deletado.
        System.out.println(p.getNome());

        //Remove o item
        BD bd = new BD(this);
        bd.deletar(p);

        //Atualiza a listView
        atualizarLista();
    }

    public void alterarItem(View v){
        ListView listaItens = (ListView) findViewById(R.id.lv);
        int id = v.getId(); //Codigo do botão vinculado na hora da definição.
        System.out.println(id);
        final Produto p =  (Produto) listaItens.getAdapter().getItem(id); //Produto que será deletado.
        System.out.println(p.getNome());

        Intent intent = new Intent(this, Alterar.class);
        Bundle params = new Bundle();
        params.putInt("id", p.getId());
        params.putString("nome" , p.getNome());
        params.putFloat("preco", p.getPreco());
        params.putString("desc", p.getDescricao());
        intent.putExtras(params);
        startActivityForResult(intent, COD_TELA_ALT);
    }

    public void onClickAdd(View view){
        Intent intent = new Intent(this , Cadastrar.class);
        startActivityForResult(intent, COD_TELA_ADD);
    }

    protected void onActivityResult(int codTela, int result, Intent intent){
        super.onActivityResult(codTela, result, intent);
        System.out.println("Metodo result chamado");
        if (codTela == COD_TELA_ADD){
            try {
                Bundle params = intent.getExtras();
                if (params != null) {
                    String resp = params.getString("msg");
                    if (resp.equals("Sucesso")) {
                        atualizarLista();
                        System.out.println("Atualizar lista de itens");
                    }
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }if (codTela == COD_TELA_ALT){
            try {
                Bundle params = intent.getExtras();
                if (params != null) {
                    String resp = params.getString("msg");
                    if (resp.equals("Sucesso")) {
                        atualizarLista();
                        System.out.println("Atualizar lista de itens");
                    }
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
