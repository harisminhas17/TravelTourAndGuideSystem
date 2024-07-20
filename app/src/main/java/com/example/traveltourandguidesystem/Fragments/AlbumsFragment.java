package com.example.traveltourandguidesystem.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.traveltourandguidesystem.Activities.AlbumsAddPhotosActivity;
import com.example.traveltourandguidesystem.Activities.AlbumsAskQuestionActivity;
import com.example.traveltourandguidesystem.Activities.AlbumsCommunity;
import com.example.traveltourandguidesystem.Activities.AlbumsCreateAlbumsActivity;
import com.example.traveltourandguidesystem.Activities.AlbumsShareExpActivity;
import com.example.traveltourandguidesystem.Activities.AlbumsWriteStoryActivity;
import com.example.traveltourandguidesystem.Activities.MainActivity;
import com.example.traveltourandguidesystem.Activities.NotificationActivity;
import com.example.traveltourandguidesystem.R;


public class AlbumsFragment extends Fragment {

    ImageView tv_back_albums_btn;
    ImageView tv_albums_notify_btn;
    TextView albums_photos, albums_story, create_albums, tv_community, ask_questions, albums_share_exp;
    private Context context;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AlbumsFragment() {
        // Required empty public constructor
    }

    public static AlbumsFragment newInstance(String param1, String param2) {
        AlbumsFragment fragment = new AlbumsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_albums, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.context = getContext();
        initViews(view);
        clickListeners();
    }

    private void initViews(View view) {
        tv_back_albums_btn = view.findViewById(R.id.tv_back_albums_btn);
        tv_albums_notify_btn = view.findViewById(R.id.tv_albums_notify_btn);
        albums_photos = view.findViewById(R.id.albums_photos);
        albums_story = view.findViewById(R.id.albums_story);
        create_albums = view.findViewById(R.id.create_albums);
        albums_share_exp = view.findViewById(R.id.albums_share_exp);
        tv_community = view.findViewById(R.id.tv_community);
        ask_questions = view.findViewById(R.id.ask_questions);
    }

    private void clickListeners() {
        tv_back_albums_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, MainActivity.class));
            }
        });
        tv_albums_notify_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, NotificationActivity.class));
            }
        });
        albums_photos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, AlbumsAddPhotosActivity.class));
            }
        });
        albums_story.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, AlbumsWriteStoryActivity.class));
            }
        });
        create_albums.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, AlbumsCreateAlbumsActivity.class));
            }
        });
        tv_community.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, AlbumsCommunity.class));
            }
        });
        ask_questions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, AlbumsAskQuestionActivity.class));
            }
        });
        albums_share_exp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, AlbumsShareExpActivity.class));
            }
        });
    }
}