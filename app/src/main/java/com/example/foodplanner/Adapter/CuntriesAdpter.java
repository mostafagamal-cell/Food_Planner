package com.example.foodplanner.Adapter;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplanner.Model.Countries;
import com.example.foodplanner.Model.Country;
import com.example.foodplanner.R;
import com.example.foodplanner.Util.MyClickListner;
import com.example.foodplanner.databinding.CardItemBinding;

import java.util.ArrayList;

public class CuntriesAdpter  extends RecyclerView.Adapter<CuntriesAdpter.CuntriesViewHolder> {
    Context context;
    MyClickListner clickListner;
    Countries countries=new Countries();
   public  CuntriesAdpter(MyClickListner clickListner){
       this.clickListner=clickListner;
       countries.meals=new ArrayList<>();
   }
    public void setCuntries(Countries cuntries) {
        countries.meals=cuntries.meals;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public CuntriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();
        return new CuntriesViewHolder(CardItemBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CuntriesViewHolder holder, int position) {
        holder.bind(countries.meals.get(position));
    }

    @Override
    public int getItemCount() {
        return countries.meals.size();
    }

    class CuntriesViewHolder extends RecyclerView.ViewHolder {
        CardItemBinding binding;
        public CuntriesViewHolder(@NonNull CardItemBinding itemView) {
            super(itemView.getRoot());
            binding=itemView;
        }
        public void bind(Country country){
            Bitmap bitmap= BitmapFactory.decodeStream(binding.myImage.getContext().getResources().openRawResource(R.raw.flags));
            binding.ImageText.setText(country.strArea);
            binding.myImage.setImageBitmap(bitmap);
            binding.getRoot().setOnClickListener(v->{
                clickListner.OnClick(country.strArea,0);
            });
        }
    }
}
