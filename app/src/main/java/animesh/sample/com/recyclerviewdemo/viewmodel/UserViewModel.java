package animesh.sample.com.recyclerviewdemo.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import animesh.sample.com.recyclerviewdemo.interactor.NetworkInteractor;
import animesh.sample.com.recyclerviewdemo.interactor.OnLoadMoreListener;
import animesh.sample.com.recyclerviewdemo.model.DataList;
import animesh.sample.com.recyclerviewdemo.model.UserListResp;
import animesh.sample.com.recyclerviewdemo.network.UserDataRepository;

public class UserViewModel extends ViewModel implements NetworkInteractor, OnLoadMoreListener {

    private MutableLiveData<List<DataList>> mUserDataList;
    private final MutableLiveData<DataList> mSelectUserData = new MutableLiveData<>();
    private UserListResp userListResp;
    private boolean isLoading;

    private MutableLiveData<UserListResp> userListRespMutableLiveData;

    public UserViewModel() {
        UserDataRepository.registerInteractot(this);
    }

    public MutableLiveData<UserListResp> getUserAllData() {
        if (userListRespMutableLiveData == null) {
            userListRespMutableLiveData = new MutableLiveData<>();
            UserDataRepository.getInstance().getUserList(1);
        }
        return userListRespMutableLiveData;
    }


    @Override
    public void onSyncData(UserListResp userListResp) {
        if (null != userListResp) {
            userListRespMutableLiveData.setValue(userListResp);
            setLoading(false);
            /*if (userListResp.getPage() < 1) {
                UserDataRepository.getInstance().getUserList(userListResp.getPage() + 1);
            }*/
        }
    }

    @Override
    public void onFailed(String error) {
        mUserDataList.setValue(null);
    }


    public void select(DataList dataList) {
        mSelectUserData.setValue(dataList);
    }

    public LiveData<DataList> getSelect() {
        return mSelectUserData;
    }

    @Override
    public void onLoadMore(int currentPage) {
        UserDataRepository.getInstance().getUserList(currentPage + 1);
    }

    public boolean isLoading() {
        return isLoading;
    }

    public void setLoading(boolean loading) {
        isLoading = loading;
    }
}
