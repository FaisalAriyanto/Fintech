package pkp.faisal.fintech.adapter;

/**
 * Created by Faisal on 6/30/2017.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import pkp.faisal.fintech.fragment.BisnisUnitFragment;
import pkp.faisal.fintech.fragment.EntryDataOrderFragment;
import pkp.faisal.fintech.fragment.SimulasiFragment;
import pkp.faisal.fintech.fragment.SimulasiKreditTanpaAnggunanFragment;
import pkp.faisal.fintech.fragment.SimulasiPinjamUangFragment;
import pkp.faisal.fintech.fragment.dummy.BisnisUnitContent;

/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class PengajuanSectionAdapter extends FragmentPagerAdapter {
    private static final int PILIH_UNIT_BISNIS = 0;
    private static final int SIMULASI_FRAGMENT = 1;
    private static final int ENTRY_DATA_ORDER = 2;

    public PengajuanSectionAdapter(FragmentManager fm) {
        super(fm);
    }



    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).

        Fragment res = null;
        switch (position) {
            case PILIH_UNIT_BISNIS:
                res = BisnisUnitFragment.newInstance(1);
                break;
            case SIMULASI_FRAGMENT:
                res = SimulasiFragment.newInstance("", "");

                break;
            case ENTRY_DATA_ORDER:
                res = EntryDataOrderFragment.newInstance("", "");
                break;
        }
        return res;
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "SECTION 1";
            case 1:
                return "SECTION 2";
            case 2:
                return "SECTION 3";
        }
        return null;
    }
}