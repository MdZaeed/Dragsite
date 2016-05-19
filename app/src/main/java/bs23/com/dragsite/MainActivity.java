package bs23.com.dragsite;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import java.util.ArrayList;
import java.util.List;

import bs23.com.dragsite.adapter.WebElementsAdapter;
import bs23.com.dragsite.fragments.AudioEditFragment;
import bs23.com.dragsite.fragments.BaseEditFragment;
import bs23.com.dragsite.fragments.ButtonEditFragment;
import bs23.com.dragsite.fragments.DividerEditFragment;
import bs23.com.dragsite.fragments.FileEditFragment;
import bs23.com.dragsite.fragments.GalleryEditFragment;
import bs23.com.dragsite.fragments.HdVideoEditFragment;
import bs23.com.dragsite.fragments.ImageEditFragment;
import bs23.com.dragsite.fragments.MapEditFragment;
import bs23.com.dragsite.fragments.SearchBoxEditFragment;
import bs23.com.dragsite.fragments.SocialIconsEditFragment;
import bs23.com.dragsite.fragments.YoutubeEditFragment;
import bs23.com.dragsite.model.ElementsModel;
import bs23.com.dragsite.widgets.AudioWidget;
import bs23.com.dragsite.widgets.BaseLinearLayout;
import bs23.com.dragsite.widgets.BlockquoteWidget;
import bs23.com.dragsite.widgets.ButtonWidget;
import bs23.com.dragsite.widgets.DividerWidget;
import bs23.com.dragsite.widgets.FileWidget;
import bs23.com.dragsite.widgets.GalleryViewWidget;
import bs23.com.dragsite.widgets.HdVideoWidget;
import bs23.com.dragsite.widgets.ImageViewWidget;
import bs23.com.dragsite.widgets.MapsWidget;
import bs23.com.dragsite.widgets.ProductsWidget;
import bs23.com.dragsite.widgets.SearchBoxWidget;
import bs23.com.dragsite.widgets.SocialIconsWidget;
import bs23.com.dragsite.widgets.SpacerWidget;
import bs23.com.dragsite.widgets.TextViewWidget;
import bs23.com.dragsite.widgets.TitleViewWidget;
import bs23.com.dragsite.widgets.YoutubeWidget;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, OnMapReadyCallback, BaseEditFragment.OnViewReady {
    public android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();

    ScrollView mainScrollView;
    SoftKeyboardLsnedRelativeLayout mainRelativeLayout;
    SlidingUpPanelLayout slidingUpPanelLayout;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    WebElementsAdapter recommendedStoreAdapter;
    BaseLinearLayout lastBaseLinearLayout;
    private LinearLayout bottomPaneLinearLayout;
    List<Fragment> fragmentList;
    BaseLinearLayout foregroundDrawn;
    TextView focusedTextView;
    EditText focusedEditText;
    PopupWindow popupWindow;

    /*
    LatLng dhaka;
*/
    MapView mapView;
    private int id = 99;
    Bundle savedInstanceState;
    MapsWidget currentMapsWidget;
/*
    private List<GoogleMap> maps;
*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.savedInstanceState = savedInstanceState;
        setContentView(R.layout.activity_main);

        //setting action bar

        Toolbar actionToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(actionToolbar);

        //setting action bar ends

        mainRelativeLayout = (SoftKeyboardLsnedRelativeLayout) findViewById(R.id.rl_main);
        mainScrollView = (ScrollView) findViewById(R.id.sv_main);
        slidingUpPanelLayout = (SlidingUpPanelLayout) findViewById(R.id.sliding_up_panel);
        setBottomPaneLinearLayout((LinearLayout) findViewById(R.id.ll_pane_layour));
        mainRelativeLayout.setOnClickListener(this);
        fragmentList = new ArrayList<>();
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        mainRelativeLayout.setOnDragListener(new MyDragListener());

        slidingUpPanelLayout.setTouchEnabled(false);
    }

    private void setUpSlidingPane() {
        Button hideButton = (Button) getBottomPaneLinearLayout().findViewById(R.id.btn_cancel_add_dialog);
        hideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideBottomOptionMenu();
            }
        });

        recyclerView = (RecyclerView) getBottomPaneLinearLayout().findViewById(R.id.rv_elements_list_add_dialog);
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recommendedStoreAdapter = new WebElementsAdapter(this, getElementsList());
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
        temp.add(new ElementsModel("Blockquote", R.drawable.ic_view_module_black_48dp));
        temp.add(new ElementsModel("Social Icons", R.drawable.ic_share_black_48dp));
        temp.add(new ElementsModel("Products", R.drawable.ic_label_outline_black_48dp));
        return temp;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_main:
                hideBottomOptionMenu();
                deleteNoticeDialog(foregroundDrawn);
                break;
        }
    }

    public void showElementsAddDialog(View view) {
        if (getBottomPaneLinearLayout().getChildCount() != 0) {
            getBottomPaneLinearLayout().removeAllViews();
        }
        getBottomPaneLinearLayout().addView(LayoutInflater.from(this).inflate(R.layout.dialog_add_elements, null));
        setUpSlidingPane();
        getBottomPaneLinearLayout().post(new Runnable() {
            @Override
            public void run() {
                slidingUpPanelLayout.setPanelState(SlidingUpPanelLayout.PanelState.EXPANDED);
            }
        });


        deleteNoticeDialog(foregroundDrawn);
    }

    public void hideBottomOptionMenu() {

        int count = fragmentManager.getBackStackEntryCount();
        for (int i = 0; i < count; i++) {
            fragmentManager.popBackStack();
        }

        for (Fragment frag : fragmentList) {
            fragmentManager.beginTransaction().remove(frag).commit();
        }

        if (getBottomPaneLinearLayout().getChildCount() != 0) {
            getBottomPaneLinearLayout().removeAllViews();
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

    public LinearLayout getBottomPaneLinearLayout() {
        return bottomPaneLinearLayout;
    }

    public void setBottomPaneLinearLayout(LinearLayout bottomPaneLinearLayout) {
        this.bottomPaneLinearLayout = bottomPaneLinearLayout;
    }

    class MyDragListener implements View.OnDragListener {

        @Override
        public boolean onDrag(View v, DragEvent event) {
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    hideBottomOptionMenu();
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    clearBottomLayoutColor(v);
                    break;
                case DragEvent.ACTION_DROP:
                    View view = (View) event.getLocalState();
                    if (view instanceof TextView) {
                        final BaseLinearLayout customLayout;

                        if (((TextView) view).getText().equals("Title")) {
                            customLayout = new TitleViewWidget(getApplicationContext());
                        } else if (((TextView) view).getText().equals("Text")) {
                            customLayout = new TextViewWidget(getApplicationContext());
                        } else if (((TextView) view).getText().equals("Image")) {
                            customLayout = new ImageViewWidget(getApplicationContext());
                        } else if (((TextView) view).getText().equals("Gallery")) {
                            customLayout = new GalleryViewWidget(getApplicationContext());
                        } else if (((TextView) view).getText().equals("Slideshow")) {
                            customLayout = null;
                            //Not yet created
                        } else if (((TextView) view).getText().equals("Map")) {
                            customLayout = new MapsWidget(getApplicationContext());
                            addNewElementsOfType(v, customLayout, event);
                            currentMapsWidget = (MapsWidget) customLayout;
                            handleMapCreation((MapsWidget) customLayout);
                        } else if (((TextView) view).getText().equals("Divider")) {
                            customLayout = new DividerWidget(getApplicationContext());
                        } else if (((TextView) view).getText().equals("Spacer")) {
                            customLayout = new SpacerWidget(getApplicationContext());
                        } else if (((TextView) view).getText().equals("Button")) {
                            customLayout = new ButtonWidget(getApplicationContext());
                        } else if (((TextView) view).getText().equals("Search Box")) {
                            customLayout = new SearchBoxWidget(getApplicationContext());
                        } else if (((TextView) view).getText().equals("HD Video")) {
                            customLayout = new HdVideoWidget(getApplicationContext());
                        } else if (((TextView) view).getText().equals("Audio")) {
                            customLayout = new AudioWidget(getApplicationContext());
                        } else if (((TextView) view).getText().equals("Youtube")) {
                            customLayout = new YoutubeWidget(getApplicationContext());
                        } else if (((TextView) view).getText().equals("File")) {
                            customLayout = new FileWidget(getApplicationContext());
                        } else if (((TextView) view).getText().equals("Blockquote")) {
                            customLayout = new BlockquoteWidget(getApplicationContext());
                        } else if (((TextView) view).getText().equals("Social Icons")) {
                            customLayout = new SocialIconsWidget(getApplicationContext());
                        } else if (((TextView) view).getText().equals("Products")) {
                            customLayout = new ProductsWidget(getApplicationContext());
                        } else {
                            customLayout = null;
                        }

                        if (customLayout != null) {
                            if (!(customLayout instanceof MapsWidget)) {
                                addNewElementsOfType(v, customLayout, event);
                            }

                            customLayout.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    hideBottomOptionMenu();
                                    showNoticeDialog(customLayout);
                                }
                            });
                        }

                        JsonWriter.getInstance(getApplicationContext()).setWidgetSequence(getSequence());
                    } else {
                        replaceNewElementsOfType(v, (BaseLinearLayout) view, event);
                        JsonWriter.getInstance(getApplicationContext()).setWidgetSequence(getSequence());
                    }
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    break;
                default:
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

    private void replaceNewElementsOfType(View v, BaseLinearLayout view, DragEvent event) {
        if (v == view) {
            Toast.makeText(this, "Dropped On The Same Element", Toast.LENGTH_LONG).show();
        } else {
            int id = view.getId();
            removeView(view);
            addElementsInRelativeLayout((ViewGroup) v, view, event, id);
            setLayoutParameters(view);
        }
    }

/*    private void setUpDragStartedViews(BaseLinearLayout view) {

        BaseLinearLayout baseLinearLayout = null;
        int belowId = ((RelativeLayout.LayoutParams) view.getLayoutParams()).getRules()[RelativeLayout.BELOW];
        if (belowId != 0) {
            baseLinearLayout = (BaseLinearLayout) findViewById(belowId);
            baseLinearLayout.setAboveId(view.getAboveId());
        }

        if (view.getAboveId() == 0) {
            lastBaseLinearLayout = baseLinearLayout;
        } else {
            BaseLinearLayout baseLinearLayout1 = (BaseLinearLayout) findViewById(view.getAboveId());
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) baseLinearLayout1.getLayoutParams();
            layoutParams.addRule(RelativeLayout.BELOW, belowId);
            baseLinearLayout1.setLayoutParams(layoutParams);
        }
    }*/

    private void addElementsInRelativeLayout(ViewGroup droppedOnLayout, BaseLinearLayout childToBeAdded, DragEvent dragEvent, int id) {
        if (droppedOnLayout instanceof RelativeLayout) {
            childToBeAdded.setId(id);
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

            childToBeAdded.setId(id);

            clearBottomLayoutColor(droppedOnLayout);

            int aboveId = ((BaseLinearLayout) droppedOnLayout).getAboveId();

            setUpAboveLayoutParameters(droppedOnLayout, childToBeAdded);

            setUpBelowLayoutParameters(aboveId, childToBeAdded);

            setUpNewChildParameters((BaseLinearLayout) droppedOnLayout, aboveId, childToBeAdded);

            childToBeAdded.setOnDragListener(new MyDragListener());

            if (aboveId == 0) {
                lastBaseLinearLayout = childToBeAdded;
                childToBeAdded.setAboveId(0);
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
        BaseLinearLayout belowLinearLayout;
        RelativeLayout.LayoutParams relaLayoutParamsOfBelow;
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
        addElementsInRelativeLayout((ViewGroup) v, customLayout, event, uniqueIdGenerator());
        setLayoutParameters(customLayout);
        customLayout.addContents();
    }

    private void showNoticeDialog(final BaseLinearLayout child) {

        if (popupWindow != null && popupWindow.isShowing()) {
            if (foregroundDrawn == child) {
                popupWindow.dismiss();
            } else {
                deleteNoticeDialog(foregroundDrawn);
            }
        }

        if (!child.isDrawn()) {
            child.drawForegroundRectangle();
            foregroundDrawn = child;
        }

        popupWindow = createPopupWindow();
        Rect rect = new Rect();
        mainRelativeLayout.getLocalVisibleRect(rect);
        Rect ownRect = new Rect();
        child.getLocalVisibleRect(ownRect);
        popupWindow.setFocusable(false);
        popupWindow.setOutsideTouchable(false);
        if (rect.contains(child.getLeft(), child.getTop() - 80)) {
            popupWindow.showAsDropDown(child, 0, -child.getHeight() - 80);
        } else if (rect.contains(child.getLeft(), child.getBottom() + 80)) {
            popupWindow.showAsDropDown(child, 0, 0);
        } else {
            popupWindow.showAsDropDown(child, 0, -ownRect.height());
        }

        popupWindow.getContentView().findViewById(R.id.edit_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editLayoutAddition(child);
                deleteNoticeDialog(child);
            }
        });

        popupWindow.getContentView().findViewById(R.id.btn_delete_widget).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteNoticeDialog(child);
                removeView(child);
            }
        });
    }

    public PopupWindow createPopupWindow() {

        PopupWindow popupWindow = new PopupWindow(this);

        popupWindow.setFocusable(true);
        popupWindow.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);

        popupWindow.setContentView(LayoutInflater.from(this).inflate(R.layout.dialog_on_click, null, false));

        return popupWindow;
    }

    private void removeView(BaseLinearLayout layoutToBeDeleted) {
        RelativeLayout.LayoutParams relaLayoutParams = (RelativeLayout.LayoutParams) layoutToBeDeleted.getLayoutParams();
        final int belowId = relaLayoutParams.getRules()[RelativeLayout.BELOW];

        final int topId = layoutToBeDeleted.getAboveId();

        BaseLinearLayout belowBaseLinearLayout = (BaseLinearLayout) this.findViewById(belowId);

        if (topId != 0) {
            BaseLinearLayout topBaseLinearLayout = (BaseLinearLayout) this.findViewById(topId);

            RelativeLayout.LayoutParams relaLayoutParams1 = (RelativeLayout.LayoutParams) topBaseLinearLayout.getLayoutParams();
            relaLayoutParams1.addRule(RelativeLayout.BELOW, belowId);
        } else {
            lastBaseLinearLayout = belowBaseLinearLayout;
        }

        belowBaseLinearLayout.setAboveId(topId);
        mainRelativeLayout.removeView(layoutToBeDeleted);
    }

    private void editLayoutAddition(BaseLinearLayout child) {
        int count = fragmentManager.getBackStackEntryCount();
        for (int i = 0; i < count; i++) {
            fragmentManager.popBackStack();
        }

        for (Fragment frag : fragmentList) {
            fragmentManager.beginTransaction().remove(frag).commit();
        }

        if (getBottomPaneLinearLayout().getChildCount() != 0) {
            getBottomPaneLinearLayout().removeAllViews();
        }

        if (child instanceof TitleViewWidget) {
            EditText editText = (EditText) child.findViewById(R.id.et_title);
            TextView textView = (TextView) child.findViewById(R.id.tv_title);
            setEditTextEdit(editText, textView, (TitleViewWidget) child);
        } else if (child instanceof TextViewWidget) {
            EditText editText = (EditText) child.findViewById(R.id.et_text_widget);
            TextView textView = (TextView) child.findViewById(R.id.tv_text_widget);
            setEditTextEdit(editText, textView,null);
        } else if (child instanceof ImageViewWidget) {
            ImageEditFragment imageEditFragment = ImageEditFragment.newInstance();
            imageEditFragment.setImageViewWidget((ImageViewWidget) child);
            beginFragmentTransaction(imageEditFragment);
            fragmentList.add(imageEditFragment);
        } else if (child instanceof GalleryViewWidget) {
            GalleryEditFragment galleryEditFragment = GalleryEditFragment.newInstance();
            galleryEditFragment.setGalleryViewWidget((GalleryViewWidget) child);
            beginFragmentTransaction(galleryEditFragment);
            fragmentList.add(galleryEditFragment);
        } else if (child instanceof DividerWidget) {
            DividerEditFragment dividerEditFragment = DividerEditFragment.newInstance();
            dividerEditFragment.setDividerWidget((DividerWidget) child);
            beginFragmentTransaction(dividerEditFragment);
            fragmentList.add(dividerEditFragment);
        } else if (child instanceof ButtonWidget) {
            ButtonEditFragment buttonEditFragment = ButtonEditFragment.newInstance();
            beginFragmentTransaction(buttonEditFragment);
        } else if (child instanceof SearchBoxWidget) {
            SearchBoxEditFragment searchBoxEditFragment = SearchBoxEditFragment.newInstance();
            searchBoxEditFragment.setSearchBoxWidget((SearchBoxWidget) child);
            beginFragmentTransaction(searchBoxEditFragment);
            fragmentList.add(searchBoxEditFragment);
        } else if (child instanceof HdVideoWidget) {
            HdVideoEditFragment hdVideoEditFragment = HdVideoEditFragment.newInstance();
            beginFragmentTransaction(hdVideoEditFragment);
        } else if (child instanceof AudioWidget) {
            AudioEditFragment audioEditFragment = AudioEditFragment.newInstance();
            beginFragmentTransaction(audioEditFragment);
        } else if (child instanceof YoutubeWidget) {
            YoutubeEditFragment youtubeEditFragment = YoutubeEditFragment.newInstance();
            youtubeEditFragment.setYoutubeWidget((YoutubeWidget) child);
            beginFragmentTransaction(youtubeEditFragment);
            fragmentList.add(youtubeEditFragment);
        } else if (child instanceof FileWidget) {
            FileEditFragment fileEditFragment = FileEditFragment.newInstance();
            beginFragmentTransaction(fileEditFragment);
        } else if (child instanceof BlockquoteWidget) {
            EditText editText = (EditText) child.findViewById(R.id.et_blockquote_text);
            TextView textView = (TextView) child.findViewById(R.id.tv_blockquote_text);
            setEditTextEdit(editText, textView,null);
        } else if (child instanceof SocialIconsWidget) {
            SocialIconsEditFragment socialIconsEditFragment = SocialIconsEditFragment.newInstance();
            beginFragmentTransaction(socialIconsEditFragment);
        } else if (child instanceof MapsWidget) {
            MapEditFragment mapEditFragment = MapEditFragment.newInstance();
            beginFragmentTransaction(mapEditFragment);
            mapEditFragment.setMapsWidget((MapsWidget) child);
            fragmentList.add(mapEditFragment);
        }
    }

    private void beginFragmentTransaction(BaseEditFragment baseEditFragment) {
        fragmentManager.beginTransaction()
                .add(getBottomPaneLinearLayout().getId(), baseEditFragment, BaseEditFragment.FRAGMENT_NAME)
                .commit();
        baseEditFragment.setFragmentManager1(fragmentManager);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void setEditTextEdit(EditText editText, TextView textView, final TitleViewWidget titleViewWidget) {

        focusedEditText = editText;
        focusedTextView = textView;

        textView.setVisibility(View.GONE);
        editText.setVisibility(View.VISIBLE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                editText.setBackground(getResources().getDrawable(R.drawable.dark_blue_border_transparent_background, getTheme()));
            } else {
                editText.setBackground(getResources().getDrawable(R.drawable.dark_blue_border_transparent_background));
            }
        } else {
            editText.setBackgroundDrawable(getResources().getDrawable(R.drawable.dark_blue_border_transparent_background));
        }

        editText.requestFocus();
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInputFromWindow(editText.getApplicationWindowToken(), InputMethodManager.SHOW_FORCED, 0);

        mainRelativeLayout.addSoftKeyboardLsner(new SoftKeyboardLsnedRelativeLayout.SoftKeyboardListenner() {

            @Override
            public void onSoftKeyboardHide() {
                hideSOftKeyBoard(titleViewWidget);
            }
        });
    }

    private void hideSOftKeyBoard(TitleViewWidget titleViewWidget) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInputFromWindow(focusedEditText.getApplicationWindowToken(), InputMethodManager.SHOW_FORCED, 0);

        focusedTextView.setText(focusedEditText.getText().toString());
        focusedTextView.setVisibility(View.VISIBLE);
        focusedEditText.setVisibility(View.GONE);
        mainRelativeLayout.requestFocus();

        mainRelativeLayout.removeSoftKeyboardLsner();

        titleViewWidget.setTitleText(focusedTextView.getText().toString());
    }

    private void deleteNoticeDialog(BaseLinearLayout child) {

        if (popupWindow != null && popupWindow.isShowing()) {
            popupWindow.dismiss();
            child.drawForegroundRectangle();
        }
    }

    private void handleMapCreation(MapsWidget mapsWidget) {

        mapView = (MapView) mapsWidget.findViewById(R.id.main_map_view);

        mapView.onCreate(savedInstanceState);
        if (mapView != null) {
            mapView.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        currentMapsWidget.setGoogleMap(googleMap);

        mapView.onResume();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mapView != null) {
            mapView.onResume();
        }
    }

    @Override
    public final void onDestroy() {
        if (mapView != null) {
            mapView.onDestroy();
        }
        super.onDestroy();
    }

    @Override
    public final void onLowMemory() {
        mapView.onLowMemory();
        super.onLowMemory();
    }

    @Override
    public final void onPause() {
        if (mapView != null) {
            mapView.onPause();
        }
        super.onPause();
    }

    public List<Integer> getSequence() {
        List<Integer> tempList = new ArrayList<>();

        BaseLinearLayout tempBaseLinearLayout=(BaseLinearLayout) mainRelativeLayout.getChildAt(0);
        tempList.add(tempBaseLinearLayout.getId());
        while(tempBaseLinearLayout.getAboveId()!=0)
        {
            tempBaseLinearLayout= (BaseLinearLayout) findViewById(tempBaseLinearLayout.getAboveId());
            tempList.add(tempBaseLinearLayout.getId());
        }
        return tempList;
    }
}
