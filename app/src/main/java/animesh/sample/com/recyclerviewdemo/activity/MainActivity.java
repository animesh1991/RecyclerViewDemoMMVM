package animesh.sample.com.recyclerviewdemo.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import animesh.sample.com.recyclerviewdemo.R;
import animesh.sample.com.recyclerviewdemo.fragment.UserListFragment;
import animesh.sample.com.recyclerviewdemo.viewmodel.UserViewModel;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements UserListFragment.OnFragmentInteractionListener {

    private UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, UserListFragment.newInstance()).commit();
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

}
