package com.example.foodplanner.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.foodplanner.Model.Ingradiants;
import com.example.foodplanner.Model.IngradintMeals;
import com.example.foodplanner.R;
import com.example.foodplanner.Util.MyClickListner;
import com.example.foodplanner.databinding.CardItemBinding;

import java.util.ArrayList;

public class InteREc extends RecyclerView.Adapter<InteREc.InteViewHolder> {
    Ingradiants ingradiants=new Ingradiants();
    MyClickListner clickListner;
    public InteREc(MyClickListner clickListner) {
        ingradiants.meals= new ArrayList<>();
        this.clickListner=clickListner;
    }
   public void setIngradiants(Ingradiants ingradintMeals){
        this.ingradiants.meals=ingradintMeals.meals;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public InteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardItemBinding binding = CardItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new InteViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull InteViewHolder holder, int position) {
        holder.setBinding(ingradiants.meals.get(position));
    }

    @Override
    public int getItemCount() {
        return ingradiants.meals.size();
    }

    class InteViewHolder extends RecyclerView.ViewHolder {
        CardItemBinding binding;
        public InteViewHolder(@NonNull CardItemBinding itemView) {
            super(itemView.getRoot());
            binding=itemView;
        }
        public void setBinding(IngradintMeals ingradintMeals){
            Log.d("aaaaaaaaaaaaaaaaaaaaaaa", "https://www.themealdb.com/images/ingredients/"+ingradintMeals.strIngredient+"-Small.png");
            Glide.with(binding.myImage.getContext()).load("https://www.themealdb.com/images/ingredients/"+ingradintMeals.strIngredient+"-Small.png")
                    .apply(new RequestOptions().placeholder(R.drawable.ic_launcher_background)
                            .error(R.drawable.ic_launcher_foreground)
                            .override(100, 100))
                    .into(binding.myImage);
            binding.ImageText.setText(ingradintMeals.strIngredient);
            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                clickListner.OnClick(ingradintMeals.strIngredient,1);
                }
            });
        }
    }
}
