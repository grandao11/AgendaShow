package vandin.nossocasanossobar.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import vandin.nossocasanossobar.R;
import vandin.nossocasanossobar.pojo.Aperitivo;

/**
 * Created by Vandin on 03/11/2016.
 */

public class ListaAperitivoAdapter extends BaseAdapter {

    private Context context;
    private List<Aperitivo> lista;

    public ListaAperitivoAdapter(Context ctx, List<Aperitivo> list) {
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
        Aperitivo aperitivo = lista.get(position);

        LayoutInflater objInflate = (LayoutInflater)context.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);

        View view = objInflate.inflate(R.layout.item_lista_aperitivo, null);

        TextView txtNome = (TextView)view.findViewById(R.id.itemTxtNome);
        txtNome.setText(aperitivo.getNome());

        TextView txtPreco = (TextView)view.findViewById(R.id.itemTxtPreco);
        txtPreco.setText(aperitivo.getPreco().toString());

        return view;
    }
}
