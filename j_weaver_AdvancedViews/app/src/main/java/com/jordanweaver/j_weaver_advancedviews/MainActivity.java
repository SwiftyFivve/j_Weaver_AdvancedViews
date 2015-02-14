package com.jordanweaver.j_weaver_advancedviews;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.OrientationEventListener;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends Activity {

    Context mContext;
    HashMap <Integer, String> castMap =  new HashMap<Integer, String >();
    TextView bioText;
    LinearLayout landscapeView;
    LinearLayout portraitView;
    OrientationEventListener theOrientationListener;
    ListView castList;
    TextView bioText2;

    ArrayList<castClass> castClasses = new ArrayList<castClass>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mContext = this;

        castClasses.add(new castClass("Charlie Kelly is the janitor of Paddy's Pub. He is a member of the Gang that works at the bar, and is considered the \"wild card\" of the group. Charlie has been best friends with Mac since childhood. He shares an apartment with Frank Reynolds, who is very likely his biological father."));
        castClasses.add(new castClass("Deandra \"Sweet Dee\" Reynolds is the bartender at Paddy's Pub. She is the twin sister of Dennis Reynolds, and the legal daughter of Frank and Barbara Reynolds. Dee considers herself a member of The Gang that runs Paddy's, although the rest of the gang often disagrees with this assertion."));
        castClasses.add(new castClass("Ronald \"Mac\" McDonald is a co-owner and sheriff of Paddy's Pub and generally the pub's most active manager. He is roommates with his best friend from high school, Dennis Reynolds, and has been best friends with Charlie Kelly since childhood. Mac is a member of The Gang which also includes Dee and Frank."));
        castClasses.add(new castClass("Frank Reynolds (aka; The Warthog) is the father of Dennis Reynolds and Dee Reynolds, and the ex-husband of Barbara Reynolds. Frank also lives with his alleged biological son (and ex-husband), Charlie Kelly."));
        castClasses.add(new castClass("Dennis Reynolds is the co-owner of Paddy's Pub, and Dee Reynolds' fraternal twin brother. Dennis is best friends with Charlie and Mac, the latter being his roommate."));
        castClasses.add(new castClass("The Waitress is a woman openly stalked by Charlie Kelly, despite numerous restraining orders. (\"The D.E.N.N.I.S. System\") She went to high school with the Gang, and used to work at a local coffee shop frequented by Charlie. She attends the high-school reunion with the Gang, where her name tag is missing (further implying her status as 'easily forgettable', a theme in that episode), preventing her name from being revealed. Many fans assumed her name was Nikki Potnick when Frank showed up with a stolen tag bearing that name. However, Glenn Howerton specified on Twitter that this is not the case."));


        //TextViews
        bioText = (TextView) findViewById(R.id.bioText);
        bioText2 = (TextView) findViewById(R.id.bioText2);

        //Views
        landscapeView = (LinearLayout) findViewById(R.id.landscapeView);
        portraitView = (LinearLayout) findViewById(R.id.portraitView);

        //Spinner
        final Spinner castSpinner = (Spinner) findViewById(R.id.castSpinner);

        //List
        castList = (ListView) findViewById(R.id.castList);


        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.spinner_stuff,
                android.R.layout.simple_dropdown_item_1line
        );

        ArrayAdapter<CharSequence> listAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.spinner_stuff,
                android.R.layout.simple_list_item_1
        );

        castSpinner.setAdapter(spinnerAdapter);


        castList.setAdapter(listAdapter);


        theOrientationListener = new OrientationEventListener(mContext, SensorManager.SENSOR_DELAY_UI) {
            @Override
            public void onOrientationChanged(int orientation) {
                if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
                    landscapeView.setVisibility(View.GONE);
                    portraitView.setVisibility(View.VISIBLE);
                } else {
                    portraitView.setVisibility(View.GONE);
                    landscapeView.setVisibility(View.VISIBLE);
                }
            }
        };



        theOrientationListener.enable();

        final CastAdapter baseAdapter = new CastAdapter(mContext, castClasses);

        castSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
              bioText.setText(castMap.get(position));


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        castList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                bioText2.setText(castMap.get(position));
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        cast();
    }

    public void cast(){
        castMap.put(0, "Charlie Kelly is the janitor of Paddy's Pub. He is a member of the Gang that works at the bar, and is considered the \"wild card\" of the group. Charlie has been best friends with Mac since childhood. He shares an apartment with Frank Reynolds, who is very likely his biological father.");
        castMap.put(1, "Deandra \"Sweet Dee\" Reynolds is the bartender at Paddy's Pub. She is the twin sister of Dennis Reynolds, and the legal daughter of Frank and Barbara Reynolds. Dee considers herself a member of The Gang that runs Paddy's, although the rest of the gang often disagrees with this assertion.");
        castMap.put(2, "Ronald \"Mac\" McDonald is a co-owner and sheriff of Paddy's Pub and generally the pub's most active manager. He is roommates with his best friend from high school, Dennis Reynolds, and has been best friends with Charlie Kelly since childhood. Mac is a member of The Gang which also includes Dee and Frank.");
        castMap.put(3, "Frank Reynolds (aka; The Warthog) is the father of Dennis Reynolds and Dee Reynolds, and the ex-husband of Barbara Reynolds. Frank also lives with his alleged biological son (and ex-husband), Charlie Kelly.");
        castMap.put(4, "Dennis Reynolds is the co-owner of Paddy's Pub, and Dee Reynolds' fraternal twin brother. Dennis is best friends with Charlie and Mac, the latter being his roommate.");
        castMap.put(5, "The Waitress is a woman openly stalked by Charlie Kelly, despite numerous restraining orders. (\"The D.E.N.N.I.S. System\") She went to high school with the Gang, and used to work at a local coffee shop frequented by Charlie. She attends the high-school reunion with the Gang, where her name tag is missing (further implying her status as 'easily forgettable', a theme in that episode), preventing her name from being revealed. Many fans assumed her name was Nikki Potnick when Frank showed up with a stolen tag bearing that name. However, Glenn Howerton specified on Twitter that this is not the case.");

    }
}
