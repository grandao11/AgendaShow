package vandin.nossocasanossobar.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;
import vandin.nossocasanossobar.R;
import vandin.nossocasanossobar.pojo.Pedido;

/**
 * Created by Vandin on 02/11/2016.
 */

public class ListaPedidoAdapter extends BaseAdapter {

    private Context context;
    private List<Pedido> lista;

    public ListaPedidoAdapter(Context ctx, List<Pedido> list) {
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
        Pedido pedido = lista.get(position);

        LayoutInflater objInflate = (LayoutInflater)context.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);

        View view = objInflate.inflate(R.layout.item_lista_pedido, null);

        TextView txtCliente = (TextView)view.findViewById(R.id.itemTxtCliente);
        txtCliente.setText(pedido.getCliente());

        TextView txtBebida = (TextView)view.findViewById(R.id.itemTxtBebida);
        txtBebida.setText(pedido.getBebida());

        TextView txtAperitivo = (TextView)view.findViewById(R.id.itemTxtAperitivo);
        txtAperitivo.setText(pedido.getAperitivo());

        return view;
    }
}
