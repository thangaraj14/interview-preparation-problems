

package reflections.retries.app.http;

import reflections.retries.annotations.InitializerClass;
import reflections.retries.annotations.InitializerMethod;

@InitializerClass
public class ServiceRegistry {

    @InitializerMethod
    public void registerService() {
        System.out.println("Service successfully registered");
    }
}
