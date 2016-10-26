package android.contacts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 0 on 2016/10/24.
 */
public class DBHelper extends SQLiteOpenHelper {
    private final static String TABLE = "DATAS";

    public DBHelper(Context context) {
        super(context, "DB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table if not exists " +
                TABLE + " ("
                + "id integer primary key autoincrement,name TEXT,"
                + "number TEXT, ship TEXT)");
        for (int i = 0; i < 5; i++) {
            ContentValues values = new ContentValues();
            values.put("name", "Person Test " + i);
            values.put("number", System.currentTimeMillis() + "");
            sqLiteDatabase.insert(TABLE, null, values);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void add(Person person) {
        ContentValues values = new ContentValues();
        values.put("name", person.getName());
        values.put("number", person.getNumber());
        String ship = "";
        for (Person p : person.getShip()) {
            ship += p.getId() + ",";
        }
        values.put("ship", ship);
        getWritableDatabase().insert(TABLE, null, values);
    }

    public void delete(Person person) {
        getWritableDatabase().delete(TABLE, "id=?", new String[]{person.getId() + ""});
    }

    public List<Person> getPersons() {
        List<Person> persons = new ArrayList<>();
        Cursor query = getWritableDatabase().rawQuery("select * from " + TABLE, null);
        while (query.moveToNext()) {
            int id = query.getInt(query.getColumnIndex("id"));
            String name = query.getString(query.getColumnIndex("name"));
            String number = query.getString(query.getColumnIndex("number"));
            String ship = query.getString(query.getColumnIndex("ship"));
            Person p = new Person(id, name, number);
            p.setIds(ship);
            persons.add(p);
        }
        for (Person person : persons) {
            if (person.getIds() != null) {
                for (Person p : persons) {
                    if (person.getIds().contains(p.getId() + "")) {
                        person.getShip().add(p);
                    }
                }
            }
        }
        return persons;
    }
}
