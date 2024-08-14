package com.example.foodplanner;

import org.junit.Test;

import static org.junit.Assert.*;

import android.util.Log;

import com.example.foodplanner.DataSourse.RemoteDataSourse;
import com.example.foodplanner.Model.Categories;
import com.example.foodplanner.Model.Meals;
import com.example.foodplanner.Util.IremoteDataSource;
import com.example.foodplanner.Util.Irepo;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect()
    {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void test() throws InterruptedException {
        Irepo.Communicator communicator= new Irepo.Communicator() {
            @Override
            public void onDataArrived(Meals meals,int type) {
                System.out.println(meals.meals.size());
            }

            @Override
            public void onError(String message) {
                System.out.println(message);

            }

            @Override
            public void onDataArrived(Categories categories,int type) {
                System.out.println(categories.categories.size());
            }
        };
        RemoteDataSourse remoteDataSource= new RemoteDataSourse(communicator);
        remoteDataSource.getcategories();
        Thread.sleep(9000);
    }
}