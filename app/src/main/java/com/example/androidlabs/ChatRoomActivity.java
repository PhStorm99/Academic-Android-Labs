package com.example.androidlabs;


import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;



public class ChatRoomActivity extends AppCompatActivity {

    ListView listView;
    EditText editText;
    List<Message> listMessage = new ArrayList<>();
    Button sendBtn;
    Button receiveBtn;
    ChatAdapter adt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatroom);

        listView = (ListView)findViewById(R.id.ListView);
        editText = (EditText)findViewById(R.id.ChatEditText);
        sendBtn = (Button)findViewById(R.id.SendBtn);
        receiveBtn = (Button)findViewById(R.id.ReceiveBtn);


        adt = new ChatAdapter(listMessage, getApplicationContext());
        listView.setAdapter(adt);


        sendBtn.setOnClickListener(c -> {
            String message = editText.getText().toString();
            Message model = new Message(message, true);
            listMessage.add(model);
            editText.setText("");

            adt.notifyDataSetChanged();
        });

        receiveBtn.setOnClickListener(c -> {
            String message = editText.getText().toString();
            Message model = new Message(message,false);
            listMessage.add(model);
            editText.setText("");
            adt.notifyDataSetChanged();
        });

        Log.d("ChatRoomActivity","onCreate");

    }


}
