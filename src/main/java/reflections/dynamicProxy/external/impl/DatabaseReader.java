

package reflections.dynamicProxy.external.impl;

import java.io.IOException;

public interface DatabaseReader {

    int countRowsInTable(String tableName) throws InterruptedException, IOException;

    String[] readRow(String sqlQuery) throws InterruptedException;
}
