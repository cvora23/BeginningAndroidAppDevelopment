package app.free.chapter2;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyAlertDialogFragment extends DialogFragment {

    CharSequence[] items = {"Google", "Apple", "Microsoft"};
    boolean[] itemsChecked = new boolean[items.length];

    public static MyAlertDialogFragment newInstance(int title) {

        MyAlertDialogFragment frag = new MyAlertDialogFragment();
        Bundle args = new Bundle();
        args.putInt("title", title);
        frag.setArguments(args);
        return frag;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {

        int title = getArguments().getInt("title");

        return new AlertDialog.Builder(getActivity())
                .setIcon(R.drawable.ic_launcher)
                .setTitle(title)
                .setPositiveButton(R.string.OK,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getActivity().getBaseContext(), "OK Clicked! ", Toast.LENGTH_SHORT).show();
                                ((DialogActivity) getActivity()).doPositiveClick();
                            }
                        }
                )
                .setNegativeButton(R.string.Cancel,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getActivity().getBaseContext(), "Cancel Clicked! ", Toast.LENGTH_SHORT).show();
                                ((DialogActivity) getActivity()).doNegativeClick();
                            }
                        }
                )
                .setMultiChoiceItems(items, itemsChecked,
                        new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                Toast.makeText(getActivity().getBaseContext(), items[which] + (isChecked ? " checked!" : " unchecked!"),
                                        Toast.LENGTH_SHORT).show();

                            }
                        }
                ).create();
    }
}



