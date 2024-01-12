
package com.example.whatsaapcloneapp.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

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
                groupAdapter.notifyDataSetChanged();
            }
        });
    }
}