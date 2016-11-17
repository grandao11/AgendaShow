package br.com.gustavo.agendashow.adapter;

/**
 * Created by Gustavo on 17/11/2016.
 */
public class ListaMusicaAdapter extends BaseAdapter {

    private Context context;
    private List<Musica> lista;

    public ListaMusicaAdapter(Context ctx, List<Musica> list) {
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

        View view = objInflate.inflate(R.layout.item_lista_musica, null);

        TextView txtNome = (TextView)view.findViewById(R.id.itemTxtNome);
        txtNome.setText(musica.getNome());

        TextView txtComentario = (TextView)view.findViewById(R.id.itemTxtComentario);
        txtComentario.setText(musica.getComentario().toString());

        return view;
    }
}
