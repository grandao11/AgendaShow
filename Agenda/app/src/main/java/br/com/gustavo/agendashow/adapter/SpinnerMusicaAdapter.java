package br.com.gustavo.agendashow.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.gustavo.agendashow.R;
import br.com.gustavo.agendashow.pojo.Musica;

/**
 * Created by Gustavo on 17/11/2016.
 */
public class SpinnerMusicaAdapter extends BaseAdapter {

    private Context context;
    private List<Musica> lista;

    public SpinnerMusicaAdapter(Context ctx, List<Musica> list) {
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
        Musica musica = lista.get(position);

        LayoutInflater objInflate = (LayoutInflater)context.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);

        View view = objInflate.inflate(R.layout.item_spinner_musica, null);

        TextView txtNome = (TextView)view.findViewById(R.id.itemTxtNome);
        txtNome.setText(musica.getNome());

        return view;
    }
}
