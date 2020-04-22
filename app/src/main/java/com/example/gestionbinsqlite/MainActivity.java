package com.example.gestionbinsqlite;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btnAjout)
    Button btnAjout;
    @BindView(R.id.btnModif)
    Button btnModif;
    @BindView(R.id.btnSupp)
    Button btnSupp;
    @BindView(R.id.btnConsult)
    Button btnConsult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btnAjout, R.id.btnModif, R.id.btnSupp, R.id.btnConsult})
    public void onViewClicked(View view) {
        Intent i=null;
        switch (view.getId()) {
            case R.id.btnAjout:
                i=new Intent(MainActivity.this, Ajout.class);
                break;
            case R.id.btnModif:
                i=new Intent(MainActivity.this, Modification.class);
                break;
            case R.id.btnSupp:
                i=new Intent(MainActivity.this, Suppression.class);
                break;
            case R.id.btnConsult:
                i=new Intent(MainActivity.this, Consultation.class);
                break;
        }
        startActivity(i);
    }
}
