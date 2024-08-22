package com.example.foodplanner.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.Plan;
import com.example.foodplanner.R;
import com.example.foodplanner.Util.MyClickListner;
import com.example.foodplanner.databinding.FavouriteitemBinding;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class PlanRec extends RecyclerView.Adapter<PlanRec.MyViewHolder> {
    MyClickListner clickListner;
    public  ArrayList<Plan> myplans=new ArrayList<>();
    public  ArrayList<Plan> filterplan=new ArrayList<>();
    public String f1;
    public String f2;
     public PlanRec(MyClickListner clickListner){
        this.clickListner=clickListner;
    }
    public void setcontent(List<Plan> plans){
        myplans.clear();
        myplans.addAll(plans);
        applyFilters();
    }
    public void setupdate(List<Plan> plans){
        filterplan.clear();
        for (int i = 0; i < plans.size(); i++) {
            if (check(plans.get(i))){
                myplans.add(plans.get(i));
            }else
            {
                myplans.add(plans.get(i));
            }
        }
        applyFilters();
        notifyDataSetChanged();
    }
    public boolean check(Plan p){
        for (int i = 0; i < myplans.size(); i++) {
            if (p.email.equals(myplans.get(i).email)&&
                    p.Day.equals(myplans.get(i).Day)
                    &&p.type.equals(myplans.get(i).type)
                    &&p.time.equals(myplans.get(i).time)){
                return true;
            }

        }
        return false;
    }

    public void filterDay(String filter) {
        f2 = filter;
        applyFilters();
    }

    public void filterType(String filter) {
        f1 = filter;
        applyFilters();
    }
    private void applyFilters() {
        filterplan.clear();
        for (Plan plan : myplans) {
            boolean matchesType = f1.equalsIgnoreCase("None") || plan.type.equalsIgnoreCase(f1);
            boolean matchesDay = f2.equalsIgnoreCase("None") || plan.time.equalsIgnoreCase(f2);
            if (matchesType && matchesDay) {
                filterplan.add(plan);
            }
        }
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(FavouriteitemBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bind(filterplan.get(position));
    }

    @Override
    public int getItemCount() {
        return filterplan.size();
    }

    class MyViewHolder extends  RecyclerView.ViewHolder{
        private FavouriteitemBinding binding;
        public MyViewHolder(@NonNull FavouriteitemBinding itemView) {
            super(itemView.getRoot());
            binding=itemView;
        }

        public void bind(Plan plan){
            Glide.with(binding.getRoot()).load(plan.strMealThumb).apply( new RequestOptions().placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_foreground)).into(binding.myImage);
            binding.mytextitem.setText(plan.strMeal);
            binding.getRoot().setOnClickListener(view -> {
                clickListner.OnClick(plan);
            });
            binding.dayofmeal.setVisibility(View.VISIBLE);
            binding.textView.setVisibility(View.VISIBLE);
            binding.dayofmeal.setText(plan.time);
            binding.imageButton2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickListner.OnClickDelte(plan);
                }
            });
            binding.typetextview.setText(plan.type);
            binding.typetextview.setVisibility(View.VISIBLE);
            binding.textView13.setVisibility(View.VISIBLE);
        }
    }
}
