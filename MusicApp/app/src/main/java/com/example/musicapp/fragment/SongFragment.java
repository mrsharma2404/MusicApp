package com.example.musicapp.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.musicapp.R;
import com.example.musicapp.activities.MainTabActivity;
import com.example.musicapp.activities.SongInfoActivity;
import com.example.musicapp.adapters.CustomArrayAdapter;

public class SongFragment extends Fragment {
    Context objContext;

    String [] strSongOptions = {"Add To PlayList", "Add TO Fav", "Details", "Delete"};

    public SongFragment(Context mContext) {
        this.objContext = objContext;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater objInflater, @Nullable ViewGroup objParent, @Nullable Bundle objBundle) {
        super.onCreateView(objInflater, objParent, objBundle);
        View objView = objInflater.inflate(R.layout.song_fragment, objParent, false);

        ListView objListView = objView.findViewById(R.id.listview);

        String [] strName = new String[MainTabActivity.objSongName.size()];

        int i=0;


       // for (i = 0; i<strName; i++);
        //{
        //    strName[i] = MainTabActivity.objSongName.get(i);
        //    //String [] strName = {"hi", "hello"};
        //}

            //we will declare custom adaptor in end
            if (objCustomArrayAdapter == null)
                objCustomArrayAdapter = new CustomArrayAdapter(getActivity(), R.layout.song_row_view, strName);

            //we will setAdapter in end
            //objListView.setAdapter(objCustomArrayAdapter);
            objListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    MainTabActivity.objSongServiceClass.PlaySongs(position);
                    MainTabActivity.objcrrSongName.setText(MainTabActivity.objSongName.get(position));
                    MainTabActivity.objcrrSongend.setText(MainTabActivity.objSongDuration.get(position));
                    MainTabActivity.objcrrSongThumnail.setImageBitmap(MainTabActivity.objSongThumnail.get(position));

                }
            });

            objListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                    final int iSelectedIndex = position;
                    AlertDialog.Builder objBuilder = new AlertDialog.Builder(SongFragment.this.getContext(), 0);
                    objBuilder.setTitle("Options");

                    ArrayAdapter<String> objArrayAdapter = new ArrayAdapter<String>(SongFragment.this.getContext(), android.R.layout.simple_list_item_1, strSongOptions);
                    objBuilder.setAdapter(objArrayAdapter, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            switch (which) {
                                case 0: //add to playList
                                    break;
                                case 1: //add to fav
                                    break;
                                case 2:
                                    Intent objIntent = new Intent(SongFragment.this.getContext(), SongInfoActivity.class);
                                    objIntent.putExtra("songpath", MainTabActivity.objSongPath.get(iSelectedIndex));
                                    objIntent.putExtra("songname", MainTabActivity.objSongName.get(iSelectedIndex));
                                    objIntent.putExtra("songbitrate", MainTabActivity.objSongBitrate.get(iSelectedIndex));

                                    SongFragment.this.getContext().startActivity(objIntent);
                                    break;
                                case 3: //delete
                                    break;
                            }
                        }
                    });
                    objBuilder.show();
                    return false;
                }
            });
            objListView.setAdapter(objCustomArrayAdapter);

        return objView;
    }
    CustomArrayAdapter objCustomArrayAdapter;

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
    }


}
