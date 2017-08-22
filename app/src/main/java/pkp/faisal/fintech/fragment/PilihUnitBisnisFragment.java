package pkp.faisal.fintech.fragment;

/**
 * Created by Faisal on 6/30/2017.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import pkp.faisal.fintech.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class PilihUnitBisnisFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    public PilihUnitBisnisFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static PilihUnitBisnisFragment newInstance(int sectionNumber) {
        PilihUnitBisnisFragment fragment = new PilihUnitBisnisFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_pengajuan, container, false);
        TextView textView = (TextView) rootView.findViewById(R.id.section_label);
        textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
        return rootView;
    }
}