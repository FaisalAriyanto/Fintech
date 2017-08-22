package pkp.faisal.fintech.activity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.ncapdevi.fragnav.FragNavController;

import pkp.faisal.fintech.R;
import pkp.faisal.fintech.fragment.DashboardFragment;
import pkp.faisal.fintech.fragment.HistoryFragment;
import pkp.faisal.fintech.fragment.ProfileFragment;
import pkp.faisal.fintech.fragment.dummy.HistoryContent;

public class MainActivity extends AppCompatActivity implements
        FragNavController.RootFragmentListener,
        FragNavController.TransactionListener,
        DashboardFragment.OnFragmentInteractionListener,
        ProfileFragment.OnFragmentInteractionListener,
        HistoryFragment.OnListFragmentInteractionListener {

    private FragNavController mNavController;

    private final int DASHBOARD = FragNavController.TAB1;
    private final int HISTORY = FragNavController.TAB2;
    private final int PROFILE = FragNavController.TAB3;
    private CollapsingToolbarLayout toolbarLayout;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {

                case R.id.navigation_dashboard:
                    mNavController.switchTab(DASHBOARD);
                    toolbarLayout.setTitle("Dashboard");
                    return true;
                case R.id.navigation_history:
                    mNavController.switchTab(HISTORY);
                    toolbarLayout.setTitle("History");
                    return true;
                case R.id.navigation_profile:
                    mNavController.switchTab(PROFILE);
                    toolbarLayout.setTitle("Profile");
                    return true;

            }
            return false;
        }

    };

    @Override
    public void onBackPressed() {
        if (!mNavController.isRootFragment()) {
            mNavController.popFragment();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mNavController != null) {
            mNavController.onSaveInstanceState(outState);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(DASHBOARD);

        toolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.flexible_example_collapsing);
        toolbarLayout.setTitle("Dashboard");

        Toolbar toolbar = (Toolbar) findViewById(R.id.flexible_example_toolbar);
        setSupportActionBar(toolbar);

        mNavController =
                FragNavController
                        .newBuilder(
                                savedInstanceState,
                                getSupportFragmentManager(),
                                R.id.frag_container)
                        .transactionListener(this)
                        .rootFragmentListener(this, 3)
                        .build();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_pengajuan, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public Fragment getRootFragment(int index) {
        Fragment res = null;
        switch (index) {
            case DASHBOARD:
                res = DashboardFragment.newInstance("", "");
                break;
            case HISTORY:
                res = HistoryFragment.newInstance(1);
                break;
            case PROFILE:
                res = ProfileFragment.newInstance("", "");
                break;
        }
        return res;
    }

    @Override
    public void onTabTransaction(Fragment fragment, int i) {
        if (getSupportActionBar() != null && mNavController != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(!mNavController.isRootFragment());
        }
    }

    @Override
    public void onFragmentTransaction(Fragment fragment, FragNavController.TransactionType transactionType) {
        //do fragmentty stuff. Maybe change title, I'm not going to tell you how to live your life
        // If we have a backstack, show the back button
        if (getSupportActionBar() != null && mNavController != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(!mNavController.isRootFragment());
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onListFragmentInteraction(HistoryContent.DummyItem item) {

    }

}
