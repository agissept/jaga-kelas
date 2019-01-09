package agis.dicoding.id.jagakelas.report;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import agis.dicoding.id.jagakelas.R;
import agis.dicoding.id.jagakelas.report.jaga.ReportJagaFragment;
import agis.dicoding.id.jagakelas.report.report.FinalReportFrament;
import agis.dicoding.id.jagakelas.report.sapuBersih.ReportSapuBersihFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class ReportFragment extends Fragment {


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_pager, container, false);

        ViewPager viewPager = view.findViewById(R.id.view_pager);
        setupViewPager(viewPager);
        TabLayout tabLayout = view.findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager());
        viewPagerAdapter.addFragment(new ReportJagaFragment(), "Jaga");
        viewPagerAdapter.addFragment(new ReportSapuBersihFragment(), "Sapu Bersih");
        viewPagerAdapter.addFragment(new FinalReportFrament(), "Final Report");
        viewPager.setAdapter(viewPagerAdapter);
    }
}
