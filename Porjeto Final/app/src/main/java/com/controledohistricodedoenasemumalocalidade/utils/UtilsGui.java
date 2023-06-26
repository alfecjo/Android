package com.controledohistricodedoenasemumalocalidade.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.EditText;

import com.controledohistricodedoenasemumalocalidade.R;

public class UtilsGui {
    public static void avisoDeErro(Context context, int idText) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.aviso);
        builder.setIcon(R.drawable.utfpr);
        builder.setMessage(idText);

        builder.setNeutralButton(R.string.ok,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }

    public static void confirmaAcao(Context context,
                                    String msg,
                                    DialogInterface.OnClickListener onClickListener){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.confirmacao);
        builder.setIcon(R.drawable.utfpr);
        builder.setMessage(msg);
        builder.setPositiveButton(R.string.simm, onClickListener);
        builder.setNeutralButton(R.string.noo, onClickListener);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public static String validaCampoTexto(Context contexto,
                                          EditText editText,
                                          int idMensagemErro){

        String texto = editText.getText().toString();

        if (UtilsString.stringVazia(texto)){
            UtilsGui.avisoDeErro(contexto, idMensagemErro);
            editText.setText(null);
            editText.requestFocus();
            return null;
        }else{
            return texto.trim();
        }
    }

    public static String validaCampoString(Context contexto,
                                          String text,
                                          int idMensagemErro){

        if (UtilsString.stringVazia(text)){
            UtilsGui.avisoDeErro(contexto, idMensagemErro);
            return null;
        }else{
            return text.trim();
        }
    }
}
