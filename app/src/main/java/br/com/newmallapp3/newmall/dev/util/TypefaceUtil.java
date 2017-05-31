package br.com.newmallapp3.newmall.dev.util;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.annotation.StringDef;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.HashMap;
import java.util.Map;

import br.com.newmallapp3.newmall.R;

public class TypefaceUtil {

    public static final String REGULAR = "fonts/roboto_medium.ttf";
    public static final String BOLD = "fonts/roboto_bold.ttf";
    public static final String ICONS = "fonts/fonts_master_24px_v1.ttf";
    private static final Map<String, Typeface> FONTS_CACHE = new HashMap<>();

    /**
     * Validação customizada para os valores de fontes.
     */
    @StringDef({REGULAR, BOLD, ICONS})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Fonts {
    }

    private TypefaceUtil() {
    }

    /**
     * Carrega uma fonte customizada usando o {@link AssetManager} e o caminho relativo.
     *
     * @param assetManager AssetManager
     * @param filePath     Caminho do arquivo de fonte dentor de assets
     * @return Objeto Typeface
     */
    @SuppressLint("ThrowableNotAtBeginning")
    @SuppressWarnings("checkstyle:IllegalCatch")
    public static Typeface load(final AssetManager assetManager, final String filePath) {
        synchronized (FONTS_CACHE) {
            try {
                if (!FONTS_CACHE.containsKey(filePath)) {
                    final Typeface typeface = Typeface.createFromAsset(assetManager, filePath);
                    FONTS_CACHE.put(filePath, typeface);
                    return typeface;
                }
            } catch (Exception e) {
                //Timber.w(e, "Não foi possível ler a fonte");
                FONTS_CACHE.put(filePath, null);
                return null;
            }
            return FONTS_CACHE.get(filePath);
        }
    }

    public static void setTypeface(TextView view, @Fonts String font) {

        if (view == null || TextUtils.isEmpty(font)) {
            return;
        }

        final Typeface typeface = load(view.getContext().getAssets(), font);

        if (typeface == null) {
            //Timber.e("Fonte não encontrada. Caminho: %s", font);
            return;
        }

        view.setTypeface(typeface);
    }

    public static void setTypeface(AttributeSet attrs, TextView textView) {

        if (attrs == null) {
            //Log.e("Attrs não deveria estar nulo","");
            return;
        }

        final Context context = textView.getContext();

        final TypedArray values =
                context.obtainStyledAttributes(attrs, R.styleable.TypefacedTextView);
        final String typefaceName = values.getString(R.styleable.TypefacedTextView_typeface);

        final Typeface typeface = load(textView.getContext().getAssets(), typefaceName);

        if (typeface != null) {
            textView.setTypeface(typeface);
        }

        values.recycle();
    }


}
