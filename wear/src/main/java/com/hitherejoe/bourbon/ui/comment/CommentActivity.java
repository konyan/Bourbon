package com.hitherejoe.bourbon.ui.comment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.wearable.view.DotsPageIndicator;
import android.support.wearable.view.GridViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.hitherejoe.bourbon.R;
import com.hitherejoe.bourbon.common.data.model.Comment;
import com.hitherejoe.bourbon.common.data.model.Shot;
import com.hitherejoe.bourbon.common.ui.browse.BrowseMvpView;
import com.hitherejoe.bourbon.common.ui.browse.BrowsePresenter;
import com.hitherejoe.bourbon.common.ui.shot.ShotMvpView;
import com.hitherejoe.bourbon.common.ui.shot.ShotPresenter;
import com.hitherejoe.bourbon.ui.base.BaseActivity;
import com.hitherejoe.bourbon.ui.browse.BrowseAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import timber.log.Timber;

public class CommentActivity extends BaseActivity implements ShotMvpView {

    public static final String EXTRA_SHOT =
            "com.hitherejoe.bourbon.ui.comment.CommentActivity.EXTRA_SHOT";

    @Bind(R.id.pager_shots)
    ViewPager mShotsPager;
    @Bind(R.id.page_indicator)
    DotsPageIndicator mPageIndicator;
    @Bind(R.id.progress)
    ProgressBar mProgress;
    @Bind(R.id.layout_error)
    View mErrorView;
    @Bind(R.id.text_error)
    TextView mErrorText;
    @Bind(R.id.image_error)
    ImageView mErrorImage;

    @Inject
    ShotPresenter mShotPresenter;

    private CommentsAdapter mCommentAdapter;

    public static Intent newIntent(Context context, Shot shot) {
        Intent intent = new Intent(context, CommentActivity.class);
        intent.putExtra(EXTRA_SHOT, shot);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        ButterKnife.bind(this);
        activityComponent().inject(this);

        Shot shot = getIntent().getParcelableExtra(EXTRA_SHOT);

        if (shot == null) {
            throw new IllegalArgumentException("CommentActivity requires a shot instance!");
        }

        mShotPresenter.attachView(this);
        mCommentAdapter = new CommentsAdapter(this);
        mShotsPager.setAdapter(mCommentAdapter);
        //mPageIndicator.setPager(mShotsPager);
        mShotPresenter.getComments(shot.id);
    }

    @Override
    public void showProgress() {
        mProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgress.setVisibility(View.GONE);
    }

    @Override
    public void showComments(List<Comment> comments) {
        mCommentAdapter.setComments(comments);
        mCommentAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError() {
        Timber.e("ERROR");
    }

    @Override
    public void showEmptyComments() {

    }

}