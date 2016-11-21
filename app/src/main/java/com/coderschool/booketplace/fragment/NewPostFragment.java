package com.coderschool.booketplace.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.coderschool.booketplace.BaseFragmemt;
import com.coderschool.booketplace.R;
import com.coderschool.booketplace.api.FirebaseApi;
import com.coderschool.booketplace.models.Book;
import com.coderschool.booketplace.utils.BitmapUtils;
import com.coderschool.booketplace.utils.PermissionUtils;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Post new book to database
 * Created by DatTran on 11/11/16.
 */

public class NewPostFragment extends BaseFragmemt {
    private static final int RC_GALLERY = 1;

    @BindView(R.id.ivBook)
    ImageView ivManga;
    @BindView(R.id.etBookName)
    EditText etName;
    @BindView(R.id.etBookAuthor)
    EditText etAuthor;
    @BindView(R.id.etBookPrice)
    EditText etPrice;
    @BindView(R.id.spCurrency)
    Spinner spCurrency;
    @BindView(R.id.etBookDescription)
    EditText etDescription;
    @BindView(R.id.spCondition)
    Spinner spCondition;


    private ArrayList<Bitmap> mSelectedBitmaps;


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
        mSelectedBitmaps = new ArrayList<>();
    }

    @OnClick(R.id.ivBook)
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

                    mSelectedBitmaps.add(MediaStore.Images.Media.getBitmap(mActivity.getContentResolver(), uri));
                    ivManga.setImageBitmap(BitmapUtils.resize(mSelectedBitmaps.get(0), mActivity));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @OnClick(R.id.btn_sell)
    public void onSell(View view) {
        Book book = new Book(
                etAuthor.getText().toString(),
                spCondition.getSelectedItem().toString(),
                etDescription.getText().toString(),
                etName.getText().toString(),
                etPrice.getText().toString(),
                spCurrency.getSelectedItem().toString(),
                FirebaseApi.getInstance().getUser().getUid());
        FirebaseApi.getInstance().writeNewBook(book, mSelectedBitmaps, new FirebaseApi.FirebaseResultListener() {
            @Override
            public void onSuccess() {
                // TODO: finish uploading
                mActivity.getSupportFragmentManager().beginTransaction().remove(NewPostFragment.this).commit();
            }

            @Override
            public void onFail() {
                // TODO: prompt error
                Toast.makeText(mActivity, "Can't upload", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
