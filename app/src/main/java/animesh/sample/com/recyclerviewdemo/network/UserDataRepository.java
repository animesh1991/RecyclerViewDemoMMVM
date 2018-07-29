package animesh.sample.com.recyclerviewdemo.network;

import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import java.util.List;

import animesh.sample.com.recyclerviewdemo.interactor.NetworkInteractor;
import animesh.sample.com.recyclerviewdemo.model.DataList;
import animesh.sample.com.recyclerviewdemo.model.UserListResp;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserDataRepository {

    private APIInterface mApiInterface;
    private static UserDataRepository mUserDataRepository;
    private int totalPage;
    private int currentPage;
    private static NetworkInteractor mNetworkInteractor;

    private UserDataRepository() {
        mApiInterface = ApiServiceUtil.getInstance().getApiInterface();
    }

    public synchronized static UserDataRepository getInstance() {
        if (mUserDataRepository == null) {
            mUserDataRepository = new UserDataRepository();
        }
        return mUserDataRepository;
    }

    public void getUserList(int pageNumber) {
        final MutableLiveData<List<DataList>> listMutableLiveData = new MutableLiveData<>();

        mApiInterface.getProjectList(String.valueOf(pageNumber)).enqueue(new Callback<UserListResp>() {
            @Override
            public void onResponse(Call<UserListResp> call, @NonNull Response<UserListResp> response) {
                if (response.body() != null && response.body().getData() != null) {
                    mNetworkInteractor.onSyncData(response.body());
                }
            }

            @Override
            public void onFailure(Call<UserListResp> call, Throwable t) {
                mNetworkInteractor.onFailed(null);
            }
        });
    }


    public static void registerInteractot(NetworkInteractor NetworkInteractor) {
        mNetworkInteractor = NetworkInteractor;
    }
}
