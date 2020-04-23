package com.example.ghich;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GhiChuAdapter extends ArrayAdapter<GhiChu> {

    private Context context;
    private int resource;
    private List<GhiChu> objects;
    private List<GhiChu> copyObjects;

    public GhiChuAdapter(@NonNull Context context, int resource, @NonNull List<GhiChu> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
        copyObjects = new ArrayList<>(objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(resource, parent, false);
        TextView txtTieuDe=(TextView) convertView.findViewById(R.id.textViewTieuDe);
        TextView txtThoiGian=(TextView) convertView.findViewById(R.id.textViewThoiGian);
        TextView txtTag=(TextView) convertView.findViewById(R.id.textViewTag);
        GhiChu ghichu=objects.get(position);

        txtTieuDe.setText(ghichu.getTieuDe());
        txtThoiGian.setText(ghichu.getThoiGianChinhSua());
        txtTag.setText(ghichu.getTag());
        return convertView;
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                List<GhiChu> data = new ArrayList<>();
                if (charSequence == null || charSequence.length() == 0) {
                    data.addAll(copyObjects);
                } else {
                    String filterString = charSequence.toString().toLowerCase().trim();
                    for (GhiChu i : copyObjects) {
                        if (i.getTieuDe().toLowerCase().contains(filterString) || i.getTag().toLowerCase().contains(charSequence)) {
                            data.add(i);
                        }
                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = data;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                objects.clear();
                objects.addAll((Collection<? extends GhiChu>) filterResults.values);
                notifyDataSetChanged();
            }
        };
    }


}
