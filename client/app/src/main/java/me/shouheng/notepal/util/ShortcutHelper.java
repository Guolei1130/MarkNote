package me.shouheng.notepal.util;

import android.content.Context;
import android.content.Intent;

import me.shouheng.notepal.R;
import me.shouheng.notepal.activity.ContentActivity;
import me.shouheng.notepal.activity.MainActivity;
import me.shouheng.notepal.config.Constants;
import me.shouheng.data.entity.Model;
import me.shouheng.data.entity.Note;

public class ShortcutHelper {

    // FIXME CHANGE the intent of short cut
    public static <T extends Model> void addShortcut(Context context, T model) {
        Context mContext = context.getApplicationContext();
        Intent shortcutIntent = new Intent(mContext, MainActivity.class);
        shortcutIntent.putExtra(Constants.EXTRA_CODE, model.getCode());
        shortcutIntent.setAction(Constants.ACTION_SHORTCUT);
        shortcutIntent.putExtra(ContentActivity.EXTRA_HAS_TOOLBAR, true);

        Intent addIntent = new Intent();
        addIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
        addIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, getShortcutName(model));
        addIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE,
                Intent.ShortcutIconResource.fromContext(mContext, R.drawable.note_shortcut));
        addIntent.setAction("com.android.launcher.action.INSTALL_SHORTCUT");

        context.sendBroadcast(addIntent);
    }

    private static <T extends Model> String getShortcutName(T model) {
         if (model instanceof Note) {
            return ((Note) model).getTitle();
        }
        return "PalmCollege";
    }
}
