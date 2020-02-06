package com.iniyan.krazybee.NetworkClient;

import com.iniyan.krazybee.Model.Album;
import com.iniyan.krazybee.Model.AlbumData;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiDataService {

    @GET("/albums")
    Call<List<Album>> getLoadFragment();

    @GET("/photos?")
    Call<ArrayList<AlbumData>> getListLoad(@Query("albumId") int albumId);


}
