package android.example.final_project;

import java.util.LinkedList;
import java.util.List;

public class Data {
    static LinkedList<Game> games;
    static LinkedList<Game> games2;
    static List<Integer> buyposition;
    static List<Integer> buyedposition;
    static LinkedList<Game> getGame() {return games;}
    static LinkedList<Game> getGame2() {return games2;}
    static List<Integer> getBuyposition() {return buyposition;}
    static List<Integer> getBuyedposition() {return buyedposition;}
    static void LoadGame(LinkedList<Game> Save) {games = Save;}
    static void LoadGame2(LinkedList<Game> Save) {games2 = Save;}
    static void LoadBuy(List<Integer> Save) {buyposition = Save;}
    static void LoadBuyed(List<Integer> Save) {buyedposition = Save;}
    static void Addposition(int position) {buyposition.add(position);}
    static void Addbuyedposition(int position) {buyedposition.add(position);}
    static void AddGame2(Game game) {games2.add(game);}

    }
