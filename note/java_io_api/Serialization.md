Objects of a class that is not marked Serializable cannot be serialized.

Class Dooby implements Serializable and since Tooby extends Dooby, it is Serializable as well.

When you serialize an object of class, only the data members of serializable classes will be serialized. Data members of unserializable classes will not be serialized.

When reading the object back (i.e. deserializing), the constructors of serializable classes are not called. Their data members are set directly from the values present in serialized data. Constructor for unserializable classes is called.
