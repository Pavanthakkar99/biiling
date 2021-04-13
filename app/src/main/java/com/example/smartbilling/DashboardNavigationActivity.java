package com.example.smartbilling;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class DashboardNavigationActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    RelativeLayout waterBox,electricityBox,gasBox;
    TextView emailIdForHeader;
    FirebaseAuth firebaseAuth;
//    Button button;
//    ImageView click_image_id;
//



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_navigation);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        firebaseAuth=FirebaseAuth.getInstance();
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                return false;
            }
        });






        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        View header=navigationView.getHeaderView(0);
        emailIdForHeader=(TextView)header.findViewById(R.id.emailIdForHeader);
        String userName=localStorage.getInstance(getApplicationContext()).getUserName();
        Log.d( "userName",userName);
        emailIdForHeader.setText(userName);
        waterBox=findViewById(R.id.waterBox);
        waterBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DashboardNavigationActivity.this, DetailsActivity.class);
                intent.putExtra("TabIndex","0");
                startActivity(intent);
//                Button camera_open_id= (Button)findViewById(R.id.camera_button);
//                click_image_id = (ImageView)findViewById(R.id.imageView7);
//
//                camera_open_id.setOnClickListener(new View.OnClickListener() {
//
//                    @Override
//                    public void onClick(View v) {
//                        try {
//                            Intent intent=new Intent();
//                            intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
//                            startActivity(intent);
//                        }catch (Exception e)
//                        {
//                            e.printStackTrace();
//                        }
//
//
//
//                    }
//                });

            }
        });

        electricityBox=findViewById(R.id.electricityBox);
        electricityBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DashboardNavigationActivity.this, DetailsActivity.class);
                intent.putExtra("TabIndex","1");

                Log.d(firebaseAuth.getUid().toString(),"getUserId");
                startActivity(intent);

            }
        }); gasBox=findViewById(R.id.gasBox);
        gasBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DashboardNavigationActivity.this, DetailsActivity.class);
                intent.putExtra("TabIndex","2");

                startActivity(intent);
            }
        });

    navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId())
            {
                case R.id.nav_water:
                    Intent intent=new Intent(DashboardNavigationActivity.this, DetailsActivity.class);
                    intent.putExtra("TabIndex","0");
                    startActivity(intent);
                    break;
                case R.id.nav_electricity:
                    Intent intent1=new Intent(DashboardNavigationActivity.this, DetailsActivity.class);
                    intent1.putExtra("TabIndex","1");
                    startActivity(intent1);
                    break;
                case R.id.nav_gas:
                    Intent intent2=new Intent(DashboardNavigationActivity.this, DetailsActivity.class);
                    intent2.putExtra("TabIndex","2");
                    startActivity(intent2);
                    break;
            }


            return true;
        }
    });


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.action_logout:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(DashboardNavigationActivity.this,LoginActivity.class));
                finish();

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.dashboard_navigation,menu);
        return true;
    }
}