package com.example.traveltourandguidesystem.Activities;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.traveltourandguidesystem.Helper.APIInterface;
import com.example.traveltourandguidesystem.Helper.ApiClient;
import com.example.traveltourandguidesystem.Helper.SharedPref;
import com.example.traveltourandguidesystem.R;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookingTourActivity extends AppCompatActivity {
    ProgressDialog progressDialog;
    EditText tv_book_name, tv_book_phone, tv_book_address, tv_book_city, tv_book_province;
    TextView tv_book_arrival_date, tv_book_departure_date;
    EditText tv_h_book_r_type, tv_h_book_rooms, tv_h_book_beds, tv_h_book_guest, tv_h_book_s_request;
    CheckBox checkBox_tour_guide_yes, checkBox_tour_guide_no, checkBox_transport_no, checkBox_transport_yes;
    Button btn_book_now;
    private Context context;
    DatePickerDialog arrivalDatePicker, departureDatePicker;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_booking_tour);
        context = BookingTourActivity.this;
        progressDialog = new ProgressDialog(BookingTourActivity.this);
        progressDialog.setCancelable(true);

        tv_book_name = findViewById(R.id.tv_book_name);
        tv_book_phone = findViewById(R.id.tv_book_phone);
        tv_book_address = findViewById(R.id.tv_book_address);
        tv_book_city = findViewById(R.id.tv_book_city);
        tv_book_province = findViewById(R.id.tv_book_province);
        tv_book_arrival_date = findViewById(R.id.tv_book_arrival_date);
        tv_book_departure_date = findViewById(R.id.tv_book_departure_date);

        tv_h_book_r_type = findViewById(R.id.tv_h_book_r_type);
        tv_h_book_rooms = findViewById(R.id.tv_h_book_rooms);
        tv_h_book_beds = findViewById(R.id.tv_h_book_beds);
        tv_h_book_guest = findViewById(R.id.tv_h_book_guest);
        tv_h_book_s_request = findViewById(R.id.tv_h_book_s_request);

        checkBox_tour_guide_yes = findViewById(R.id.checkBox_tour_guide_yes);
        checkBox_tour_guide_no = findViewById(R.id.checkBox_tour_guide_no);

        checkBox_transport_yes = findViewById(R.id.checkBox_transport_yes);
        checkBox_transport_no = findViewById(R.id.checkBox_transport_no);


        btn_book_now = findViewById(R.id.btn_book_now);

        arrivalDatePicker = new DatePickerDialog(context);
        departureDatePicker = new DatePickerDialog(context);

        tv_book_arrival_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrivalDatePicker.show();
            }
        });
        tv_book_departure_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                departureDatePicker.show();
            }
        });
        arrivalDatePicker.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                tv_book_arrival_date.setText(dayOfMonth + "-" + (month + 1) + "-" + year);
            }
        });
        departureDatePicker.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                tv_book_departure_date.setText(dayOfMonth + "-" + (month + 1) + "-" + year);
            }
        });
        checkBox_tour_guide_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox_tour_guide_yes.isChecked()) {
                    checkBox_tour_guide_no.setChecked(false);
                    Toast.makeText(context, "Tour Guide: Yes", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Set OnClickListener for No CheckBox
        checkBox_tour_guide_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox_tour_guide_no.isChecked()) {
                    checkBox_tour_guide_yes.setChecked(false);
                    Toast.makeText(context, "Tour Guide: No", Toast.LENGTH_SHORT).show();
                }
            }
        });


        checkBox_transport_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox_transport_yes.isChecked()) {
                    checkBox_transport_no.setChecked(false);
                    Toast.makeText(context, "Transportation: Confirm", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Set OnClickListener for No CheckBox
        checkBox_transport_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox_transport_no.isChecked()) {
                    checkBox_transport_yes.setChecked(false);
                    Toast.makeText(context, "Transportation: Un confirm", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_book_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name,
                        phone_number,
                        address,
                        city,
                        arrival_date,
                        departure_date,
                        province,
                        room_type,
                        no_of_rooms,
                        bed_type,
                        no_of_guests,
                        special_request;

                name = tv_book_name.getText().toString();
                phone_number = tv_book_phone.getText().toString();
                address = tv_book_address.getText().toString();
                city = tv_book_city.getText().toString();
                arrival_date = tv_book_arrival_date.getText().toString();
                departure_date = tv_book_departure_date.getText().toString();
                province = tv_book_province.getText().toString();
                room_type = tv_h_book_r_type.getText().toString();
                no_of_rooms = tv_h_book_rooms.getText().toString();
                bed_type = tv_h_book_beds.getText().toString();
                no_of_guests = tv_h_book_guest.getText().toString();
                special_request = tv_h_book_s_request.getText().toString();

                if (name.length() < 2) {
                    Toast.makeText(context, "Enter Your Name ", Toast.LENGTH_SHORT).show();
                } else if (phone_number.length() < 6) {
                    Toast.makeText(context, "Enter Your Correct Number ", Toast.LENGTH_SHORT).show();
                } else if (address.length() < 2) {
                    Toast.makeText(context, "Enter Your Correct Address ", Toast.LENGTH_SHORT).show();
                } else if (city.length() < 2) {
                    Toast.makeText(context, "Enter Your City ", Toast.LENGTH_SHORT).show();
                } else if (province.length() < 2) {
                    Toast.makeText(context, "Enter Your Province ", Toast.LENGTH_SHORT).show();
                } else if (arrival_date.length() < 2) {
                    Toast.makeText(context, "Enter Correct Arrival Date ", Toast.LENGTH_SHORT).show();
                } else if (departure_date.length() < 2) {
                    Toast.makeText(context, "Enter Correct Departure Date ", Toast.LENGTH_SHORT).show();
                } else if (no_of_rooms.length() > 10) {
                    Toast.makeText(context, "Tens Rooms Booking Only ", Toast.LENGTH_SHORT).show();
                } else if (room_type.length() < 2) {
                    Toast.makeText(context, "Single or Double Room ", Toast.LENGTH_SHORT).show();
                } else if (bed_type.length() > 10) {
                    Toast.makeText(context, "Only 10 Beds Avaliable ", Toast.LENGTH_SHORT).show();
                } else if (no_of_guests.length() > 15) {
                    Toast.makeText(context, "Fifteen Guests ", Toast.LENGTH_SHORT).show();
                } else if (special_request.length() < 2) {
                    Toast.makeText(context, "Enter Your Request", Toast.LENGTH_SHORT).show();
                } else {
                    bookingTour(
                            name,
                            phone_number,
                            address,
                            city,
                            arrival_date,
                            departure_date,
                            province,
                            room_type,
                            no_of_rooms,
                            bed_type,
                            no_of_guests,
                            special_request);
                }
            }
        });
    }

    private void bookingTour(String name,
                             String phone_number,
                             String address,
                             String city,
                             String arrival_date,
                             String departure_date,
                             String province,
                             String room_type,
                             String no_of_rooms,
                             String bed_type,
                             String no_of_guests,
                             String special_request) {
        progressDialog.setMessage("Reservation is in process...");
        progressDialog.show();
        int id = new SharedPref().getid(BookingTourActivity.this);
        ApiClient apiClient = new ApiClient();
        Call<Object> responseCall = apiClient.getClient(context).create(APIInterface.class).bookingTour(
                name,
                phone_number,
                address,
                city,
                arrival_date,
                departure_date,
                province,
                room_type,
                no_of_rooms,
                bed_type,
                no_of_guests,
                special_request,
                id + "");
        responseCall.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                progressDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(new Gson().toJson(response.body()));
                    boolean error = jsonObject.getBoolean("error");
                    if (error) {
                        String msg = jsonObject.getString("Reservation Process Not Complete");
                        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "Reservation Process Completed", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(context, PaymentMethodActivity.class));
                        finish();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable throwable) {
                progressDialog.dismiss();
                throwable.printStackTrace();
            }
        });
    }

}
