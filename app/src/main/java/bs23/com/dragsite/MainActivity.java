package bs23.com.dragsite;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RelativeLayout mainRelativeLayout;
    SlidingUpPanelLayout slidingUpPanelLayout;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    RecommendedStoreAdapter recommendedStoreAdapter;
    BaseLinearLayout lastBaseLinearLayout;
    private int id = 99;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_alt);

        mainRelativeLayout = (RelativeLayout) findViewById(R.id.rl_main);
        slidingUpPanelLayout = (SlidingUpPanelLayout) findViewById(R.id.sliding_up_panel);

        recyclerView = (RecyclerView) findViewById(R.id.rv_elements_list_add_dialog);
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recommendedStoreAdapter = new RecommendedStoreAdapter(this, getElementsList());
        recyclerView.setAdapter(recommendedStoreAdapter);

        chotokoro();
    }

    private ArrayList<ElementsModel> getElementsList() {
        ArrayList temp = new ArrayList<>();
        temp.add(new ElementsModel("Text", android.R.drawable.ic_input_add));
        temp.add(new ElementsModel("Image", android.R.drawable.ic_menu_report_image));
        temp.add(new ElementsModel("Video", android.R.drawable.btn_star_big_on));
        temp.add(new ElementsModel("Nothing", android.R.drawable.ic_menu_week));
        return temp;
    }

    private void chotokoro() {
        mainRelativeLayout.setOnDragListener(new MyDragListener());
    }

    public void showDialog(View view) {
        slidingUpPanelLayout = (SlidingUpPanelLayout) findViewById(R.id.sliding_up_panel);
/*
        slidingUpPanelLayout.setPanelState(SlidingUpPanelLayout.PanelState.EXPANDED);
*/
    }


    class MyDragListener implements View.OnDragListener {

        @Override
        public boolean onDrag(View v, DragEvent event) {
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
/*
                    Log.d("Action Drag started  ", " " + v);
*/
                    slidingUpPanelLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
/*
                    Log.d("Action Drag entered  ", " " + v);
*/
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    clearBottomLayoutColor(v);
/*
                    Log.d("Action Drag exited  ", " " + v);
*/
                    break;
                case DragEvent.ACTION_DROP:
                    View view = (View) event.getLocalState();
                    if (((TextView) view).getText().equals("Text")) {
                        CustomLayout customLayout = new CustomLayout(getApplicationContext());
                        addElementsInRelativeLayout((ViewGroup) v, customLayout, event);
                        ViewGroup.LayoutParams params = customLayout.getLayoutParams();
                        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
                        params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                        customLayout.setLayoutParams(params);
                    }
                    if (((TextView) view).getText().equals("Image")) {
                        ImageViewWidget customLayout = new ImageViewWidget(getApplicationContext());
                        addElementsInRelativeLayout((ViewGroup) v, customLayout, event);
                        ViewGroup.LayoutParams params = customLayout.getLayoutParams();
                        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
                        params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                        customLayout.setLayoutParams(params);
                        customLayout.addContents();
                    }
/*
                    Log.d("Up Goes the mountain", "Working drag dropped " + v);
*/
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
/*
                    Log.d("Action Drag ended  ", " " + v);
*/
                    break;
                default:
                    Log.d("Default ", " " + v);
                    if (v instanceof BaseLinearLayout) {
                        int topLayoutId = ((RelativeLayout.LayoutParams) v.getLayoutParams()).getRules()[RelativeLayout.BELOW];
                        BaseLinearLayout baseLinearLayout = (BaseLinearLayout) v;
                        if (isInLowerHalf(baseLinearLayout, event)) {
                            changeTopAndBottomlayoutColor(v.getId(), topLayoutId);
                        } else {
                            changeTopAndBottomlayoutColor(topLayoutId, v.getId());
                        }
                        break;
                    }
            }
            return true;
        }
    }

    private void addElementsInRelativeLayout(ViewGroup droppedOnLayout, BaseLinearLayout childToBeAdded, DragEvent dragEvent) {
        if (droppedOnLayout instanceof RelativeLayout) {
            childToBeAdded.setId(uniqueIdGenerator());
            if (lastBaseLinearLayout != null) {
                setUpAboveLayoutParameters(lastBaseLinearLayout, childToBeAdded);
                setUpNewChildParameters(lastBaseLinearLayout, 0, childToBeAdded);
            } else {
                mainRelativeLayout.addView(childToBeAdded);
            }
            childToBeAdded.setOnDragListener(new MyDragListener());

            lastBaseLinearLayout = childToBeAdded;
            return;
        } else {

            int topLayoutId = ((RelativeLayout.LayoutParams) droppedOnLayout.getLayoutParams()).getRules()[RelativeLayout.BELOW];
            if (!isInLowerHalf(droppedOnLayout, dragEvent) && topLayoutId != 0) {
                droppedOnLayout = (ViewGroup) findViewById(topLayoutId);
            }
            childToBeAdded.setId(uniqueIdGenerator());

            clearBottomLayoutColor(droppedOnLayout);

            int aboveId = ((BaseLinearLayout) droppedOnLayout).getAboveId();

            setUpAboveLayoutParameters(droppedOnLayout, childToBeAdded);

            setUpBelowLayoutParameters(aboveId, childToBeAdded);

            setUpNewChildParameters((BaseLinearLayout) droppedOnLayout, aboveId, childToBeAdded);

            childToBeAdded.setOnDragListener(new MyDragListener());

            if (aboveId == 0) {
                lastBaseLinearLayout = childToBeAdded;
            }
            return;
        }
    }

    private int uniqueIdGenerator() {
        id++;
        return id;
    }

    private int setUpAboveLayoutParameters(ViewGroup linearLayout, BaseLinearLayout child) {
        BaseLinearLayout droppedOnLayout = (BaseLinearLayout) linearLayout;
        RelativeLayout.LayoutParams relaLayoutParams = (RelativeLayout.LayoutParams) droppedOnLayout.getLayoutParams();
        droppedOnLayout.setAboveId(child.getId());
        return relaLayoutParams.getRules()[RelativeLayout.BELOW];
    }

    private void setUpBelowLayoutParameters(int layoutId, BaseLinearLayout child) {
        BaseLinearLayout belowLinearLayout = null;
        RelativeLayout.LayoutParams relaLayoutParamsOfBelow = null;
        if (layoutId != 0) {
            belowLinearLayout = (BaseLinearLayout) this.findViewById(layoutId);
            relaLayoutParamsOfBelow = (RelativeLayout.LayoutParams) belowLinearLayout.getLayoutParams();
            relaLayoutParamsOfBelow.addRule(RelativeLayout.BELOW, child.getId());
            mainRelativeLayout.updateViewLayout(belowLinearLayout, relaLayoutParamsOfBelow);
        }
    }

    private void setUpNewChildParameters(BaseLinearLayout linearLayout, int aboveId, BaseLinearLayout child) {
        RelativeLayout.LayoutParams parameters = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        parameters.addRule(RelativeLayout.BELOW, linearLayout.getId());
        if (aboveId != 0) {
            child.setAboveId(aboveId);
        }
        mainRelativeLayout.addView(child, parameters);
    }

    private boolean isInLowerHalf(ViewGroup droppedOnLayout, DragEvent dragEvent) {
        if (dragEvent.getY() < (droppedOnLayout.getTop() + (droppedOnLayout.getHeight() / 2)) - droppedOnLayout.getTop()) {
            return false;
        } else {
            return true;
        }
    }

    private void clearBottomLayoutColor(View v) {
        if (v instanceof BaseLinearLayout) {
            ((BaseLinearLayout) v).setBottomViewColor(android.R.color.white);
        }
    }

    private void changeTopAndBottomlayoutColor(int idToBeBlacked, int idToBeWhited) {
        if(idToBeBlacked!=0) {
            BaseLinearLayout baseLinearLayout = (BaseLinearLayout) findViewById(idToBeBlacked);
            baseLinearLayout.setBottomViewColor(android.R.color.black);
        }

        if(idToBeWhited!=0) {
            BaseLinearLayout baseLinearLayout1 = (BaseLinearLayout) findViewById(idToBeWhited);
            baseLinearLayout1.setBottomViewColor(android.R.color.white);
        }
    }

}
