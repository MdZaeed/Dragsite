package bs23.com.dragsite;

import android.content.ClipData;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    RelativeLayout mainRelativeLayout;
    Button button;
    SlidingUpPanelLayout slidingUpPanelLayout;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    RecommnededStoreAdapter recommnededStoreAdapter;
    BaseLinearLayout lastBaseLinearLayout;
    private int id=99;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_alt);

        mainRelativeLayout=(RelativeLayout) findViewById(R.id.rl_main);

        recyclerView=(RecyclerView) findViewById(R.id.rv_elements_list_add_dialog);

        slidingUpPanelLayout = (SlidingUpPanelLayout) findViewById(R.id.sliding_up_panel);

        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recommnededStoreAdapter = new RecommnededStoreAdapter(this, getElementsList());
        recyclerView.setAdapter(recommnededStoreAdapter);
        chotokoro();
    }

    private ArrayList<ElementsModel> getElementsList() {
        ArrayList temp=new ArrayList<>();
        temp.add(new ElementsModel("Text",android.R.drawable.ic_input_add));
        temp.add(new ElementsModel("Image",android.R.drawable.ic_menu_report_image));
        temp.add(new ElementsModel("Video",android.R.drawable.btn_star_big_on));
        temp.add(new ElementsModel("Nothing",android.R.drawable.ic_menu_week));

/*        temp.add("Text");
        temp.add("Image");
        temp.add("Text");
        temp.add("Text");
        temp.add("Text");
        temp.add("Text");
        temp.add("Text");
        temp.add("Text");
        temp.add("Text");
        temp.add("Text");
        temp.add("Text");
        temp.add("Text");
        temp.add("Text");
        temp.add("Text");
        temp.add("Text");
        temp.add("Text");
        temp.add("Text");
        temp.add("Text");
        temp.add("Text");
        temp.add("Text");
        temp.add("Text");
        temp.add("Text");
        temp.add("Text");*/
        return temp;
    }

    private void chotokoro() {
/*        button=(Button) findViewById(R.id.button_to_drag);
        button.setOnTouchListener(new MyTouchListener());
        button.setOnDragListener(new MyDragListener());*/
        mainRelativeLayout.setOnDragListener(new MyDragListener());
    }

    public void showDialog(View view) {
        slidingUpPanelLayout = (SlidingUpPanelLayout) findViewById(R.id.sliding_up_panel);
/*
        slidingUpPanelLayout.setPanelState(SlidingUpPanelLayout.PanelState.EXPANDED);
*/
    }

    final class MyTouchListener implements View.OnTouchListener {
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                view.startDrag(data, shadowBuilder, view, 0);
                Log.d("Down Goes the mountain", "Action Down Working " + view);
                return true;
            } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                Log.d("Up Goes the mountain", "Working Action Up " + view);
                return true;
            } else {
                return false;
            }
        }
    }


    class MyDragListener implements View.OnDragListener {

        @Override
        public boolean onDrag(View v, DragEvent event) {
/*
            isInLowerHalf((ViewGroup) v,event);
*/
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    Log.d("Up Goes the mountain", "Working Drag started " + v);
                    slidingUpPanelLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
                    // do nothing
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    v.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                    Log.d("Up Goes the mountain", "Working drag entered " + v);
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    v.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    break;
                case DragEvent.ACTION_DROP:
                    // Dropped, reassign View to ViewGroup
/*
                    Log.d("And we do the dropping ", "" + view);
*/
                    View view = (View) event.getLocalState();
                    if(((TextView) view).getText().equals("Text"))
                    {
/*                        Button button = new Button(getApplicationContext());
                        button.setText("Hi hello");
                        container.addView(button);*/
                        CustomLayout customLayout=new CustomLayout(getApplicationContext());
                        addElementsInRelativeLayout((ViewGroup) v, customLayout,event);
                        ViewGroup.LayoutParams params=customLayout.getLayoutParams();
                        params.width= ViewGroup.LayoutParams.MATCH_PARENT;
                        params.height=ViewGroup.LayoutParams.WRAP_CONTENT;
                        customLayout.setLayoutParams(params);
                    }
                    if(((TextView) view).getText().equals("Image"))
                    {
/*                        Button button = new Button(getApplicationContext());
                        button.setText("Hi hello");
                        container.addView(button);*/
                        ImageViewWidget customLayout=new ImageViewWidget(getApplicationContext());
                        addElementsInRelativeLayout((ViewGroup)v,customLayout,event);
                        ViewGroup.LayoutParams params=customLayout.getLayoutParams();
                        params.width= ViewGroup.LayoutParams.MATCH_PARENT;
                        params.height=ViewGroup.LayoutParams.WRAP_CONTENT;
                        customLayout.setLayoutParams(params);
                        customLayout.addContents();
                    }
                    Log.d("Up Goes the mountain", "Working drag dropped " + v);
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
/*                    Log.d("And we do the dropping ", "" + view);
                    Log.d("Up Goes the mountain", "Working drag dropped " + v);*/
                    break;
                default:
                    Log.d("Up Goes the mountain", "Working drag ended " + v);
                    break;
            }
            return true;
        }
    }

    private void addElementsInRelativeLayout(ViewGroup droppedOnLayout,BaseLinearLayout childToBeAdded,DragEvent dragEvent)
    {
        try
        {
            RelativeLayout relativeLayout=(RelativeLayout) droppedOnLayout;
            childToBeAdded.setId(uniqueIdGenerator());
            if(lastBaseLinearLayout!=null)
            {
                setUpAboveLayoutParameters(lastBaseLinearLayout,childToBeAdded);
                setUpNewChildParameters(lastBaseLinearLayout,0,childToBeAdded);
            }else
            {
                mainRelativeLayout.addView(childToBeAdded);
            }
            childToBeAdded.setOnDragListener(new MyDragListener());

            lastBaseLinearLayout=childToBeAdded;
            return;
        } catch (Exception e)
        {

            int topLayoutId=((RelativeLayout.LayoutParams)droppedOnLayout.getLayoutParams()).getRules()[RelativeLayout.BELOW];
            if(!isInLowerHalf(droppedOnLayout,dragEvent) && topLayoutId!=0)
            {
                droppedOnLayout=(ViewGroup) findViewById(topLayoutId);
            }
            childToBeAdded.setId(uniqueIdGenerator());

/*            BaseLinearLayout linearLayout1=(BaseLinearLayout) droppedOnLayout;
            RelativeLayout.LayoutParams relaLayoutParams=(RelativeLayout.LayoutParams)linearLayout1.getLayoutParams();
            linearLayout1.setAboveId(childToBeAdded.getId())*/;

            int aboveId=((BaseLinearLayout)droppedOnLayout).getAboveId();

            setUpAboveLayoutParameters(droppedOnLayout,childToBeAdded);
/*
            Log.d("Below id & Above", belowId + "   " + aboveId);
*/


/*
            Log.d("Below id & Above", relaLayoutParams.getRules()[RelativeLayout.BELOW] + "   " + relaLayoutParams.getRules()[RelativeLayout.ABOVE]);
*/

/*            BaseLinearLayout belowLinearLayout2=null;
            RelativeLayout.LayoutParams relaLayoutParams1=null;
            if(aboveId!=0) {
                belowLinearLayout2 = (BaseLinearLayout) this.findViewById(aboveId);
                relaLayoutParams1 = (RelativeLayout.LayoutParams) belowLinearLayout2.getLayoutParams();
                relaLayoutParams1.addRule(RelativeLayout.BELOW, childToBeAdded.getId());
                mainRelativeLayout.updateViewLayout(belowLinearLayout2, relaLayoutParams1);
            }*/

            setUpBelowLayoutParameters(aboveId, childToBeAdded);

/*            RelativeLayout.LayoutParams p = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            p.addRule(RelativeLayout.BELOW, droppedOnLayout.getId());
            if(aboveId!=0) {
                childToBeAdded.setAboveId(aboveId);
            }
            mainRelativeLayout.addView(childToBeAdded, p);*/

            setUpNewChildParameters((BaseLinearLayout)droppedOnLayout,aboveId,childToBeAdded);


/*            RelativeLayout.LayoutParams relaLayoutParams=(RelativeLayout.LayoutParams)childToBeAdded.getLayoutParams();
*//*            for(int i=0;i<relaLayoutParams.getRules().length;i++) {
                Log.d("Below id", relaLayoutParams.getRules()[i] + "   " + i);
            }*//*
            Log.d("Below id", relaLayoutParams.getRules()[RelativeLayout.BELOW] + "   ");*/
/*
            mainRelativeLayout.updateViewLayout(linearLayout1, relaLayoutParams);
*/

            childToBeAdded.setOnDragListener(new MyDragListener());

            if(aboveId==0)
            {
                lastBaseLinearLayout=childToBeAdded;
            }
            return;
        }
    }

    private int uniqueIdGenerator()
    {
        id++;
        return id;
    }

    private int setUpAboveLayoutParameters(ViewGroup linearLayout,BaseLinearLayout child)
    {
        BaseLinearLayout droppedOnLayout=(BaseLinearLayout) linearLayout;
        RelativeLayout.LayoutParams relaLayoutParams=(RelativeLayout.LayoutParams)droppedOnLayout.getLayoutParams();
        droppedOnLayout.setAboveId(child.getId());
        return relaLayoutParams.getRules()[RelativeLayout.BELOW];
    }

    private void setUpBelowLayoutParameters(int layoutId,BaseLinearLayout child)
    {
        BaseLinearLayout belowLinearLayout=null;
        RelativeLayout.LayoutParams relaLayoutParamsOfBelow=null;
        if(layoutId!=0) {
            belowLinearLayout = (BaseLinearLayout) this.findViewById(layoutId);
            relaLayoutParamsOfBelow = (RelativeLayout.LayoutParams) belowLinearLayout.getLayoutParams();
            relaLayoutParamsOfBelow.addRule(RelativeLayout.BELOW, child.getId());
            mainRelativeLayout.updateViewLayout(belowLinearLayout, relaLayoutParamsOfBelow);
        }
    }

    private void setUpNewChildParameters(BaseLinearLayout linearLayout,int aboveId,BaseLinearLayout child)
    {
        RelativeLayout.LayoutParams parameters = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        parameters.addRule(RelativeLayout.BELOW, linearLayout.getId());
        if(aboveId!=0) {
            child.setAboveId(aboveId);
        }
        mainRelativeLayout.addView(child, parameters);
    }

    private boolean isInLowerHalf(ViewGroup droppedOnLayout,DragEvent dragEvent)
    {
        if(dragEvent.getY() < ( droppedOnLayout.getTop()+ (droppedOnLayout.getHeight()/2) ) - droppedOnLayout.getTop() )
        {
            return false;
        }else
        {
            return true;
        }
/*
        Log.d("Drop position" , dragEvent.getY() + "");
        Log.d("Drop position" , droppedOnLayout.getTop() + "");
        return true;*/
    }

}
