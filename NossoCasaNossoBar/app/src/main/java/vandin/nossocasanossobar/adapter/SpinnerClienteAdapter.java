package vandin.nossocasanossobar.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

import vandin.nossocasanossobar.R;
import vandin.nossocasanossobar.pojo.Cliente;

/**
 * Created by Vandin on 06/11/2016.
 */

public class SpinnerClienteAdapter extends BaseAdapter {

    private Context context;
    private List<Cliente> lista;

    public SpinnerClienteAdapter(Context ctx, List<Cliente> list) {
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

        View view = objInflate.inflate(R.layout.item_spinner_cliente, null);

        TextView txtNome = (TextView)view.findViewById(R.id.itemTxtNome);
        txtNome.setText(cliente.getNome());

        return view;
    }
}
