package com.example.frankkang.dictionaryquizjapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {
    private static final int ADD_WORD_ACT_CODE = 12345;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void addWordClick(View view) {
        Intent intent = new Intent(this, AddWordsActivity.class);
        startActivityForResult(intent, ADD_WORD_ACT_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if (requestCode == ADD_WORD_ACT_CODE) {
            //im coming back from add word activity
            String word = intent.getStringExtra("word");
            String defn = intent.getStringExtra("definition");
            CharSequence text = "You want to add the word " + word + ", with definition " + defn;
            Toast toast = Toast.makeText(this, text, Toast.LENGTH_SHORT);
            toast.show();
        }
    }


    public void playGameClick(View view) {
        Intent intent = new Intent(this, DictionaryQuiz.class);
        String firstWord = "create";
        intent.putExtra("firstWord", firstWord);
        startActivity(intent);

    }
}
