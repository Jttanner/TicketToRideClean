package ui.views;

import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Hwang on 10/20/2017.
 */

public class DestinationCardFragment extends Fragment {
    Spinner spinner;
    EditText gameName;
    Game game;
    Player player;
    Button buttonRed;

    private EditText mRoute1;
    private EditText mRoute2;
    private EditText mRoute3;
    private Button buttonStart;

    public DestinationCardFragment(){
        System.out.println("hello");


        game = new Game();
        game.setPlayerMax(2);
        System.out.println(CModel.getInstance().getMyUser().getUserName());
        player = new Player(CModel.getInstance().getMyUser().getUserName());
        player.setPlayerName(CModel.getInstance().getMyUser().getInfo().getUserName());
        player.setColor(MyColor.BLACK.toString());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View v = inflater.inflate(R.layout.fragment_creategame, container, false);

        setUp(v);

        return v;
    }

    //TODO: Create the view, make a list of routes, draw randomly from the list of routes, change the textview based on those routes
    private void setUp(View v){
        gameName = (EditText) v.findViewById(R.id.gameName);
        gameName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //r1.textid.setcolor()
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                game.setGameName(s.toString());
            }
        });

        List<String> number = new ArrayList<>();
        number.add("2");
        number.add("3");
        number.add("4");
        number.add("5");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,number);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner = (Spinner) v.findViewById(R.id.spinner);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String size = (String) adapterView.getItemAtPosition(position);
                game.setPlayerMax(Integer.parseInt(size));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        
        buttonRed = (Button) v.findViewById(R.id.ColorRed);

        wireUp();
    }
    void wireUp(){
        buttonBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.setColor(MyColor.BLUE.toString());
            }
        });
        buttonRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.setColor(MyColor.RED.toString());
            }
        });
        buttonYellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.setColor(MyColor.YELLOW.toString());
            }
        });
        buttonGreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.setColor(MyColor.GREEN.toString());
            }
        });
        buttonSubmit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //game.addPlayer(player);
                ((GameListActivity)getActivity()).presenter.CreateGame(game);
                getDialog().dismiss();
            }
        });
        buttonCancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                getDialog().dismiss();

            }
        });
    }
}
