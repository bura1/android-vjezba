package com.tb.flp;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class SecondFragment extends Fragment {

    DBHelper database;
    Button refresh;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.second_fragment, container, false);

        refresh = (Button) view.findViewById(R.id.refresh);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initData(view);
            }
        });

        initData(view);

        return view;
    }

    private void initData(View view) {
        ArrayList <Triangle> triangles = new ArrayList<Triangle> ();
        List<String> listOfStrings = new ArrayList<String>();

        database = new DBHelper(getContext());
        Cursor cursor = database.readAllData();

        if (cursor.getCount() == 0) {
            Toast.makeText(getContext(), "Database empty", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                triangles.add(new Triangle(cursor.getInt(0), cursor.getInt(1), cursor.getInt(2),
                        cursor.getInt(3), cursor.getDouble(4), cursor.getLong(5)));
            }
        }

        for (Triangle tr : triangles) {
            listOfStrings.add(tr.getId() + ".) " + tr.getA() + ", " + tr.getB() + ", " + tr.getC() + ", " + tr.getArea() + ", " + tr.getTime());
        }

        ListView l;
        l = view.findViewById(R.id.list_view);
        ArrayAdapter<String> arr;
        arr = new ArrayAdapter<String>(getContext(), R.layout.support_simple_spinner_dropdown_item, listOfStrings);
        l.setAdapter(arr);
    }

    public static SecondFragment newInstance() {
        SecondFragment fragment = new SecondFragment();

        return fragment;
    }
}
