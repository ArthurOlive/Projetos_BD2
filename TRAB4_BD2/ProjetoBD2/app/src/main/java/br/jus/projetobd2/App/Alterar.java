package br.jus.projetobd2.App;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import br.jus.projetobd2.BD.BD;
import br.jus.projetobd2.Beans.Produto;
import br.jus.projetobd2.R;

public class Alterar extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alterar_item);

        Intent intent = getIntent(); //Recebe o bundle passado
        if (intent != null) {
            Bundle params = intent.getExtras();

            System.out.println(params.getString("nome"));

            TextView id = (TextView) findViewById(R.id.cod_item_alt);
            EditText nome = (EditText) findViewById(R.id.nome_alt);
            EditText preco = (EditText) findViewById(R.id.preco_alt);
            EditText desc = (EditText) findViewById(R.id.desc_alt);

            id.setText(String.valueOf(params.getInt("id")));
            nome.setText(params.getString("nome"));
            preco.setText(String.valueOf(params.getFloat("preco")));
            desc.setText(params.getString("desc"));
        } else {
            finish();
        }
    }

    public void onClickAltera(View v){
        TextView id = (TextView) findViewById(R.id.cod_item_alt);
        EditText nome = (EditText) findViewById(R.id.nome_alt);
        EditText preco = (EditText) findViewById(R.id.preco_alt);
        EditText desc = (EditText) findViewById(R.id.desc_alt);

        String id_p = id.getText().toString();
        String nome_p = nome.getText().toString();
        String preco_p = preco.getText().toString();
        String desc_p = desc.getText().toString();

        if (!nome_p.isEmpty() && !preco_p.isEmpty() && !desc_p.isEmpty()) {

            int id_s = Integer.valueOf(id_p);
            String nome_s = nome_p;
            float preco_s = Float.parseFloat(preco_p);
            String desc_s = desc_p;

            Produto p = new Produto(id_s, nome_s, preco_s, desc_s);

            BD bd = new BD(this);
            bd.atualizar(p);

            //Retorna a resposta para a tela da chamada
            //Se foi cadastrado com sucesso!
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putString("msg", "Sucesso");
            intent.putExtras(bundle);
            setResult(2, intent);

            finish(); //fecha a pilha de tela.
        }
        else{
            Toast torrada = Toast.makeText(getApplicationContext(), "Campos invalidos!", Toast.LENGTH_SHORT);
            torrada.show();
        }
    }

}
