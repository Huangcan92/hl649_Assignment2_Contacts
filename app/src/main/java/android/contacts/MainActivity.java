package android.contacts;

import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.Stack;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        ListFragment listFragment = new ListFragment();

        transaction.replace(R.id.list, listFragment);
//        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
//        transaction.addToBackStack(null);
        transaction.commit();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (!isLand()) {
            getSupportFragmentManager().popBackStack();
        }
    }

    public void update() {
        Log.e("log","update---");
        ListFragment list = (ListFragment) getSupportFragmentManager().findFragmentById(R.id.list);
        if (list != null) {
            list.update();
        }
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.content);
        if (fragment instanceof  AddFragment) {
            AddFragment add = (AddFragment) fragment;
            if (add != null) {
                add.update();
            }
        }
    }

    private boolean isLand() {
        Configuration configuration = getResources().getConfiguration();
        int orientation = configuration.orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            return true;
        } else {
            return false;
        }
    }
}
