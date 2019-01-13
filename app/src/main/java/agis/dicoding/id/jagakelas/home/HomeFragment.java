package agis.dicoding.id.jagakelas.home;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import agis.dicoding.id.jagakelas.R;
import agis.dicoding.id.jagakelas.home.jaga.JagaFragment;
import agis.dicoding.id.jagakelas.home.sapuBersih.SapuBersihFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_pager, container, false);
        setRetainInstance(true);

        ViewPager viewPager = view.findViewById(R.id.view_pager);
        viewPager.setOffscreenPageLimit(2);
        setupViewPager(viewPager);
        TabLayout tabLayout = view.findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager());
        viewPagerAdapter.addFragment(new JagaFragment(), "Jaga");
        viewPagerAdapter.addFragment(new SapuBersihFragment(), "Sapu Bersih");
        viewPager.setAdapter(viewPagerAdapter);
    }
}
