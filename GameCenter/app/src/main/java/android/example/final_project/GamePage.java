package android.example.final_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class GamePage extends AppCompatActivity {

    private TextView info;
    private ImageView imageView;
    private int position ;
    private Button buy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_page);
        imageView = findViewById(R.id.imageView);
        info = findViewById(R.id.TextView);
        buy = findViewById(R.id.Buy);
        Intent intent = getIntent();
        position = intent.getIntExtra(MainActivity.EXTRA_MESSAGE,0);
        try {
            DisplayGame();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void DisplayGame() throws IOException {
        if(Data.getBuyposition().contains(position) || Data.getBuyedposition().contains(position)) buy.setEnabled(false);
        Game game = Data.getGame().get(position);
        Uri uri = Uri.parse(game.image);
        InputStream stream = getContentResolver().openInputStream(uri);
        Bitmap bitmap = BitmapFactory.decodeStream(stream);
        info.setText(game.ingredient+game.Date+game.Developer+game.Publisher + "\n\n" + game.procedure);
        stream.close();
        imageView.setImageBitmap(bitmap);
    }
    public void Buy(View view)
    {
        Intent intent = new Intent(this, BuyActivity.class);
        intent.putExtra("function","加入購物車");
        intent.putExtra("position",position);
        startActivity(intent);
    }
    public void Give(View view)
    {
        Intent intent = new Intent(this, GiveActivity.class);
        startActivity(intent);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.buycar:
                Intent intent = new Intent(this, BuyCar.class);
                startActivity(intent);
                break;
            default:
        }
        return true;
    }
}
