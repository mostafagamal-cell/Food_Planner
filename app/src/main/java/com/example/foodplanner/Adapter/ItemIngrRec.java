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

public class ItemIngrRec  extends RecyclerView.Adapter<ItemIngrRec.ItemIngrRecViewHolder> {
    private Meals meals= new Meals();
    MyClickListner clickListner;
    public ItemIngrRec(MyClickListner clickListner){
        meals.meals=new Vector<Meal>();
        this.clickListner=clickListner;
    }
    @NonNull
    @Override
    public ItemIngrRec.ItemIngrRecViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CatitemBinding binding = CatitemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ItemIngrRec.ItemIngrRecViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemIngrRec.ItemIngrRecViewHolder holder, int position) {
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

    protected class ItemIngrRecViewHolder extends RecyclerView.ViewHolder {
        CatitemBinding binding;
        public ItemIngrRecViewHolder(@NonNull CatitemBinding itemView) {
            super(itemView.getRoot());
            binding=itemView;
        }
        public void bind(Meal eee){
            Glide.with(binding.getRoot()).load(eee.strMealThumb).apply( new RequestOptions().placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_foreground)).into(binding.myImage);
            binding.mytextitem.setText(eee.strMeal);
            binding.getRoot().setOnClickListener(view -> {
                clickListner.OnClick(eee);
            });
        }
    }
}

