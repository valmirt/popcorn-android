package com.torres.valmir.kotlin_mvp_dagger2.ui.main.detail_person;

import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Space;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.torres.valmir.kotlin_mvp_dagger2.R;
import com.torres.valmir.kotlin_mvp_dagger2.adapter.SectionsPagerAdapter;
import com.torres.valmir.kotlin_mvp_dagger2.model.Person;
import com.torres.valmir.kotlin_mvp_dagger2.ui.base.BaseActivity;
import com.torres.valmir.kotlin_mvp_dagger2.ui.main.detail_person.credits.CreditsFragment;
import com.torres.valmir.kotlin_mvp_dagger2.ui.main.detail_person.info_person.InfoPersonFragment;

import java.util.Locale;

import javax.inject.Inject;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.torres.valmir.kotlin_mvp_dagger2.utils.Constants.BASE_URL_IMAGE_W185;
import static com.torres.valmir.kotlin_mvp_dagger2.utils.Constants.PERSON;

public class DetailPersonActivity extends BaseActivity implements DetailPersonContract.View {
    private final static float EXPAND_AVATAR_SIZE_DP = 80f;
    private final static float COLLAPSED_AVATAR_SIZE_DP = 32f;

    @Inject
    DetailPersonContract.Presenter mPresenter;

    private View mContainerView;

    private AppBarLayout mAppBarLayout;
    private CircleImageView mAvatarImageView;
    private TextView mToolbarTextView, mTitleTextView;
    private Space mSpace;
    private Toolbar mToolBar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    private Person person;
    private SectionsPagerAdapter mSectionPagerAdapter;

    private hearsilent.amazingavatar.libs.AppBarStateChangeListener mAppBarStateChangeListener;

    private float[] mAvatarPoint = new float[2], mSpacePoint = new float[2], mToolbarTextPoint =
            new float[2], mTitleTextViewPoint = new float[2];
    private float mTitleTextSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        mPresenter.attach(this);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            person = (Person) bundle.getSerializable(PERSON);
        }

        Window window = this.getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        }

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        findViews();
        setUpViews();

        mSectionPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        fillPages();

        viewPager.setAdapter(mSectionPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
    }

    private void fillPages() {
        if (person != null) {
            Glide.with(this)
                    .load(BASE_URL_IMAGE_W185+person.getProfilePath())
                    .into(mAvatarImageView);

            mTitleTextView.setText(person.getName());

            Fragment fragment1 = new InfoPersonFragment();
            Bundle arg1 = new Bundle();
            arg1.putSerializable(PERSON, person);
            fragment1.setArguments(arg1);
            mSectionPagerAdapter.addPages(fragment1, getString(R.string.title_tab_one));

            Fragment fragment2 = new CreditsFragment();
            fragment2.setArguments(arg1);
            mSectionPagerAdapter.addPages(fragment2, getString(R.string.title_tab_four));
        }
    }

    private void findViews() {
        mContainerView = findViewById(R.id.view_container);
        mAppBarLayout = findViewById(R.id.app_bar);
        mAvatarImageView = findViewById(R.id.imageView_avatar);
        mToolbarTextView = findViewById(R.id.toolbar_title);
        mTitleTextView = findViewById(R.id.textView_title);
        mSpace = findViewById(R.id.space);
        mToolBar = findViewById(R.id.toolbar);
        tabLayout = findViewById(R.id.tab_person);
        viewPager = findViewById(R.id.viewpager_container_person);
    }

    private void setUpViews() {
        mTitleTextSize = mTitleTextView.getTextSize();
        setUpToolbar();
        setUpAmazingAvatar();
    }

    private void setUpToolbar() {
        mAppBarLayout.getLayoutParams().height = hearsilent.amazingavatar.libs.Utils.getDisplayMetrics(this).widthPixels * 9 / 16;
        mAppBarLayout.requestLayout();

        setSupportActionBar(mToolBar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void setUpAmazingAvatar() {
        mAppBarStateChangeListener = new hearsilent.amazingavatar.libs.AppBarStateChangeListener() {

            @Override
            public void onStateChanged(AppBarLayout appBarLayout,
                                       hearsilent.amazingavatar.libs.AppBarStateChangeListener.State state) {
            }

            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onOffsetChanged(hearsilent.amazingavatar.libs.AppBarStateChangeListener.State state, float offset) {
                translationView(offset);
            }
        };
        mAppBarLayout.addOnOffsetChangedListener(mAppBarStateChangeListener);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void translationView(float offset) {
        float newAvatarSize = hearsilent.amazingavatar.libs.Utils.convertDpToPixel(
                EXPAND_AVATAR_SIZE_DP - (EXPAND_AVATAR_SIZE_DP - COLLAPSED_AVATAR_SIZE_DP) * offset,
                this);
        float expandAvatarSize = hearsilent.amazingavatar.libs.Utils.convertDpToPixel(EXPAND_AVATAR_SIZE_DP, this);
        float xAvatarOffset =
                (mSpacePoint[0] - mAvatarPoint[0] - (expandAvatarSize - newAvatarSize) / 2f) *
                        offset;
        // If avatar center in vertical, just half `(expandAvatarSize - newAvatarSize)`
        float yAvatarOffset =
                (mSpacePoint[1] - mAvatarPoint[1] - (expandAvatarSize - newAvatarSize)) * offset;
        mAvatarImageView.getLayoutParams().width = Math.round(newAvatarSize);
        mAvatarImageView.getLayoutParams().height = Math.round(newAvatarSize);
        mAvatarImageView.setTranslationX(xAvatarOffset);
        mAvatarImageView.setTranslationY(yAvatarOffset);

        float newTextSize =
                mTitleTextSize - (mTitleTextSize - mToolbarTextView.getTextSize()) * offset;
        Paint paint = new Paint(mTitleTextView.getPaint());
        paint.setTextSize(newTextSize);
        float newTextWidth = hearsilent.amazingavatar.libs.Utils.getTextWidth(paint, mTitleTextView.getText().toString());
        paint.setTextSize(mTitleTextSize);
        float originTextWidth = hearsilent.amazingavatar.libs.Utils.getTextWidth(paint, mTitleTextView.getText().toString());
        // If rtl should move title view to end of view.
        boolean isRTL = TextUtils.getLayoutDirectionFromLocale(Locale.getDefault()) ==
                View.LAYOUT_DIRECTION_RTL ||
                mContainerView.getLayoutDirection() == View.LAYOUT_DIRECTION_RTL;
        float xTitleOffset = ((mToolbarTextPoint[0] + (isRTL ? mToolbarTextView.getWidth() : 0)) -
                (mTitleTextViewPoint[0] + (isRTL ? mTitleTextView.getWidth() : 0)) -
                (mToolbarTextView.getWidth() > newTextWidth ?
                        (originTextWidth - newTextWidth) / 2f : 0)) * offset;
        float yTitleOffset = (mToolbarTextPoint[1] - mTitleTextViewPoint[1]) * offset;
        mTitleTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, newTextSize);
        mTitleTextView.setTranslationX(xTitleOffset);
        mTitleTextView.setTranslationY(yTitleOffset);
    }

    private void resetPoints() {
        final float offset = mAppBarStateChangeListener.getCurrentOffset();

        float newAvatarSize = hearsilent.amazingavatar.libs.Utils.convertDpToPixel(
                EXPAND_AVATAR_SIZE_DP - (EXPAND_AVATAR_SIZE_DP - COLLAPSED_AVATAR_SIZE_DP) * offset,
                this);
        float expandAvatarSize = hearsilent.amazingavatar.libs.Utils.convertDpToPixel(EXPAND_AVATAR_SIZE_DP, this);

        int[] avatarPoint = new int[2];
        mAvatarImageView.getLocationOnScreen(avatarPoint);
        mAvatarPoint[0] = avatarPoint[0] - mAvatarImageView.getTranslationX() -
                (expandAvatarSize - newAvatarSize) / 2f;
        // If avatar center in vertical, just half `(expandAvatarSize - newAvatarSize)`
        mAvatarPoint[1] = avatarPoint[1] - mAvatarImageView.getTranslationY() -
                (expandAvatarSize - newAvatarSize);

        int[] spacePoint = new int[2];
        mSpace.getLocationOnScreen(spacePoint);
        mSpacePoint[0] = spacePoint[0];
        mSpacePoint[1] = spacePoint[1];

        int[] toolbarTextPoint = new int[2];
        mToolbarTextView.getLocationOnScreen(toolbarTextPoint);
        mToolbarTextPoint[0] = toolbarTextPoint[0];
        mToolbarTextPoint[1] = toolbarTextPoint[1];

        Paint paint = new Paint(mTitleTextView.getPaint());
        float newTextWidth = hearsilent.amazingavatar.libs.Utils.getTextWidth(paint, mTitleTextView.getText().toString());
        paint.setTextSize(mTitleTextSize);
        float originTextWidth = hearsilent.amazingavatar.libs.Utils.getTextWidth(paint, mTitleTextView.getText().toString());
        int[] titleTextViewPoint = new int[2];
        mTitleTextView.getLocationOnScreen(titleTextViewPoint);
        mTitleTextViewPoint[0] = titleTextViewPoint[0] - mTitleTextView.getTranslationX() -
                (mToolbarTextView.getWidth() > newTextWidth ?
                        (originTextWidth - newTextWidth) / 2f : 0);
        mTitleTextViewPoint[1] = titleTextViewPoint[1] - mTitleTextView.getTranslationY();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.person_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            if (isTaskRoot()) {
                mPresenter.sendToHomeActivity(this);
            } else onBackPressed();
        }
        if (item.getItemId() == R.id.share_button_person) {
            if (person != null) {
                mPresenter.sharePerson(person.getId(), "person");
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (!hasFocus) {
            return;
        }
        resetPoints();
    }
}
