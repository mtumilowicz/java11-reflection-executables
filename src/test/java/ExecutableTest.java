import org.junit.Test;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Objects;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by mtumilowicz on 2019-02-02.
 */
public class ExecutableTest {
    
    @Test
    public void getParameters() {
        Method method = findMethod("method");

        var parameters = method.getParameters();
        assertThat(parameters.length, is(3));

        var parametersAsString = Arrays.toString(parameters);
        assertThat(parametersAsString, containsString("java.lang.String arg0"));
        assertThat(parametersAsString, containsString("int arg1"));
        assertThat(parametersAsString, containsString("T arg2"));
    }
    
    @Test
    public void getExceptionTypes() {
        Method method = findMethod("method");

        var exceptionTypes = method.getExceptionTypes();
        assertThat(exceptionTypes.length, is(2));

        var exceptionTypesAsString = Arrays.toString(exceptionTypes);
        assertThat(exceptionTypesAsString, containsString("class java.lang.IllegalArgumentException"));
        assertThat(exceptionTypesAsString, containsString("class java.io.IOException"));
    }

    @Test
    public void getParameterTypes() {
        Method method = findMethod("method");

        var parameters = method.getParameterTypes();
        assertThat(parameters.length, is(3));

        var parametersAsString = Arrays.toString(parameters);
        assertThat(parametersAsString, containsString("class java.lang.String"));
        assertThat(parametersAsString, containsString("int"));
        assertThat(parametersAsString, containsString("class java.lang.Object"));
    }

    @Test
    public void getModifiers() {
        Method method = findMethod("method");

        var parameters = method.getModifiers() & Modifier.methodModifiers();
        assertThat(Modifier.toString(parameters), is("public"));
    }

    @Test
    public void getTypeParameters() {
        Method method = findMethod("method");

        var typeParameters = method.getTypeParameters();
        assertThat(typeParameters.length, is(1));
        
        var typeParametersAsString = Arrays.toString(typeParameters);
        assertThat(typeParametersAsString, containsString("T"));
    }

    private Method findMethod(String name) {
        return Arrays.stream(X.class.getDeclaredMethods()).filter(m -> Objects.equals(m.getName(), name))
                .findAny()
                .orElseThrow();
    }
}
