[![Build Status](https://travis-ci.com/mtumilowicz/java11-reflection-executables.svg?branch=master)](https://travis-ci.com/mtumilowicz/java11-reflection-executables)

# java11-reflection-executables
Using reflection to retrieve information common to `Method` and `Constructor` (`Executable`).

_Reference_: https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/reflect/Executable.html

# preface
* `Method` class
* `Constructor` class
* `Parameter` class (since java 8)
* `Executable` - common superclass of `Method` and `Constructor` 
since java 8 (methods retrieving information common added/moved 
to this class) and the methods are quite self-explanatory:
    * `Parameter[] getParameters()` - actual parameter names are
    replaced by `arg0`, `arg1`, etc. by default; if we want to
    have actual parameter names we have to compile the source 
    code using the `-parameters` flag
    * `Class<?>[] getExceptionTypes()`
    * `int getModifiers()` - returns modifiers as int (https://github.com/mtumilowicz/java11-ORed-container)
        * `int Modifier.methodModifiers()` - returns possible 
        modifiers for a method
    * `TypeVariable<Method>[] getTypeParameters()` - declared 
    types of generic method
    * `Class<?>[] getParameterTypes()` - types of parameters; note 
    that generic parameter type is replaced by `Object`
    
# project description
We will test all mentioned above methods but only for a given method
to reduce code redundancy (for constructor tests will
be pretty the same).

We have a simple class with a single method:
```
public class X {
    public <T> String method(String first, int second, T type) throws IllegalArgumentException, IOException {
        return "";
    }
}
```

All tests are in `ExecutableTest` class
* `getParameters`
    ```
    var method = findMethod("method");
    
    var parameters = method.getParameters();
    assertThat(parameters.length, is(3));
    
    var parametersAsString = Arrays.toString(parameters);
    assertThat(parametersAsString, containsString("java.lang.String arg0"));
    assertThat(parametersAsString, containsString("int arg1"));
    assertThat(parametersAsString, containsString("T arg2"));
    ```
* `getExceptionTypes`
    ```
    var method = findMethod("method");
    
    var exceptionTypes = method.getExceptionTypes();
    assertThat(exceptionTypes.length, is(2));
    
    var exceptionTypesAsString = Arrays.toString(exceptionTypes);
    assertThat(exceptionTypesAsString, containsString("class java.lang.IllegalArgumentException"));
    assertThat(exceptionTypesAsString, containsString("class java.io.IOException"));
    ```
* `getModifiers`
    ```
    var method = findMethod("method");
    
    var parameters = method.getModifiers() & Modifier.methodModifiers();
    assertThat(Modifier.toString(parameters), is("public"));
    ```
* `getTypeParameters`
    ```
    var method = findMethod("method");
    
    var typeParameters = method.getTypeParameters();
    assertThat(typeParameters.length, is(1));
    
    var typeParametersAsString = Arrays.toString(typeParameters);
    assertThat(typeParametersAsString, containsString("T"));
    ```
* `getParameterTypes`
    ```
    var method = findMethod("method");
    
    var parameters = method.getParameterTypes();
    assertThat(parameters.length, is(3));
    
    var parametersAsString = Arrays.toString(parameters);
    assertThat(parametersAsString, containsString("class java.lang.String"));
    assertThat(parametersAsString, containsString("int"));
    assertThat(parametersAsString, containsString("class java.lang.Object"));
    ```
    
# projects
* https://github.com/mtumilowicz/java11-reflection-methods
* https://github.com/mtumilowicz/java11-reflection-constructors 
