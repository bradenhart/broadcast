package io.bradenhart.broadcast;

import android.content.Context;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private final String LOGTAG = MainActivity.class.getSimpleName();
    private Context context;


    @BindView(R.id.tb_main)
    Toolbar toolbar;
    @BindView(R.id.gv_messages_actions)
    GridView messagesActionsGrid;
    @BindView(R.id.gv_groups_actions)
    GridView groupsActionsGrid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = MainActivity.this;

        ButterKnife.bind(this);

        setUpActionBar();

        messagesActionsGrid.setAdapter(new ButtonGridViewAdapter(context, fetchMessagesGridItems()));
        groupsActionsGrid.setAdapter(new ButtonGridViewAdapter(context, fetchGroupsGridItems()));

    }

    private void setUpActionBar() {
        setSupportActionBar(toolbar);
        ActionBar mActionBar = getSupportActionBar();
        if (mActionBar != null) {
            mActionBar.setDisplayShowTitleEnabled(false);
            TextView titleTV = ButterKnife.findById(toolbar, R.id.tv_base_title);
            titleTV.setText(getString(R.string.app_name));
        }
    }


    private class ButtonGridViewAdapter extends BaseAdapter {

        private Context mContext;
        private List<ButtonGridItem> mItems;


        public ButtonGridViewAdapter(Context context, List<ButtonGridItem> items) {
            mContext = context;
            mItems = items;
        }

        @Override
        public int getCount() {
            return mItems.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
                convertView = layoutInflater.inflate(R.layout.item_button_grid, null);
            }

            int imageResId = mItems.get(position).getIcon();
            String label = mItems.get(position).getLabel();

            ((ImageView) convertView.findViewById(R.id.iv_button_icon)).setImageResource(imageResId);
            ((TextView) convertView.findViewById(R.id.tv_button_label)).setText(label);

            return convertView;
        }
    }

    private class ButtonGridItem {

        private int mIcon;
        private String mLabel;

        public ButtonGridItem(int icon, int label) {
            mIcon = icon;
            mLabel = getString(label);
        }

        public int getIcon() {
            return mIcon;
        }

        public void setIcon(int icon) {
            mIcon = icon;
        }

        public String getLabel() {
            return mLabel;
        }

        public void setLabel(String label) {
            mLabel = label;
        }
    }

    private List<ButtonGridItem> fetchMessagesGridItems() {
        List<ButtonGridItem> items = new ArrayList<>();

        items.add(new ButtonGridItem(R.drawable.ic_message_black_24dp, R.string.label_message_action_view));
        items.add(new ButtonGridItem(R.drawable.ic_message_black_24dp, R.string.label_message_action_create));
        items.add(new ButtonGridItem(R.drawable.ic_message_black_24dp, R.string.label_message_action_find));
        items.add(new ButtonGridItem(R.drawable.ic_message_black_24dp, R.string.label_message_action_history));

        return items;
    }

    private List<ButtonGridItem> fetchGroupsGridItems() {
        List<ButtonGridItem> items = new ArrayList<>();

        items.add(new ButtonGridItem(R.drawable.ic_group_black_24dp, R.string.label_group_action_view));
        items.add(new ButtonGridItem(R.drawable.ic_group_black_24dp, R.string.label_group_action_create));
        items.add(new ButtonGridItem(R.drawable.ic_group_black_24dp, R.string.label_group_action_find));

        return items;
    }
}
