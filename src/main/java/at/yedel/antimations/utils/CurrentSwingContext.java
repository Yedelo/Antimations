package at.yedel.antimations.utils;



public class CurrentSwingContext {
    public static SwingContext currentContext;
    public static void context(SwingContext context) {
        currentContext = context;
    }
}
