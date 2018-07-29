package animesh.sample.com.recyclerviewdemo.activity;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.net.Uri;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import animesh.sample.com.recyclerviewdemo.R;
import animesh.sample.com.recyclerviewdemo.fragment.UserDataFragment;
import animesh.sample.com.recyclerviewdemo.viewmodel.UserViewModel;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements UserDataFragment.OnFragmentInteractionListener {

    private UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, UserDataFragment.newInstance()).commit();
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

}
