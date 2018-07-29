package animesh.sample.com.recyclerviewdemo.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.TextView;

import animesh.sample.com.recyclerviewdemo.R;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class BaseFragment extends Fragment {

    private int mLayoutId;
    private View mViewProgressBarContainer;


    public BaseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_base, container, false);
        FrameLayout fragmentLayoutContainer = view.findViewById(R.id.layout_container);
        inflater.inflate(mLayoutId, fragmentLayoutContainer);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    protected void setLayout(int layoutId) {
        mLayoutId = layoutId;
    }

    private void initView(View view) {
        mViewProgressBarContainer = view.findViewById(R.id.progress_bar_container);
    }

    public void showProgressBar() {
        if (mViewProgressBarContainer != null) {
            mViewProgressBarContainer.setVisibility(View.VISIBLE);
        }
    }

    public void hideProgressBar() {
        if (mViewProgressBarContainer != null) {
            mViewProgressBarContainer.setVisibility(View.GONE);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


}
