package com.iniyan.krazybee;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.iniyan.krazybee.Adapter.FragmentAdapter;
import com.iniyan.krazybee.Fragment.DynamicFragment;
import com.iniyan.krazybee.Model.Album;
import com.iniyan.krazybee.NetworkClient.ApiDataService;
import com.iniyan.krazybee.NetworkClient.RetrofitClient;

import org.greenrobot.eventbus.EventBus;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private ArrayList<Fragment> fragments;
    private Toolbar toolbar;
    private ProgressDialog pd;
    private String TAG = MainActivity.class.getName();
    ArrayList<String> tabTitle = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        viewPager = (ViewPager) findViewById(R.id.pager);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        fragments = new ArrayList<>();
        pd = new ProgressDialog(MainActivity.this, R.style.MyTheme);
        pd.setCancelable(false);
        pd.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
        pd.show();
        tabTitle.clear();

        apiCall();
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
//                Bundle bundle = new Bundle();
//                bundle.putInt("id", albumsList.get(position).getId());
//                DynamicFragment myFragment = new DynamicFragment();
//                myFragment.setArguments(bundle);
//                getSupportFragmentManager().beginTransaction().replace(R.id.container, myFragment).commit();
//                Log.e(TAG, "AddonTabSelect" + position);
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }

    public void setUpViewPager(final List<Album> albumsList) {

        pd.dismiss();

        for (int i = 0; i < albumsList.size(); i++) {

            tabLayout.addTab(tabLayout.newTab().setText(albumsList.get(i).getTitle()));
            tabTitle.add(albumsList.get(i).getTitle());
        }
        FragmentAdapter adapter = new FragmentAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount(), tabTitle,albumsList);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);


    }

    public void apiCall() {

        final ApiDataService apiDataService = RetrofitClient.getService();

        Call<List<Album>> call = apiDataService.getLoadFragment();

        call.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(@NotNull Call<List<Album>> call, @NotNull retrofit2.Response<List<Album>> response) {

                if (response.isSuccessful()) {
                    assert response.body() != null;

                    setUpViewPager(response.body());

                } else pd.dismiss();
            }

            @Override
            public void onFailure(@NotNull Call<List<Album>> call, Throwable t) {
                Log.d(TAG, "error===>" + t.getMessage());
                pd.dismiss();
            }
        });
    }


}


