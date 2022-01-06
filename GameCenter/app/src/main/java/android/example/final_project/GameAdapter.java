package android.example.final_project;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.GameViewHolder> {

    private LinkedList<Game> mGames;
    private LayoutInflater mInflater;
    private Context context;

    class GameViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final TextView Game_title;
        public final TextView Game_content;
        public final ImageView GameImage;
        final GameAdapter mAdapter;

        public GameViewHolder(View itemView, GameAdapter adapter) {
            super(itemView);
            Game_title = itemView.findViewById(R.id.game_title);
            Game_content = itemView.findViewById(R.id.game_content);
            GameImage = itemView.findViewById(R.id.image);
            itemView.setOnClickListener(this);
            this.mAdapter = adapter;
        }
        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Intent intent = new Intent(context, GamePage.class);
            intent.putExtra(MainActivity.EXTRA_MESSAGE, position);
            context.startActivity(intent);
        }
    }
    public GameAdapter(Context context,LinkedList<Game> games){
        mInflater = LayoutInflater.from(context);
        this.mGames = games;
        this.context = context;
    }

    @NonNull
    @Override
    public GameAdapter.GameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.game, parent, false);
        return new GameViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull GameViewHolder holder, int position){
        Game game = mGames.get(position);
        try {
            Uri uri = Uri.parse(game.image);
            InputStream stream = context.getContentResolver().openInputStream(uri);
            Bitmap bitmap = BitmapFactory.decodeStream(stream);
            stream.close();
            holder.GameImage.setImageBitmap(bitmap);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        holder.Game_title.setText(game.name);
        holder.Game_content.setText(game.description);
    }

    @Override
    public int getItemCount() {
        return mGames.size();
    }
}
