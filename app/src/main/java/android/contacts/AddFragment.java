package android.contacts;


import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AddFragment extends Fragment implements View.OnClickListener {

    private EditText name;
    private EditText number;
    private List<Person> lists;
    private PersonAdapter adapter;
    DBHelper helper;

    public AddFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add, container, false);

        name = (EditText) view.findViewById(R.id.name);
        number = (EditText) view.findViewById(R.id.number);
        ListView listView = (ListView) view.findViewById(R.id.listView);
        helper = new DBHelper(getActivity());
        lists = helper.getPersons();
        adapter = new PersonAdapter(getActivity(), lists);
        listView.setAdapter(adapter);

        view.findViewById(R.id.add).setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        String sName = name.getText().toString();
        String sNumber = number.getText().toString();
        if ("".equals(sName) || "".equals(sNumber)) {
            Toast.makeText(getActivity(),"Name or Number is Null.",Toast.LENGTH_SHORT).show();
            return;
        }
        List<Person> tmp = new ArrayList<>();
        for (int i = 0; i < lists.size(); i++) {
            if (lists.get(i).isChoose()) {
                tmp.add(lists.get(i));
            }
        }
        Person person = new Person(sName, sNumber);
        person.setShip(tmp);
        helper.add(person);
        name.setText("");
        number.setText("");
        if (!isLand()) {
            getActivity().onBackPressed();
        }
        Log.e("log","add success");
        ((MainActivity)getActivity()).update();
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

    public void update(){
        lists.clear();
        lists.addAll(helper.getPersons());
        adapter.notifyDataSetChanged();
    }
}
