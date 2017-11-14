package com.example.frankkang.dictionaryquizjapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddWordsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_words);
    }

    public void okClick(View view) {
        EditText wordTextBox = (EditText) findViewById(R.id.new_word);
        EditText defnTextBox = (EditText) findViewById(R.id.new_defn);

        String word = wordTextBox.getText().toString();
        String defn = defnTextBox.getText().toString();
        Intent intent = new Intent();
        intent.putExtra("word", word);
        intent.putExtra("definition", defn);
        setResult(RESULT_OK, intent);
        finish();
    }
}
