package animesh.sample.com.recyclerviewdemo.fragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.ArrayList;

import animesh.sample.com.recyclerviewdemo.R;
import animesh.sample.com.recyclerviewdemo.adapter.UserDataListAdapter;
import animesh.sample.com.recyclerviewdemo.interactor.RecyclerClickListener;
import animesh.sample.com.recyclerviewdemo.model.DataList;
import animesh.sample.com.recyclerviewdemo.model.UserListResp;
import animesh.sample.com.recyclerviewdemo.viewmodel.UserViewModel;
import butterknife.BindView;

public class UserDataFragment extends BaseFragment implements RecyclerClickListener {

    @BindView(R.id.recycler_client_list)
    RecyclerView recyclerListClient;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    private UserViewModel userViewModel;
    private OnFragmentInteractionListener mListener;

    private int currentPage = 0;
    private int totalPage = 0;

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
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerListClient.setLayoutManager(linearLayoutManager);
        recyclerListClient.setItemAnimator(new DefaultItemAnimator());
        recyclerListClient.setAdapter(userDataListAdapter);
        userViewModel = ViewModelProviders.of(getActivity()).get(UserViewModel.class);

/*
        userViewModel.getUserDataList().observe(getActivity(), new Observer<List<DataList>>() {
            @Override
            public void onChanged(@Nullable List<DataList> dataLists) {
                if (null != dataLists && dataLists.size() > 0) {
                    hideProgressBar();
                    progressBar.setVisibility(View.GONE);
                    userDataListAdapter.addItem(dataLists);
                }
            }
        });
*/

        userViewModel.getUserAllData().observe(getActivity(), new Observer<UserListResp>() {
            @Override
            public void onChanged(@Nullable UserListResp userListResp) {
                if (null != userListResp && userListResp.getData().size() > 0) {
                    currentPage = userListResp.getPage();
                    totalPage = userListResp.getTotalPages();
                    hideProgressBar();
                    progressBar.setVisibility(View.GONE);
                    userDataListAdapter.addItem(userListResp.getData());
                }
            }
        });

        recyclerListClient.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

/*              visibleItemCount = linearLayoutManager.getChildCount();
                totalItemCount = linearLayoutManager.getItemCount();
                lastVisibleItem = linearLayoutManager.findFirstVisibleItemPosition();

                if (!userViewModel.isLoading() && (totalItemCount - visibleItemCount) <= (lastVisibleItem + visibleThreshold)) {
                    userViewModel.onLoadMore();
                }

                userViewModel.setLoading(true);*/

                if (dy > 0) {
                    if (!recyclerView.canScrollVertically(RecyclerView.FOCUS_DOWN) && currentPage < totalPage && !userViewModel.isLoading()) {
                        progressBar.setVisibility(View.VISIBLE);
                        userViewModel.setLoading(true);
                        userViewModel.onLoadMore(currentPage);
                    }
                }
                else {
                    if ((linearLayoutManager.findLastVisibleItemPosition() == linearLayoutManager.getItemCount() -1) && currentPage < totalPage && !userViewModel.isLoading()) {
                        progressBar.setVisibility(View.VISIBLE);
                        userViewModel.setLoading(true);
                        userViewModel.onLoadMore(currentPage);
                    }
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
