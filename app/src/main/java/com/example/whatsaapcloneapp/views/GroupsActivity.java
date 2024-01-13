
package com.example.whatsaapcloneapp.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.whatsaapcloneapp.R;
import com.example.whatsaapcloneapp.databinding.ActivityGroupsBinding;
import com.example.whatsaapcloneapp.model.ChatGroup;
import com.example.whatsaapcloneapp.viewmodel.MyViewModel;
import com.example.whatsaapcloneapp.views.adapters.GroupAdapter;

import java.util.ArrayList;
import java.util.List;

public class GroupsActivity extends AppCompatActivity {

    private ArrayList<ChatGroup> chatGroupArrayList;

    private RecyclerView recyclerView;
    private GroupsActivity groupsActivity;

    private GroupAdapter groupAdapter;
    private ActivityGroupsBinding binding;
    private MyViewModel myViewModel;

    //Dialog
    private Dialog chatGroupDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groups);

        //initialize
        binding = DataBindingUtil.setContentView(this,R.layout.activity_groups);
        myViewModel = new ViewModelProvider(this).get(MyViewModel.class);
        recyclerView = binding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        myViewModel.getGroupList().observe(this, new Observer<List<ChatGroup>>() {
            @Override
            public void onChanged(List<ChatGroup> chatGroups) {
                chatGroupArrayList = new ArrayList<>();
                chatGroupArrayList.addAll(chatGroups);


                groupAdapter = new GroupAdapter(chatGroupArrayList);
                recyclerView.setAdapter(groupAdapter);
                groupAdapter.notifyDataSetChanged();
            }
        });

        binding.fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){showDialog();}
        });
    }

    public void showDialog(){
        chatGroupDialog = new Dialog(this);
        chatGroupDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_layout,null);
        chatGroupDialog.setContentView(view);
        chatGroupDialog.show();

        Button submit = view.findViewById(R.id.subitButton);
        EditText editText = view.findViewById(R.id.GroupName);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String groupName = editText.getText().toString();

                Toast.makeText(GroupsActivity.this, "Your Chat Group Name " + groupName, Toast.LENGTH_SHORT).show();
                //TextView textView = view.findViewById(R.layout.tex)
                myViewModel.createNewGroup(groupName);
                chatGroupDialog.dismiss();
            }
        });

    }
}