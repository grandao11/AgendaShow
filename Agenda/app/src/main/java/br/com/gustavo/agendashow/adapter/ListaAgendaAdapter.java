package br.com.gustavo.agendashow.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.gustavo.agendashow.R;
import br.com.gustavo.agendashow.activities.ListaAgendaActivity;
import br.com.gustavo.agendashow.pojo.Agenda;

/**
 * Created by Gustavo on 17/11/2016.
 */
public class ListaAgendaAdapter extends BaseAdapter {
    private Context context;
    private List<Agenda> lista;

    public ListaAgendaAdapter(Context ctx, List<Agenda> list) {
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
        Agenda agenda = lista.get(position);

        LayoutInflater objInflate = (LayoutInflater)context.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);

        View view = objInflate.inflate(R.layout.item_lista_agenda, null);

        TextView txtMusica = (TextView)view.findViewById(R.id.itemTxtMusica);
        txtMusica.setText(agenda.getMusica());

        TextView txtCidade = (TextView)view.findViewById(R.id.itemTxtCidade);
        txtCidade.setText(agenda.getCidade());

        return view;
    }
}

