package com.begentgroup.samplerecyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    RecyclerView containerView;
    MyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        containerView = (RecyclerView)findViewById(R.id.recycler_conatiner);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
//        RecyclerView.LayoutManager manager = new GridLayoutManager(this,2,GridLayoutManager.HORIZONTAL, false);
//        RecyclerView.LayoutManager manager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.HORIZONTAL);
        containerView.setLayoutManager(manager);
        DividerItemDecoration decoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST);
        containerView.addItemDecoration(decoration);
        containerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new MyAdapter();
        containerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new MyAdapter.OnItemClickListener(){
            @Override
            public void onItemClicked(MyAdapter.ViewHolder holder, View view, ItemData data, int position) {
                Toast.makeText(MainActivity.this, "item clicked : " + data.title , Toast.LENGTH_SHORT).show();
            }
        });
        initData();
    }

    private void initData() {
        for (int i = 0; i < 40; i++) {
            ItemData item = new ItemData();
            item.title = "Item " + i;
            mAdapter.add(item);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
