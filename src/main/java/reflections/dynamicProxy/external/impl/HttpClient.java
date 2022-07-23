

package reflections.dynamicProxy.external.impl;

public interface HttpClient {

    void initialize();

    String sendRequest(String request);
}
