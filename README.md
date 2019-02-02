# java11-reflection-executables
Basic info of Executables (Method, Constructor).

_Reference_: https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/reflect/Executable.html

# preface
* `Method` class
* `Constructor` class
* `Parameter` class (since java 8)
* `Executable` - common superclass since java 8 
(methods retrieving information common added/moved to this class)
and the methods are quite self-explanatory:
    * `Parameter[] getParameters()`
    * `Class<?>[] getExceptionTypes()`
    * `int getModifiers()` - returns modifiers as int (https://github.com/mtumilowicz/java11-ORed-container)
        * `int Modifier.methodModifiers()` - returns possible modifiers for class
    * `TypeVariable<Method>[] getTypeParameters()` - types of
    generic method
    * `Class<?>[] getParameterTypes()` - types of parameters
    
# project description
We will test 4 mentioned above methods only for a given method
only to reduce code redundancy (for constructor tests will
be pretty exact the same).


    
# projects
* https://github.com/mtumilowicz/java11-reflection-methods
* https://github.com/mtumilowicz/java11-reflection-constructors 