package vandin.nossocasanossobar.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import vandin.nossocasanossobar.pojo.Cliente;
import vandin.nossocasanossobar.R;

/**
 * Created by Vandin on 01/11/2016.
 */

public class ListaClienteAdapter extends BaseAdapter {

    private Context context;
    private List<Cliente> lista;

    public ListaClienteAdapter(Context ctx, List<Cliente> list) {
        this.context = ctx;
        this.lista = list;
    }

    public int getCount() {
        return this.lista.size();
    }

    public Object getItem(int position) {
        return this.lista.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        Cliente cliente = lista.get(position);

        LayoutInflater objInflate = (LayoutInflater)context.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);

        View view = objInflate.inflate(R.layout.item_lista_cliente, null);

        TextView txtNome = (TextView)view.findViewById(R.id.itemTxtNome);
        txtNome.setText(cliente.getNome());

        TextView txtCelular = (TextView)view.findViewById(R.id.itemTxtCelular);
        txtCelular.setText(cliente.getCelular());

        return view;
    }
}
