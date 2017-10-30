package ui.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import MVP_coms_classes.MVP_Chat;
import clientModel.CModel;
import poller.Poller;
import presenters.ChatPresenter;
import teamjapannumbahone.tickettoride.R;

/**
 * Created by ACL1 on 10/20/2017.
 */

public class ChatFragment extends DialogFragment implements MVP_Chat.ChatViewOps {
    private TextView chatView;
    private EditText chatEdit;
    private Button Enter;
    private String currentString ;
    private MVP_Chat.ChatPresenterOps presenter = new ChatPresenter(this);

    public ChatFragment(){
        currentString = "";
    }

    @Override
    public void UpdateChatView() {
        changeChat();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Poller.getInstance().stopGetCommandsPoller();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View v = inflater.inflate(R.layout.fragment_chat, container, false);

        Poller.getInstance().getCommandList();

        getDialog().show();
        getDialog().getWindow().setLayout(1000,1000);

        chatView = (TextView) v.findViewById(R.id.chatText);
        changeChat();
        Enter = (Button)v.findViewById(R.id.Enter);
        Enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                presenter.UpdateChatServer(currentString);
               // CModel.getInstance().getChatHistory().add(currentString);
                currentString = "";
                chatEdit.setText("");
                changeChat();
            }
        });
        chatEdit = (EditText) v.findViewById(R.id.chatEntry);
        chatEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                currentString = s.toString();
            }
        });
        return v;
    }
    void changeChat(){
        List<String> list = CModel.getInstance().getCurrGame().getChatHistory();
        String display = "";
        if(list.size()<5){
            for(String a : list){
                display+=a;
                display+="\n";
            }
        }
        else{
            for(int i = list.size()-5; i < list.size();i++){
                display += list.get(i);
                display += "\n";
            }
        }
        chatView.setText(display);
    }
}
