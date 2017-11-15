package edu.cuny.brooklyn.cisc3120.project.game.utils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Dealing with internationalization, commonly, called i18n, i as in the first letter 
 * in internationalization, and n as in the last letter in internationalization, and 
 * 18 letters in between. 
 */
public class I18n {
    private static Logger LOGGER = LoggerFactory.getLogger(I18n.class);
    
    private final static String I18N_MSG_BUNDLE_FN = "MessagesBundle";
    private final static String PROPERTY_FILE_SUFFIX = ".properties";
    private final static String I18N_DIR = "i18n";
    private final static Locale DEFAULT_LOCALE = new Locale("en", "US");

    
    private static ResourceBundle bundle 
        = ResourceBundle.getBundle(I18n.getBundleBaseName(), I18n.DEFAULT_LOCALE);
    
    private static Locale selectedLocale = I18n.DEFAULT_LOCALE;
    
    public static Locale getSelectedLocale() {
        return selectedLocale;
    }

    public static void setSelectedLocale(Locale selectedLocale) {
        I18n.selectedLocale = selectedLocale;
    }

    public static List<Locale> getAvailableLocale() throws IOException, URISyntaxException {
        List<Locale> lcList = new LinkedList<Locale>();
        Enumeration<URL> urls = I18n.class.getClassLoader().getResources(I18N_DIR);
        while (urls.hasMoreElements()) {
            try (Stream<Path> pathStream = Files.list(Paths.get(urls.nextElement().toURI()))) {
                pathStream
                    .filter(new Predicate<Path>() {

                        @Override
                        public boolean test(Path path) {
                            return FileSystems.getDefault()
                                    .getPathMatcher("glob:**/*" + I18N_MSG_BUNDLE_FN + "*" + PROPERTY_FILE_SUFFIX)
                                    .matches(path);
                        }
                    })
                    .forEach(path -> { 
                        String fn = path.getFileName().toString();
                        String[] lc = fn.substring(I18N_MSG_BUNDLE_FN.length(), fn.length() - PROPERTY_FILE_SUFFIX.length()).split("_");
                        Locale locale = DEFAULT_LOCALE;
                        if (lc.length == 3) {
                            locale = new Locale(lc[1], lc[2]);
                            lcList.add(locale);
                            LOGGER.debug(String.format("found locale: %s (%s)", locale.getDisplayLanguage(), locale.getCountry()));
                        } else if (lc.length <= 1) {
                            lcList.add(locale);
                            LOGGER.debug(String.format("found locale: %s (%s)", locale.getDisplayLanguage(), locale.getCountry()));
                        } else {
                            LOGGER.debug("skipped locale: " + String.join("-", lc));
                        }
                    });
            }
        }
        return lcList;
    }
    
    public static String getBundleBaseName() {
        return I18N_DIR + "/" + I18N_MSG_BUNDLE_FN;
    }
    
    public static ResourceBundle getBundle() {
        return bundle;
    }
    
    public static void setBundle(ResourceBundle bundle) {
        I18n.bundle = bundle;
    }
    
    public static Locale getDefaultLocale() {
        return DEFAULT_LOCALE;
    }
    
    public static String getDisplayLC(Locale lc) {
        LOGGER.debug("getDisplayLC(Locale lc): " + lc.toString());
        return lc.getDisplayLanguage(lc) + " (" + lc.getDisplayCountry(lc) + ")";
    }
}
