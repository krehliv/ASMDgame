package css.cis3334.asmdgame;

import android.app.FragmentTransaction;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;



/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FirstFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FirstFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FirstFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TextView textViewCurrentNum;
    public TextView textViewModifierNum;
    public TextView textViewTimeNum;
    public TextView textViewScoreNum;
    public TextView textViewHighScoreNum;
    public TextView textViewGoalNum;
    private Button buttonAdd;

    private int init = 0;

    private OnFragmentInteractionListener mListener;

    public FirstFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FirstFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FirstFragment newInstance(String param1, String param2) {
        FirstFragment fragment = new FirstFragment();
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

    /**
     * Creates the View for the first fragment. Upon pressing the button, the
     * modifier is added to the current value. If the current value now
     * equals the goal, or if timer drops below zero, newGame() is called.
     * Otherwise, timer decrements and the game continues.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View inflation = inflater.inflate(R.layout.fragment_first, container, false);

        textViewCurrentNum = (TextView) inflation.findViewById(R.id.textViewCurrentNum);
        textViewModifierNum = (TextView) inflation.findViewById(R.id.textViewModifierNum);
        textViewTimeNum = (TextView) inflation.findViewById(R.id.textViewTimeNum);
        textViewScoreNum = (TextView) inflation.findViewById(R.id.textViewScoreNum);
        textViewHighScoreNum = (TextView) inflation.findViewById(R.id.textViewHighScoreNum);
        textViewGoalNum = (TextView) inflation.findViewById(R.id.textViewGoalNum);
        buttonAdd = (Button) inflation.findViewById(R.id.buttonAdd);

        textViewScoreNum.setText(Integer.toString(MainActivity.score));
        textViewHighScoreNum.setText(Integer.toString(MainActivity.highScore));
        textViewTimeNum.setText(Integer.toString(MainActivity.timer));
        textViewCurrentNum.setText(Integer.toString(MainActivity.currentNum));
        textViewGoalNum.setText(Integer.toString(MainActivity.goal));
        textViewModifierNum.setText(Integer.toString(MainActivity.addMod));

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MainActivity.timer--;

                int modifier = Integer.parseInt(textViewModifierNum.getText().toString());

                MainActivity.currentNum += modifier;

                textViewTimeNum.setText(Integer.toString((MainActivity.timer)));
                textViewCurrentNum.setText(Integer.toString(MainActivity.currentNum));

                //updater();

                if (MainActivity.currentNum == MainActivity.goal) {
                    textViewTimeNum.setText(Integer.toString(10));
                    textViewScoreNum.setText(Integer.toString(MainActivity.score + 1));
                    MainActivity.newGame();
                    textViewScoreNum.setText(Integer.toString(MainActivity.score));
                    textViewHighScoreNum.setText(Integer.toString(MainActivity.highScore));
                    textViewTimeNum.setText(Integer.toString(MainActivity.timer));
                    textViewCurrentNum.setText(Integer.toString(MainActivity.currentNum));
                    textViewGoalNum.setText(Integer.toString(MainActivity.goal));
                    textViewModifierNum.setText(Integer.toString(MainActivity.addMod));
                }
                if (MainActivity.timer < 0) {
                    textViewTimeNum.setText(Integer.toString(10));
                    MainActivity.score = -1;
                    MainActivity.newGame();
                    textViewScoreNum.setText(Integer.toString(MainActivity.score));
                    textViewHighScoreNum.setText(Integer.toString(MainActivity.highScore));
                    textViewTimeNum.setText(Integer.toString(MainActivity.timer));
                    textViewCurrentNum.setText(Integer.toString(MainActivity.currentNum));
                    textViewGoalNum.setText(Integer.toString(MainActivity.goal));
                    textViewModifierNum.setText(Integer.toString(MainActivity.addMod));
                }

            }
        });
        return inflation;
    }

    /** Final failed attempt at refreshing adjacent fragments.

     private void updater() {
        FirstFragment one = (FirstFragment) getFragmentManager().findFragmentById(R.id.frag_one);
        one.textViewCurrentNum.setText(Integer.toString(MainActivity.currentNum));
     }*/

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
        void onFragment1Interaction(Uri uri);
    }
}
