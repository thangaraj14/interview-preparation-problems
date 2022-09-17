package reflections.customMockito;

public class OurMockTest {
    ExternalService externalService = (ExternalService) OurMockito.mock(ExternalService.class);

    //@Test
    public void stubbing_method() throws Exception {
        OurMockito.stub(externalService, "concat", "dummy");
        String returned = externalService.concat(null, null);
        //assertEquals("dummy", returned);
    }

    // @Test
    public void stubbing_error_conditions() throws Exception {
        OurMockito.stub(externalService, "divide", 0);
        int returned = externalService.divide(0, 0);
        //assertEquals(0, returned);
    }

    // @Test
    public void stubbing_exception() throws Exception {
        OurMockito.stub(externalService, "someStrangeOperation", new
                RuntimeException("Just blow this up!"));
        externalService.someStrangeOperation(null);
    }
}