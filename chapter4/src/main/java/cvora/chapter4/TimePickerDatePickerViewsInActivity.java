package cvora.chapter4;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimePickerDatePickerViewsInActivity extends AppCompatActivity {

    TimePicker timePicker;
    DatePicker datePicker;
    int hour,minute;
    int yr,month,day;
    static final int TIME_DIALOG_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_picker_date_picker_views_in);
        timePicker = (TimePicker)findViewById(R.id.timePicker);
        timePicker.setIs24HourView(true);
        datePicker = (DatePicker)findViewById(R.id.datePicker);

        Calendar today = Calendar.getInstance();
        yr = today.get(Calendar.YEAR);
        month = today.get(Calendar.MONTH);
        day = today.get(Calendar.DAY_OF_MONTH);

        showTimePicker().show();
        showDatePicker().show();
    }

    public TimePickerDialog showTimePicker(){
        return new TimePickerDialog(this,mTimeSetListener,hour,minute,false);
    }

    public DatePickerDialog showDatePicker(){
        return new DatePickerDialog(this,mDateSetListener,yr,month,day);
    }

    private TimePickerDialog.OnTimeSetListener mTimeSetListener = new TimePickerDialog.OnTimeSetListener(){
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minuteOfHour) {

            hour = hourOfDay;
            minute = minuteOfHour;

            SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm aa");
            Date date = new Date(0,0,0,hour,minute);
            String strDate = timeFormat.format(date);

            Toast.makeText(getBaseContext(),"You have selected "+strDate,Toast.LENGTH_SHORT).show();

        }
    };

    private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener(){
        public void onDateSet(DatePicker view,int year,int monthOfYear,int dayOfMonth){
            yr = year;
            month = monthOfYear;
            day = dayOfMonth;
            Toast.makeText(getBaseContext(),
                    "You have selected : "+ (month + 1) + "/"+day+"/"+year,Toast.LENGTH_SHORT).show();
        }
    };

    public void onClick(View view){
        Toast.makeText(getBaseContext(),"Time selected: " +
                timePicker.getCurrentHour() + ":" + timePicker.getCurrentMinute(),Toast.LENGTH_SHORT).show();
        Toast.makeText(getBaseContext(),"Date selected: " +
                (datePicker.getMonth() + 1) + ":" + datePicker.getDayOfMonth() + ":" + datePicker.getYear(),Toast.LENGTH_SHORT).show();    }
}
