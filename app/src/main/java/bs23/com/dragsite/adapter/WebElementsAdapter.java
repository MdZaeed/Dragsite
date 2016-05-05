package bs23.com.dragsite.adapter;

import android.content.ClipData;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import bs23.com.dragsite.R;
import bs23.com.dragsite.model.ElementsModel;

/**
 * Created by Ashraful on 3/3/2016.
 */
public class WebElementsAdapter extends RecyclerView.Adapter {
    List<ElementsModel> elements;
    protected Context context;

    public WebElementsAdapter(Context context, List<ElementsModel> elements) {
        this.context = context;
        this.elements = elements;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_add_elements, parent, false);
        return new ElementsHolder(itemView);
    }

    public ElementsModel getItem(int position) {
        return elements.get(position);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ElementsHolder elementsHolder = (ElementsHolder) holder;
        ElementsModel element = getItem(position);
        elementsHolder.elementName.setText(element.getElementName());
        Drawable drawable=context.getResources().getDrawable(element.getImageId());
        elementsHolder.elementName.setCompoundDrawablesWithIntrinsicBounds(null, drawable, null, null);
    }


    @Override
    public int getItemCount() {
        return elements.size();
    }

    protected class ElementsHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {
        protected TextView elementName;

        public ElementsHolder(View itemView) {
            super(itemView);

            elementName = (TextView) itemView.findViewById(R.id.tv_element_name);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public boolean onLongClick(View v) {
            ClipData data = ClipData.newPlainText("", "");
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
            v.startDrag(data, shadowBuilder, v, 0);
            return true;
        }
    }
}
