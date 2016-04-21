package app.free.chapter2;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class SimpleFragment1 extends Fragment {

    private final String tag = "SimpleFragment1";

    public SimpleFragment1() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(tag,"onCreateView");
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_simple_fragment1, container, false);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.d(tag,"onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(tag,"onCreate");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(tag,"onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(tag,"onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(tag,"onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(tag,"onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(tag,"onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(tag,"onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(tag,"onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(tag,"onDetach");
    }
}
