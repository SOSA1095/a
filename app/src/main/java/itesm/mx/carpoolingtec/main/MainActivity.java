package itesm.mx.carpoolingtec.main;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import itesm.mx.carpoolingtec.R;
import itesm.mx.carpoolingtec.contacts.ContactsFragment;
import itesm.mx.carpoolingtec.rides.RidesFragment;
import itesm.mx.carpoolingtec.schedule.ScheduleFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.tab_layout) TabLayout tabLayout;
    @BindView(R.id.view_pager) ViewPager viewPager;
    @BindView(R.id.fab) FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        fab.setOnClickListener(this);

        viewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        // Set refresh icon color to white.
        Drawable refreshIcon = menu.findItem(R.id.action_sort).getIcon();
        if (refreshIcon != null) {
            refreshIcon.mutate();
            refreshIcon.setColorFilter(ContextCompat.getColor(this, R.color.white),
                    PorterDuff.Mode.SRC_ATOP);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
            case R.id.action_sort:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(this, "FAB clicked", Toast.LENGTH_SHORT).show();
    }

    class MyAdapter extends FragmentPagerAdapter {

        private static final int FRAGMENT_COUNT = 3;

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position)
        {
            switch (position){
                case 0:
                    return ContactsFragment.newInstance();
                case 1:
                    return RidesFragment.newInstance();
                case 2:
                    return ScheduleFragment.newInstance();
            }
            return null;
        }

        @Override
        public int getCount() {
            return FRAGMENT_COUNT;
        }

        @Override
        public CharSequence getPageTitle(int position) {

            switch (position){
                case 0:
                    return getResources().getString(R.string.contacts);
                case 1:
                    return getResources().getString(R.string.rides);
                case 2:
                    return getResources().getString(R.string.schedule);
            }
            return null;
        }
    }
}
