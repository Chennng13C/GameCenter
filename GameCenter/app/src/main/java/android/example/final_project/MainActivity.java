package android.example.final_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private LinkedList<Game> games;
    private LinkedList<Game> games2;
    private List<Integer> buyposition;
    private List<Integer> buyedposition;
    private List<Integer> BuyArray;
    private List<Integer> BuyedArray;
    private RecyclerView mRecyclerView;
    private DividerItemDecoration mDecoration;
    private LinkedList<Game> GameArray=null;
    private LinkedList<Game> GameArray2=null;
    private GameAdapter mAdapter;
    public static final String EXTRA_MESSAGE
            = "android.example.a107590033_107590039.extra.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.RecyclerView);
        games = Data.getGame();
        games2 = Data.getGame2();
        buyposition = Data.getBuyposition();
        buyedposition = Data.getBuyedposition();
        if(games!=null && games2 !=null )SaveData();
        loadData();
        Data.LoadGame(GameArray);
        Data.LoadGame2(GameArray2);
        Data.LoadBuy(BuyArray);
        Data.LoadBuyed(BuyedArray);
        games = Data.getGame();
        games2 = Data.getGame2();
        mAdapter = new GameAdapter(this, games);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mDecoration = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
        mRecyclerView.addItemDecoration(mDecoration);
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
    }}
  