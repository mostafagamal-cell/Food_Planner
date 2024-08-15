package com.example.foodplanner.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.foodplanner.Model.Categories;
import com.example.foodplanner.Model.Category;
import com.example.foodplanner.R;
import com.example.foodplanner.databinding.CardItemBinding;

import java.util.ArrayDeque;
import java.util.ArrayList;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoryViewHolder> {
    public static ArrayList<Category> Categories= new ArrayList<>();
    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
             LayoutInflater inflater = (LayoutInflater)  parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
             CardItemBinding binding= CardItemBinding.inflate(inflater,parent,false);
             return new CategoryViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        holder.bind(Categories.get(position));
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    protected class CategoryViewHolder extends RecyclerView.ViewHolder{
        CardItemBinding binding;
        public CategoryViewHolder(@NonNull CardItemBinding itemView) {
            super(itemView.getRoot());
            this.binding=itemView;
        }
        public void bind(Category category){
            Glide.with(binding.myImage.getContext()).load(category.strCategoryThumb)
                    .apply( new RequestOptions().placeholder(R.drawable.ic_launcher_background)
                            .error(R.drawable.ic_launcher_foreground).override(100, 100)).into(binding.myImage);
            binding.ImageText.setText(category.strCategory);
        }

    }
}
