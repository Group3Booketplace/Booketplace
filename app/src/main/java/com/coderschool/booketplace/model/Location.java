package com.coderschool.booketplace.model;

import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.PropertyName;

/**
 * Created by DatTran on 11/11/16.
 */

public class Location {
    @PropertyName("lat")
    private double lat;

    @PropertyName("lon")
    private double lon;

    public Location() {}

    public Location(LatLng latLng) {
        this.lat = latLng.latitude;
        this.lon = latLng.longitude;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }
}
