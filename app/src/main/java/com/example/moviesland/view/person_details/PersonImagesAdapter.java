package com.example.moviesland.view.person_details;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviesland.R;
import com.example.moviesland.databinding.ItemPersonImageBinding;
import com.example.moviesland.view.image_view.ImageViewActivity;

import java.util.List;

public class PersonImagesAdapter extends RecyclerView.Adapter<PersonImagesAdapter.ImageHolder> {

    private List<String> imagePathsList;
    private Context context;
    private LayoutInflater layoutInflater;

    PersonImagesAdapter(List<String> imagePathsList) {
        this.imagePathsList = imagePathsList;
    }

    @NonNull
    @Override
    public ImageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null)
            layoutInflater = LayoutInflater.from(parent.getContext());
        context = parent.getContext();
        ItemPersonImageBinding imageBinding = DataBindingUtil.inflate(layoutInflater,
                R.layout.item_person_image, parent, false);
        return new ImageHolder(imageBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageHolder holder, int position) {
        holder.binding.setImagePath(imagePathsList.get(position));

        holder.binding.cvPersonItem.setOnClickListener(view ->
                goToImageView(imagePathsList.get(position)));
    }

    private void goToImageView(String imagePath) {
        Intent intent = new Intent(context, ImageViewActivity.class);
        intent.putExtra(context.getResources().getString(R.string.image_path), imagePath);
        context.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return imagePathsList.size();
    }

    class ImageHolder extends RecyclerView.ViewHolder {

        ItemPersonImageBinding binding;

        ImageHolder(ItemPersonImageBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
