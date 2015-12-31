package com.zhg.api.samples.design;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridLayout;
import android.widget.Toast;

import com.zhg.api.samples.R;

public class ToolbarActivity extends AppCompatActivity {
    private GridLayout gridLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar);
        gridLayout= (GridLayout) findViewById(R.id.id_gridLayout);
        Toolbar toolbar= (Toolbar) findViewById(R.id.id_toolbar);
        toolbar.setLogo(R.mipmap.ic_launcher);
        toolbar.setNavigationIcon(R.mipmap.nav_logo);
        toolbar.setTitle("title");
        toolbar.setSubtitle("sub Title");


        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ToolbarActivity.this,"click navigationBar",Toast.LENGTH_SHORT).show();
            }
        });
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getItemId()==R.id.id_menu_1){
                    Snackbar.make(gridLayout,"this is a snackbar",Snackbar.LENGTH_SHORT)
                            .setAction("click me", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Log.e("info","Hey,do not click me");
                                }
                            }).show();
                    return true;
                }
                return false;
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.id_menu_1){
            Toast.makeText(this,"id_menu_1",Toast.LENGTH_SHORT).show();
            return true;
        }else if(id==R.id.id_menu_2){
            Toast.makeText(this,"id_menu_2",Toast.LENGTH_SHORT).show();
            return true;
        }else if(id==R.id.id_menu_3){
            Toast.makeText(this,"id_menu_3",Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
