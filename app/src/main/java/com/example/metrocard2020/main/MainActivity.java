package com.example.metrocard2020.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.metrocard2020.R;
import com.example.metrocard2020.features.AboutActivity;
import com.example.metrocard2020.features.HelpActivity;
import com.example.metrocard2020.features.SettingActivity;
import com.example.metrocard2020.features.TicketActivity;
import com.example.metrocard2020.features.UserAccountActivity;
import com.example.metrocard2020.login.LoginActivity;
import com.example.metrocard2020.payment.FawryActivity;
import com.example.metrocard2020.payment.WalletActivity;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    View header;
    String phone;

    TextView userNameTextView;
    Button WhereTo,AddMoney;

    String [] Stations={" Helwan "," Ain Helwan "," Helwan University  "," Wadi Hof "," Hadayek Helwan "," El-Maasara "," Tora El-Asmant"," Kozzika "," Tora El-Balad "," Sakanat El-Maadi "," Maadi "," Hadayek El-Maadi "," Dar El-Salam "," El-Zahraa' "," Mar Girgis"," El-Malek El-Saleh "," Al-Sayeda Zeinab "," Saad Zaghloul "," Sadat "," Nasser "," Orabi "," Al-Shohadaa"," Ghamra 	"," El-Demerdash "," Manshiet El-Sadr "," Kobri El-Qobba "," Hammamat El-Qobba "," Saray El-Qobba "," Hadayeq El-Zaitoun "," Helmeyet El-Zaitoun "," El-Matareyya "," Ain Shams "," Ezbet El-Nakhl "," El-Marg "," New El-Marg 	"," El-Mounib "," Sakiat Mekky 	"," Omm El-Masryeen"," Giza "," Faisal "," Cairo University 	"," El Bohoth 	"," Dokki "," Opera "," Sadat "," Mohamed Naguib "," Attaba "," Al-Shohadaa"," Masarra "," Road El-Farag "," St. Teresa "," Khalafawy "," Mezallat "," Kolleyyet El-Zeraa "," Shubra El-Kheima "," Airport "," Ahmed Galal "," Adly Mansour "," El Haykestep "," Omar Ibn El-Khattab "," Qobaa "," Hesham Barakat "," El-Nozha "," Nadi El-Shams "," Alf Maskan "," Heliopolis Square "," Haroun "," Al-Ahram "," Koleyet El-Banat "," Stadium "," Fair Zone "," Abbassia "," Abdou Pasha "," El Geish "," Bab El Shaaria"," Attaba "," Nasser "," Maspero "," Zamalek "," Kit Kat "," Sudan Street "," Imbaba "," El-Bohy "," El-Kawmeya Al-Arabiya "," Ring Road "," Rod El-Farag Axis "," El-Tawfikeya "," Wadi El-Nil "," Gamaat El Dowal Al-Arabiya "," Bulaq El-Dakroor "," Cairo University 	"};
    double [] longitude={ 31.334222109482518,   31.32489320259818, 31.320056699482002, 31.313583099662416, 31.303885340703733, 31.299482450743465, 31.28750034659564, 31.28183186924061, 31.27296556407064, 31.26294145512941, 31.25763288137365, 31.250610879613454, 31.24215723981455, 31.231153137225803, 31.229581439058705,31.23118430616145, 31.2354344276038, 31.23836430216, 31.23438841247254, 31.23874251716944, 31.24205395089425, 31.246083464251907, 31.264618726880812, 31.277802675761727, 31.287533997148966, 31.294097562141545, 31.298908212584887, 31.304555158262232, 31.31046647790619, 31.313967295070864, 31.313723863869903, 31.318958783625828, 31.324413316625197, 31.33568150184823, 31.33836162785319, 31.21241053282305, 31.208697814914448, 31.20811845781359, 31.20722958131365, 31.203777410954682, 31.201155319657648, 31.200150901597777, 31.212205792716496, 31.22498505999716, 31.23441879171944, 31.244141968581978, 31.246799214339568, 31.246041041370166, 31.245073683794054, 31.24540010610837, 31.24549213865504, 31.245402607096576, 31.245655901375436, 31.248653461337486, 31.244532863825892, 31.399756846757768, 31.421611516018633, 31.404559497086435, 31.394160058939093, 31.38391355484683, 31.372443587242643, 31.360591287047175, 31.349121319909504, 31.34018906090973, 31.338275569185548, 31.332963197027134, 31.326313999782702, 31.329013999830902, 31.317108852712757, 31.30102753151248, 31.28334512478373, 31.27479424275881, 31.26687274767932, 31.255882917246417, 31.246791503649632, 31.2387347471485, 31.232132921808056, 31.222182329129772, 31.213025655812505, 31.204250265788552, 31.2077472720069,0, 31.199498858853346,0,0,0,0,0,0,0, 31.201150191774907};
    double [] latitude={29.84900398325833,29.862731779771412,29.869611547189766,29.879078671757107,29.897187530440462,29.90604148137043,29.925956504381535,29.936296104641528,29.946749485501662,29.953321092122604,29.960292756792455,29.97013530915339,29.982039115896015,29.995491085972326,30.006119899945794,30.017674889628935,30.029303396466222,30.037012274202596,30.04412684753736,30.05340335654347,30.056686367553233,30.061037575558405,30.069007614579014,30.077293694230885,30.081986991214524,30.087198618680784,30.09123784885388,30.09764373337332,30.105883152342276,30.113251229227274,30.121328561198357,30.1310738978928,30.139297929094067,30.152079247592326,30.1636481810127,29.981140847968522,29.995472489834253,30.005609400613412,30.010711973223504,30.017321528873428,30.026002870450572,30.035772599558516,30.038416725202634,30.041951738439245,30.04414786373947,30.045323115407182,30.052336409390733,30.061053156814125,30.070906287504773,30.080598121630704,30.08794727279331,30.097883056552185,30.104114625322385,30.11370427452355,30.122431971649508,30.11737502390076,30.147188785405188,30.14375030592672,30.140576218557637,30.134822925084297,30.131053343950704,30.128077257145492,30.12569632307381,30.11898533359229,30.1084658905277,30.101367577390462,30.091723439092704,30.084038677167165,30.072845001656273,30.07322090264124,30.07196494749705,30.064704046226627,30.06173594490481,30.054140649887035,30.052330933838224,30.05343132214466,30.0557105515954,30.062605038615835,30.066505929217687,30.070192746255362,30.076381816749194,0,30.096429066940814,0,0,0,0,0,0,0, 30.026035190956698};

    com.google.android.gms.maps.SupportMapFragment SupportMapFragment;
    FusedLocationProviderClient client;
    SharedPreferences preferences, preferences1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //-------------------------------------------------------------------------------
        preferences = getSharedPreferences("ticket",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("qrdata","");
        editor.putString("from","");
        editor.putString("to","");
        editor.apply();
        //----------------------------------
        preferences1 = getSharedPreferences("stay_login",MODE_PRIVATE);
        String Username = preferences1.getString("name","");
        phone  = preferences1.getString("phone", "");
        //----------------------------------
        //-------------------------------------------------------------------------------
        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_bar);
        AddMoney = findViewById(R.id.add_money_button);
        header = navigationView.getHeaderView(0);
        userNameTextView =(header).findViewById(R.id.user_name_text_view_nav);
        navigationBar();
        userNameTextView.setText(Username);
        WhereTo = findViewById(R.id.where_to_button);
        //-------------------------------------------------------------------------------
        SupportMapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.google_map);

        client = LocationServices.getFusedLocationProviderClient(this);
        if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            getCurrentLocation();
        } else {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
        }

        //-------------------------------------------------------------------------------

        userNameTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, UserAccountActivity.class));
            }
        });
        userNameTextView.setText(Username);
        WhereTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,NewTraditionalActivity.class));
            }
        });

        AddMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FawryActivity.class));
            }
        });

    }

    //----------------------------------------------------------------------------------
    private void getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Task<Location> task = client.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    SupportMapFragment.getMapAsync(new OnMapReadyCallback() {
                        @Override
                        public void onMapReady(GoogleMap googleMap) {
                            LatLng latLng=new LatLng(location.getLatitude(),location.getLongitude());
                            double[] diff;
                            Integer id=0;
                            double Difference=Math.sqrt((((latLng.latitude-latitude[0])*(latLng.latitude-latitude[0]))+((latLng.longitude-longitude[0])*(latLng.longitude-longitude[0]))));
                            double New_Difference=0;
                            for(Integer i=0;i<latitude.length;i++){
                                New_Difference=Math.sqrt((((latLng.latitude-latitude[i])*(latLng.latitude-latitude[i]))+((latLng.longitude-longitude[i])*(latLng.longitude-longitude[i]))));
                                if(New_Difference<Difference)
                                {
                                    Difference=New_Difference;
                                    id=i;
                                }
                                else if(New_Difference>Difference){
                                    New_Difference=Difference;

                                }
                            }
                            SharedPreferences pref = getSharedPreferences("station",MODE_PRIVATE);
                            SharedPreferences.Editor edit = pref.edit();
                            edit.putString("base", Stations[id]);
                            edit.apply();
                            Toast.makeText(MainActivity.this, "Nearest Station: " + Stations[id], Toast.LENGTH_LONG).show();
                            MarkerOptions options=new MarkerOptions().position(latLng)
                                    .title("I am there");
                            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,10));
                            googleMap.addMarker(options);
                        }
                    });


                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==44)
        {
            if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                getCurrentLocation();

            }
        }

    }

    //-----------------------------------------------------------------------------------
    public void navigationBar()
    {
        setSupportActionBar(toolbar);
        toggle = new ActionBarDrawerToggle(this,
                drawerLayout,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.tickets_item:
                        startActivity(new Intent(MainActivity.this, TicketActivity.class));
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.setting_item:
                        startActivity(new Intent(MainActivity.this, SettingActivity.class));
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.wallet_item:
                        startActivity(new Intent(MainActivity.this, WalletActivity.class));
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.help_item:
                        startActivity(new Intent(MainActivity.this, HelpActivity.class));
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.about_item:
                        startActivity(new Intent(MainActivity.this, AboutActivity.class));
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.logout_item:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        SharedPreferences.Editor editor = preferences1.edit();
                        editor.putString("remember","false");
                        editor.apply();
                        startActivity(new Intent(MainActivity.this,LoginActivity.class));
                        break;
                }
                return true;
            }
        });
    }
    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }
    }

}