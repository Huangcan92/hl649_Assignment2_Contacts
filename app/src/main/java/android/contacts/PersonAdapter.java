package android.contacts;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 0 on 2016/10/24.
 */
public class PersonAdapter extends BaseAdapter {
    private Context context;
    private List<Person> lists;
    private boolean showBox = true;
    public PersonAdapter(Context context, List<Person> lists) {
        this.context = context;
        if (lists == null) {
            lists = new ArrayList<>();
        }
        this.lists = lists;
    }

    public boolean isShowBox() {
        return showBox;
    }

    public void setShowBox(boolean showBox) {
        this.showBox = showBox;
    }

    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public Object getItem(int i) {
        return lists.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.item, null);
        final Person person = lists.get(i);
        TextView name = (TextView) view.findViewById(R.id.name);
        name.setText(person.getName());
        CheckBox checkbox = (CheckBox) view.findViewById(R.id.checkbox);
        if (!showBox) {
            checkbox.setVisibility(View.INVISIBLE);
        } else {
            checkbox.setChecked(person.isChoose());
            checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    person.setChoose(b);
                }
            });
        }
        return view;
    }
}
