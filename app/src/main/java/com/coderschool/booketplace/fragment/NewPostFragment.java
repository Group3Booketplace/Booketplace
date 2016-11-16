package com.coderschool.booketplace.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.coderschool.booketplace.BaseFragmemt;
import com.coderschool.booketplace.R;
import com.coderschool.booketplace.api.FirebaseApi;
import com.coderschool.booketplace.models.Book;
import com.coderschool.booketplace.utils.BitmapUtils;
import com.coderschool.booketplace.utils.PermissionUtils;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by DatTran on 11/11/16.
 */

public class NewPostFragment extends BaseFragmemt {
    private static final int RC_GALLERY = 1;

    @BindView(R.id.iv_manga)
    ImageView ivManga;
    @BindView(R.id.et_manga_name)
    EditText etName;
    @BindView(R.id.et_manga_author)
    EditText etAuthor;
    @BindView(R.id.et_manga_price)
    EditText etPrice;
    @BindView(R.id.et_manga_description)
    EditText etDescription;
    @BindView(R.id.sp_condition)
    Spinner spCondition;

    private Bitmap mSelectedBitmap;


    public static NewPostFragment newInstance() {
        
        Bundle args = new Bundle();
        
        NewPostFragment fragment = new NewPostFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_new_post, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        PermissionUtils.requestLocation(mActivity);
        PermissionUtils.requestCamera(mActivity);
    }

    @OnClick(R.id.iv_manga)
    public void choosePicture(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        if (intent.resolveActivity(mActivity.getPackageManager()) != null) {
            startActivityForResult(intent, RC_GALLERY);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_GALLERY) {
            if (data != null) {
                Uri uri = data.getData();
                try {
                    mSelectedBitmap = MediaStore.Images.Media.getBitmap(mActivity.getContentResolver(), uri);
                    ivManga.setImageBitmap(BitmapUtils.resize(mSelectedBitmap, mActivity));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @OnClick(R.id.btn_sell)
    public void onSell(View view) {
        // TODO: post to firebase
        Book book = new Book(
                etAuthor.getText().toString(),
                spCondition.getSelectedItem().toString(),
                etDescription.getText().toString(),
                etName.getText().toString(),
                etPrice.getText().toString());
        FirebaseApi.getInstance().writeNewBook(book, mSelectedBitmap, new FirebaseApi.FirebaseResultListener() {
            @Override
            public void onSuccess() {
                // TODO: finish uploading
            }

            @Override
            public void onFail() {
                // TODO: prompt error
            }
        });
    }

}
