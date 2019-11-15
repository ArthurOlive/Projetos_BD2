package br.jus.projetobd2.App;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import br.jus.projetobd2.BD.BD;
import br.jus.projetobd2.Beans.Produto;
import br.jus.projetobd2.R;

public class Cadastrar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_item);

    }

    public void onClickConclui(View view){
        EditText nomeProduto = (EditText) findViewById(R.id.nome);
        EditText precoProduto = (EditText) findViewById(R.id.preco);
        EditText descProduto = (EditText) findViewById(R.id.desc);

        String precoConteudo = String.valueOf(precoProduto.getText());
        String nomeConteudo = String.valueOf(nomeProduto.getText());
        String descConteudo = String.valueOf(descProduto.getText());

        //Teste de campo vazio
        if(!precoConteudo.isEmpty() && !nomeConteudo.isEmpty() && !descConteudo.isEmpty()) {
            Produto produto_novo = new Produto(nomeConteudo, Float.parseFloat(precoConteudo), descConteudo);

            System.out.println(produto_novo.getNome() + " \n" + produto_novo.getPreco() + " \n" + produto_novo.getDescricao());

            //Cadastra o item no sqlite.
            BD bd = new BD(this);
            bd.inserir(produto_novo);

            //Retorna a resposta para a tela da chamada
            //Se foi cadastrado com sucesso!
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putString("msg", "Sucesso");
            intent.putExtras(bundle);
            setResult(1, intent);

            finish(); //fecha a pilha de tela.
        } else {
            Toast msgErr = Toast.makeText(getApplicationContext(), "Campo vazio", Toast.LENGTH_LONG);
            msgErr.show();
        }
    }

}
