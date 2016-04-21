package app.free.chapter2.interfragcommn;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import app.free.chapter2.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment2 extends Fragment {


    public Fragment2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment2, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        Button bntGetText = (Button) getActivity().findViewById(R.id.btnGetText);
        bntGetText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView lbl = (TextView)getActivity().findViewById(R.id.fragment1lbl);
                Toast.makeText(getActivity(),lbl.getText(),Toast.LENGTH_SHORT).show();
            }
        });

    }
}
