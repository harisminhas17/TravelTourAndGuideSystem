package com.example.traveltourandguidesystem.Helper;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import java.util.Locale;

public class HelperMethods {

    public static void openGoogleMap(Context context, float latitude, float longitude) {

        String url = String.format(Locale.ENGLISH, "http://maps.google.com/maps?q=%f,%f", latitude, longitude);

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));

        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intent);
        }
    }

}
