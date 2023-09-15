package src.test.test_override;

public class Parent {
    public void testMethod1() throws ParentException {}
    public void testMethod2() throws ParentException {}
    public void testMethod3() throws ParentException {}
    public void testMethod4() throws ParentException {}
}

class Sub extends Parent {
//    @Override public void testMethod1() throws GrandParentException {} Not allow for wider spectrum exception
    @Override public void testMethod2() throws ParentException {}
    @Override public void testMethod3() throws ChildException {}
    @Override public void testMethod4() {}
}

class GrandParentException extends Exception {}
class ParentException extends GrandParentException {}
class ChildException extends ParentException {}
