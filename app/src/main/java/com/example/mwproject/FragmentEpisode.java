/*package com.example.mwproject;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.mwproject.EPAdapter;
import com.example.mwproject.Player;
import com.example.mwproject.R;

public class FragmentEpisode extends Fragment {
    private EPAdapter adapter;
    private ListView listView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View Current_v = inflater.inflate(R.layout.episode,container,false);

        adapter = new EPAdapter();
        listView = (ListView)Current_v.findViewById(R.id.listView);

        setData();

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        Intent intent = new Intent(getActivity(), Player.class);
                        intent.putExtra("videoID","ok9sgJtaIvY");
                        startActivity(intent);
                        break;
                }
            }
        });
        return Current_v;
    }

    private void setData() {
        TypedArray arrResId = getResources().obtainTypedArray(R.array.resId);
        String[] titles = getResources().getStringArray(R.array.title);

        for (int i = 0; i < arrResId.length(); i++) {
            System.out.println(arrResId.length());
            DTP dto = new DTP();
            dto.setResId(arrResId.getResourceId(i, 0));
            dto.setTitle(titles[i]);

            adapter.addItem(dto);
        }
    }
}*/
