package com.example.gestionbinsqlite;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Ajout extends AppCompatActivity {

    @BindView(R.id.edTitre)
    EditText edTitre;
    @BindView(R.id.ednNb)
    EditText ednNb;
    @BindView(R.id.btnValider)
    Button btnValider;
    @BindView(R.id.btnRetour)
    Button btnRetour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btnValider, R.id.btnRetour})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnValider:
                Ajouter();
                break;
            case R.id.btnRetour:
                finish();
                break;
        }
    }

    private void Ajouter() {
        String titre = edTitre.getText().toString();
        String nb = ednNb.getText().toString();
        if (!titre.equals("") && !nb.equals(""))
        {
            SGLiteBib b = new SGLiteBib(getApplicationContext(), "biblio.db", null, 1);
            SQLiteDatabase db = b.getWritableDatabase();
            ContentValues cv  = new ContentValues();
            cv.put("titre", titre);
            cv.put("nbrePages", Integer.parseInt(nb));
            db.insert("livre", null, cv);
            db.close();
            ednNb.setText("");
            edTitre.setText("");
            Toast.makeText(getApplicationContext(), "Livre ajouté", Toast.LENGTH_SHORT).show();

    }
}
}