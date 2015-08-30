package com.mkurutin.narrator;

import android.os.Bundle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.robolectric.Shadows.shadowOf;

@RunWith(RobolectricTestRunner.class)
public class MainActivityTest {

    @Test
    public void shouldSetMainContentView() throws Exception {
        MainActivity mainActivity = new MainActivity();
        mainActivity.onCreate(Bundle.EMPTY);

        assertThat(shadowOf(mainActivity).getContentView().getId(), is(R.layout.activity_main));
    }
}