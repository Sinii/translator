package com.titmouse.anton.translator.ui;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hannesdorfmann.mosby3.mvp.MvpFragment;
import com.titmouse.anton.translator.R;
import com.titmouse.anton.translator.database.entity.TranslateEntity;
import com.titmouse.anton.translator.history.adapter.HistoryAdapter;
import com.titmouse.anton.translator.history.presenter.HistoryPresenter;
import com.titmouse.anton.translator.history.presenter.HistoryPresenterImpl;
import com.titmouse.anton.translator.history.view.HistoryView;
import com.titmouse.anton.translator.oth.RecyclerTouchListener;
import com.titmouse.anton.translator.oth.Translate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anton on 23.04.17.
 */

public class HistoryFragment extends MvpFragment<HistoryView, HistoryPresenter> implements HistoryView, RecyclerTouchListener.ClickListener, FragmentLifecycle {

    private RecyclerView mRecyclerView;
    private HistoryAdapter mAdapter;
    private List<Translate> translateEntityList = new ArrayList<>();
    private TextView mEmptyText;


    @NonNull
    @Override
    public HistoryPresenter createPresenter() {
        return new HistoryPresenterImpl();
    }



    public HistoryFragment() {

    }

    @Override public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    @Override public void onResume() {


        presenter.onResumeHistory();
        Log.d("historyFragment", "onResume");

        super.onResume();
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_history, container, false);


        mEmptyText = (TextView) rootView.findViewById(R.id.history_empty_text);

        mAdapter = new HistoryAdapter(translateEntityList);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.history_recyclerview);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(), mRecyclerView, this));
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setNestedScrollingEnabled(true);


        return rootView;
    }

    @Override
    public void showTranslateList(List<Translate> translateList) {

        if (translateList.size()>0) mEmptyText.setVisibility(View.GONE); else mEmptyText.setVisibility(View.VISIBLE);
        translateEntityList = translateList;
        mAdapter.updateList(translateList);



    }

    @Override
    public void onClick(View view, int position) {
        //nothing to do here
    }

    @Override
    public void onLongClick(View view, final int position) {
        if (position>=0) {
            DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which){
                        case DialogInterface.BUTTON_POSITIVE:
                            presenter.deleteTranslate(translateEntityList.get(position));
                            break;
                        case DialogInterface.BUTTON_NEGATIVE:

                            break;

                    }
                }
            };

            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setMessage(R.string.delete_history_element).setPositiveButton(R.string.yes, dialogClickListener)
                    .setNegativeButton(R.string.no, dialogClickListener).show();

        }



    }

    @Override
    public void onOpenFragment() {
        if (presenter!=null) {
            presenter.onResumeHistory();
        }
    }
}
