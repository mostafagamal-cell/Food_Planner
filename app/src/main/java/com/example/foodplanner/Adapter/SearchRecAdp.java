package com.example.foodplanner.Adapter;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.foodplanner.Model.Category;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.R;
import com.example.foodplanner.Util.MyClickListner;
import com.example.foodplanner.databinding.CatitemBinding;

import java.util.ArrayList;

public class SearchRecAdp extends RecyclerView.Adapter<SearchRecAdp.SearchViewHolder> {
    MyClickListner clickListner;
    ArrayList<Meal> data=new ArrayList<>();
    public SearchRecAdp(MyClickListner clickListner){
        this.clickListner=clickListner;
    }
    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      CatitemBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new SearchViewHolder( CatitemBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false));
    }
    public void setData(ArrayList<Meal> data){
        this.data.clear();
        this.data.addAll(data);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
    holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class SearchViewHolder extends RecyclerView.ViewHolder{
        CatitemBinding binding;
        public SearchViewHolder(@NonNull CatitemBinding itemView) {
            super(itemView.getRoot());
            binding=itemView;
        }
        public void bind(Meal category){
            Glide.with(binding.myImage.getContext()).load(category.strMealThumb)
                    .apply( new RequestOptions().placeholder(R.drawable.ic_launcher_background)
                            .error(R.drawable.ic_launcher_foreground).override(100, 100)).into(binding.myImage);
            Log.i("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee",category.strMeal);
            binding.mytextitem.setText(category.strMeal);
            binding.mytextitem.setGravity(Gravity.BOTTOM | Gravity.CENTER);
            binding.getRoot().setOnClickListener(view -> {
                clickListner.OnClick(category);
            });
        }
    }
}
