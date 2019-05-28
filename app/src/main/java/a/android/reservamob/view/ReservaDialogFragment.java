package a.android.reservamob.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.text.DateFormat;
import java.util.Date;

import a.android.reservamob.R;

public class ReservaDialogFragment extends DialogFragment implements DatePickerFragment.DateDialogListener {

    EditText editTextDtEntrada;
    EditText editTextDtSaida;
    Button btnDtChegada;
    Button btnDtSaida;
    Integer year, month, day;
    ReservaDialogListener listener;


    @Override
    public Dialog onCreateDialog(final Bundle savedInstanceState) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View promptView = inflater.inflate(R.layout.dialog_layout, null);
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        btnDtChegada = promptView.findViewById(R.id.btnDtChegada);
        btnDtSaida = promptView.findViewById(R.id.btnDtSaida);
        editTextDtEntrada = promptView.findViewById(R.id.editTextChegada);
        editTextDtSaida = promptView.findViewById(R.id.editTextSaida);

//      Botão Calendário de Entrada
        btnDtChegada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    openDatePicker();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

//        Botão Calendário de Saída
        btnDtSaida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    openDatePicker();
                } catch (Exception e) {
                    e.printStackTrace();
                }
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
                String dtChegada = editTextDtEntrada.getText().toString();
                String dtSaida = editTextDtSaida.getText().toString();
                listener.consultar(dtChegada, dtSaida);
            }
        });
        return builder.create();
    }

    private void openDatePicker() {
        DatePickerFragment datePicker = new DatePickerFragment();
        datePicker.show(getActivity().getSupportFragmentManager(), "Data de Chegada");
        this.dismiss();
    }

    public void setDates(int year, int month, int day){
        this.year = year;
        this.month = month;
        this.day = day;
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
    public void onFinishDialog(Date date) {
        editTextDtEntrada.setText(DateFormat.getDateInstance(DateFormat.FULL).format(date.getTime()));
        editTextDtSaida.setText(DateFormat.getDateInstance(DateFormat.FULL).format(date.getTime()));

    }

    public interface ReservaDialogListener {
        void consultar(String dtEntrada, String dtSaida);
    }
}