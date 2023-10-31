package com.example.vietcal;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ExtensionAdapter extends RecyclerView.Adapter<ExtensionAdapter.ExtensionViewHolder> {
    private List<Extension> mListExtension;

    public ExtensionAdapter(List<Extension> mListExtension) {
        this.mListExtension = mListExtension;
    }

    @NonNull
    @Override
    public ExtensionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_extension, parent, false);
        return new ExtensionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExtensionViewHolder holder, int position) {
        Extension extension = mListExtension.get(position) ;
        if(extension == null) {
            return;
        }
        holder.tvItemExtension.setText(extension.getNameExtension());
        holder.ivItemResource.setImageResource(extension.getImageResource());
    }

    @Override
    public int getItemCount() {
        if(mListExtension != null) {
            return mListExtension.size();
        }else{
            return 0;
        }
    }

    public class ExtensionViewHolder extends RecyclerView.ViewHolder {
        private TextView tvItemExtension;
        private ImageView ivItemResource;
        public ExtensionViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItemExtension = itemView.findViewById(R.id.tv_item_extension);
            ivItemResource = itemView.findViewById(R.id.iv_item_extension);
        }
    }
}
