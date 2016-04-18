package bs23.com.dragsite;

import android.app.FragmentManager;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, ImageEditFragment.OnViewReady,OnMapReadyCallback {

    public final android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
    public final android.support.v4.app.FragmentTransaction transaction = fragmentManager.beginTransaction();

    ScrollView mainScrollView;
    RelativeLayout mainRelativeLayout;
    SlidingUpPanelLayout slidingUpPanelLayout;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    RecommendedStoreAdapter recommendedStoreAdapter;
    BaseLinearLayout lastBaseLinearLayout;
    LinearLayout bottomPaneLinearLayout;
    EditOptionsDialog editOptionsDialog;



    LatLng dhaka;
    MapView mapView;

    private int id = 99;
    Bundle savedInstanceState;
    private List<GoogleMap> maps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.savedInstanceState=savedInstanceState;
        setContentView(R.layout.activity_main);

        //setting action bar

        Toolbar actionToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(actionToolbar);

        //setting action bar ends

        mainRelativeLayout = (RelativeLayout) findViewById(R.id.rl_main);
        mainScrollView = (ScrollView) findViewById(R.id.sv_main);
        slidingUpPanelLayout = (SlidingUpPanelLayout) findViewById(R.id.sliding_up_panel);
        bottomPaneLinearLayout=(LinearLayout) findViewById(R.id.ll_pane_layour);
        mainRelativeLayout.setOnClickListener(this);

        chotokoro();
    }

    private void setUpSlidingPane() {
        Button hideButton = (Button) bottomPaneLinearLayout.findViewById(R.id.btn_cancel_add_dialog);
        hideButton.setOnClickListener(this);
/*
        mainRelativeLayout.setOnClickListener(this);
*/

        recyclerView = (RecyclerView) bottomPaneLinearLayout.findViewById(R.id.rv_elements_list_add_dialog);
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
        temp.add(new ElementsModel("Slide show", R.drawable.ic_view_module_black_48dp));
        temp.add(new ElementsModel("Map", R.drawable.ic_place_black_48dp));
        temp.add(new ElementsModel("Divider", R.drawable.ic_view_module_black_48dp));
        temp.add(new ElementsModel("Spacer", R.drawable.ic_view_module_black_48dp));
        temp.add(new ElementsModel("Button", R.drawable.ic_view_module_black_48dp));
        temp.add(new ElementsModel("Search Box", R.drawable.ic_search_black_48dp));
        temp.add(new ElementsModel("HD Video", R.drawable.ic_switch_video_black_48dp));
        temp.add(new ElementsModel("Audio", R.drawable.ic_view_module_black_48dp));
        temp.add(new ElementsModel("Youtube", R.drawable.ic_view_module_black_48dp));
        temp.add(new ElementsModel("File", R.drawable.ic_insert_drive_file_black_48dp));
        temp.add(new ElementsModel("Block Quote", R.drawable.ic_view_module_black_48dp));
        temp.add(new ElementsModel("Social Icons", R.drawable.ic_share_black_48dp));
        temp.add(new ElementsModel("Products", R.drawable.ic_label_outline_black_48dp));
        return temp;
    }

    private void chotokoro() {
        mainRelativeLayout.setOnDragListener(new MyDragListener());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_cancel_add_dialog:
                hideBottomOptionMenu();
                break;

            case R.id.rl_main:
                hideBottomOptionMenu();
                deleteNoticeDialog();
                break;
        }
    }

    public void showElementsAddDialog(View view) {
        if (bottomPaneLinearLayout.getChildCount() != 0) {
            bottomPaneLinearLayout.removeAllViews();
        }
        bottomPaneLinearLayout.addView(LayoutInflater.from(this).inflate(R.layout.dialog_add_elements, null));
        setUpSlidingPane();
        bottomPaneLinearLayout.post(new Runnable() {
            @Override
            public void run() {
                slidingUpPanelLayout.setPanelState(SlidingUpPanelLayout.PanelState.EXPANDED);
            }
        });

        deleteNoticeDialog();
    }

    private void hideBottomOptionMenu() {
        if (bottomPaneLinearLayout.getChildCount() != 0) {
            bottomPaneLinearLayout.removeAllViews();
        }
        slidingUpPanelLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
    }


    @Override
    public void onReady(View view) {
        view.post(new Runnable() {
            @Override
            public void run() {
                slidingUpPanelLayout.setPanelState(SlidingUpPanelLayout.PanelState.EXPANDED);
            }
        });
    }


    class MyDragListener implements View.OnDragListener {

        @Override
        public boolean onDrag(View v, DragEvent event) {
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    hideBottomOptionMenu();
/*
                    Log.d("Action Drag started  ", " " + v);
*/
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
                    final BaseLinearLayout customLayout;
                    if (((TextView) view).getText().equals("Text")) {
                        customLayout = new TextViewWidget(getApplicationContext());
                        addNewElementsOfType(v, customLayout, event);
                    } else if (((TextView) view).getText().equals("Image")) {
                        customLayout = new ImageViewWidget(getApplicationContext());
                        addNewElementsOfType(v, customLayout, event);
                    } else if (((TextView) view).getText().equals("Title")) {
                        customLayout = new TitleViewWidget(getApplicationContext());
                        addNewElementsOfType(v, customLayout, event);
                    } else if (((TextView) view).getText().equals("Button")) {
                        customLayout = new ButtonViewWidget(getApplicationContext());
                        addNewElementsOfType(v, customLayout, event);
                    } else if (((TextView) view).getText().equals("Gallery")) {
                        customLayout = new GalleryViewWidget(getApplicationContext());
                        addNewElementsOfType(v, customLayout, event);
                    } else if (((TextView) view).getText().equals("Map")) {
                        customLayout = new MapsWidget(getApplicationContext());
                        addNewElementsOfType(v, customLayout, event);
                        handleMapCreation((MapsWidget)customLayout);
                    } else {
                        customLayout = null;
                    }

                    if (customLayout != null) {
                        customLayout.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                hideBottomOptionMenu();
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
            belowLinearLayout =(BaseLinearLayout) this.findViewById(layoutId);
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
        if (idToBeBlacked != 0) {
            BaseLinearLayout baseLinearLayout = (BaseLinearLayout) findViewById(idToBeBlacked);
            baseLinearLayout.setBottomViewColor(android.R.color.black);
        }

        if (idToBeWhited != 0) {
            BaseLinearLayout baseLinearLayout1 = (BaseLinearLayout) findViewById(idToBeWhited);
            baseLinearLayout1.setBottomViewColor(android.R.color.transparent);
        }
    }

    private void setLayoutParameters(BaseLinearLayout customLayout) {
        ViewGroup.LayoutParams params = customLayout.getLayoutParams();
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        customLayout.setLayoutParams(params);
    }

    private void addNewElementsOfType(View v, BaseLinearLayout customLayout, DragEvent event) {
        addElementsInRelativeLayout((ViewGroup) v, customLayout, event);
        setLayoutParameters(customLayout);
        customLayout.addContents();
    }

    private void showNoticeDialog(final BaseLinearLayout child) {

        if (editOptionsDialog != null) {
            deleteNoticeDialog();
        }

        editOptionsDialog = new EditOptionsDialog(this);
        ViewCompat.setElevation(editOptionsDialog, 20);
        mainRelativeLayout.addView(editOptionsDialog);
        editOptionsDialog.addContents();
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) editOptionsDialog.getLayoutParams();
        params.addRule(RelativeLayout.ABOVE, child.getId());
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        editOptionsDialog.setLayoutParams(params);


        editOptionsDialog.findViewById(R.id.edit_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (child instanceof TextViewWidget) {
                    Log.d("ajaira", "textview eita");

                    if(bottomPaneLinearLayout.getChildCount()!=0)
                    {
                        bottomPaneLinearLayout.removeAllViews();
                    }

                    ImageEditFragment imageEditFragment= ImageEditFragment.newInstance();
                    transaction.add(bottomPaneLinearLayout.getId(), imageEditFragment).commit();

                }
                else if (child instanceof TitleViewWidget)
                    Log.d("hudai", "Title view eita");

            }
        });



        child.setBackgroundResource(R.drawable.dark_blue_border_transparent_background);

    }

    private void deleteNoticeDialog() {

        if (editOptionsDialog != null) {
            mainRelativeLayout.removeView(editOptionsDialog);
            editOptionsDialog=null;
        }
    }

    private void handleMapCreation(MapsWidget mapsWidget)
    {
        dhaka=new LatLng(23.7000,90.3667);
/*
        MapFragment mapFragment = (MapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.main_map_view);
        mapFragment.getMapAsync(this);*/
        mapView=(MapView) mapsWidget.findViewById(R.id.main_map_view);
        mapView.onCreate(savedInstanceState);
        if (mapView != null) {
            mapView.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        if(maps==null)
        {
            maps=new ArrayList<>();
        }
        maps.add(googleMap);

        CameraPosition cameraPosition=new CameraPosition.Builder()
                .zoom(10)
                .target(dhaka)
                .build();
        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
/*        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.getUiSettings().setScrollGesturesEnabled(false);*/
        MarkerOptions endMarkerOption = new MarkerOptions().position(dhaka)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
        googleMap.addMarker(endMarkerOption);
        googleMap.getUiSettings().setAllGesturesEnabled(false);
        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                Log.d("Map Clicked" , "True");
            }
        });
        googleMap.setOnMarkerClickListener(null);
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        mapView.onResume();
    }

    public void openMaps(View view) {
/*        changeZoom(0, (float) 5.0);

        Log.d("Lati and Long: ", getLatitude(0) + " and " + getLongitude(0));
        setLatitude(0, 25.0);
*//*
        setLongitude(0,95.0);
*/
        setPositionByAddress(0,"Jhenaidah");
    }

    private void setPositionByAddress(int positionOfTheMap,String address)
    {
        MapsWidget.setAddress(maps.get(positionOfTheMap), address);
    }

    private float getZoom(int positionOfTheMap)
    {
        return MapsWidget.getZoom(maps.get(positionOfTheMap));
    }

    private void setMarkerShowing(int positionOfTheMap,boolean show)
    {
        MapsWidget.setMarkerShowing(maps.get(positionOfTheMap),show);

    }

    private void setZoomControlEnabled(int positionOfTheMap,boolean enabled)
    {
        MapsWidget.setZoomControlEnabled(maps.get(positionOfTheMap),enabled);
    }

    private void changeZoom(int positionOfTheMap, float zoom)
    {
        MapsWidget.changeZoom(maps.get(positionOfTheMap),zoom);
    }

    public Double getLatitude(int positionOfTheMap)
    {
        return MapsWidget.getLatitude(maps.get(positionOfTheMap));
    }

    public Double getLongitude(int positionOfTheMap)
    {
        return MapsWidget.getLongitude(maps.get(positionOfTheMap));
    }

    public void setLatitude(int positionOfTheMap,Double newLatitude)
    {
        MapsWidget.setLatitude(maps.get(positionOfTheMap),newLatitude);
    }

    public void setLongitude(int positionOfTheMap,Double newLongitude)
    {
        MapsWidget.setLongitude(maps.get(positionOfTheMap),newLongitude);
    }


    @Override
    protected void onResume()
    {
        super.onResume();
        if (mapView != null) {
            mapView.onResume();
        }
    }

    @Override
    public final void onDestroy()
    {
        if (mapView != null) {
            mapView.onDestroy();
        }
        super.onDestroy();
    }

    @Override
    public final void onLowMemory()
    {
        mapView.onLowMemory();
        super.onLowMemory();
    }

    @Override
    public final void onPause()
    {
        mapView.onPause();
        super.onPause();
    }

}
