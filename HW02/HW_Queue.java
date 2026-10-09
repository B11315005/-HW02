package 資料結構.HW02;

import java.util.Random;
import queue.LinkedQueue;
enum Suit { Spades, Hearts, Diamonds, Clubs }

class Card {
    Suit suit;
    int number;
    Card(Suit s, int n) { suit = s; number = n; }
}

public class HW_Queue {

    static void printCards(Card[] cards) {	//跑過card的花色和點數
        for (int k = 0; k < cards.length; k++) {
            System.out.println(cards[k].suit + ", " + cards[k].number+"");
        }
    }

    static void shuffle(Card[] cards) {		//將牌打亂
        for (int k = 0; k < 52; k++) {
            int i = (int)(Math.random() * 52); //隨機產生0-51，每次執行都會隨機產生
            int j = (int)(Math.random() * 52);
            Card temp = cards[i]; 	//遞迴：將順序i,j對調，用temp當媒介，讓i和j可以交換
            cards[i] = cards[j];	
            cards[j] = temp;
        }
    }

    static void dealCards(Card[] cards, LinkedQueue<Card>[] players) {
        for (int k = 0; k < cards.length; k++) {	//根據卡片的位置長度
            players[ k%4 ].append( cards[k] );	
        }
    }

    public static void main(String[] args) {
        Card[] cards = new Card[52];	//新增52個空間陣列
        int n = 0;	
        for (Suit s : Suit.values()) {	//S:
            for (int i = 1; i <= 13; i++) {
                cards[n] = new Card(s, i);
                n++;
            }
        }
        printCards(cards);
        shuffle(cards);

        LinkedQueue<Card>[] players = new LinkedQueue[4];	//開四個空位置，裡面是null
        for (int p = 0; p < 4 ; p++) {
            players[p] = new LinkedQueue<Card>();	
        }

        dealCards(cards, players);

        for (int p = 0; p < 4; p++) {
            System.out.print("家 " + p + ":");
            players[p].forEach(c -> System.out.print(c.suit+" "+c.number+" " ));	//對佇列裡面的花色和點數做印出
            System.out.println();
        }
    }
}