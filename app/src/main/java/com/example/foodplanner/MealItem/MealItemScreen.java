package com.example.foodplanner.MealItem;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;

import android.os.IBinder;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.foodplanner.Adapter.InteREc;
import com.example.foodplanner.Adapter.ItemCatigoryRec;
import com.example.foodplanner.App;
import com.example.foodplanner.DataSourse.LocalDataSourse;
import com.example.foodplanner.DataSourse.RemoteDataSourse;
import com.example.foodplanner.Model.Ingradiants;
import com.example.foodplanner.Model.IngradintMeals;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.Plan;
import com.example.foodplanner.R;
import com.example.foodplanner.Repository.MyRepository;
import com.example.foodplanner.Util.MyClickListner;
import com.example.foodplanner.auth.AuthActivity;
import com.example.foodplanner.databinding.BotttommodelsheetBinding;
import com.example.foodplanner.databinding.FragmentMealItemScreenBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.play.integrity.internal.b;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;


public class MealItemScreen extends Fragment implements ImealItemFragment, MyClickListner {
    FragmentMealItemScreenBinding binding;
    InteREc rec;
    MealItemPresenter presenter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding= FragmentMealItemScreenBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }
    boolean isfav=false;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
          presenter= new MealItemPresenter(this, MyRepository.getInstance(LocalDataSourse.getInstance(this.getActivity().getApplication()), RemoteDataSourse.getInstance()));
          App.Login_State.observe(getViewLifecycleOwner(),e-> {
              email = requireActivity().getSharedPreferences("user", Context.MODE_PRIVATE).getString("user", null);
          });
            if (MealItemScreenArgs.fromBundle(getArguments()).getMeal().strInstructions!=null)
            OnSucess(MealItemScreenArgs.fromBundle(getArguments()).getMeal());
        else presenter.loadMealById(MealItemScreenArgs.fromBundle(getArguments()).getMeal());
        presenter.checkinDatabase(MealItemScreenArgs.fromBundle(getArguments()).getMeal().idMeal).observe(getViewLifecycleOwner(),e->{
                Log.i("dasadasd223wee2465478",e+"");
                if (e==1) {
                    isfav = true;
                    binding.floatingActionButton.setImageResource(R.drawable.filledfavouriteicon);
                }else {
                    isfav=false;
                    binding.floatingActionButton.setImageResource(R.drawable.unfilledfavouriteicon);
                }
          });

    }

    @Override
    public void OnSucess(Meal meal) {
        ((AppCompatActivity)requireActivity()).getSupportActionBar().setTitle(meal.strMeal);
        String [] stps=meal.strInstructions.split("\n\r");
        StringBuilder str= new StringBuilder();
        for (int i = 0; i < stps.length; i++) {
           str.append('\n').append("Step ").append(i + 1).append('\n').append(stps[i]).append('\n');
        }
        binding.mealstp.setText(str.toString());
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
                if (email!=null) {
                    meal.email = email;
                    presenter.instertMeal(meal);
                    if (isfav) {
                        Toast.makeText(MealItemScreen.this.requireContext(), getString(R.string.mealallreadysaved), Toast.LENGTH_SHORT).show();

                    }else{
                        Toast.makeText(MealItemScreen.this.requireContext(), getString(R.string.mealsaved), Toast.LENGTH_SHORT).show();

                    }
                    }else{
                    App.Login_State.setValue(App.not_Logged_in);
                    Intent intent=new Intent(requireActivity(), AuthActivity.class);
                    App.naigateback=true;
                    startActivity(intent);
                }
         }

        });
        binding.floatingActionButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (email!=null) {
                    showBottomSheetDialog(meal);

                }else{
                    App.Login_State.setValue(App.not_Logged_in);
                    Intent intent=new Intent(requireActivity(), AuthActivity.class);
                    App.naigateback=true;
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public void OnError(String message) {
        Toast.makeText(this.requireContext(), getString(R.string.error), Toast.LENGTH_SHORT).show();
    }

    String email;
    String dayName="";
    MutableLiveData<String> eee=new MutableLiveData<>("");
    private void showData(){
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(MealItemScreen.this.requireContext(),
                (ss,year1, month1, dayOfMonth) -> {
                    // Update the TextView with the selected date
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

                      eee.setValue(String.format("%d-%d-%d", year1, month1 + 1, dayOfMonth));
                     calendar.set(year1, month1, dayOfMonth);
                     name =calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());
                     dayName = sdf.format(calendar.getTime());
                },
                year, month, day);
            datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis());

        datePickerDialog.show();

    }
    String name="";
    String type="";
    private void showBottomSheetDialog(Meal meal) {
        // Create the BottomSheetDialog
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this.requireContext());
        bottomSheetDialog.setCancelable(false);
        // Inflate the layout
        BotttommodelsheetBinding bottomSheetView = BotttommodelsheetBinding.inflate(getLayoutInflater());
        // Set the content view
        bottomSheetDialog.setContentView(bottomSheetView.getRoot());

        // Setup any event listeners
        String Data;

        bottomSheetView.chipGroup.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(@NonNull ChipGroup group, int checkedId) {
                if (bottomSheetView.brealfast.getId()==checkedId){
                    type="Breakfast";
                }else if (bottomSheetView.lunch.getId()==checkedId){
                    type="Lunch";
                }else if (bottomSheetView.dinner.getId()==checkedId){
                    type="Dinner";
                }else{
                    type="";
                }
            }
        });
        bottomSheetView.button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (eee.getValue().isEmpty()){
                    Toast.makeText(MealItemScreen.this.requireContext(), getString(R.string.pleasechooseday), Toast.LENGTH_SHORT).show();
                    return;
                }
                if (type.isEmpty()){
                    Toast.makeText(MealItemScreen.this.requireContext(), getString(R.string.pleasechoosetype), Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!eee.getValue().isEmpty()&&!type.isEmpty()){
                    Plan plan=copyMealToPlan(meal);
                    plan.Day=dayName;
                    presenter.savePlan(plan);
                    Toast.makeText(MealItemScreen.this.requireContext(), getString(R.string.plansaved), Toast.LENGTH_SHORT).show();

                }
                eee.setValue("");
                type="";
                bottomSheetDialog.dismiss(); // Dismiss the bottom sheet
            }
        });
        bottomSheetView.button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            showData();
            }
        });
        eee.observe(this,e-> {
            bottomSheetView.MyData.setText(e);
        });
        bottomSheetView.button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("dawee2465478",eee+"  "+type);
                bottomSheetDialog.dismiss(); // Dismiss the bottom sheet
                eee.setValue("");
                type="";
            }
        });
        bottomSheetDialog.show();
    }
    private Plan copyMealToPlan(Meal meal) {
        Plan plan = new Plan();

        // Get all declared fields from the Meal class
        Field[] fields = Meal.class.getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true); // Allow access to private fields
            try {
                // Copy each field value from Meal to Plan
                field.set(plan, field.get(meal));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        plan.email=email;
        plan.type = type;  // Or assign the correct value = "Monday";
        plan.time=name;
        return plan;
    }
}