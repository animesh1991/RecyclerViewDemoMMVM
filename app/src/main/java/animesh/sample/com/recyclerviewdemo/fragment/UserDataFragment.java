package animesh.sample.com.recyclerviewdemo.fragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import animesh.sample.com.recyclerviewdemo.R;
import animesh.sample.com.recyclerviewdemo.activity.MainActivity;
import animesh.sample.com.recyclerviewdemo.adapter.UserDataListAdapter;
import animesh.sample.com.recyclerviewdemo.interactor.RecyclerClickListener;
import animesh.sample.com.recyclerviewdemo.model.DataList;
import animesh.sample.com.recyclerviewdemo.viewmodel.UserViewModel;
import butterknife.BindView;
import butterknife.ButterKnife;

public class UserDataFragment extends BaseFragment implements RecyclerClickListener {

    @BindView(R.id.recycler_client_list)
    RecyclerView recyclerListClient;

    private UserViewModel userViewModel;
    private OnFragmentInteractionListener mListener;

    public UserDataFragment() {
        // Required empty public constructor
    }

    public static UserDataFragment newInstance() {
        UserDataFragment fragment = new UserDataFragment();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setLayout(R.layout.fragment_user_list);
        return super.onCreateView(inflater, container, savedInstanceState);
        }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showProgressBar();
        initData();
    }


    private void initData() {
        final UserDataListAdapter userDataListAdapter = new UserDataListAdapter(getActivity(), new ArrayList<DataList>(), this);
        recyclerListClient.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerListClient.setItemAnimator(new DefaultItemAnimator());
        recyclerListClient.setAdapter(userDataListAdapter);
        userViewModel = ViewModelProviders.of(getActivity()).get(UserViewModel.class);

        userViewModel.getUserDataList().observe(getActivity(), new Observer<List<DataList>>() {
            @Override
            public void onChanged(@Nullable List<DataList> dataLists) {
                if (null != dataLists && dataLists.size() > 0) {
                    hideProgressBar();
                    userDataListAdapter.addItem(dataLists);
                }
            }
        });
    }

    @Override
    public void onClickItem(DataList dataList) {
        userViewModel.select(dataList);
        getFragmentManager().beginTransaction().add(R.id.fragment_container, UserDetailFragment.newInstance()).addToBackStack(null).commit();
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }


}
