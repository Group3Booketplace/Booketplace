package com.coderschool.booketplace.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.coderschool.booketplace.BaseActivity;
import com.coderschool.booketplace.R;
import com.coderschool.booketplace.api.FirebaseApi;
import com.coderschool.booketplace.models.Book;
import com.coderschool.booketplace.utils.BitmapUtils;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by dattran on 11/24/16.
 */

public class NewPostActivity extends BaseActivity {
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
    @BindView(R.id.etBookLocation)
    EditText etLocation;
    @BindView(R.id.spCategory)
    Spinner spCategory;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private Bitmap mSelectedBitmap;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
    }

    public static Intent getIntent(Context context) {
        return new Intent(context, NewPostActivity.class);
    }

    @OnClick(R.id.ivBook)
    public void choosePicture(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        if (intent.resolveActivity(this.getPackageManager()) != null) {
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

                    mSelectedBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                    ivManga.setImageBitmap(BitmapUtils.resize(mSelectedBitmap, this));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @OnClick(R.id.btn_sell)
    public void onSell(View view) {
        if (isFormValid()) {
            Book book = new Book(
                    etName.getText().toString(),
                    etAuthor.getText().toString(),
                    spCategory.getSelectedItem().toString(),
                    etPrice.getText().toString(),
                    spCurrency.getSelectedItem().toString(),
                    etDescription.getText().toString(),
                    spCondition.getSelectedItem().toString(),
                    etLocation.getText().toString(),
                    FirebaseApi.getInstance().getUser().getUid()
            );
            FirebaseApi.getInstance().writeNewBook(book, mSelectedBitmap, new FirebaseApi.FirebaseResultListener() {
                @Override
                public void onSuccess() {
                    // TODO: finish uploading
                   finish();
                }

                @Override
                public void onFail() {
                    // TODO: prompt error
                    Toast.makeText(NewPostActivity.this, "Can't upload", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private boolean isFormValid() {
        boolean result = true;
        if (mSelectedBitmap == null) {
            Toast.makeText(NewPostActivity.this, "You must choose a picture", Toast.LENGTH_SHORT).show();
            result = false;
        }

        if (etName.getText().toString().isEmpty()) {
            etName.setError(getString(R.string.require));
            result = false;
        }

//        if (etAuthor.getText().toString().isEmpty()) {
//            etAuthor.setError(getString(R.string.require));
//            result = false;
//        }

        if (etPrice.getText().toString().isEmpty()) {
            etPrice.setError(getString(R.string.require));
            result = false;
        }

        if (etDescription.getText().toString().isEmpty()) {
            etDescription.setError(getString(R.string.require));
            result = false;
        }

//        if (etLocation.getText().toString().isEmpty()) {
//            etLocation.setError(getString(R.string.require));
//            result = false;
//        }

        return result;
    }
}
