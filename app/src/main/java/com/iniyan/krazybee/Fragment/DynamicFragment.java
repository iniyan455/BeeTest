package com.iniyan.krazybee.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.iniyan.krazybee.Adapter.GridLayoutAdapter;
import com.iniyan.krazybee.Model.AlbumData;
import com.iniyan.krazybee.NetworkClient.ApiDataService;
import com.iniyan.krazybee.NetworkClient.RetrofitClient;
import com.iniyan.krazybee.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;

public class DynamicFragment extends Fragment {

    private String TAG = DynamicFragment.class.getName();
    private int id = 0;

    private RecyclerView recyclerView;

    public static DynamicFragment newInstance(int val) {
        DynamicFragment fragment = new DynamicFragment();
        Bundle args = new Bundle();
        args.putInt("id", val);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.home_fragment, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        if (getArguments() != null) {


            id = this.getArguments().getInt("id");

            if (id != 0) {
                Log.d(TAG, "getting data" + (id + 1));
                apiRequestCall(id + 1);

            } else {
                apiRequestCall(id + 1);
            }
        }
        return view;
    }


    private void apiRequestCall(int id) {

        final ApiDataService apiDataService = RetrofitClient.getService();

        Call<ArrayList<AlbumData>> call = apiDataService.getListLoad(id);


        call.enqueue(new Callback<ArrayList<AlbumData>>() {
            @Override
            public void onResponse(@NotNull Call<ArrayList<AlbumData>> call, retrofit2.Response<ArrayList<AlbumData>> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    setUpRecyclerView(response.body());
                }
            }

            @Override
            public void onFailure(@NotNull Call<ArrayList<AlbumData>> call, Throwable t) {
                Log.d(TAG, "error===>" + t.getMessage());
            }
        });
    }

    private void setUpRecyclerView(ArrayList<AlbumData> dataList) {
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 3, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        final GridLayoutAdapter adapter = new GridLayoutAdapter(dataList, getActivity());
        recyclerView.setAdapter(adapter);

    }


}