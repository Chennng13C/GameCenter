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
        setContentView(R.layout.activity_game_page);//連結xml
        imageView = findViewById(R.id.imageView);//圖片
        info = findViewById(R.id.TextView);//遊戲敘述
        buy = findViewById(R.id.Buy);//購買按鈕
        Intent intent = getIntent();
        position = intent.getIntExtra(MainActivity.EXTRA_MESSAGE,0);//得到點選遊戲的position
        try {
            DisplayGame();      //顯示遊戲敘述
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void DisplayGame() throws IOException {     //private 所以不會和BuyActivity的 DisplayGame function衝突

        if(Data.getBuyposition().contains(position) || Data.getBuyedposition().contains(position))
            buy.setEnabled(false);  //如果點選的遊戲(position)在購物車或已購買過則讓按鈕無法點選

        Game game = Data.getGame().get(position);

        Uri uri = Uri.parse(game.image);//得到圖片路徑
        InputStream stream = getContentResolver().openInputStream(uri); //讀入
        Bitmap bitmap = BitmapFactory.decodeStream(stream);
        imageView.setImageBitmap(bitmap);                               //顯示

        info.setText(game.ingredient+game.Date+game.Developer+game.Publisher + "\n\n" + game.procedure);
                                                                        //顯示遊戲敘述
        stream.close();

    }
    public void Buy(View view)      //由activity_game_page.xml button onClick 觸發
    {
        Intent intent = new Intent(this, BuyActivity.class);    //跳轉到購買頁面
        intent.putExtra("function","加入購物車");    //將資料一同傳送過去
        intent.putExtra("position",position);
        startActivity(intent);  //啟動
    }
    public void Give(View view)     //由activity_game_page.xml button onClick 觸發
    {
        Intent intent = new Intent(this, GiveActivity.class);   //跳轉到送禮頁面
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {         // 設置要用哪個menu檔做為選單
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.buycar){                               //如果點選購物車圖案
            Intent intent = new Intent(this, BuyCar.class);   //切換到購物車畫面
            startActivity(intent);
        }
        return true;
    }
}
