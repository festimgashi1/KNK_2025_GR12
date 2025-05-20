package services;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class LanguageManager {
    private static final String BASE_NAME = "languages.messages";
    private static LanguageManager instance;
    private Locale locale;
    private LanguageManager(){
        this.locale = new Locale("sq");
    }
    public static LanguageManager getInstance(){
        if(instance == null){
            instance = new LanguageManager();
        }
        return instance;
    }
    public void setLocale(Locale locale){
        this.locale = locale;
    }
    public Locale getLocale(){
        return this.locale;
    }
    public ResourceBundle getResourceBundle(){
        return ResourceBundle.getBundle(BASE_NAME, getLocale());
    }
    private final List<Runnable> listeners = new ArrayList<>();

    public void addListener(Runnable listener) {
        listeners.add(listener);
    }

    public void notifyListeners() {
        for (Runnable listener : listeners) {
            listener.run();
        }
    }


}