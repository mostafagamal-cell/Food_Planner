package com.example.foodplanner.SearchScreen;

import static com.example.foodplanner.Repository.MyRepository.all_meals;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.foodplanner.Adapter.SearchRecAdp;
import com.example.foodplanner.DataSourse.LocalDataSourse;
import com.example.foodplanner.DataSourse.RemoteDataSourse;
import com.example.foodplanner.Model.Categories;
import com.example.foodplanner.Model.Countries;
import com.example.foodplanner.Model.Ingradiants;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.R;
import com.example.foodplanner.Repository.MyRepository;
import com.example.foodplanner.Util.MyClickListner;
import com.example.foodplanner.databinding.FragmentSearchBinding;

import java.util.ArrayList;


public class SearchFrag extends Fragment implements IsearchFragment ,MyClickListner {
    SearchPresenter presenter;
    SearchRecAdp adp;
    FragmentSearchBinding db;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         db= db.inflate(inflater);
        return db.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter=new SearchPresenter(this,MyRepository.getInstance(LocalDataSourse.getInstance(this.requireActivity().getApplication()), RemoteDataSourse.getInstance()));

        if(savedInstanceState!=null){
                f1=savedInstanceState.getString("f1");
                f2=savedInstanceState.getString("f2");
                f3=savedInstanceState.getString("f3");
                query=savedInstanceState.getString("query");
            }else{
            f1=getString(R.string.Non);
            f2=getString(R.string.Non);
            f3=getString(R.string.Non);
            query="";
            presenter.gen();
            }
          ArrayAdapter<String>areas =new ArrayAdapter<>(this.requireActivity(), android.R.layout.simple_spinner_dropdown_item, MyRepository.areas);
          db.Areasspinner.setAdapter(areas);
          db.Areasspinner.setSelection(MyRepository.areas.indexOf(f1));
        ArrayAdapter<String>categories =new ArrayAdapter<>(this.requireActivity(), android.R.layout.simple_spinner_dropdown_item, MyRepository.all_cat);
        db.Catspinner.setAdapter(categories);
        db.Catspinner.setSelection(MyRepository.all_cat.indexOf(f2));
        ArrayAdapter<String>ing =new ArrayAdapter<>(this.requireActivity(), android.R.layout.simple_spinner_dropdown_item, MyRepository.all_ing);
        db.Ingspinner.setAdapter(ing);
        db.Ingspinner.setSelection(MyRepository.all_ing.indexOf(f3));
        requireActivity().runOnUiThread(new Runnable() {

            @Override
            public void run() {
                db.Catspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        f2=MyRepository.all_cat.get(i);
                        presenter.search(query,f1,f2,f3,getString(R.string.Non));
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
                db.Ingspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        f3=MyRepository.all_ing.get(i);
                        presenter.search(query,f1,f2,f3,getString(R.string.Non));

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

                db.Areasspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        f1=MyRepository.areas.get(i);
                        presenter.search(query,f1,f2,f3,getString(R.string.Non));
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
                adp=new SearchRecAdp(SearchFrag.this);
                db.SearchRec.setAdapter(adp);
                db.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String s) {
                        query = s;
                        presenter.search(query,f1,f2,f3,getString(R.string.Non));
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String s) {

                        query = s;
                        presenter.search(query,f1,f2,f3,getString(R.string.Non));
                        return false;
                    }
                });
            }
        });

    }

    @Override
    public void Sucess(ArrayList<Meal> result) {
        requireActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adp.setData(result);
                db.SearchRec.setAdapter(adp);
            }
        });
    }

    @Override
    public void onSucess(ArrayList<String> ing) {
        ing.add(0,getString(R.string.Non));
        db.Ingspinner.setAdapter(new ArrayAdapter<>(this.requireActivity(), android.R.layout.simple_spinner_dropdown_item, ing));
    }

    @Override
    public void onsUcess(ArrayList<String> ing) {
        ing.add(0,getString(R.string.Non));
        db.Catspinner.setAdapter(new ArrayAdapter<>(this.requireActivity(), android.R.layout.simple_spinner_dropdown_item, ing));

    }

    @Override
    public void onsucess(ArrayList<String> countries) {
        countries.add(0,getString(R.string.Non));
        db.Areasspinner.setAdapter(new ArrayAdapter<>(this.requireActivity(), android.R.layout.simple_spinner_dropdown_item, countries));
    }

    @Override
    public void Fail(String meassage) {
        Toast.makeText(this.requireActivity(),meassage,Toast.LENGTH_LONG).show();
    }

    @Override
    public void OnClick(Meal meal) {
       NavHostFragment.findNavController(this).navigate(SearchFragDirections.actionSearchFragToMealItemScreen(meal));

    }
    String f1,f2,f3,query="";

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("f1",f1);
        outState.putString("f2",f2);
        outState.putString("f3",f3);
        outState.putString("query",query);
    }
}