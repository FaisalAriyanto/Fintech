package pkp.faisal.fintech.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pkp.faisal.fintech.R;
import pkp.faisal.fintech.fragment.dummy.BisnisUnitContent;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SimulasiFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SimulasiFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SimulasiFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public SimulasiFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SimulasiFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SimulasiFragment newInstance(String param1, String param2) {
        SimulasiFragment fragment = new SimulasiFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    private Fragment childFragment;
    private FragmentTransaction transaction;
    private View view;
    private Bundle savedInstanceState;

    public void changeView(BisnisUnitContent.BisnisUnit item) {
        switch (item.id) {
            case "1":
                childFragment = new SimulasiPinjamUangFragment();
                break;
            case "2":
                childFragment = new SimulasiKreditTanpaAnggunanFragment();
                break;
        }
        setChildFragment();
    }

    private void setChildFragment() {
        if (childFragment == null) return;

        onViewCreated(view, savedInstanceState);
//        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
//
//// Replace whatever is in the fragment_container view with this fragment,
//// and add the transaction to the back stack if needed
//        transaction.replace(R.id.child_fragment_container, childFragment);
//        transaction.addToBackStack(null);
//
//// Commit the transaction
//        transaction.commit();
//
//        transaction.replace(R.id.child_fragment_container, childFragment)
//                .addToBackStack(null)
//                .commit();

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
//        this.view = view;
//        this.savedInstanceState = savedInstanceState;
//        if (childFragment == null)
//            childFragment = new SimulasiPinjamUangFragment();
//        transaction = getChildFragmentManager().beginTransaction();
//        transaction.replace(R.id.child_fragment_container, childFragment).commit();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_simulasi, container, false);

        FragmentTransaction transaction = getFragmentManager()
                .beginTransaction();
		/*
		 * When this container fragment is created, we fill it with our first
		 * "real" fragment
		 */
        transaction.replace(R.id.root_frame, new SimulasiPinjamUangFragment());

        transaction.commit();

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);

    }
}
