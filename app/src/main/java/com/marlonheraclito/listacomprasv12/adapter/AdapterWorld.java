package com.marlonheraclito.listacomprasv12.adapter;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.marlonheraclito.listacomprasv12.R;
import com.marlonheraclito.listacomprasv12.modelo.Compra;
import com.marlonheraclito.listacomprasv12.utils.Common;

import java.util.List;

public class AdapterWorld  extends RecyclerView.Adapter<AdapterWorld.WordViewHolder> {

    private Context context;
    private List<Compra> lista;

    public AdapterWorld(Context context, List<Compra> lista){
        this.context = context;
        this.lista = lista;
    }

    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layouthelper, parent, false);
        return new WordViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder holder, int position) {
        holder.txtNome.setText(lista.get(position).getNome());
        holder.txtQuant.setText("Quant: " + lista.get(position).getQuant());
        holder.txtPreco.setText("Preco: " + lista.get(position).getPreco() + " $00");
        holder.txtTotal.setText("Total: " + lista.get(position).getTotal() + " $00");
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    class WordViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {

        public final TextView txtNome;
        public final TextView txtQuant;
        public final TextView txtPreco;
        public final TextView txtTotal;

        public WordViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNome = itemView.findViewById(R.id.txtNomeHelper);
            txtQuant = itemView.findViewById(R.id.txtQUANT);
            txtPreco = itemView.findViewById(R.id.txtPRECO);
            txtTotal = itemView.findViewById(R.id.txtTotal);

            itemView.setOnCreateContextMenuListener(this);
            
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.add(this.getAdapterPosition(), 2, 0, "Alterar");
            menu.add(this.getAdapterPosition(), 1, 0, "Apagar");
        }
    }

    public void remover(final int position){
        Common.listaCompras.remove(position);
        notifyDataSetChanged();
    }



}
