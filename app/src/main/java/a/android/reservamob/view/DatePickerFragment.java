package a.android.reservamob.view;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.Date;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    DatePicker datePicker;
    int year, month, day;
    DateDialogListener listener;


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
/*        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.date_dialog_layout, null);
        datePicker = view.findViewById(R.id.dateDialog);

        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(view)
                .setTitle(null)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int year = datePicker.getYear();
                        int month = datePicker.getMonth();
                        int dayOfMonth = datePicker.getDayOfMonth();

                        Date date = new GregorianCalendar(year, month, dayOfMonth).getTime();
                        DateDialogListener listener = (DateDialogListener) getParentFragment();
                        listener.onFinishDialog(date);
                    }
                }).setNegativeButton("Voltar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        return builder.create();*/
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        FragmentManager manager = getActivity().getSupportFragmentManager();
        ReservaDialogFragment newReservaDialogFragment = new ReservaDialogFragment();
        newReservaDialogFragment.setDates(year, month, day);
        newReservaDialogFragment.show(manager, "Buscar Reserva");
    }

    @Override
    public void onDateSet(DatePicker datePicker, int day, int month, int year) {
        this.year = year;
        this.month = month;
        this.day = day;
    }


    public interface DateDialogListener {
        void onFinishDialog(Date date);
    }
}
