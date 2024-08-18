package com.example.foodplanner.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.foodplanner.Model.Categories;
import com.example.foodplanner.Model.Category;
import com.example.foodplanner.R;
import com.example.foodplanner.Util.MyClickListner;
import com.example.foodplanner.databinding.CardItemBinding;

import java.util.ArrayDeque;
import java.util.ArrayList;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoryViewHolder> {
    MyClickListner clickListner;
    public CategoriesAdapter(MyClickListner clickListner){
        this.clickListner=clickListner;
    }
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
    public void setCategories(Categories categories){
        Categories.clear();
        Categories.addAll(categories.categories);
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return Categories.size();
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
            Log.i("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee",category.strCategory);
            binding.ImageText.setText(category.strCategory);
            binding.ImageText.setGravity(Gravity.BOTTOM|Gravity.CENTER);
            binding.myCard.setOnClickListener(view -> {
                clickListner.OnClick(category);
            });
        }

    }
}
