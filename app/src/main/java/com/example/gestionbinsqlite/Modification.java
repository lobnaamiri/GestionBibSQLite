package com.example.gestionbinsqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Modification extends AppCompatActivity {

    @BindView(R.id.spLivre)
    Spinner spLivre;
    @BindView(R.id.edTitre)
    EditText edTitre;
    @BindView(R.id.edNb)
    EditText edNb;
    @BindView(R.id.btnModifier)
    Button btnModifier;
    @BindView(R.id.btnRetour)
    Button btnRetour;
    private ArrayAdapter<Livre> adpLivre;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modification);
        ButterKnife.bind(this);

        adpLivre = new ArrayAdapter<Livre>(getApplicationContext(), android.R.layout.simple_list_item_1);
        spLivre.setAdapter(adpLivre);


        remplir();

        spLivre.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Livre l = (Livre) spLivre.getSelectedItem();
                edTitre.setText(l.getTitre());
                edNb.setText(l.getNbpage()+"");

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @OnClick({R.id.btnModifier, R.id.btnRetour})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnModifier:
                modifier();
                break;
            case R.id.btnRetour:
                finish();
                break;
        }
    }
    private void remplir() {
        SGLiteBib b = new SGLiteBib(getApplicationContext(), "biblio.db", null, 1);
        SQLiteDatabase db = b.getReadableDatabase();
        Cursor c = db.query("livre", new String[]{"id", "titre", "nbrePages"},"",null,null,null,null);
        adpLivre.clear();
        while (c.moveToNext()){
            int id=c.getInt(0);
            String titre=c.getString(1);
            int nbpages=c.getInt(2);
            Livre l=new Livre(id,titre,nbpages);
            adpLivre.add(l);
        }
        db.close();
    }


    private void modifier() {
        SGLiteBib b = new SGLiteBib(getApplicationContext(), "biblio.db", null, 1);
        SQLiteDatabase db = b.getWritableDatabase();
        ContentValues cv = new ContentValues();
        Livre l = (Livre) spLivre.getSelectedItem();

        cv.put("titre", edTitre.getText().toString());
        cv.put("nbrePages", Integer.parseInt(edNb.getText().toString()));
        db.update("livre", cv, "id="+l.getId(), null);
        db.close();
        remplir();



    }
}
