package bs23.com.dragsite;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity implements View.OnClickListener {

    ScrollView mainScrollView;
    RelativeLayout mainRelativeLayout;
    SlidingUpPanelLayout slidingUpPanelLayout;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    RecommendedStoreAdapter recommendedStoreAdapter;
    BaseLinearLayout lastBaseLinearLayout;
    EditOptionsDialog editOptionsDialog;
    private int id = 99;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_alt);

        mainRelativeLayout = (RelativeLayout) findViewById(R.id.rl_main);
        mainScrollView=(ScrollView) findViewById(R.id.sv_main);

        setUpSlidingPane();
        chotokoro();
    }

    private void setUpSlidingPane() {
        slidingUpPanelLayout = (SlidingUpPanelLayout) findViewById(R.id.sliding_up_panel);
        Button hideButton=(Button) findViewById(R.id.btn_cancel_add_dialog);
        hideButton.setOnClickListener(this);
        mainRelativeLayout.setOnClickListener(this);

        recyclerView = (RecyclerView) findViewById(R.id.rv_elements_list_add_dialog);
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recommendedStoreAdapter = new RecommendedStoreAdapter(this, getElementsList());
        recyclerView.setAdapter(recommendedStoreAdapter);
    }

    private ArrayList<ElementsModel> getElementsList() {
        ArrayList temp = new ArrayList<>();
        temp.add(new ElementsModel("Title", R.drawable.ic_title_black_48dp));
        temp.add(new ElementsModel("Text", R.drawable.ic_format_align_left_black_48dp));
        temp.add(new ElementsModel("Image", R.drawable.ic_image_black_48dp));
        temp.add(new ElementsModel("Gallery", R.drawable.ic_view_module_black_48dp));
        temp.add(new ElementsModel("Slide show",R.drawable.ic_view_module_black_48dp));
        temp.add(new ElementsModel("Map",R.drawable.ic_place_black_48dp));
        temp.add(new ElementsModel("Divider",R.drawable.ic_view_module_black_48dp));
        temp.add(new ElementsModel("Spacer",R.drawable.ic_view_module_black_48dp));
        temp.add(new ElementsModel("Button",R.drawable.ic_view_module_black_48dp));
        temp.add(new ElementsModel("Search Box",R.drawable.ic_search_black_48dp));
        temp.add(new ElementsModel("HD Video",R.drawable.ic_switch_video_black_48dp));
        temp.add(new ElementsModel("Audio",R.drawable.ic_view_module_black_48dp));
        temp.add(new ElementsModel("Youtube",R.drawable.ic_view_module_black_48dp));
        temp.add(new ElementsModel("File",R.drawable.ic_insert_drive_file_black_48dp));
        temp.add(new ElementsModel("Block Quote",R.drawable.ic_view_module_black_48dp));
        temp.add(new ElementsModel("Social Icons",R.drawable.ic_share_black_48dp));
        temp.add(new ElementsModel("Products",R.drawable.ic_label_outline_black_48dp));
        return temp;
    }

    private void chotokoro() {
        mainRelativeLayout.setOnDragListener(new MyDragListener());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btn_cancel_add_dialog:
/*
            case R.id.rl_main:
*/
                slidingUpPanelLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
                break;
        }
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
                        TextViewWidget customLayout = new TextViewWidget(getApplicationContext());
                        addNewElementsOfType(v,customLayout,event);
                    }
                    else if (((TextView) view).getText().equals("Image")) {
                        ImageViewWidget customLayout = new ImageViewWidget(getApplicationContext());
                        addNewElementsOfType(v,customLayout,event);
                    }
                    else if (((TextView) view).getText().equals("Title")) {
                        TitleViewWidget customLayout = new TitleViewWidget(getApplicationContext());
                        addNewElementsOfType(v,customLayout,event);
                    }
                    else if (((TextView) view).getText().equals("Button")) {
                        ButtonViewWidget customLayout = new ButtonViewWidget(getApplicationContext());
                        addNewElementsOfType(v,customLayout,event);
                    }
                    else if (((TextView) view).getText().equals("Gallery")) {
                        final GalleryViewWidget customLayout = new GalleryViewWidget(getApplicationContext());
                        addNewElementsOfType(v,customLayout,event);
                        customLayout.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                showNoticeDialog(customLayout);
                            }
                        });
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
            ((BaseLinearLayout) v).setBottomViewColor(android.R.color.transparent);
        }
    }

    private void changeTopAndBottomlayoutColor(int idToBeBlacked, int idToBeWhited) {
        if(idToBeBlacked!=0) {
            BaseLinearLayout baseLinearLayout = (BaseLinearLayout) findViewById(idToBeBlacked);
            baseLinearLayout.setBottomViewColor(android.R.color.black);
        }

        if(idToBeWhited!=0) {
            BaseLinearLayout baseLinearLayout1 = (BaseLinearLayout) findViewById(idToBeWhited);
            baseLinearLayout1.setBottomViewColor(android.R.color.transparent);
        }
    }

    private void setLayoutParameters(BaseLinearLayout customLayout)
    {
        ViewGroup.LayoutParams params = customLayout.getLayoutParams();
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        customLayout.setLayoutParams(params);
    }

    private void addNewElementsOfType(View v,BaseLinearLayout customLayout,DragEvent event)
    {
        addElementsInRelativeLayout((ViewGroup) v, customLayout, event);
        setLayoutParameters(customLayout);
        customLayout.addContents();
    }

    private void showNoticeDialog(BaseLinearLayout child) {

        if(editOptionsDialog!=null)
        {
            deleteNoticeDialog();
        }
        editOptionsDialog=new EditOptionsDialog(this);
        ViewCompat.setElevation(editOptionsDialog, 20);
        mainRelativeLayout.addView(editOptionsDialog);
        editOptionsDialog.addContents();
        RelativeLayout.LayoutParams params =(RelativeLayout.LayoutParams) editOptionsDialog.getLayoutParams();
        params.addRule(RelativeLayout.ABOVE, child.getId());
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        editOptionsDialog.setLayoutParams(params);
    }

    private void deleteNoticeDialog()
    {
        mainRelativeLayout.removeView(editOptionsDialog);
    }
}
