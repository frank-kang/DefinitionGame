package com.example.frankkang.dictionaryquizjapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

import stanford.androidlib.SimpleActivity;
import stanford.androidlib.SimpleList;

public class DictionaryQuiz extends AppCompatActivity {

    // fields (instance variables) to store map of (word => definition),
    // overall list of words,
    // ArrayList => ListVeiw adapter,
    // and list of five definations that user must pick from
    private HashMap<String, String> dictionary;
    private ArrayList<String> list;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> fiveDfns;
    private String theWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary_quiz);

        Intent intent = new Intent();
        String firstWord = intent.getStringExtra("firstWord");

        dictionary = new HashMap<String, String>();
        list = new ArrayList<String>();
        fiveDfns = new ArrayList<>();
        readWordsFromFile();
        pickRandomWords(firstWord);

    }

    private void readWordsFromFile() {
        Scanner scan = new Scanner( getResources().openRawResource(R.raw.words));
        scan.useDelimiter("\t");
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            String[] parts = line.split("\t");
            if (parts.length >= 2) {
                String word = parts[0];
                String defn = parts[1];

                list.add(word);
                dictionary.put(word, defn);
            }
        }
    }

    private void pickRandomWords(String firstWord) {
        ArrayList<String> fiveWords = new ArrayList<String>();
        Collections.shuffle(list);

        int wordsToAdd = 5;

        if (firstWord != null) {
            fiveWords.add(firstWord);
            wordsToAdd--;
        }

        for (int i = 0; i < wordsToAdd; i++) {
            fiveWords.add(list.get(i));
        }

        theWord = fiveWords.get(0);

        fiveDfns.clear();

        fiveDfns = new ArrayList<String>();
        for (String word : fiveWords) {
            fiveDfns.add(dictionary.get(word));
        }

        Collections.shuffle(fiveDfns);
        showWhatIPicked();
    }

    private void showWhatIPicked() {
        // have to have adapter for ListView, ugh
        TextView theWordView = (TextView) findViewById(R.id.the_word);
        theWordView.setText(theWord);
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                fiveDfns);

        ListView listView = (ListView) findViewById(R.id.defn_list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String defnClicked = fiveDfns.get(position);
                String rightAnswer = dictionary.get(theWord);
                if (defnClicked.equals(rightAnswer)) {
                    Toast.makeText(DictionaryQuiz.this, "You are awesome!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(DictionaryQuiz.this, "Wrong", Toast.LENGTH_SHORT).show();
                }
                pickRandomWords(null);
            }
        });

    }
}
