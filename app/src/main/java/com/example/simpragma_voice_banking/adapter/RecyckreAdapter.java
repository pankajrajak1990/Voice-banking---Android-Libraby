package com.example.simpragma_voice_banking.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.concurrent.RecursiveAction;

public class RecyckreAdapter extends RecyclerView.Adapter<RecyckreAdapter.ViewHoldew> {





    @NonNull
    @Override
    public ViewHoldew onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoldew holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHoldew extends RecyclerView.ViewHolder {
        public ViewHoldew(@NonNull View itemView) {
            super(itemView);
        }
    }
}
