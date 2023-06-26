package com.controledohistricodedoenasemumalocalidade.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.controledohistricodedoenasemumalocalidade.R;
import com.controledohistricodedoenasemumalocalidade.model.ControleDoencas;

import java.util.ArrayList;
import java.util.List;

public class DoencaAdapter extends BaseAdapter {

    private Context context;
    private List<ControleDoencas> controleDoencas;

    private static class DoencaHolder {
        public TextView textViewValorDoenca;
        public TextView textViewValorContagio;
        public TextView textViewValorPodeLevarAObto;
        public TextView textViewValorPrimeirosSocorros;
        public TextView textViewValorMedicamentos;
        public TextView textViewValorLocalidade;
    }

    public DoencaAdapter(Context context, List<ControleDoencas> controleDoencas) {
        this.context = context;
        this.controleDoencas = controleDoencas;
    }

    @Override
    public int getCount() {
        return controleDoencas.size();
    }

    @Override
    public Object getItem(int i) {
        return controleDoencas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        DoencaHolder holder;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.linha_lista_itens, viewGroup, false);

            holder = new DoencaHolder();

            holder.textViewValorDoenca = view.findViewById(R.id.textViewValorDoenca);
            holder.textViewValorContagio = view.findViewById(R.id.textViewValorContagio);
            holder.textViewValorPodeLevarAObto = view.findViewById(R.id.textViewValorLevarAObto);
            holder.textViewValorPrimeirosSocorros = view.findViewById(R.id.textViewValorPrimeirosSocorros);
            holder.textViewValorMedicamentos = view.findViewById(R.id.textViewValorMedicamentos);
            holder.textViewValorLocalidade = view.findViewById(R.id.textViewValorLocalidades);
            view.setTag(holder);
        } else {
            holder = (DoencaHolder) view.getTag();
        }
        holder.textViewValorDoenca.setText(controleDoencas.get(i).getDoenca());
        holder.textViewValorContagio.setText(controleDoencas.get(i).getContagio());

        switch (controleDoencas.get(i).getTipoPodeLevarAObto()) {
            case "Sim":
            case "Yes":
                holder.textViewValorPodeLevarAObto.setText(R.string.sim);
                break;
            case "Não":
            case "No":
                holder.textViewValorPodeLevarAObto.setText(R.string.nao);
        }

        switch (controleDoencas.get(i).getPrimeirosSocorros()) {
            case "até 12 horas!":
            case "Up to 12 hours":
                holder.textViewValorPrimeirosSocorros.setText(R.string.dozeHoras);
                break;
            case "até 24 horas!":
            case "Up to 24 hours":
                holder.textViewValorPrimeirosSocorros.setText(R.string.vinteEQuatroHoras);
                break;
            case "acima de 24 horas!":
            case"Over 24 hours":
                holder.textViewValorPrimeirosSocorros.setText(R.string.acimaDeVinteQuatroHoras);
                break;
        }
        holder.textViewValorMedicamentos.setText(controleDoencas.get(i).getMedicamento());

        holder.textViewValorLocalidade.setText(controleDoencas.get(i).getLocalidade());
        return view;
    }
}
