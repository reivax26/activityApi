package com.xavier.pouyadoux.prog1.api.model;

import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xavier.pouyadoux.prog1.api.R;

import java.util.List;

public class Postadapter extends RecyclerView.Adapter<Postadapter.ViewHolder> {

    private static List<Post> postList;

    public Postadapter(List<Post> postList) {
        Postadapter.postList = postList;
    }

    public static List<Post> getTarea() {
        return postList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(postList.get(position).getTitle());
        holder.bind(position, postList);
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = (TextView) itemView.findViewById(R.id.Tarea_titulo);

            /**
             * Listener para marcar tarea realizada
             */
            itemView.findViewById(R.id.Button_estado).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    for (int i = 0; i < postList.size(); i++) {

                        if (postList.get(i).isRealizado()) {
                            textView.setPaintFlags(textView.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                            postList.get(i).setCompleted(false);
                        } else {
                            textView.setPaintFlags(textView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                            postList.get(i).setCompleted(true);
                        }
                    }
                }
            });

        }

        public void bind(int position, List<Post> postList) {

            if (postList.get(position).isRealizado()) {
                textView.setText(postList.get(position).getTitle());
                textView.setPaintFlags(textView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            } else {
                textView.setText(postList.get(position).getTitle());
            }
        }

        public List<Post> getTarea() {
            return postList;
        }
    }

}




