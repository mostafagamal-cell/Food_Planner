package com.example.foodplanner.SearchScreen;

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

import com.example.foodplanner.Adapter.SearchRecAdp;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.R;
import com.example.foodplanner.Repository.MyRepository;
import com.example.foodplanner.Util.MyClickListner;
import com.example.foodplanner.databinding.FragmentSearchBinding;

import java.util.ArrayList;


public class SearchFrag extends Fragment implements IsearchPresenter.Comm, MyClickListner {
    SearchPresenter presenter;
    SearchRecAdp adp;
    FragmentSearchBinding fragmentSearchBinding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         fragmentSearchBinding= FragmentSearchBinding.inflate(inflater);
        return fragmentSearchBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
          presenter=SearchPresenter.getInstance(this,this.requireActivity().getApplication());
          if (presenter.f1==null&&presenter.f2==null){
              presenter.f1=getString(R.string.Non);
              presenter.f2=getString(R.string.Non);
              presenter.f3=getString(R.string.Non);
              presenter.query="";
          }
          ArrayAdapter<String>areas =new ArrayAdapter<>(this.requireActivity(), android.R.layout.simple_spinner_dropdown_item, MyRepository.areas);
          fragmentSearchBinding.Areasspinner.setAdapter(areas);
          fragmentSearchBinding.Areasspinner.setSelection(MyRepository.areas.indexOf(presenter.f1));
        ArrayAdapter<String>categories =new ArrayAdapter<>(this.requireActivity(), android.R.layout.simple_spinner_dropdown_item, MyRepository.all_cat);
        fragmentSearchBinding.Catspinner.setAdapter(categories);
        fragmentSearchBinding.Catspinner.setSelection(MyRepository.all_cat.indexOf(presenter.f2));
        ArrayAdapter<String>ing =new ArrayAdapter<>(this.requireActivity(), android.R.layout.simple_spinner_dropdown_item, MyRepository.all_ing);
        fragmentSearchBinding.Ingspinner.setAdapter(ing);
        fragmentSearchBinding.Ingspinner.setSelection(MyRepository.all_ing.indexOf(presenter.f3));
        requireActivity().runOnUiThread(new Runnable() {

            @Override
            public void run() {
                fragmentSearchBinding.Catspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        presenter.f2=MyRepository.all_cat.get(i);
                        Log.i("asddsadasdasdasdd f3",presenter.f3);

                        presenter.search();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
                fragmentSearchBinding.Ingspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        presenter.f3=MyRepository.all_ing.get(i);
                        Log.i("asddsadasdasdasdd f3",presenter.f3);

                        presenter.search();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

                fragmentSearchBinding.Areasspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        presenter.f1=MyRepository.areas.get(i);
                        Log.i("asddsadasdasdasdd f1",presenter.f1);
                        presenter.search();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
                adp=new SearchRecAdp(SearchFrag.this);
                fragmentSearchBinding.SearchRec.setAdapter(adp);
                fragmentSearchBinding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String s) {

                            presenter.query = s;
                        presenter.search();
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String s) {

                            presenter.query = s;
                        presenter.search();
                        return false;
                    }
                });
            }
        });

    }

    @Override
    public void dataArrivedSearch(ArrayList<Meal> result) {
        requireActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adp.setData(result);
                fragmentSearchBinding.SearchRec.setAdapter(adp);
            }
        });
    }

    @Override
    public void OnClick(Meal meal) {
       NavHostFragment.findNavController(this).navigate(SearchFragDirections.actionSearchFragToMealItemScreen(meal));

    }


}