package animesh.sample.com.recyclerviewdemo.fragment;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import animesh.sample.com.recyclerviewdemo.R;
import animesh.sample.com.recyclerviewdemo.model.DataList;
import animesh.sample.com.recyclerviewdemo.viewmodel.UserViewModel;
import butterknife.BindView;
import butterknife.ButterKnife;


public class UserDetailFragment extends BaseFragment {


    @BindView(R.id.user_image)
    ImageView userImage;

    @BindView(R.id.user_name)
    TextView userName;

    private UserViewModel userViewModel;

    public static UserDetailFragment newInstance() {
        return new UserDetailFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setLayout(R.layout.fragment_user_detail);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    private void initData() {
        userViewModel = ViewModelProviders.of(getActivity()).get(UserViewModel.class);
        LiveData<DataList> dataListLiveData = userViewModel.getSelect();
        if (dataListLiveData != null && dataListLiveData.getValue() != null) {
            userName.setText(String.format("%s %s", dataListLiveData.getValue().getFirstName(), dataListLiveData.getValue().getLastName()));
            loadImage(dataListLiveData.getValue().getAvatar());
        }
    }

    private void loadImage(String avatar) {
        Glide.with(getActivity()).load(avatar).into(userImage);
    }


}
