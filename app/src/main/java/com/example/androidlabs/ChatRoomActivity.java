package com.example.androidlabs;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

    DatabaseClass dbhelp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatroom);

        listView = (ListView)findViewById(R.id.ListView);
        editText = (EditText)findViewById(R.id.ChatEditText);
        sendBtn = (Button)findViewById(R.id.SendBtn);
        receiveBtn = (Button)findViewById(R.id.ReceiveBtn);
        dbhelp = new DatabaseClass(this);


        adt = new ChatAdapter(listMessage, getApplicationContext());
        listView.setAdapter(adt);


        sendBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view)
            {
                //  String message = editText.getText().toString();
                // Message model = new Message(message, true);
                //listMessage.add(model);
                //editText.setText("");

                //adt.notifyDataSetChanged();

                if(!editText.getText().toString().equals(""))
                    {
                        dbhelp.insertData(editText.getText().toString(), true);
                        editText.setText("");
                        listMessage.clear();
                        viewData();
                    }
            }


        });

        receiveBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view)
            {

//            String message = editText.getText().toString();
//            Message model = new Message(message,false);
//            listMessage.add(model);
//            editText.setText("");
//            adt.notifyDataSetChanged();

                if(!editText.getText().toString().equals(""))
                {
                    dbhelp.insertData(editText.getText().toString(), false);
                    editText.setText("");
                    listMessage.clear();
                    viewData();
                }


            }
        });

        viewData();
        Log.d("ChatRoomActivity","onCreate");

    }

    private void viewData(){

        Cursor cursor = dbhelp.viewData();

        if (cursor.getCount() != 0){

            while (cursor.moveToNext()){

                Message msg = new Message(cursor.getString(1), cursor.getInt(2) == 0);

                listMessage.add(msg);

                ChatAdapter chatAdapter = new ChatAdapter(listMessage, getApplicationContext());

                listView.setAdapter(chatAdapter);

            }

        }

    }

}
