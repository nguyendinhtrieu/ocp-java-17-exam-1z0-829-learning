package src.foundation_test_02;

public sealed class Q44_SealedClass {
    public static sealed class InnerSealed {
        // No explicit permitted subclasses
    }
}

sealed class AA  {

}
final class B extends AA {

}

final class SubclassA extends Q44_SealedClass {}

// Subclass of InnerSealed
final class SubclassB extends Q44_SealedClass.InnerSealed {}
//
//// Subclass of InnerSealed
//final class SubclassC extends Q44_SealedClass.InnerSealed {}