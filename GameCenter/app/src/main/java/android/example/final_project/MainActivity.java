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
    private LinkedList<Game> games;    //LinkedList 連結串列
    private LinkedList<Game> games2;
    private List<Integer> buyposition;   //紀錄購物車
    private List<Integer> buyedposition; //紀錄已購買
    private List<Integer> BuyArray;
    private List<Integer> BuyedArray;
    private RecyclerView mRecyclerView;
    private DividerItemDecoration mDecoration;
    private LinkedList<Game> GameArray=null;
    private LinkedList<Game> GameArray2=null;
    private GameAdapter mAdapter;
    public static final String EXTRA_MESSAGE
            = "android.example.final_project.extra.MESSAGE";

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
    }
    public void SaveData (){
        Gson gson = new Gson();
        SharedPreferences sharedPreferences = getSharedPreferences( "Games",MODE_PRIVATE);   //產生一個檔名為Games.xml的設定儲存檔，並只供本專案(app)可讀取
        SharedPreferences sharedPreferences2 = getSharedPreferences( "Games2",MODE_PRIVATE);
        SharedPreferences sharedPreferences3 = getSharedPreferences( "Buyposition",MODE_PRIVATE);
        SharedPreferences sharedPreferences4 = getSharedPreferences( "Buyedposition",MODE_PRIVATE);
        SharedPreferences.Editor GamesEditor = sharedPreferences.edit();
        SharedPreferences.Editor GamesEditor2 = sharedPreferences2.edit();
        SharedPreferences.Editor GamesEditor3 = sharedPreferences3.edit();
        SharedPreferences.Editor GamesEditor4 = sharedPreferences4.edit();
        String json = gson.toJson(games);    // Object to Json
        String json2 = gson.toJson(games2);
        String json3 = gson.toJson(buyposition);
        String json4 = gson.toJson(buyedposition);
        GamesEditor.putString("Games",json);   //呼叫Editor的putString()方法將json字串的內容寫入設定檔，資料標籤為”Games”
        GamesEditor2.putString("Games2",json2);
        GamesEditor3.putString("Buyposition",json3);
        GamesEditor4.putString("Buyedposition",json4);
        GamesEditor.apply();
        GamesEditor2.apply();
        GamesEditor3.apply();
        GamesEditor4.apply();   // apply() 採用非同步的方式通常來說效率會更高一些
    }
    public void loadData(){
        Log.d("aaa","aaa");   //僅輸出debug調試 過濾起來可以通過DDMS的Logcat標籤來選擇
        Gson gson = new Gson();
        Log.d("aaa","aaa");
        SharedPreferences sharedPreferences = getSharedPreferences("Games",MODE_PRIVATE);   //MODE_PRIVATE  只允許本應用程式內存取
        SharedPreferences sharedPreferences2 = getSharedPreferences("Games2",MODE_PRIVATE);
        SharedPreferences sharedPreferences3 = getSharedPreferences("Buyposition",MODE_PRIVATE);
        SharedPreferences sharedPreferences4 = getSharedPreferences("Buyedposition",MODE_PRIVATE);
        String json = sharedPreferences.getString("Games",null);    //在同個class =>   null
        String json2 = sharedPreferences2.getString("Games2",null);
        String json3 = sharedPreferences3.getString("Buyposition",null);
        String json4 = sharedPreferences4.getString("Buyedposition",null);
        Type type = new TypeToken<LinkedList<Game>>(){}.getType();
        Type type2 = new TypeToken<ArrayList<Integer>>(){}.getType();
        if(json!=null) GameArray = gson.fromJson(json,type);     //反序列化
        if(json2!=null) GameArray2 = gson.fromJson(json2,type);
        if(json3!=null) BuyArray = gson.fromJson(json3,type2);
        if(json4!=null) BuyedArray = gson.fromJson(json4,type2);
        if(GameArray == null){       //初始化
            GameArray = new LinkedList<Game>();
            String name="俠隱閣(PathOfWuxia)";
            String description="《俠隱閣》是河洛工作室繼《俠客風雲傳》系列後推出的第四部單機作品，本次遊戲再度回歸系列玩家熟悉的養成玩法，以新穎的動漫畫風將武俠世界觀與青春校園生活相結合，扮演初入武林便身懷絕技的的少年俠客，拜入名為俠隱閣的武俠書院，與各色同儕砥礪成長，追尋心目中理想的俠道之路。";
            String image = "android.resource://android.example.final_project/" + R.mipmap.game1_foreground;
            String ingredients = "所有評論： "+ "極度好評 (14,932)\n";
            String Date ="發行日期： " + "2020 年 5 月 1 日\n";
            String Developer ="開發人員： " + "Heluo Studio\n";
            String Publisher ="發行商： " + "香港商河洛互動娛樂股份有限公司\n";
            String procedure = "關於此遊戲\n" +
                    "《俠隱閣》是河洛工作室繼《俠客風雲傳》系列後推出的第四部單機作品，本次遊戲再度回歸系列玩家熟悉的養成玩法，以新穎的動漫畫風將武俠世界觀與青春校園生活相結合，扮演初入武林便身懷絕技的的少年俠客，拜入名為俠隱閣的武俠書院，與各色同儕砥礪成長，追尋心目中理想的俠道之路。\n" +
                    "\n" +
                    "修文習武 學園養成\n" +
                    "由傳說中的東方大俠所創立的名門正派-俠隱閣，在這座以破除武林門戶之見，作育少年英俠為宗旨的武俠書院中，你將接受形形色色師父們的教導，修習高深武藝，最終成為一代大俠。鍛鍊修業、比試考校、陶冶四藝、培養人格、交朋結友、下山行俠，透過豐富的養成指令和事件形塑專屬於你的角色，多采多姿的閣中生活，你會如何度過？\n" +
                    "\n" +
                    "同窗契友 推心置腹\n" +
                    "俠隱閣聚集了來自各門各派，不同身分背景的少年俠士，若是潛心相交，必能成為行俠仗義的一大助力，一同習武修業的這段時間，將是你們了解彼此的寶貴時光，最終，你們將因志同道合結為生死之交？鴛鴦眷侶？抑或⋯⋯因水火不容而反目成仇？\n" +
                    "\n" +
                    "六角戰棋 五行相剋\n" +
                    "經典的六角格戰棋玩法再度回歸，除了六大武器、心法、特質間的自由搭配，側襲、背襲等戰術走位，本次也將引入各角色體內潛藏的五行功體，戰鬥中如何善用相剋之理，揚長抑短，避實擊虛，將會成為勝負關鍵，運用與生俱來的特技-五炁朝元扭轉乾坤，與少俠們一同齊心協力，突破眼前難關！\n" +
                    "\n" +
                    "追本溯源 俠道為何\n" +
                    "一步步通過閣中考驗，你也將逐漸發掘塵封已久的武林過往，所謂白日當空，晦暗猶生，如今平靜無波的江湖，實則是暗潮洶湧，山雨欲來，身為濟弱扶傾的俠客，你的「行俠仗義」將決定許多人的命運，在一切分崩離析之際，你又能否直面過往作為，堅守心中俠道？\n";
            GameArray.add(new Game(name, description, image, ingredients, procedure,Date,Developer,Publisher));
            name="Destiny 2";
            description="潛入《天命2》免費玩的遊戲世界，盡情體驗反應熱烈的第一人稱射擊（FPS）戰鬥，探索在我們太陽系中的奧秘，自訂獨特的裝備給你的守護者，並釋放元素能力對抗強大的敵人。";
            image = "android.resource://android.example.final_project/" + R.mipmap.destiny_foreground;
            ingredients = "所有評論： "+ "極度好評 (240,876)\n";
            Date ="發行日期： " + "2019 年 10 月 1 日\n";
            Developer ="開發人員： " + "Bungie\n";
            Publisher ="發行商： " + "Bungie\n";
            procedure = "關於此遊戲\n" +
                    "潛入《天命2》免費玩的遊戲世界，盡情體驗反應熱烈的第一人稱射擊戰鬥，探索在我們太陽系中的奧秘，並釋放元素能力對抗強大的敵人。今天就下載遊戲，創造自己的守護者並收集獨一無二的武器、防具和裝備，自訂你的外觀和玩法風格。獨自一人或與朋友一起體驗《天命2》電影般的劇情，加入其他守護者，執行具有挑戰性的合作任務，或在各種PvP模式下與朋友對戰。由你決定自己的傳說。\n" +
                    "特色：\n" +
                    "引人入勝的劇情\n" +
                    "• 保衛人類的最終城邦對抗太陽系以外的黑暗勢力\n" +
                    "守護者職業\n" +
                    "• 從全副武裝的泰坦、神秘的術士或迅捷的獵人中選擇\n" +
                    "團隊合作和競賽對戰多人玩家模式\n" +
                    "• 在各種PvE與PvP遊戲模式中，和好友一起玩，或是對戰朋友和其他守護者\n" +
                    "異域武器與防具\n" +
                    "• 收集一系列令人讚嘆的武器與防具，自訂你的戰鬥風格\n";
            GameArray.add(new Game(name, description, image, ingredients, procedure,Date,Developer,Publisher));
            name="《終極動員令》重製典藏版";
            description="《終極動員令》與《紅色警戒》將推出 4K 復刻版，這些是由前任 Westwood Studios 成員所打造。包含全 3 款擴充包、重置版多人模式、現代版介面、地圖編輯器、額外影片相簿和超過 7 小時的復刻版音樂。";
            image = "android.resource://android.example.final_project/" + R.mipmap.command_foreground;
            ingredients = "所有評論： " + "極度好評 (7,955)\n";
            Date ="發行日期： "  + "2020 年 6 月 6 日\n";
            Developer ="開發人員： " + "Petroglyph, Lemon Sky Studios\n";
            Publisher ="發行商： " + "Electronic Arts\n";
            procedure = "關於此遊戲\n" +
                    "《終極動員令》與《紅色警戒》在 25 年前奠定了即時戰略遊戲，現在，這兩款遊戲將推出完整的 4K 復刻版，由目前在 Petroglyph Games 的前任 Westwood Studios 成員所打造。包含全 3 款擴充包、重置版多人模式、現代版介面、地圖編輯器、過去未曾推出的全動態影像相簿和由 Frank Klepacki 所打造且超過 7 小時的經典復刻版音樂。歡迎回來，指揮官。\n" +
                    "\n" +
                    "\n" +
                    "新增與重製功能\n" +
                    "圖像切換 — 在單人模式中即時切換舊版與重製版 4K 圖像。\n" +
                    "額外相簿 — 包含 4 小時的幕後花絮、製作過程相片和未曾推出的音樂。\n";
            GameArray.add(new Game(name, description, image, ingredients, procedure,Date,Developer,Publisher));
            name="Legendary Knight - 傳奇騎士";
            description="敵人大舉入侵我們的領土，國王帶領一眾手下極力抵抗。國王擔當起保衛家園的重任，誓要將一波又一波的敵軍驅逐出我國國土，並且守護象徵著我國統治權力的神聖雕像。";
            image = "android.resource://android.example.final_project/" + R.mipmap.legendaryknight_foreground;
            ingredients = "所有評論： " + "好評 (31)\n";
            Date ="發行日期： "  + "2020 年 2 月 29 日\n";
            Developer ="開發人員： " + "Oki Aki\n";
            Publisher ="發行商： " + "Oki Game Lab\n";
            procedure = "關於此遊戲\n" +
                    "敵人大舉入侵我們的領土，國王帶領一眾手下極力抵抗。國王擔當起保衛家園的重任，誓要將一波又一波的敵軍驅逐出我國國土，並且守護象徵著我國統治權力的神聖雕像。\n" +
                    "\n" +
                    "在預備時間中，國王可以\n" +
                    "購買各種道具\n" +
                    "培訓軍隊增強戰鬥力\n" +
                    "指派將軍和他的部下保護重要建築物\n" +
                    "使用在戰場上獲得的經驗值學習不同技能來提升自己和手下的戰鬥能力\n" +
                    "\n" +
                    "通過所有回合是你唯一獲得勝利的方法。「村莊」共有25 個回合而「要塞」則有40 個回合。假如雕像被摧毁或國王戰死，你就會立即戰敗。\n" +
                    "\n" +
                    "祝你武運昌隆！\n";
            GameArray.add(new Game(name, description, image, ingredients, procedure,Date,Developer,Publisher));
            name="Ori and the Will of the Wisps";
            description="在廣闊的奇幻世界中開啟全新旅程，征服強大的敵人，解開極具挑戰性的難題，在一次次任務中，揭開奧裡的命運。";
            image ="android.resource://android.example.final_project/" + R.mipmap.game4_foreground;
            ingredients = "所有評論： "  + "極度好評 (27,332)\n";
            Date ="發行日期： "  + "2020 年 3 月 11 日\n";
            Developer ="開發人員： " + "Moon Studios GmbH\n";
            Publisher ="發行商： " + "Xbox Game Studios\n";
            procedure = "關於此遊戲\n" +
                    "Ori and the Will of the Wisps 是一款必玩的遊戲*：\n" +
                    "• 98/100 GAMESBEAT「…令人精神一振，情感滿溢的傑作」\n" +
                    "• 9.5/10 GAMEINFORMER「劇情棒呆了，遊戲世界美得令人屏息」\n" +
                    "• 9/10 IGN「最值得盛讚的續作」\n" +
                    "• 9.5/10 DESTRUCTOID「早早為未來十年樹立經典」\n" +
                    "• 4.5/5 WINDOWS CENTRAL「令人印象深刻的極出色遊戲」\n" +
                    "• 90/100 GAMERS HEROES「Ori and the Will of the Wisps 是洋溢熱情，用心打造的遊戲。」\n" +
                    "• 9/10 PRESS START AUS「結局將填滿您的心靈，使其充滿對生命的熱情。」\n" +
                    "• 9/10 AUS GAMERS\n" +
                    "• 9/10 EUROGAMER ITALY\n" +
                    "• 91/100 GAMESTAR.DE\n" +
                    "• 90/100 ATOMIX\n" +
                    "• 5/5 HARDCORE GAMER\n" +
                    "• 9.4/10 VANDAL\n" +
                    "• 9/10 VIDEOGAMER\n" +
                    "• 5/5 DAILY STAR：「一款精心製作、觸動人心的大作，讓 Metroidvania (類銀河戰士惡魔城) 遊戲達到另一個境界」\n" +
                    "• 9.2/10 MERISTATION\n" +
                    "• 9/10 GAMESPEW 「Ori and the Will of the Wisps 或許是我玩過最華麗的遊戲。」\n" +
                    "• 9.8/10 THE GAMES MACHINE\n" +
                    "• 4.5/5 SCREEN RANT 「令人讚歎不已的續作」\n" +
                    "• 9.5/10 EASYALLIES 「你絕對不想錯過的精彩遊戲。」\n" +
                    "• 9.2/10 GAMERSKY\n" +
                    "• 4.5/5 TWINFINITE 「一場奇幻般的 Metroidvania 冒險」\n" +
                    "• 94/100 COGconnected\n" +
                    "\n" +
                    "*評分和引述來源 Windows PC 版和/或主機版遊戲。資料來源 Metacritic.com 2020/03/17\n" +
                    "\n" +
                    "小精靈奧里對危機可不陌生，但在一次命運的飛行中，小貓頭鷹小黑陷入危機，重聚家庭、重塑破碎的世界並揭開奧里的真正命運，可不是只有勇敢就可以做到的。知名動作平台遊戲大作，Ori and the Blind Forest 的創作群推出令人高度期待的續作。投身滿是新朋友和敵人的廣大世界，享受令人驚艷的手繪畫面，開啟一個全新的冒險旅程。除了契合遊戲的原創音樂之外，Ori and the Will of the Wisps 也延續了 Moon Studios 精緻平台遊戲與深情敘事手法的傳統\n";
            GameArray.add(new Game(name, description, image, ingredients, procedure,Date,Developer,Publisher));
        }
        if(GameArray2 ==null){
            GameArray2 = new LinkedList<Game>();
        }
        if(BuyArray == null)
        {
            BuyArray=new ArrayList<Integer>();
        }
        if(BuyedArray == null)
        {
            BuyedArray=new ArrayList<Integer>();
        }
    }
}
