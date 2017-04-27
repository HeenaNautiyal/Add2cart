package com.bizhawkz.add2cart;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Heena on 3/24/2017.
 */
public class Country implements Parcelable {

    String code = "";
    String name = "";
    String continent = "";
    String region = "";
    int population = 0;
    int listPosition = 0;

    public Country(String code, String name, String continent, String region, int population) {
        super();
        this.code = code;
        this.name = name;
        this.continent = continent;
        this.region = region;
        this.population = population;
    }

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getContinent() {
        return continent;
    }
    public void setContinent(String continent) {
        this.continent = continent;
    }
    public String getRegion() {
        return region;
    }
    public void setRegion(String region) {
        this.region = region;
    }
    public int getPopulation() {
        return population;
    }
    public void setPopulation(int population) {
        this.population = population;
    }


    public int getListPosition() {
        return listPosition;
    }

    public void setListPosition(int listPosition) {
        this.listPosition = listPosition;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(this.code);
        dest.writeString(this.name);
        dest.writeString(this.region);
        dest.writeString(this.continent);
        dest.writeInt(this.population);
        dest.writeInt(this.listPosition);

    }

    public static final Parcelable.Creator<Country> CREATOR
            = new Parcelable.Creator<Country>() {
        public Country createFromParcel(Parcel in) {
            return new Country(in);
        }

        public Country[] newArray(int size) {
            return new Country[size];
        }
    };

    private Country(Parcel in) {
        this.code = in.readString();
        this.name = in.readString();
        this.region = in.readString();
        this.continent = in.readString();
        this.population = in.readInt();
        this.listPosition = in.readInt();
    }
}
