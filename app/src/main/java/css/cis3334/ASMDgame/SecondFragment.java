package css.cis3334.asmdgame;

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
 * {@link SecondFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SecondFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SecondFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private TextView textViewCurrentNum;
    private TextView textViewModifierNum;
    private TextView textViewTimeNum;
    private TextView textViewScoreNum;
    private TextView textViewHighScoreNum;
    private TextView textViewGoalNum;
    private Button buttonSubtract;

    private OnFragmentInteractionListener mListener;

    public SecondFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SecondFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SecondFragment newInstance(String param1, String param2) {
        SecondFragment fragment = new SecondFragment();
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
        View inflation = inflater.inflate(R.layout.fragment_second, container, false);

        textViewCurrentNum = (TextView) inflation.findViewById(R.id.textViewCurrentNum);
        textViewModifierNum = (TextView) inflation.findViewById(R.id.textViewModifierNum);
        textViewTimeNum = (TextView) inflation.findViewById(R.id.textViewTimeNum);
        textViewScoreNum = (TextView) inflation.findViewById(R.id.textViewScoreNum);
        textViewHighScoreNum = (TextView) inflation.findViewById(R.id.textViewHighScoreNum);
        textViewGoalNum = (TextView) inflation.findViewById(R.id.textViewGoalNum);
        buttonSubtract = (Button) inflation.findViewById(R.id.buttonSubtract);

        textViewScoreNum.setText(Integer.toString(MainActivity.score));
        textViewHighScoreNum.setText(Integer.toString(MainActivity.highScore));
        textViewTimeNum.setText(Integer.toString(MainActivity.timer));
        textViewCurrentNum.setText(Integer.toString(MainActivity.currentNum));
        textViewGoalNum.setText(Integer.toString(MainActivity.goal));
        textViewModifierNum.setText(Integer.toString(MainActivity.subtractMod));

        buttonSubtract.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MainActivity.timer--;

                int modifier = Integer.parseInt(textViewModifierNum.getText().toString());

                MainActivity.currentNum -= modifier;

                textViewTimeNum.setText(Integer.toString((MainActivity.timer)));
                textViewCurrentNum.setText(Integer.toString(MainActivity.currentNum));

                if (MainActivity.currentNum == MainActivity.goal) {
                    textViewTimeNum.setText(Integer.toString(10));
                    textViewScoreNum.setText(Integer.toString(MainActivity.score + 1));
                    MainActivity.newGame();
                    textViewScoreNum.setText(Integer.toString(MainActivity.score));
                    textViewHighScoreNum.setText(Integer.toString(MainActivity.highScore));
                    textViewTimeNum.setText(Integer.toString(MainActivity.timer));
                    textViewCurrentNum.setText(Integer.toString(MainActivity.currentNum));
                    textViewGoalNum.setText(Integer.toString(MainActivity.goal));
                    textViewModifierNum.setText(Integer.toString(MainActivity.subtractMod));
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
                    textViewModifierNum.setText(Integer.toString(MainActivity.subtractMod));
                }

            }
        });
        return inflation;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragment2Interaction(uri);
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
        void onFragment2Interaction(Uri uri);
    }
}
