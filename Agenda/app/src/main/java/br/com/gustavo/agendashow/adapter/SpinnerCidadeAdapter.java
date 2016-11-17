package br.com.gustavo.agendashow.adapter;

/**
 * Created by Gustavo on 17/11/2016.
 */
public class SpinnerCidadeAdapter extends BaseAdapter {

    private Context context;
    private List<Cidade> lista;

    public SpinnerCidadeAdapter(Context ctx, List<Cidade> list) {
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
        Cidade cidade = lista.get(position);

        LayoutInflater objInflate = (LayoutInflater)context.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);

        View view = objInflate.inflate(R.layout.item_spinner_cidade, null);

        TextView txtNome = (TextView)view.findViewById(R.id.itemTxtNome);
        txtNome.setText(cidade.getNome());

        return view;
    }
}
