package com.example.foodplanner;

import org.junit.Test;

import static org.junit.Assert.*;

import com.example.foodplanner.DataSourse.RemoteDataSourse;
import com.example.foodplanner.Model.Categories;
import com.example.foodplanner.Model.Meals;
import com.example.foodplanner.Repository.Irepo;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

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
        System.out.println(getDateOfDayInCurrentWeek("Thursday"));
    }

    public String getDateOfDayInCurrentWeek(String dayName) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

        for (int i = 0; i < 7; i++) {
            String currentDay = calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.ENGLISH);
            if (currentDay.equalsIgnoreCase(dayName)) {
                int t=7;
                for (int j = 0; j < 7; j++) {
                    calendar.add(Calendar.DAY_OF_WEEK,-1);
                }
                return sdf.format(calendar.getTime());
            }
            calendar.add(Calendar.DAY_OF_YEAR, 1);
        }
        return null;
    }
}