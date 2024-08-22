package com.example.foodplanner.SearchScreen;

import android.app.Application;

import com.example.foodplanner.App;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Repository.MyRepository;

import java.util.ArrayList;

public class SearchPresenter implements IsearchPresenter,IsearchPresenter.Comm{
    private static SearchPresenter searchPresenter;
    private static MyRepository repository;
    private IsearchPresenter.Comm comm;
    public String f1;
    public String f2;
    public String f3;

    public String query;
    @Override
    public void search() {
        repository.search(query,f1,f2);
    }

    public static final String TAG = "SearchPresenter";
    private SearchPresenter(Application application){
        repository= MyRepository.getInstance(this,application,TAG);
    }
    public static SearchPresenter getInstance(IsearchPresenter.Comm comm,Application application) {
        if (searchPresenter == null){
            searchPresenter = new SearchPresenter(application);

        }
        searchPresenter.comm=comm;
        return searchPresenter ;
    }

    @Override
    public void dataArrivedSearch(ArrayList<Meal> result) {
        comm.dataArrivedSearch(result);
    }
}
