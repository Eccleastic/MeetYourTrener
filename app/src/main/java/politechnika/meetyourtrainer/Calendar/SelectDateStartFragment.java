package politechnika.meetyourtrainer.Calendar;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

import politechnika.meetyourtrainer.R;

public class SelectDateStartFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar calendar = Calendar.getInstance();
        int yy = calendar.get(Calendar.YEAR);
        int mm = calendar.get(Calendar.MONTH);
        int dd = calendar.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(getActivity(), this, yy, mm, dd);
    }

    public void onDateSet(DatePicker view, int yy, int mm, int dd) {
        populateSetDate(yy, mm+1, dd);
    }
    public void populateSetDate(int year, int month, int day) {
        String dd, mm;
        if (day < 10)
            dd = "0" + day;
        else
            dd = Integer.toString(day);
        if (month < 10)
            mm = "0" + month;
        else
            mm = Integer.toString(month);

        TextView date = (TextView) getActivity().findViewById(R.id.dateStart);
        date.setText(dd + "." + mm + "." + year);
    }

}