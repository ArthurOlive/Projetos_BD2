package br.jus.projetobd2.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;


import java.util.ArrayList;

import br.jus.projetobd2.Beans.Produto;
import br.jus.projetobd2.R;

public class ListaAdapterItem extends ArrayAdapter {
    private Context context;
    private ArrayList<Produto> lista;

    public ListaAdapterItem(Context context, ArrayList<Produto> lista) {
        super(context, 0, lista);
        this.context = context;
        this.lista = lista;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Produto p = this.lista.get(position);
        final Context c = this.context;

        convertView = LayoutInflater.from(this.context).inflate(R.layout.item,  null);

        TextView nome = (TextView) convertView.findViewById(R.id.nome_item);
        nome.setText(p.getNome());

        TextView cod = (TextView) convertView.findViewById(R.id.cod_item);
        cod.setText(String.valueOf(p.getId()));

        TextView preco = (TextView) convertView.findViewById(R.id.preco_item);
        preco.setText(String.valueOf(p.getPreco()));

        TextView desc = (TextView) convertView.findViewById(R.id.desc_item);
        desc.setText(p.getDescricao());

        Button btAlt = (Button) convertView.findViewById(R.id.atlBt);
        btAlt.setId(position);

        Button btRem = (Button) convertView.findViewById(R.id.remBt);
        btRem.setId(position);

        return convertView;
    }

}
