

package reflections.retries.app;

import reflections.retries.annotations.InitializerClass;
import reflections.retries.annotations.InitializerMethod;

@InitializerClass
public class AutoSaver {

    @InitializerMethod
    public void startAutoSavingThreads() {
        System.out.println("Start automatic data saving to disk");
    }
}
