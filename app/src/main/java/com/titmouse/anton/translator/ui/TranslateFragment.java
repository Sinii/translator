package com.titmouse.anton.translator.ui;



import android.os.Bundle;

import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.hannesdorfmann.mosby3.mvp.MvpFragment;
import com.titmouse.anton.translator.R;
import com.titmouse.anton.translator.oth.Translate;
import com.titmouse.anton.translator.translate.presenter.TranslatePresenter;
import com.titmouse.anton.translator.translate.presenter.TranslatePresenterImpl;
import com.titmouse.anton.translator.translate.view.TranslateView;


public class TranslateFragment extends MvpFragment<TranslateView, TranslatePresenter> implements  TranslateView {

    private EditText mTranslateEditText;
    private TextView mTranslateTextView;
    private ImageView mFavoriteImage;
    private Spinner mSpinner;
    private Translate mTranslate = new Translate();
    private int mLanguageSelect = 3;
    private Boolean mFavorite = false;

    private static final int ENGLISH_SPINNER = 3;


    public TranslateFragment() {

    }

    @NonNull
    @Override public TranslatePresenter createPresenter() {
        return new TranslatePresenterImpl(getContext());
    }

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                       Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_translate, container, false);

        mTranslate.setFavorite(false);

        mSpinner = (Spinner) rootView.findViewById(R.id.translate_language_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.language_name_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mSpinner.setAdapter(adapter);
        mSpinner.setSelection(ENGLISH_SPINNER);

        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mLanguageSelect = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        TextView mHyperLink = (TextView) rootView.findViewById(R.id.translate_hyperlink_text_view);
        mHyperLink.setMovementMethod(LinkMovementMethod.getInstance());
        mHyperLink.setText(Html.fromHtml(getResources().getString(R.string.translate_by)));

        mFavoriteImage = (ImageView) rootView.findViewById(R.id.translate_favorite_imageview);
        mFavoriteImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mFavorite = !mFavorite;
                mTranslate.setFavorite(mFavorite);
                if (mTranslate.getFavorite()) {
                    mFavoriteImage.setImageDrawable(getResources().getDrawable(R.drawable.favorite_1));
                } else  {
                    mFavoriteImage.setImageDrawable(getResources().getDrawable(R.drawable.favorite_0));
                }
                if (mTranslate!=null && mTranslate.getTranslate() !=null && !mTranslate.getTranslate().isEmpty()) presenter.buttonNewSaveStatus(mTranslate);
            }
        });
        mTranslateTextView = (TextView) rootView.findViewById(R.id.translate_translate_text_view);
        mTranslateEditText = (EditText) rootView.findViewById(R.id.translate_translate_edit_text);
        mTranslateEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mFavorite = false;
                mFavoriteImage.setImageDrawable(getResources().getDrawable(R.drawable.favorite_0));
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length()>0) {
                    presenter.onTranslateTextChanged(s.toString(), getResources().getStringArray(R.array.language_value_array)[mLanguageSelect], mFavorite);
                }
            }
        });
        return rootView;
    }


    @Override public void showTranslate (Translate translate) {
        mTranslateTextView.setText(translate.getTranslate());
        mTranslate = translate;


    }

    @Override public void showNewSaveStatus(Boolean saved) {
        if (saved) mFavoriteImage.setImageDrawable(getResources().getDrawable(R.drawable.favorite_1)); else
            mFavoriteImage.setImageDrawable(getResources().getDrawable(R.drawable.favorite_0));

    }

    @Override
    public void showError(String text) {
        Toast.makeText(getContext(), R.string.error_connection, Toast.LENGTH_SHORT).show();
    }

}
