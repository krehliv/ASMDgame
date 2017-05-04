package css.cis3334.asmdgame;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Main Activity. Creates a game with four fragments, one each for addition,
 * subtraction, multiplication, and division. The user must use the randomly
 * generated current number and modifier numbers to reach the goal number. Upon
 * success, the user's score increases by 1. If a new high score is reached,
 * the number is stored in firebase. The number in firebase is downloaded and
 * set to the high score upon running the game.
 */
public class MainActivity extends AppCompatActivity
        implements FirstFragment.OnFragmentInteractionListener,
        SecondFragment.OnFragmentInteractionListener,
        ThirdFragment.OnFragmentInteractionListener,
        FourthFragment.OnFragmentInteractionListener
{

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    /**
     * Initialization of necessary numbers.
     */
    public static int timer = -1;
    public static int score = -1;
    public static int currentNum = -1;
    public static int goal = -1;
    public static int highScore;

    public static int addMod = -1;
    public static int subtractMod = -1;
    public static int multiplyMod = -1;
    public static int divideMod = -1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * Grabs the previous high score from and sets it equal to the
         * highScore variable.
         */
        DatabaseReference reff = FirebaseDatabase.getInstance().getReference();
        DatabaseReference asmdgamescore = reff.child("asmdgame");

        asmdgamescore.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String highScoreString = dataSnapshot.getValue(String.class);
                Log.d("CIS3334", "Highscore value is: " + highScoreString);
                highScore = Integer.parseInt(highScoreString);
                //newGame();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        /**
         * Runs the newGame() method.
         */
        newGame();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

    }

    /**
     * Sets all of the necessary variables to random numbers, increments score,
     * and resets highScore if necessary.
     */
    public static void newGame() {
        score++;
        if (score > highScore) {
            highScore = score;
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("asmdgame");
            myRef.setValue(Integer.toString(highScore));
            Log.d("CIS3334", "New Highscore Value is: " + myRef.toString());
        }

        timer = 10;

        goal = ThreadLocalRandom.current().nextInt(15, 31); //random num between 15 and 30
        currentNum = ThreadLocalRandom.current().nextInt(1, 15); //random num between 1 and 14

        addMod = ThreadLocalRandom.current().nextInt(1, 5); //random num between 1 and 4
        subtractMod = ThreadLocalRandom.current().nextInt(1, 5); //random num between 1 and 4
        multiplyMod = ThreadLocalRandom.current().nextInt(2, 5); //random num between 2 and 4
        divideMod = ThreadLocalRandom.current().nextInt(2, 5); //random num between 2 and 4
    }

    @Override
    public void onFragment1Interaction(Uri uri) {
        // Do stuff -- process messages from Fragement 1
    }
    @Override
    public void onFragment2Interaction(Uri uri) {
        // Do stuff
    }
    @Override
    public void onFragment3Interaction(Uri uri) {
        // Do stuff
    }
    @Override
    public void onFragment4Interaction(Uri uri) {
        // Do stuff
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter

    {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            //return PlaceholderFragment.newInstance(position + 1);
            if (position == 0 ){
                return FirstFragment.newInstance("This", "That");
            }
            if (position == 1 ){
                return SecondFragment.newInstance("This", "That");
            }
            if (position == 2 ){
                return ThirdFragment.newInstance("This", "That");
            }
            if (position == 3 ){
                return FourthFragment.newInstance("This", "That");
            }
            return null;
        }

        @Override
        public int getCount() {
            // Show 4 total pages.
            return 4;
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
                case 3:
                    return "SECTION 4";
            }
            return null;
        }

    }
}
