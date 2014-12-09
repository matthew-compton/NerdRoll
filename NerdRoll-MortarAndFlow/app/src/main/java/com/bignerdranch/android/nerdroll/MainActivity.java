package com.bignerdranch.android.nerdroll;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;

import com.bignerdranch.android.nerdroll.android.ActionBarOwner;
import com.bignerdranch.android.nerdroll.screen.MainScreen;
import com.bignerdranch.android.nerdroll.view.MainView;

import javax.inject.Inject;

import flow.Flow;
import mortar.Mortar;
import mortar.MortarActivityScope;
import mortar.MortarScope;

import static android.content.Intent.ACTION_MAIN;
import static android.content.Intent.CATEGORY_LAUNCHER;

public class MainActivity extends ActionBarActivity implements ActionBarOwner.View {

    private MortarActivityScope activityScope;
    private ActionBarOwner.MenuAction actionBarMenuAction;

    @Inject ActionBarOwner actionBarOwner;
    private Flow mainFlow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (isWrongInstance()) {
            finish();
            return;
        }

        MortarScope parentScope = Mortar.getScope(getApplication());
        activityScope = Mortar.requireActivityScope(parentScope, new MainScreen());
        Mortar.inject(this, this);

        activityScope.onCreate(savedInstanceState);
        setContentView(R.layout.view_main);
        MainView mainView = (MainView) findViewById(R.id.container);
        mainFlow = mainView.getFlow();

        // Custom look for overview screen
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            TypedValue typedValue = new TypedValue();
            Resources.Theme theme = getTheme();
            theme.resolveAttribute(R.attr.colorPrimary, typedValue, true);
            int color = typedValue.data;

            Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.ic_overview);
            ActivityManager.TaskDescription td = new ActivityManager.TaskDescription(null, bm, color);

            setTaskDescription(td);
            bm.recycle();
        }

        actionBarOwner.takeView(this);
    }

    @Override
    public Object getSystemService(String name) {
        if (Mortar.isScopeSystemService(name)) {
            return activityScope;
        }
        return super.getSystemService(name);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        activityScope.onSaveInstanceState(outState);
    }

    /**
     * Inform the view about back events.
     */
    @Override
    public void onBackPressed() {
        // Give the view a chance to handle going back. If it declines the honor, let super do its thing.
        if (!mainFlow.goBack()) super.onBackPressed();
    }

    /**
     * Inform the view about up events.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            return mainFlow.goUp();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (actionBarMenuAction != null) {
            menu.add(getString(actionBarMenuAction.title))
                    .setIcon(getResources().getDrawable(actionBarMenuAction.icon))
                    .setShowAsActionFlags(actionBarMenuAction.flag)
                    .setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem menuItem) {
                            actionBarMenuAction.action.call();
                            return true;
                        }
                    });
        }
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        actionBarOwner.dropView(this);

        // activityScope may be null in case isWrongInstance() returned true in onCreate()
        if (isFinishing() && activityScope != null) {
            MortarScope parentScope = Mortar.getScope(getApplication());
            parentScope.destroyChild(activityScope);
            activityScope = null;
        }
    }

    @Override
    public Context getMortarContext() {
        return this;
    }

    @Override
    public void setShowHomeEnabled(boolean enabled) {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(false);
    }

    @Override
    public void setUpButtonEnabled(boolean enabled) {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(enabled);
        actionBar.setHomeButtonEnabled(enabled);
    }

    @Override
    public void setTitle(CharSequence title) {
        getSupportActionBar().setTitle(title);
    }

    @Override
    public void setMenu(ActionBarOwner.MenuAction action) {
        if (action != actionBarMenuAction) {
            actionBarMenuAction = action;
            invalidateOptionsMenu();
        }
    }

    private boolean isWrongInstance() {
        if (!isTaskRoot()) {
            Intent intent = getIntent();
            boolean isMainAction = intent.getAction() != null && intent.getAction().equals(ACTION_MAIN);
            return intent.hasCategory(CATEGORY_LAUNCHER) && isMainAction;
        }
        return false;
    }

}