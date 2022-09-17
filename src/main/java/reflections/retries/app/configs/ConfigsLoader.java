

package reflections.retries.app.configs;

import  reflections.retries.annotations.InitializerClass;
import reflections.retries.annotations.InitializerMethod;

@InitializerClass
public class ConfigsLoader {

    @InitializerMethod
    public void loadAllConfigs() {
        System.out.println("Loading all configuration files");
    }
}
