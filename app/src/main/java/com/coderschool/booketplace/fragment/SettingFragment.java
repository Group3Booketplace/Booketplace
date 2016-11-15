package com.coderschool.booketplace.fragment;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.coderschool.booketplace.BaseFragmemt;
import com.coderschool.booketplace.R;

/**
 * Created by DatTran on 11/11/16.
 */

public class SettingFragment extends PreferenceFragment {

    public static SettingFragment newInstance() {

        Bundle args = new Bundle();

        SettingFragment fragment = new SettingFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.setting);
    }


}
