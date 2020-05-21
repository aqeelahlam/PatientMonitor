package com.example.cholesterol.UserInterfaces;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.cholesterol.Objects.Patient;
import com.example.cholesterol.R;
import com.example.cholesterol.ServerCalls.PatientData;
import com.example.cholesterol.ServerCalls.PatientList;
import com.google.android.material.snackbar.Snackbar;

import java.util.HashMap;

/*
* Android: 9.0 (Pie)
* API Level: 28
*
* Make sure the app is given "Internet" access within the AndroidManifest.xml  :-
* Place the dependency right below "package"
* <uses-permission android:name="android.permission.INTERNET" />
*
* I have used Volley to perform the relevant network requests.
* https://developer.android.com/training/volley
*
*
* */

public class MainActivity extends AppCompatActivity {

    public static Context context;
    RecyclerView patientRecyclerView;

    private static HashMap<String, Patient> patientDetailsMap = new HashMap<>();

    public static HashMap<String, Patient> getPatientDetailsMap() {
        return patientDetailsMap;
    }

    public static HashMap<String, Patient> getMonitoredPatients() {
        return monitoredPatients;
    }

    private static HashMap<String, Patient> monitoredPatients = new HashMap<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        patientRecyclerView = findViewById(R.id.recycler_view);
        patientRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    public void FindBtn(final View view) {

        EditText keyword;
        keyword = findViewById(R.id.editText);
//        String practitionerID = keyword.getText().toString();

        String practitionerID = "1381208";
//        String practitionerID = "6832728";

        PatientList.patientHandler(practitionerID, this, patientRecyclerView, patientDetailsMap, monitoredPatients);

    }



    public void monitorBtn(View view) {

        if(monitoredPatients.isEmpty()){
            Snackbar.make(view, "You have not chosen any patients", Snackbar.LENGTH_LONG).setAction("Action", null).show();
        }else {
            Intent intent = new Intent(this, MonitorActivity.class);
            startActivity(intent);
        }
    }
}










