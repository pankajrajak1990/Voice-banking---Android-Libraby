package com.example.simpragma_voice_banking.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.simpragma_voice_banking.R;
import com.example.simpragma_voice_banking.model.ChatMessage;

import java.util.List;


public class ChatListAdapter extends RecyclerView.Adapter<ChatListAdapter.ViewHolder> {
    public List<ChatMessage> chatMessageList;
    public  int sender=1,reciver=2;

    public ChatListAdapter(List<ChatMessage> policiesList) {
        this.chatMessageList = policiesList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView;
        if (viewType == sender){
             itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.chat_item_sent, parent, false);

        }else {
             itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.chat_item_rcv, parent, false);

        }

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ChatMessage policies = chatMessageList.get(position);
        holder.title.setText(policies.getMessage());
       // holder.imageView.setImageResource(policies.getImage_url());
    }

    @Override
    public int getItemCount() {
        return chatMessageList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (chatMessageList.get(position).getType().equalsIgnoreCase("sender")){
            return sender;
        }else {return reciver;}

    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView title;
        public ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.message_text_view);
        }
    }
}
