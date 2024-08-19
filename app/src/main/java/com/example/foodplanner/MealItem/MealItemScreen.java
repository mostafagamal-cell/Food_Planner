package com.example.foodplanner.MealItem;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebViewClient;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.foodplanner.Adapter.InteREc;
import com.example.foodplanner.Adapter.ItemCatigoryRec;
import com.example.foodplanner.Model.Ingradiants;
import com.example.foodplanner.Model.IngradintMeals;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.R;
import com.example.foodplanner.Util.MyClickListner;
import com.example.foodplanner.databinding.FragmentMealItemScreenBinding;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Objects;


public class MealItemScreen extends Fragment implements ImealItemPreseter.ImealScreenComm, MyClickListner {
    FragmentMealItemScreenBinding binding;
    InteREc rec;
    MealItemPresenter presenter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding= FragmentMealItemScreenBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
       // presenter= MealItemPresenter.getInstance(this,this.requireActivity().getApplication());

        dataArrived(MealItemScreenArgs.fromBundle(getArguments()).getMeal());
    }

    @Override
    public void dataArrived(Meal meal) {
        binding.ImageText.setText(meal.strMeal);
        binding.myTextCountry.setText(meal.strArea);
        Glide.with(this)
                .load(meal.strMealThumb)
                .apply( new RequestOptions().placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_foreground))
                .into(binding.myImage);
        binding.TextType.setText(meal.strCategory);
        String id=meal.strYoutube.substring(meal.strYoutube.lastIndexOf("=")+1);
        String frameVideo = "<html><body style=\"margin:0;padding:0;\"><div style=\"position:relative;padding-bottom:56.25%;height:0;overflow:hidden;\"><iframe style=\"position:absolute;top:0;left:0;width:100%;height:100%;\" src=\"https://www.youtube.com/embed/" + id + "\" frameborder=\"0\" allowfullscreen></iframe></div></body></html>";        Log.d("asddddddddddddddddddddd", "dataArrived: "+id);
        binding.webView.getSettings().setJavaScriptEnabled(true);
        binding.webView.setWebViewClient(new WebViewClient());
        binding.webView.loadData(frameVideo, "text/html", "utf-8");
        Ingradiants i =new Ingradiants();
        i.meals=new ArrayList<>();
        Field[] fields = Meal.class.getDeclaredFields();
        for (Field field : fields) {
            if (field.getName().startsWith("strIngredient")){
                try {
                    IngradintMeals meals =new IngradintMeals();
                    field.setAccessible(true);
                    String value = (String) field.get(meal);
                    if (value != null && !value.isEmpty()) {
                        meals.strIngredient=value;
                        i.meals.add(meals);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        rec=new InteREc(this);
        rec.setIngradiants(i);
        binding.ingrrec.setAdapter(rec);
        binding.floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email= requireActivity().getSharedPreferences("user", Context.MODE_PRIVATE).getString("user",null);
                meal.email=email;
                presenter.instertMeal(meal);
            }
        });
    }
}