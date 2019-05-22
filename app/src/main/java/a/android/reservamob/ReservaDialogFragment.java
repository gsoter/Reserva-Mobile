package a.android.reservamob;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class ReservaDialogFragment extends DialogFragment implements DatePickerFragment.DateDialogListener, DatePickerDialog.OnDateSetListener {

    EditText editTextDt_Entrada;
    EditText editTextDt_Saida;
    Button btnDtChegada;
    Button btnDtSaida;
    ReservaDialogListener listener;


    @Override
    public Dialog onCreateDialog(final Bundle savedInstanceState) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View promptView = inflater.inflate(R.layout.dialog_layout, null);
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        btnDtChegada = promptView.findViewById(R.id.btnDtChegada);
        btnDtSaida = promptView.findViewById(R.id.btnDtSaida);
        editTextDt_Entrada = promptView.findViewById(R.id.editTextChegada);
        editTextDt_Saida = promptView.findViewById(R.id.editTextSaida);

//      Botão Calendário de Entrada
        btnDtChegada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    DatePickerFragment datePicker = new DatePickerFragment();
                    FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                    transaction.add(R.id.dateDialog, datePicker).commit();
                } catch (Exception e) {
                    dismiss();
                    e.printStackTrace();
                }
            }
        });
//        Botão Calendário de Saída
        btnDtSaida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        builder.setView(promptView)
                .setTitle("Buscar Reserva")
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                }).setPositiveButton("Buscar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String dtChegada = editTextDt_Entrada.getText().toString();
                String dtSaida = editTextDt_Saida.getText().toString();
                listener.consultar(dtChegada, dtSaida);
            }
        });
        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (ReservaDialogListener) context;

        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "Deve implementar o Dialog");
        }
    }

    @Override
    public void onDateSet(DatePicker datePicker, int day, int month, int year) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_MONTH, day);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.YEAR, year);
    }

    @Override
    public void onFinishDialog(Date date) {
        if (getView().getId() == R.id.btnDtChegada)
            editTextDt_Entrada.setText(DateFormat.getDateInstance(DateFormat.FULL).format(date.getTime()));
        if (getView().getId() == R.id.btnDtSaida)
            editTextDt_Saida.setText(DateFormat.getDateInstance(DateFormat.FULL).format(date.getTime()));

    }

    public interface ReservaDialogListener {
        void consultar(String dtEntrada, String dtSaida);
    }
}