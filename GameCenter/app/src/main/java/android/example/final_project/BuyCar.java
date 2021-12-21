package android.example.final_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;

public class BuyCar extends AppCompatActivity {
    private LinkedList<Game> games2;
    private RecyclerView mRecyclerView;
    private DividerItemDecoration mDecoration;
    private GameAdapter mAdapter;
    private List<Integer> Buyposition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_car);
        games2 = Data.getGame2();
        Buyposition =Data.getBuyposition();
        mRecyclerView = findViewById(R.id.RecyclerView);
        mAdapter = new GameAdapter(this, games2);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mDecoration = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
        mRecyclerView.addItemDecoration(mDecoration);
    }
    public void Jump(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void buy(View view)
    {
        AlertDialog.Builder dialog = new AlertDialog.Builder(BuyCar.this);
        dialog.setTitle("確認");
        dialog.setMessage("確定要購買以上"+games2.size()+"件遊戲？");
        dialog.setNegativeButton("是",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                Toast.makeText(BuyCar.this, "已購買上述"+games2.size()+"件遊戲",Toast.LENGTH_SHORT).show();
                Data.getGame2().clear();
                for (int i =0;i<Buyposition.size();i++) {
                    Data.Addbuyedposition(Buyposition.get(i));
                }
                Jump();
            }

        });
        dialog.setPositiveButton("否",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
            }
        });
        dialog.show();

    }
    public void Clean(View view)
    {
        AlertDialog.Builder dialog = new AlertDialog.Builder(BuyCar.this);
        dialog.setTitle("確認");
        dialog.setMessage("確定要清除購物車");
        dialog.setNegativeButton("是",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                Toast.makeText(BuyCar.this, "清除成功",Toast.LENGTH_SHORT).show();
                Data.getGame2().clear();
                Data.getBuyposition().clear();
                Jump();
            }

        });
        dialog.setPositiveButton("否",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
            }
        });
        dialog.show();
    }
}
