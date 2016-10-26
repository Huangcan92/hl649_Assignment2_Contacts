package android.contacts;


import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends Fragment implements View.OnClickListener {

    private ListView listView;
    private List<Person> lists;
    private PersonAdapter adapter;
    private DBHelper helper;

    public ListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        listView = (ListView) view.findViewById(R.id.listView);
        helper = new DBHelper(getActivity());
        lists = helper.getPersons();
        adapter = new PersonAdapter(getActivity(), lists);
        listView.setAdapter(adapter);
        view.findViewById(R.id.add).setOnClickListener(this);
        view.findViewById(R.id.delete).setOnClickListener(this);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Person person = lists.get(i);
                DetialFragment detailFragment = new DetialFragment();
                Bundle bundle = new Bundle();
                bundle.putSerializable("person", person);
                detailFragment.setArguments(bundle);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                if (!isLand()) {
                    transaction.replace(R.id.list, detailFragment);
                    transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                    transaction.addToBackStack(null);
                } else {
                    transaction.replace(R.id.content, detailFragment);
                }
                transaction.commit();
            }
        });
        return view;
    }

    @Override
    public void onClick(View view) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        AddFragment addFragment = new AddFragment();
        switch (view.getId()) {
            case R.id.add:
                if (!isLand()) {
                    transaction.replace(R.id.list, addFragment);
                    transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                    transaction.addToBackStack(null);
                } else {
                    transaction.replace(R.id.content, addFragment);
                }
                transaction.commit();
                break;
            case R.id.delete:
                for (int i = lists.size() - 1; i >= 0; i--) {
                    if (lists.get(i).isChoose()) {
                        Person per = lists.remove(i);
                        helper.delete(per);
                    }
                }
                adapter.notifyDataSetChanged();
                Log.e("log","delete");
                ((MainActivity)getActivity()).update();
                break;
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

    @Override
    public void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    public void update(){
        lists.clear();
        lists.addAll(helper.getPersons());
        adapter.notifyDataSetChanged();
    }
}
