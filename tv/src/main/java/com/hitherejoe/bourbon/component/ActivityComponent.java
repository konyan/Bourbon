package com.hitherejoe.bourbon.component;

import com.hitherejoe.bourboncommon.injection.PerActivity;
import com.hitherejoe.bourboncommon.injection.component.ApplicationComponent;
import com.hitherejoe.bourboncommon.injection.module.ActivityModule;
import com.hitherejoe.bourbon.ui.browse.BrowseFragment;
import com.hitherejoe.bourbon.ui.shot.ShotActivity;

import dagger.Component;

/**
 * This component injects dependencies to all Activities across the application
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(BrowseFragment browseFragment);
    void inject(ShotActivity shotActivity);
}