package com.example.gestionbinsqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Consultation extends AppCompatActivity {

    @BindView(R.id.btnRetour)
    Button btnRetour;
    @BindView(R.id.lvLivre)
    ListView lvLivre;
    private ArrayAdapter<Livre> adpLivre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultation);
        ButterKnife.bind(this);

        adpLivre = new ArrayAdapter<Livre>(getApplicationContext(), android.R.layout.simple_list_item_1);
        lvLivre.setAdapter(adpLivre);
        remplir();
    }

    private void remplir() {
        SGLiteBib b = new SGLiteBib(getApplicationContext(), "biblio.db", null, 1);
        SQLiteDatabase db = b.getReadableDatabase();
        Cursor c = db.query("livre", new String[]{"id", "titre", "nbrePages"}, "", null, null, null, null);
        while (c.moveToNext()){
            int id=c.getInt(0);
            String titre=c.getString(1);
            int nbpages=c.getInt(2);
            Livre l=new Livre(id, titre, nbpages);
            adpLivre.add(l);
        }
        db.close();
    }

    @OnClick(R.id.btnRetour)
    public void onViewClicked() {
        finish();
    }
}
