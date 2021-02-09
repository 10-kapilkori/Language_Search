package com.task.experiment.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.google.gson.JsonObject;
import com.task.experiment.model.API;
import com.task.experiment.model.PostData;
import com.task.experiment.repository.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewModel extends androidx.lifecycle.ViewModel {
    private static final String TAG = "ViewModel";

    private final MutableLiveData<JsonObject[]> mutableLiveData;

    public ViewModel() {
        mutableLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<JsonObject[]> getMutableLiveData() {
        return mutableLiveData;
    }

    public void makeCall(String result) {

        API api = RetrofitInstance.getInstance().create(API.class);
        Call<PostData> call = api.getData(result);

        call.enqueue(new Callback<PostData>() {
            @Override
            public void onResponse(Call<PostData> call, Response<PostData> response) {
//                if (!response.isSuccessful()) {
//                    return;
//                }
                Log.i(TAG, "onResponse: " + response.body());
                Log.i(TAG, "onResponse: " + result);
                Log.i(TAG, "onResponse: " + call.request());
                PostData item = response.body();
                JsonObject[] items = item.getItems();
                mutableLiveData.postValue(items);
            }

            @Override
            public void onFailure(Call<PostData> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }
}
