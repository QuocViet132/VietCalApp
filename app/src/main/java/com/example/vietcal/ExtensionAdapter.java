package com.example.vietcal;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vietcal.interfaces.IClickItemExtensionListener;

import java.util.List;

public class ExtensionAdapter extends RecyclerView.Adapter<ExtensionAdapter.ExtensionViewHolder> {
    private List<Extension> mListExtension;
    private IClickItemExtensionListener mIClickItemExtensionListener;

    public ExtensionAdapter(List<Extension> mListExtension, IClickItemExtensionListener listener) {
        this.mListExtension = mListExtension;
        this.mIClickItemExtensionListener = listener;
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
        holder.layoutItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIClickItemExtensionListener.onClickItemExtension(extension);
            }
        });
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
        private LinearLayout layoutItem;
        private TextView tvItemExtension;
        private ImageView ivItemResource;
        public ExtensionViewHolder(@NonNull View itemView) {
            super(itemView);
            layoutItem = itemView.findViewById(R.id.layout_item_extension);
            tvItemExtension = itemView.findViewById(R.id.tv_item_extension);
            ivItemResource = itemView.findViewById(R.id.iv_item_extension);
        }
    }
}
