package com.example.moviepaggingapp.adapter;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.LoadState;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviepaggingapp.R;
import com.example.moviepaggingapp.databinding.LoadStateItemBinding;

public class LoadStateAdapter extends androidx.paging.LoadStateAdapter<LoadStateAdapter.LoadStateViewHolder> {


    private View.OnClickListener retryCallback;

    public LoadStateAdapter(View.OnClickListener retryCallback) {
        this.retryCallback = retryCallback;
    }

    @Override
    public void onBindViewHolder(@NonNull LoadStateViewHolder loadStateViewHolder, @NonNull LoadState loadState) {
        loadStateViewHolder.bind(loadState);
    }

    @NonNull
    @Override
    public LoadStateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, @NonNull LoadState loadState) {
        return new LoadStateViewHolder(parent,retryCallback);
    }

    public static class LoadStateViewHolder extends RecyclerView.ViewHolder{

        private ProgressBar progressBar;
        private TextView textView;
        private Button button;

        public LoadStateViewHolder(@NonNull ViewGroup parent, @NonNull View.OnClickListener retryCallback)
        {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.load_state_item,parent,false));
            LoadStateItemBinding binding = LoadStateItemBinding.bind(itemView);
            progressBar = binding.progressBar;
            textView = binding.errorMsg;
            button = binding.retryButton;
            button.setOnClickListener(retryCallback);
        }

        public void bind(LoadState loadState)
        {
            if(loadState instanceof LoadState.Error)
            {
                LoadState.Error loadStateError = (LoadState.Error) loadState;

                textView.setText(((LoadState.Error) loadState).getError().getLocalizedMessage());
            }
            progressBar.setVisibility(loadState instanceof LoadState.Loading ?View.VISIBLE:View.GONE);
            textView.setVisibility(loadState instanceof LoadState.Error ?View.VISIBLE:View.GONE);
            button.setVisibility(loadState instanceof LoadState.Error ?View.VISIBLE:View.GONE);
        }
    }
}
