package android.example.final_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class GiveActivity extends AppCompatActivity {

    private EditText name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_give);
        name = findViewById(R.id.name_text);
    }
    public void Give (View view)
    {
        Intent intent = new Intent(this, BuyActivity.class);
        intent.putExtra("function","購買成功，已送給"+name.getText());
        startActivity(intent);
    }
}
