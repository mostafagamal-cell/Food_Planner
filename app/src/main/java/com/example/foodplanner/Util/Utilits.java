package com.example.foodplanner.Util;

public class Utilits {
    public  static   String emailPattern = "^[a-zA-Z0-9_\\-.]+@[a-zA-Z0-9\\-]+\\.[a-zA-Z]{2,}$";
    public static     String passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,20}$";
    public static final int GgetMealByletter=0;
    public static final int Gbyname=1;
    public static final int GgetRandommeal=2;
    public static final int Ggetcategories=3;
    public static  final int GfilterBycategory=4;
    public static  final int GfilterByarea=5;
    public static  final int GgetMealByid=6;
    public static final int GfilterByingredient=7;
    public static final int GgetListOfcategories=8;
    public static final int GgetListOfarea=9;
    public static final int GgetListOfingredients=10;
    public static final String USA="https://cdn.britannica.com/33/4833-004-828A9A84/Flag-United-States-of-America.jpg";
    public static final String EG="https://flagpedia.net/data/flags/w580/eg.webp";
}
