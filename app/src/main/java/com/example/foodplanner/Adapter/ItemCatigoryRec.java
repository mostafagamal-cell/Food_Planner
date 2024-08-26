package com.example.foodplanner.Adapter;

import android.view.LayoutInflater;
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

import java.util.ArrayList;
import java.util.Vector;

public class ItemCatigoryRec extends RecyclerView.Adapter<ItemCatigoryRec.ItemsRecViewHolder> {
    private Meals meals= new Meals();
    MyClickListner clickListner;
    public ItemCatigoryRec(MyClickListner clickListner){
        this.clickListner= clickListner;
        meals.meals=new ArrayList<>();
    }
    @NonNull
    @Override
    public ItemsRecViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CatitemBinding binding = CatitemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ItemsRecViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemsRecViewHolder holder, int position) {
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

    protected class ItemsRecViewHolder extends RecyclerView.ViewHolder {
        CatitemBinding binding;
        public ItemsRecViewHolder(@NonNull CatitemBinding itemView) {
            super(itemView.getRoot());
            binding=itemView;
        }
        public void bind(Meal meals){
            Glide.with(binding.getRoot()).load(meals.strMealThumb).apply( new RequestOptions().placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_foreground)).into(binding.myImage);
            binding.mytextitem.setText(meals.strMeal);
            binding.getRoot().setOnClickListener(v -> {
                clickListner.OnClick(meals);
            });
        }

    }
}
