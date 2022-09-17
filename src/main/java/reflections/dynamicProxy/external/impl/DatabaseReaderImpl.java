

package reflections.dynamicProxy.external.impl;


public final class DatabaseReaderImpl implements DatabaseReader {
    @Override
    public int countRowsInTable(String tableName) throws InterruptedException {
        System.out.println(String.format("DatabaseReaderImpl - counting rows in table %s", tableName));

        Thread.sleep(1000);
        return 50;
    }

    @Override
    public String[] readRow(String sqlQuery) throws InterruptedException {
        System.out.println(String.format("DatabaseReaderImpl - Executing SQL query : %s", sqlQuery));

        Thread.sleep(1500);
        return new String[]{"column1", "column2", "column3"};
    }
}
