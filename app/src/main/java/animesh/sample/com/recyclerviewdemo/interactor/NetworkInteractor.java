package animesh.sample.com.recyclerviewdemo.interactor;

import java.util.List;

import animesh.sample.com.recyclerviewdemo.model.UserListResp;


public interface NetworkInteractor {
    void onSyncData(UserListResp userListResp);
    void onFailed(String error);
}
