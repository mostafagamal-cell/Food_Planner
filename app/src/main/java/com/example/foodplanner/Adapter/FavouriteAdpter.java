package com.example.foodplanner.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.Meals;
import com.example.foodplanner.R;
import com.example.foodplanner.Util.MyClickListner;
import com.example.foodplanner.databinding.CatitemBinding;
import com.example.foodplanner.databinding.FavouriteitemBinding;

import java.util.ArrayList;

public class FavouriteAdpter extends RecyclerView.Adapter<FavouriteAdpter.FavouriteItemHolder> {
    public Meals meals= new Meals();
    MyClickListner clickListner;
    public FavouriteAdpter(MyClickListner clickListner){
        this.clickListner=clickListner;
        meals.meals=new ArrayList<Meal>();
    }
    @NonNull
    @Override
    public FavouriteAdpter.FavouriteItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        FavouriteitemBinding binding = FavouriteitemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new FavouriteAdpter.FavouriteItemHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull FavouriteAdpter.FavouriteItemHolder holder, int position) {
        holder.bind(meals.meals.get(position));
    }

    @Override
    public int getItemCount() {
        return meals.meals.size();
    }
    public void setcontect(Meals meals){
        this.meals=meals;
        notifyDataSetChanged();
    }

    protected class FavouriteItemHolder extends RecyclerView.ViewHolder {
        FavouriteitemBinding binding;
        public FavouriteItemHolder(@NonNull FavouriteitemBinding itemView) {
            super(itemView.getRoot());
            binding=itemView;
        }
        public void bind(Meal meals){
            Glide.with(binding.getRoot()).load(meals.strMealThumb).apply( new RequestOptions().placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_foreground)).into(binding.myImage);
            binding.mytextitem.setText(meals.strMeal);
            binding.getRoot().setOnClickListener(view -> {
                clickListner.OnClick(meals);
            });
            binding.imageButton2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickListner.OnClickDelte(meals);
                }
            });
        }
    }
}

