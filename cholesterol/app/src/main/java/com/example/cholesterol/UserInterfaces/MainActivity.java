package com.example.cholesterol.UserInterfaces;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cholesterol.Objects.Patient;
import com.example.cholesterol.R;
import com.example.cholesterol.ServerCalls.BloodPressureData;
import com.example.cholesterol.ServerCalls.CholesterolData;
import com.example.cholesterol.ServerCalls.observationHandler;
import com.example.cholesterol.ServerCalls.CholesterolTest;
import com.example.cholesterol.ServerCalls.PatientList;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONException;

import java.text.ParseException;
import java.util.HashMap;

/*
* Android: 9.0 (Pie)
* API Level: 28
*
* Make sure the app is given "Internet" access within the AndroidManifest.xml  :-
* Place the dependency right below "package"
* <uses-permission android:name="android.permission.INTERNET" />
*
* We have used Volley to perform the relevant network requests.
* https://developer.android.com/training/volley
*
*
* */

public class MainActivity extends AppCompatActivity {

    public static Context context;
    RecyclerView patientRecyclerView;

    private static HashMap<String, Patient> patientDetailsMap = new HashMap<>();

    /**
     * This method is used to get the patientDetailsMap HashMap
     * @return Hashmap<String, Patient> patientDetailsMap:
     */
    public static HashMap<String, Patient> getPatientDetailsMap() {
        return patientDetailsMap;
    }

    private static HashMap<String, Patient> monitoredPatients = new HashMap<>();

    /**
     * This method is used to get the monitoredPatients HashMap
     * @return Hashmap<String, Patient> monitoredPatients:
     */
    public static HashMap<String, Patient> getMonitoredPatients() {
        return monitoredPatients;
    }


    public static void setMonitoredPatients(String key, Patient patientDetails) {
        monitoredPatients.put(key, patientDetails);
    }

    /**
     * This method is used to "inflate" the layout and controls the
     * @param savedInstanceState The current state of the device
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//      Inflates the layout:
        setContentView(R.layout.activity_main);

//      This is used to obtain a reference of the recyclerView located in the MainActivity Layout
        patientRecyclerView = findViewById(R.id.recycler_view);
        patientRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    /**
     * This is function that will be invoked when the Find button is clicked
     * @param view viewObject: Find Button
     */
    public void FindBtn(final View view) {

        EditText keyword;
        keyword = findViewById(R.id.editText);
//        String practitionerID = keyword.getText().toString();
        String practitionerID = "1381208";
//      This will make sure we have entered practitioner id
        if(practitionerID.isEmpty()){
            Toast.makeText(this, "Please Enter Practitioner ID", Toast.LENGTH_LONG).show();
        }
//      We will obtain the list of Patients for the current practitioner
        PatientList.patientHandler(practitionerID, this, patientRecyclerView, patientDetailsMap, monitoredPatients);
    }


    /**
     * This is function that will be invoked when the Monitor button is clicked
     * @param view viewObject: Monitor Button
     */
    public void monitorBtn(View view) throws JSONException, ParseException {

//      If we have not selected any Patients
        if(monitoredPatients.isEmpty()){
            Snackbar.make(view, "You have not chosen any patients", Snackbar.LENGTH_LONG).setAction("Action", null).show();
        } else {
            CholesterolData.getCholesterol(monitoredPatients, patientDetailsMap, this, patientRecyclerView);
//            BloodPressureData.getBloodPressure(monitoredPatients, patientDetailsMap, this, patientRecyclerView);
//            observationHandler.getObservation("Chol", monitoredPatients, patientDetailsMap, this, patientRecyclerView);

//          We move to the next activity to monitor the patients:
            Intent intent = new Intent(this, MonitorActivity.class);
            startActivity(intent);
        }
    }
}











