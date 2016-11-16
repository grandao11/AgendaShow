package vandin.nossocasanossobar.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import vandin.nossocasanossobar.R;
import vandin.nossocasanossobar.pojo.Bebida;

/**
 * Created by Vandin on 03/11/2016.
 */

public class ListaBebidaAdapter extends BaseAdapter {

    private Context context;
    private List<Bebida> lista;

    public ListaBebidaAdapter(Context ctx, List<Bebida> list) {
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
        Bebida bebida = lista.get(position);

        LayoutInflater objInflate = (LayoutInflater)context.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);

        View view = objInflate.inflate(R.layout.item_lista_bebida, null);

        TextView txtNome = (TextView)view.findViewById(R.id.itemTxtNome);
        txtNome.setText(bebida.getNome());

        TextView txtPreco = (TextView)view.findViewById(R.id.itemTxtPreco);
        txtPreco.setText(bebida.getPreco().toString());

        return view;
    }
}
