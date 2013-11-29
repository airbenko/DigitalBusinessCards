package com.airbenko.digitalbusinesscards;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Created by davidbenko on 11/29/13.
 */

public class CardStorageController {
    // Our one instance of this class
    private static final CardStorageController INSTANCE = new CardStorageController();

    private final String FILE_PATH = Environment.getExternalStorageDirectory() + "";
    private final String FILE_NAME = "/cards.dat";

    // private constructor so a new CardStorageController isn't called
    private CardStorageController(){}

    // static method to get our one instance
    public static CardStorageController getInstance(){
        return INSTANCE;
    }

    /*
        This method serializes and writes an ArrayList of Cards to a given path
     */
    private void writeCardsToPath(Context c, ArrayList<Card> cards, String path){

        try {
            // check if file exists, if not create it

            if(!Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())){
                Toast.makeText(c, "External SD card not mounted", Toast.LENGTH_LONG).show();
            }

            File file = new File(path);
            if(!file.exists()) {
                file.createNewFile();
            }

            // file output stream to write to a file
            FileOutputStream fos = new FileOutputStream (new File(path),false);//c.openFileOutput(path, Context.MODE_PRIVATE);
            // object output stream to serialize an object
            ObjectOutputStream os = new ObjectOutputStream(fos);
            // write the cards to the file
            os.writeObject(cards);
            // close the stream
            os.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    private ArrayList<Card> getCardsFromPath(Context c, String path){

        // declare our return variable
        ArrayList<Card> cards = null;

        try {
            FileInputStream fis = new FileInputStream (new File(path));
            ObjectInputStream is = new ObjectInputStream(fis);
            cards = (ArrayList<Card>) is.readObject();
            is.close();
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }

        return cards;
    }

    public void writeCards(Context c, ArrayList<Card> cards){
        writeCardsToPath(c,cards,FILE_PATH + FILE_NAME);
    }
    public ArrayList<Card> readCards(Context c){
        return getCardsFromPath(c,FILE_PATH + FILE_NAME);
    }

}
