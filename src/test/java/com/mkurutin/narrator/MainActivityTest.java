package com.mkurutin.narrator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.robolectric.Shadows.shadowOf;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class MainActivityTest {

    @Test
    public void shouldSetMainContentView() throws Exception {
        MainActivity activity = Robolectric.buildActivity(MainActivity.class).create().get();

        assertThat(shadowOf(activity).getContentView().getId(), is(R.id.main_linear_layout));
    }
}