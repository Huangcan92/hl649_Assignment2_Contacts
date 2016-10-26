package android.contacts;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class DetialFragment extends Fragment {

    private Person mPerson;
    private PersonAdapter adapter;
    public DetialFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detial, container, false);
        mPerson = (Person) getArguments().getSerializable("person");

        TextView name = (TextView) view.findViewById(R.id.name);
        TextView number = (TextView) view.findViewById(R.id.number);
        ListView listView = (ListView) view.findViewById(R.id.listView);

        adapter = new PersonAdapter(getActivity(), mPerson.getShip());
        adapter.setShowBox(false);
        listView.setAdapter(adapter);
        name.setText(mPerson.getName());
        number.setText(mPerson.getNumber());
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Person person = mPerson.getShip().get(i);
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
