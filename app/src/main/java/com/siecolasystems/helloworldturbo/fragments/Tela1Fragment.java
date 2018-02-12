package com.siecolasystems.helloworldturbo.fragments;

//import android.app.Fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.siecolasystems.helloworldturbo.R;
import com.siecolasystems.helloworldturbo.models.Order;
import com.siecolasystems.helloworldturbo.tasks.OrderEvents;
import com.siecolasystems.helloworldturbo.tasks.OrderTasks;
import com.siecolasystems.helloworldturbo.webservice.WebServiceResponse;

import java.util.List;

public class Tela1Fragment extends Fragment {

    private EditText editText1;
    private Button button1;
    private TextView textView1;
    private CheckBox chkConfig1;

    private static String PREF_CONFIG_1 = "pref_config_1";

    private static final String TAG = "Tela1Fragment";

    private static String STATE_USER_TEXT = "user_text";

    public Tela1Fragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tela1,
                container, false);

        editText1 = (EditText) rootView.findViewById(R.id.editText1);
        button1 = (Button) rootView.findViewById(R.id.button1);
        textView1 = (TextView) rootView.findViewById(R.id.textView1);
        chkConfig1 = (CheckBox) rootView.findViewById(R.id.chkConfig1);
        editText1.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == 66) {
                    setTexto();
                }
                return false;
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTexto();
            }
        });

        if (savedInstanceState != null) {
            textView1.setText(savedInstanceState.getString(STATE_USER_TEXT));
        } else {
            textView1.setText("Olá de novo!!!");
        }

        SharedPreferences sharedSettings = getActivity().
                getSharedPreferences(getActivity().getClass().
                        getSimpleName(), Context.MODE_PRIVATE);

        Boolean config1 = sharedSettings.getBoolean(PREF_CONFIG_1, false);

        chkConfig1.setChecked(config1);

        return rootView;
    }

    @Override
    public void onStop() {
        super.onStop();

        SharedPreferences sharedSettings = getActivity().
                getSharedPreferences(getActivity().getClass().
                        getSimpleName(), Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedSettings.edit();

        editor.putBoolean(PREF_CONFIG_1, chkConfig1.isChecked());

        editor.commit();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString(STATE_USER_TEXT, textView1.getText().toString());
        super.onSaveInstanceState(outState);
    }


    private void setTexto() {
        if (!editText1.getText().toString().isEmpty()) {
            textView1.setText(editText1.getText().toString());
        } else {
            Log.i(TAG, "Usuário não digitou o texto");
            Toast.makeText(getActivity(), "O usuário não digitou o texto",
                    Toast.LENGTH_SHORT).show();
        }
    }
}











