package com.example.gestionbinsqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Suppression extends AppCompatActivity {

    @BindView(R.id.spLivre)
    Spinner spLivre;
    @BindView(R.id.btnSupp)
    Button btnSupp;
    @BindView(R.id.btnRetour)
    Button btnRetour;
    private ArrayAdapter<Livre> adpLivre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suppression);
        ButterKnife.bind(this);
        adpLivre = new ArrayAdapter<Livre>(getApplicationContext(), android.R.layout.simple_list_item_1);
        spLivre.setAdapter(adpLivre);
        remplir();
    }

    @OnClick({R.id.btnSupp, R.id.btnRetour})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnSupp:
                supprimer();
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
    private void supprimer() {
        SGLiteBib b = new SGLiteBib(getApplicationContext(), "biblio.db", null, 1);
        SQLiteDatabase db = b.getReadableDatabase();
        Livre l = (Livre) spLivre.getSelectedItem();
        db=b.getReadableDatabase();
        db.delete("livre", "id="+l.getId(), null);
        db.close();
        remplir();
    }
}
