package com.example.foodplanner.Model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Category implements Parcelable {
    public String idCategory;
    public String strCategory;
    public String strCategoryThumb;
    public String strCategoryDescription;

    // Default constructor
    public Category() {}

    // Constructor to create from Parcel
    protected Category(Parcel in) {
        idCategory = in.readString();
        strCategory = in.readString();
        strCategoryThumb = in.readString();
        strCategoryDescription = in.readString();
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int flags) {
        parcel.writeString(idCategory);
        parcel.writeString(strCategory);
        parcel.writeString(strCategoryThumb);
        parcel.writeString(strCategoryDescription);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<Category> CREATOR = new Parcelable.Creator<Category>() {
        @Override
        public Category createFromParcel(Parcel in) {
            return new Category(in);
        }

        @Override
        public Category[] newArray(int size) {
            return new Category[size];
        }
    };
}
