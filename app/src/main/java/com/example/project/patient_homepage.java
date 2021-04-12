package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class patient_homepage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    FirebaseFirestore fStore;
    TextView name;

    String got_name;
    String got_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View screen = getWindow().getDecorView();
        int ui = View.SYSTEM_UI_FLAG_FULLSCREEN;
        screen.setSystemUiVisibility(ui);
        setContentView(R.layout.activity_patient_homepage);
        Toolbar toolbar = findViewById(R.id.toolbar_patient_homepage);
        setSupportActionBar(toolbar);
        fStore = FirebaseFirestore.getInstance();
        Intent intent = getIntent();
        got_email = intent.getStringExtra("email");
        got_name = intent.getStringExtra("Name");

        drawer = findViewById(R.id.drawer_layout_patient);
        NavigationView navigationView = findViewById(R.id.nav_view);
        View headerview = navigationView.getHeaderView(0);
        name = headerview.findViewById(R.id.textView11);
        TextView email = (TextView) headerview.findViewById(R.id.tv_email_header);

        name.setText(got_name);
        email.setText(got_email);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbar,
                R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        Fragment_patient_homepage fragment = new Fragment_patient_homepage();

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).commit();

    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        } else{
            super.onBackPressed();
        }

    }

    public String getData(){
        return got_email;
    }
    public String getName(){return got_name;}

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return true;
    }
}