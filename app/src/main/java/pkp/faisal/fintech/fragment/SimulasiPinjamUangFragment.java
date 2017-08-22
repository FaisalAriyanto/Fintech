package pkp.faisal.fintech.fragment;


import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatSeekBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import pkp.faisal.fintech.R;
import pkp.faisal.fintech.fragment.dummy.BisnisUnitContent;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SimulasiPinjamUangFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SimulasiPinjamUangFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private AppCompatSeekBar mJumlahSeekbar;
    private static final int SEEKBAR_JUMLAH_OFFSET = 5;
    private static final int SEEKBAR_JANGKA_OFFSET = 1;
    private AppCompatSeekBar mJangkaSeekbar;
    private OnFragmentInteractionListener mListener;

    private TextView mJumlahPinjamanText;
    private TextView mJangkaWaktuText;

    private Button lanjutBtn;

    private LinearLayout mResult;
    private ProgressBar mLoading;

    public SimulasiPinjamUangFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SimulasiPinjamUangFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SimulasiPinjamUangFragment newInstance(String param1, String param2) {
        SimulasiPinjamUangFragment fragment = new SimulasiPinjamUangFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_simulasi_pinjam_uang, container, false);

        mJangkaSeekbar = (AppCompatSeekBar) view.findViewById(R.id.jangka_seekbar);
        mJumlahSeekbar = (AppCompatSeekBar) view.findViewById(R.id.jumlah_seekbar);
        mJumlahPinjamanText = (TextView) view.findViewById(R.id.tv_jumlah_pinjaman);
        mJangkaWaktuText = (TextView) view.findViewById(R.id.tv_jangka_waktu);
        mResult = (LinearLayout) view.findViewById(R.id.result);
        mLoading = (ProgressBar) view.findViewById(R.id.loading);
        lanjutBtn = (Button) view.findViewById(R.id.lanjut_btn);

        lanjutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.goToEntryData();
            }
        });
        setJumlahText(5);
        setJangkaText(1);
        mJumlahSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChanged = 0;
            int stepSize = 5;

            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {

                progress = ((int) Math.round(progress / stepSize)) * stepSize;
                seekBar.setProgress(progress);
                progressChanged = progress;
                int val = progressChanged + SEEKBAR_JUMLAH_OFFSET;
                setJumlahText(val);
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                showLoading();
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        hideLoading();
                    }
                }, 1000);
            }
        });

        mJangkaSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChanged = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChanged = progress;
                int val = progressChanged + SEEKBAR_JANGKA_OFFSET;
                setJangkaText(val);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                showLoading();
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        hideLoading();
                    }
                }, 1000);
            }
        });

        return view;
    }

    private void showLoading() {
        mLoading.setVisibility(View.VISIBLE);
        mResult.setVisibility(View.INVISIBLE);
    }

    private void hideLoading() {
        mLoading.setVisibility(View.INVISIBLE);
        mResult.setVisibility(View.VISIBLE);

    }

    private void setJumlahText(int val) {
        StringBuilder str = new StringBuilder();
        str.append(getString(R.string.jumlah_pinjaman) + " Rp. " + val * 100000);
        mJumlahPinjamanText.setText(str);
    }

    private void setJangkaText(int val) {
        StringBuilder str = new StringBuilder();
        str.append(getString(R.string.jangka_waktu) + " " + val + " bulan");
        mJangkaWaktuText.setText(str);
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

        void goToEntryData();
    }
}
