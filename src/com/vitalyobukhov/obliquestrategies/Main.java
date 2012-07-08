package com.vitalyobukhov.obliquestrategies;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.app.Activity;
import android.os.Bundle;
import android.view.*;
import android.widget.TextView;
import java.util.Random;

/**
 * Main activity
 */
public class Main extends Activity {


    /* TextView with strategy text */
    private TextView tvStrategy;

    /* random seed and instance */
    private long seed;
    private Random random;

    /* current strategy and strategies array from resources */
    private Integer strategy;
    private String[] strategies;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        tvStrategy = (TextView)this.findViewById(R.id.tvStrategy);

        /* first start - init all */
        if (savedInstanceState == null) {
            seed = System.currentTimeMillis();
            random = new Random(seed);
            strategy = null;
            strategies = getResources().getStringArray(R.array.strategies);

            showRandomStrategy();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        /* save state data */
        outState.putLong("seed", seed);
        outState.putInt("strategy", strategy);
        outState.putStringArray("strategies", strategies);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        /* restore state data */
        seed = savedInstanceState.getLong("seed");
        random = new Random(seed);
        strategy = savedInstanceState.getInt("strategy");
        strategies = savedInstanceState.getStringArray("strategies");

        showStrategy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.layout.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean result;

        switch (item.getItemId()) {

            /* get next strategy */
            case R.id.main_menu_next:
                showRandomStrategy();
                result = true;
                break;

            /* show about dialog */
            case R.id.main_menu_about:
                new AlertDialog.Builder(this)
                        .setTitle(R.string.about_dialog_title)
                        .setMessage(R.string.about_dialog_message)
                        .setPositiveButton(R.string.about_dialog_button_close,
                                new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) { }
                        }).show();
                result = true;
                break;

            /* exit */
            case R.id.main_menu_exit:
                finish();
                result = true;
                break;

            default:
                result = super.onOptionsItemSelected(item);
                break;
        }

        return result;
    }

    public void onTvStrategyClick(View v) {
        showRandomStrategy();
    }

    /**
     * Gets random strategy text.
     *
     * @return random strategy index
     */
    private String getRandomStrategy() {
        int newStrategy;
        do {
            newStrategy = random.nextInt(strategies.length);
        } while (strategy != null && newStrategy == strategy);
        strategy = newStrategy;
        return strategies[strategy];
    }

    private void showRandomStrategy() {
        tvStrategy.setText(formatStrategyText(getRandomStrategy()));
    }

    private void showStrategy() {
        tvStrategy.setText(formatStrategyText(strategies[strategy]));
    }

    private String formatStrategyText(String text) {
        final String format = "#%d: %s";
        return String.format(format, strategy + 1, text);
    }
}
