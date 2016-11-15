package com.coderschool.booketplace.api;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.coderschool.booketplace.utils.PermissionUtils;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderApi;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import static com.google.android.gms.location.LocationServices.FusedLocationApi;

/**
 * Singleton
 * Created by DatTran on 11/11/16.
 */

public class GoogleApi implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {
    private static final String TAG = GoogleApi.class.getSimpleName();
    private static volatile GoogleApi sInstance;

    private GoogleApiClient mApiClient;
    private Location mLocation;
    private Context mContext;

    public static GoogleApi getInstance(Context context) {
        if (sInstance == null) {
            synchronized (TAG) {
                if (sInstance == null) {
                    sInstance = new GoogleApi(context);
                }
            }
        }
        return sInstance;
    }

    private GoogleApi(Context context) {
        mContext = context;
        mApiClient = new GoogleApiClient.Builder(context)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        try {
            mLocation = LocationServices.FusedLocationApi.getLastLocation(mApiClient);
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    public void updateLocation() {
        try {
            LocationServices.FusedLocationApi.requestLocationUpdates(mApiClient, request(), mLocationListener);
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    public void connect() {
        mApiClient.connect();
    }

    public void disconnect() {
        mApiClient.disconnect();
    }

    private LocationRequest request() {
        return LocationRequest.create()
                .setNumUpdates(1)
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

    }

    private LocationListener mLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            mLocation = location;
        }
    };
}
