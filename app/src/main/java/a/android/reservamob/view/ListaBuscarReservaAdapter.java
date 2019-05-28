package a.android.reservamob.view;

import android.content.Context;
import android.os.TestLooperManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import a.android.reservamob.R;
import a.android.reservamob.model.Reserva;

public class ListaBuscarReservaAdapter extends BaseAdapter {
    private Map<Integer, Reserva> reservaSelect;
    private ArrayList<Reserva> reservas;
    private final Context context;
    private ListaBuscarReservaAdapterListener listener;
    private int position;

    public ListaBuscarReservaAdapter(Map<Integer, Reserva> reservaSelect, ArrayList<Reserva> reservas, Context context, ListaBuscarReservaAdapterListener listener) {
        this.context = context;

        if (reservas != null) {
            this.reservas = reservas;
        } else {
            this.reservas = new ArrayList<>();
        }
/*
        if (reservaSelect != null) {
            this.reservaSelect = reservaSelect;
        } else {
            this.reservaSelect = new HashMap<>();
        }*/

        if (listener != null) {
            this.listener = listener;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement ListaBuscarReservaAdapterListener");
        }
    }

    public Context getContext() {
        return context;
    }


    public ArrayList<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(ArrayList<Reserva> reservas) {
        if (reservas != null)
            this.reservas = reservas;
    }

    @Override
    public int getCount() {
        return reservas.size();
    }

    @Override
    public Object getItem(int i) {
        return reservas.get(position);
    }

    @Override
    public long getItemId(int i) {
        return reservas.get(position).getId();
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        Reserva reserva = reservas.get(position);
        ViewHolder holder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_buscar_reserva, null);
            holder = new ViewHolder();
            holder.titulo = view.findViewById(R.id.txtNome);
            holder.descricao = view.findViewById(R.id.txt_item_descricao);
            holder.valor = view.findViewById(R.id.txt_item_titulo);
            holder.selecionar = view.findViewById(R.id.btnSelecionar);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        /*holder.titulo.setText(servico.getNome());
        holder.fieldDescricao.setText(servico.getDescricao());
        holder.fieldValor.setText(context.getString(R.string.total, servico.getValor()));*/
        if (reservaSelect.containsKey(position))
            view.setBackgroundColor(context.getResources().getColor(R.color.colorAccent));
        else
            view.setBackgroundColor(context.getResources().getColor(android.R.color.transparent));

        return view;
    }
    public void onItemClick(int position) {
        Reserva reserva = reservas.get(position);

        if (!reservaSelect.containsKey(position)) {
            reservaSelect.put(position, reserva);
            listener.itemMarcado(reserva);
        } else {
            reservaSelect.remove(position);
            listener.itemDesmarcado(reserva);
        }

        notifyDataSetChanged();
    }
    private interface ListaBuscarReservaAdapterListener {
        void itemMarcado(Reserva reserva);

        void itemDesmarcado(Reserva reserva);
    }

    private static class ViewHolder {
        TextView titulo;
        TextView descricao;
        TextView valor;
        Button selecionar;
    }
}
