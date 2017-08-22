package pkp.faisal.fintech.activity;

import android.app.ProgressDialog;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;

import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import pkp.faisal.fintech.R;
import pkp.faisal.fintech.adapter.PengajuanSectionAdapter;
import pkp.faisal.fintech.customView.CustomViewPager;
import pkp.faisal.fintech.fragment.BisnisUnitFragment;
import pkp.faisal.fintech.fragment.EntryDataOrderFragment;
import pkp.faisal.fintech.fragment.SimulasiFragment;
import pkp.faisal.fintech.fragment.SimulasiKreditTanpaAnggunanFragment;
import pkp.faisal.fintech.fragment.SimulasiPinjamUangFragment;
import pkp.faisal.fintech.fragment.dummy.BisnisUnitContent;

import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import static android.R.attr.fragment;

public class PengajuanActivity extends AppCompatActivity implements
        BisnisUnitFragment.OnListFragmentInteractionListener,
        SimulasiFragment.OnFragmentInteractionListener,
        SimulasiKreditTanpaAnggunanFragment.OnFragmentInteractionListener,
        EntryDataOrderFragment.OnListFragmentInteractionListener,
        SimulasiPinjamUangFragment.OnFragmentInteractionListener {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private PengajuanSectionAdapter mPengajuanSectionAdapter;

    /**
     * The {@link CustomViewPager} that will host the section contents.
     */
    private CustomViewPager mViewPager;

    private TextView step1, step2, step3;
    private ImageView checkStep1, checkStep2, checkStep3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengajuan);

        step1 = (TextView) findViewById(R.id.step1);
        step2 = (TextView) findViewById(R.id.step2);
        step3 = (TextView) findViewById(R.id.step3);

        checkStep1 = (ImageView) findViewById(R.id.check_step1);
        checkStep2 = (ImageView) findViewById(R.id.check_step2);
        checkStep3 = (ImageView) findViewById(R.id.check_step3);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Pengajuan");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mPengajuanSectionAdapter = new PengajuanSectionAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (CustomViewPager) findViewById(R.id.container);
        mViewPager.setPagingEnabled(true);
        mViewPager.setAdapter(mPengajuanSectionAdapter);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        setCurrentTab(0);

    }





    @Override
    public void onListFragmentInteraction(BisnisUnitContent.BisnisUnit item) {
        Log.d("", "");
//        new SimulasiFragment().changeView(item);

        Fragment simulasiFrag = null;
        switch (item.id) {
            case "1":
                simulasiFrag = new SimulasiPinjamUangFragment();
                break;
            case "2":
                simulasiFrag = new SimulasiKreditTanpaAnggunanFragment();
                break;

        }


        FragmentTransaction trans = getSupportFragmentManager()
                .beginTransaction();
        trans.replace(R.id.root_frame, simulasiFrag);
//        trans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        trans.addToBackStack(null);
        trans.commit();

        mViewPager.setCurrentItem(1, true);
        checkStep1.setVisibility(View.VISIBLE);
        addCheckedToStep(0);
    }

    @Override
    public void onSendEntryData() {
        addCheckedToStep(2);
        final ProgressDialog mLoading = new ProgressDialog(this);
        mLoading.setTitle("Menunggu");
        mLoading.setMessage("Mengirim data");
        mLoading.show();
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mLoading.dismiss();
                exit();
            }
        }, 1000);
    }

    private void exit() {
        finish();

    }

    private void addCheckedToStep(int step) {
        switch (step) {
            case 0:
                checkStep1.setVisibility(View.VISIBLE);
                break;
            case 1:
                checkStep2.setVisibility(View.VISIBLE);
                break;
            case 2:
                checkStep3.setVisibility(View.VISIBLE);
                break;
        }
    }

    private void removeCheckedToStep(int step) {
        switch (step) {
            case 0:
                checkStep1.setVisibility(View.INVISIBLE);
                break;
            case 1:
                checkStep2.setVisibility(View.INVISIBLE);
                break;
            case 2:
                checkStep3.setVisibility(View.INVISIBLE);
                break;
        }
    }

    private void setCurrentTab(int currentTab) {
        switch (currentTab) {
            case 0:
                step1.setTypeface(null, Typeface.BOLD_ITALIC);
                step2.setTypeface(null, Typeface.NORMAL);
                step3.setTypeface(null, Typeface.NORMAL);

                step1.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.primary_text));
                step2.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.divider));
                step3.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.divider));

                removeCheckedToStep(1);
                break;
            case 1:
                step1.setTypeface(null, Typeface.BOLD_ITALIC);
                step2.setTypeface(null, Typeface.BOLD_ITALIC);
                step3.setTypeface(null, Typeface.NORMAL);

                step1.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.primary_text));
                step2.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.primary_text));
                step3.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.divider));

                removeCheckedToStep(2);
                break;

            case 2:
                step1.setTypeface(null, Typeface.BOLD_ITALIC);
                step2.setTypeface(null, Typeface.BOLD_ITALIC);
                step3.setTypeface(null, Typeface.BOLD_ITALIC);

                step1.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.primary_text));
                step2.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.primary_text));
                step3.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.primary_text));
                break;
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void goToEntryData() {
        mViewPager.setCurrentItem(2, true);
        addCheckedToStep(1);
    }
}
