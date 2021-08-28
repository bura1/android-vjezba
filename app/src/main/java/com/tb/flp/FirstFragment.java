package com.tb.flp;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;

public class FirstFragment extends Fragment {

    Button calculate;
    String a;
    String b;
    String c;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.first_fragment, container, false);

        calculate = (Button) view.findViewById(R.id.calculate);
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText text_a = view.findViewById(R.id.a);
                a = text_a.getText().toString();
                EditText text_b = view.findViewById(R.id.b);
                b = text_b.getText().toString();
                EditText text_c = view.findViewById(R.id.c);
                c = text_c.getText().toString();

                if (parseInt(a) + parseInt(b) <= parseInt(c) ||
                    parseInt(a) + parseInt(c) <= parseInt(b) ||
                    parseInt(b) + parseInt(c) <= parseInt(a)) {
                    Toast.makeText(getContext(), "Trokut nije ispravan", Toast.LENGTH_SHORT).show();
                } else {
                    DBHelper myDB = new DBHelper(getContext());
                    myDB.addTriangle(a, b, c, calculateArea(a, b, c));
                    final AlertDialog.Builder builderWidth = new AlertDialog.Builder(getContext());
                    builderWidth.setTitle("Površina trokuta je:");
                    builderWidth.setMessage(calculateArea(a, b, c).toString());
                    builderWidth.show();
                }
            }

        });

        return view;
    }

    private Double calculateArea(String a, String b, String c) {
        double s = (parseFloat(a) + parseFloat(b) + parseFloat(c)) / 2;
        double area = Math.sqrt(s * (s - parseInt(a)) * (s - parseInt(b)) * ( s - parseInt(c)));
        return round(area, 2);
    }

    public static double round(double value, int places) {
        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public static FirstFragment newInstance() {
        FirstFragment fragment = new FirstFragment();
        return fragment;
    }
}
