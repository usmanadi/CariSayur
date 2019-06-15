package com.example.usman.carisayur.activity;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.usman.carisayur.R;
import com.example.usman.carisayur.model.bobot;

public class BobotActivity extends AppCompatActivity {

    private Button btnNext;
    private int vJarak = 0, vHarga = 0, vSayur = 0, vRating = 0;
    public bobot Bobot;
    private EditText inJarak, inHarga, inJumlahSayur, inRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_bobot);
        setTitle("Bobot");

        inJarak = (EditText)findViewById(R.id.inJarak);
        inHarga = (EditText)findViewById(R.id.inHarga);
        inJumlahSayur = (EditText)findViewById(R.id.inJumlahSayur);
        inRating = (EditText)findViewById(R.id.inRating);

        btnNext = findViewById(R.id.next);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Asu", "Asu Harga "+inHarga.getText());
                if (isBobotEmpty()){
                    Toast.makeText(getApplicationContext(), "Tidak Boleh Ada Field Yang Kosong ", Toast.LENGTH_LONG).show();
                }else {
                    launchPilihAlternatif();
                }
            }
        });

    }

    private boolean isBobotEmpty(){
        String textJarak = inJarak.getText().toString();
        if (!"".equals(textJarak.trim())){
            vJarak =  Integer.parseInt( inJarak.getText().toString());
        }
        String textHarga = inHarga.getText().toString();
        if (!"".equals(textHarga.trim())){
            vHarga =  Integer.parseInt( inHarga.getText().toString());
        }

        String textSayur = inJumlahSayur.getText().toString();
        if (!"".equals(textSayur.trim())){
            vSayur =  Integer.parseInt( inJumlahSayur.getText().toString());
        }

        String textRating = inRating.getText().toString();
        if (!"".equals(textRating.trim())){
            vRating =  Integer.parseInt( inRating.getText().toString());
        }

        if (vJarak==0 || vHarga == 0 || vSayur == 0 || vRating == 0){
            return true;
        }else return false;
    }

    private void launchPilihAlternatif() {
        Log.d("launch activity", "Button clicked!");

        float jarak = Float.parseFloat(String.valueOf(vJarak));
        float harga = Float.parseFloat(String.valueOf(vHarga));
        float jenis_sayur = Float.parseFloat(String.valueOf(vSayur));
        float rating = Float.parseFloat(String.valueOf(vRating));

        setBobot(jarak, harga, jenis_sayur, rating);

        Intent intent = new Intent(this, AlternatifActivity.class);

        startActivity(intent);
    }

    private void setBobot(float jarak, float harga, float jenis_sayur, float rating){
        Bobot = new bobot(jarak, harga, jenis_sayur, rating);
    }

}
