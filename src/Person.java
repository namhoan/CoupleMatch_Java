/**
 Namho An
 3/25/2015
 PA05
 */
import java.util.*;
//The person class to implement to StableMarriage class 
    public class Person {
       public static final int NOBODY = -1; //used public to allow pass the value
       private List<Integer> preferences;
       private List<Integer> oldPreferences;
       private String name;
       private int partner;
//constructors that receive name as a parameters and set two preferences as ArrayList
    public Person(String name) {
        this.name = name;
        preferences = new ArrayList<Integer>();
        oldPreferences = new ArrayList<Integer>();
    }
//this will make partner value -1 which is nobody
    public void erasePartner() {
        partner = NOBODY;
    }
//if men or women has a partner, the value of partner is not -1
    public boolean hasPartner() {
        return partner != NOBODY;
    }

    public int getPartner() {
        return partner;
    }

    public void setPartner(int partner) {
        this.partner = partner;
    }

    public String getName() {
        return name;
    }

    public boolean hasChoices() {
        return !preferences.isEmpty();
    }
//having first choice by receiving index 0 in ArrayList
    public int getFirstChoice() {
        return preferences.get(0);
    }
//add each person in the ArrayList of preference and oldPreference
    public void addChoice(int person) {
        preferences.add(person);
        oldPreferences.add(person);
    }
//for get choices of first preference
    public List<Integer> getChoices() {
        return preferences;
    }
//rank each of the partner
    public int getPartnerRank() {
        return oldPreferences.indexOf(partner) + 1;
    }
}