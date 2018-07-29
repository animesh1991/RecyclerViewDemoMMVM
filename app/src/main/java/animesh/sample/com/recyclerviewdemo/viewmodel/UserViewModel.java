package animesh.sample.com.recyclerviewdemo.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import animesh.sample.com.recyclerviewdemo.interactor.NetworkInteractor;
import animesh.sample.com.recyclerviewdemo.model.DataList;
import animesh.sample.com.recyclerviewdemo.model.UserListResp;
import animesh.sample.com.recyclerviewdemo.network.UserDataRepository;

public class UserViewModel extends ViewModel implements NetworkInteractor {

    private MutableLiveData<List<DataList>> mUserDataList;
    private final MutableLiveData<DataList> mSelectUserData = new MutableLiveData<>();

    public UserViewModel() {
        UserDataRepository.registerInteractot(this);
    }

    public MutableLiveData<List<DataList>> getUserDataList() {
        if(mUserDataList == null) {
            mUserDataList = new MutableLiveData<>();
            UserDataRepository.getInstance().getUserList(1);
        }
        return mUserDataList;
    }


    @Override
    public void onSyncData(UserListResp userListResp) {
        if (null != userListResp) {
            mUserDataList.setValue(userListResp.getData());
            if (userListResp.getPage() < userListResp.getTotalPages()) {
                UserDataRepository.getInstance().getUserList(userListResp.getPage()+1);
            }
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
}
