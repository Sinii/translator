package com.titmouse.anton.translator.ui;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
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


public class TranslateFragment extends MvpFragment<TranslateView, TranslatePresenter> implements TranslateView {
	
	private TextView mTranslateTextView;
	private ImageView mFavoriteImage;
	private Translate mTranslate = new Translate();
	private int mLanguageSelect = 3;
	private Boolean mFavorite = false;
	
	private static final int ENGLISH_SPINNER = 3;
	
	
	public TranslateFragment() {
	
	}
	
	@NonNull
	@Override
	public TranslatePresenter createPresenter() {
		return new TranslatePresenterImpl(getContext());
	}
	
	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
		final Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		final View rootView = inflater.inflate(R.layout.fragment_translate, container, false);
		
		mTranslate.setFavorite(false);
		
		final Spinner spinner = rootView.findViewById(R.id.translate_language_spinner);
		final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
			R.array.language_name_array, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		spinner.setAdapter(adapter);
		spinner.setSelection(ENGLISH_SPINNER);
		
		spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			
			@Override
			public void onItemSelected(final AdapterView<?> parent, final View view, final int position, final long id) {
				mLanguageSelect = position;
			}
			
			@Override
			public void onNothingSelected(final AdapterView<?> parent) {
				
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
				} else {
					mFavoriteImage.setImageDrawable(getResources().getDrawable(R.drawable.favorite_0));
				}
				if (mTranslate != null && mTranslate.getTranslate() != null && !mTranslate.getTranslate().isEmpty()) {
					presenter.buttonNewSaveStatus(mTranslate);
				}
			}
		});
		mTranslateTextView = rootView.findViewById(R.id.translate_translate_text_view);
		final EditText translateEditText = rootView.findViewById(R.id.translate_translate_edit_text);
		translateEditText.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void beforeTextChanged(final CharSequence s, final int start, final int count, final int after) {
				
			}
			
			@Override
			public void onTextChanged(final CharSequence s, final int start, final int before, final int count) {
				mFavorite = false;
				mFavoriteImage.setImageDrawable(getResources().getDrawable(R.drawable.favorite_0));
			}
			
			@Override
			public void afterTextChanged(final Editable s) {
				if (s.toString().length() > 0) {
					presenter.onTranslateTextChanged(s.toString(), getResources().getStringArray(R.array.language_value_array)[mLanguageSelect], mFavorite);
				}
			}
		});
		return rootView;
	}
	
	
	@Override
	public void showTranslate(final Translate translate) {
		mTranslateTextView.setText(translate.getTranslate());
		mTranslate = translate;
		
		
	}
	
	@Override
	public void showNewSaveStatus(final Boolean saved) {
		if (saved) {
			mFavoriteImage.setImageDrawable(getResources().getDrawable(R.drawable.favorite_1));
		} else {
			mFavoriteImage.setImageDrawable(getResources().getDrawable(R.drawable.favorite_0));
		}
		
	}
	
	@Override
	public void showError(final String text) {
		Toast.makeText(getContext(), R.string.error_connection, Toast.LENGTH_SHORT).show();
	}
	
}
