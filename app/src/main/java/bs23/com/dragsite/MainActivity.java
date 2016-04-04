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
import android.widget.TextView;

import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    LinearLayout linearLayout;
    Button button;
    SlidingUpPanelLayout slidingUpPanelLayout;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    RecommnededStoreAdapter recommnededStoreAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linearLayout=(LinearLayout) findViewById(R.id.lllllll);

        recyclerView=(RecyclerView) findViewById(R.id.rv_elements_list_add_dialog);

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
        linearLayout.setOnDragListener(new MyDragListener());
    }

    public void showDialog(View view) {
        slidingUpPanelLayout = (SlidingUpPanelLayout) findViewById(R.id.sliding_up_panel);
        slidingUpPanelLayout.setPanelState(SlidingUpPanelLayout.PanelState.EXPANDED);
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
                    View view = (View) event.getLocalState();
                    LinearLayout container = (LinearLayout) v;
                    if(((TextView) view).getText().equals("Text"))
                    {
/*                        Button button = new Button(getApplicationContext());
                        button.setText("Hi hello");
                        container.addView(button);*/
                        CustomLayout customLayout=new CustomLayout(getApplicationContext());
                        container.addView(customLayout);
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
                        container.addView(customLayout);
                        ViewGroup.LayoutParams params=customLayout.getLayoutParams();
                        params.width= ViewGroup.LayoutParams.MATCH_PARENT;
                        params.height=ViewGroup.LayoutParams.WRAP_CONTENT;
                        customLayout.setLayoutParams(params);
                        customLayout.addContents();
                    }
                    Log.d("And we do the dropping ", "" + view);
                    Log.d("Up Goes the mountain", "Working drag dropped " + v);
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    break;
                default:
                    Log.d("Up Goes the mountain", "Working drag ended " + v);
                    break;
            }
            return true;
        }
    }
}
