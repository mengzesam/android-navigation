package com.mzs.androidnavigation;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.widget.Toolbar;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.Set;



public class MainActivity extends AppCompatActivity {
    private AppBarConfiguration appBarConfiguration;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_activity);
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        NavHostFragment host=(NavHostFragment)getSupportFragmentManager().findFragmentById(R.id.my_nav_host_fragment);
        NavController navController=host.getNavController();
        DrawerLayout drawerLayout=findViewById(R.id.drawer_layout);
        appBarConfiguration= new AppBarConfiguration.Builder(
                R.id.home_dest,R.id.deeplink_dest, R.id.settings_dest
        ).setOpenableLayout(drawerLayout)
                .build();
        NavigationUI.setupActionBarWithNavController(this,navController,appBarConfiguration);
        NavigationView sideNavView=findViewById(R.id.nav_view);
        if(sideNavView != null) {
            NavigationUI.setupWithNavController(sideNavView, navController);
        }
        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav_view);
        if(bottomNav != null) {
            NavigationUI.setupWithNavController(bottomNav, navController);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        boolean retValue=super.onCreateOptionsMenu(menu);
        NavigationView navigationView=findViewById(R.id.nav_view);
 //       if(navigationView==null){
            getMenuInflater().inflate(R.menu.overflow_menu,menu);
//            return true;
//        }
        return retValue;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        NavHostFragment host=(NavHostFragment)getSupportFragmentManager().findFragmentById(R.id.my_nav_host_fragment);
        NavController navController=host.getNavController();
        if(item.getItemId()==R.id.settings_dest){
            navController.navigate(R.id.settings_dest);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController= Navigation.findNavController(this, R.id.my_nav_host_fragment);
        return NavigationUI.navigateUp(navController,appBarConfiguration)
                ||super.onSupportNavigateUp();
    }

}
