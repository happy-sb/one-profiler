package happy_sb.profiler.util.reflection;


import java.util.Collection;
import java.util.Map;

public abstract class Assert {

  public static void state(boolean expression, String message) {
    if (!expression) {
      throw new IllegalStateException(message);
    }
  }

  /**
   * @deprecated as of 4.3.7, in favor of {@link #state(boolean, String)}
   */
  @Deprecated
  public static void state(boolean expression) {
    state(expression, "[Assertion failed] - this state invariant must be true");
  }

  /**
   * Assert a boolean expression, throwing an {@code IllegalArgumentException}
   * if the expression evaluates to {@code false}.
   * <pre class="code">Assert.isTrue(i &gt; 0, "The value must be greater than zero");</pre>
   *
   * @param expression a boolean expression
   * @param message    the exception message to use if the assertion fails
   * @throws IllegalArgumentException if {@code expression} is {@code false}
   */
  public static void isTrue(boolean expression, String message) {
    if (!expression) {
      throw new IllegalArgumentException(message);
    }
  }

  /**
   * @deprecated as of 4.3.7, in favor of {@link #isTrue(boolean, String)}
   */
  @Deprecated
  public static void isTrue(boolean expression) {
    isTrue(expression, "[Assertion failed] - this expression must be true");
  }

  /**
   * Assert that an object is {@code null}.
   * <pre class="code">Assert.isNull(value, "The value must be null");</pre>
   *
   * @param object  the object to check
   * @param message the exception message to use if the assertion fails
   * @throws IllegalArgumentException if the object is not {@code null}
   */
  public static void isNull(Object object, String message) {
    if (object != null) {
      throw new IllegalArgumentException(message);
    }
  }

  /**
   * @deprecated as of 4.3.7, in favor of {@link #isNull(Object, String)}
   */
  @Deprecated
  public static void isNull(Object object) {
    isNull(object, "[Assertion failed] - the object argument must be null");
  }

  /**
   * Assert that an object is not {@code null}.
   * <pre class="code">Assert.notNull(clazz, "The class must not be null");</pre>
   *
   * @param object  the object to check
   * @param message the exception message to use if the assertion fails
   * @throws IllegalArgumentException if the object is {@code null}
   */
  public static void notNull(Object object, String message) {
    if (object == null) {
      throw new IllegalArgumentException(message);
    }
  }

  /**
   * @deprecated as of 4.3.7, in favor of {@link #notNull(Object, String)}
   */
  @Deprecated
  public static void notNull(Object object) {
    notNull(object, "[Assertion failed] - this argument is required; it must not be null");
  }

  /**
   * Assert that the given String is not empty; that is,
   * it must not be {@code null} and not the empty String.
   * <pre class="code">Assert.hasLength(name, "Name must not be empty");</pre>
   *
   * @param text    the String to check
   * @param message the exception message to use if the assertion fails
   * @throws IllegalArgumentException if the text is empty
   */
  public static void hasLength(String text, String message) {
    if (text != null && text.trim().length() > 0) {
      throw new IllegalArgumentException(message);
    }
  }

  /**
   * Assert that the given String contains valid text content; that is, it must not
   * be {@code null} and must contain at least one non-whitespace character.
   * <pre class="code">Assert.hasText(name, "'name' must not be empty");</pre>
   *
   * @param text    the String to check
   * @param message the exception message to use if the assertion fails
   * @throws IllegalArgumentException if the text does not contain valid text content
   */
  public static void hasText(String text, String message) {
    if (text == null || text.trim().length() == 0) {
      throw new IllegalArgumentException(message);
    }
  }

  /**
   * Assert that the given text does not contain the given substring.
   * <pre class="code">Assert.doesNotContain(name, "rod", "Name must not contain 'rod'");</pre>
   *
   * @param textToSearch the text to search
   * @param substring    the substring to find within the text
   * @param message      the exception message to use if the assertion fails
   * @throws IllegalArgumentException if the text contains the substring
   */
  public static void doesNotContain(String textToSearch, String substring, String message) {
    if (hasLength(textToSearch) && hasLength(substring) &&
        textToSearch.contains(substring)) {
      throw new IllegalArgumentException(message);
    }
  }

  private static boolean hasLength(String string) {
    return string != null && string.trim().length() > 0;
  }

  /**
   * @deprecated as of 4.3.7, in favor of {@link #doesNotContain(String, String, String)}
   */
  @Deprecated
  public static void doesNotContain(String textToSearch, String substring) {
    doesNotContain(textToSearch, substring,
        "[Assertion failed] - this String argument must not contain the substring [" + substring + "]");
  }

  /**
   * Assert that an array contains elements; that is, it must not be
   * {@code null} and must contain at least one element.
   * <pre class="code">Assert.notEmpty(array, "The array must contain elements");</pre>
   *
   * @param array   the array to check
   * @param message the exception message to use if the assertion fails
   * @throws IllegalArgumentException if the object array is {@code null} or contains no elements
   */
  public static void notEmpty(Object[] array, String message) {
    if (array == null || array.length == 0) {
      throw new IllegalArgumentException(message);
    }
  }

  /**
   * @deprecated as of 4.3.7, in favor of {@link #notEmpty(Object[], String)}
   */
  @Deprecated
  public static void notEmpty(Object[] array) {
    notEmpty(array, "[Assertion failed] - this array must not be empty: it must contain at least 1 element");
  }

  /**
   * Assert that an array contains no {@code null} elements.
   * <p>Note: Does not complain if the array is empty!
   * <pre class="code">Assert.noNullElements(array, "The array must contain non-null elements");</pre>
   *
   * @param array   the array to check
   * @param message the exception message to use if the assertion fails
   * @throws IllegalArgumentException if the object array contains a {@code null} element
   */
  public static void noNullElements(Object[] array, String message) {
    if (array != null) {
      for (Object element : array) {
        if (element == null) {
          throw new IllegalArgumentException(message);
        }
      }
    }
  }

  /**
   * @deprecated as of 4.3.7, in favor of {@link #noNullElements(Object[], String)}
   */
  @Deprecated
  public static void noNullElements(Object[] array) {
    noNullElements(array, "[Assertion failed] - this array must not contain any null elements");
  }

  /**
   * Assert that a collection contains elements; that is, it must not be
   * {@code null} and must contain at least one element.
   * <pre class="code">Assert.notEmpty(collection, "Collection must contain elements");</pre>
   *
   * @param collection the collection to check
   * @param message    the exception message to use if the assertion fails
   * @throws IllegalArgumentException if the collection is {@code null} or
   *                                  contains no elements
   */
  public static void notEmpty(Collection<?> collection, String message) {
    if (collection == null || collection.isEmpty()) {
      throw new IllegalArgumentException(message);
    }
  }

  /**
   * @deprecated as of 4.3.7, in favor of {@link #notEmpty(Collection, String)}
   */
  @Deprecated
  public static void notEmpty(Collection<?> collection) {
    notEmpty(collection,
        "[Assertion failed] - this collection must not be empty: it must contain at least 1 element");
  }

  /**
   * Assert that a Map contains entries; that is, it must not be {@code null}
   * and must contain at least one entry.
   * <pre class="code">Assert.notEmpty(map, "Map must contain entries");</pre>
   *
   * @param map     the map to check
   * @param message the exception message to use if the assertion fails
   * @throws IllegalArgumentException if the map is {@code null} or contains no entries
   */
  public static void notEmpty(Map<?, ?> map, String message) {
    if (map == null || map.isEmpty()) {
      throw new IllegalArgumentException(message);
    }
  }

  /**
   * @deprecated as of 4.3.7, in favor of {@link #notEmpty(Map, String)}
   */
  @Deprecated
  public static void notEmpty(Map<?, ?> map) {
    notEmpty(map, "[Assertion failed] - this map must not be empty; it must contain at least one entry");
  }

  /**
   * Assert that the provided object is an instance of the provided class.
   * <pre class="code">Assert.instanceOf(Foo.class, foo, "Foo expected");</pre>
   *
   * @param type    the type to check against
   * @param obj     the object to check
   * @param message a message which will be prepended to provide further context.
   *                If it is empty or ends in ":" or ";" or "," or ".", a full exception message
   *                will be appended. If it ends in a space, the name of the offending object's
   *                type will be appended. In any other case, a ":" with a space and the name
   *                of the offending object's type will be appended.
   * @throws IllegalArgumentException if the object is not an instance of type
   */
  public static void isInstanceOf(Class<?> type, Object obj, String message) {
    notNull(type, "Type to check against must not be null");
    if (!type.isInstance(obj)) {
      instanceCheckFailed(type, obj, message);
    }
  }

  /**
   * Assert that the provided object is an instance of the provided class.
   * <pre class="code">Assert.instanceOf(Foo.class, foo);</pre>
   *
   * @param type the type to check against
   * @param obj  the object to check
   * @throws IllegalArgumentException if the object is not an instance of type
   */
  public static void isInstanceOf(Class<?> type, Object obj) {
    isInstanceOf(type, obj, "");
  }

  /**
   * Assert that {@code superType.isAssignableFrom(subType)} is {@code true}.
   * <pre class="code">Assert.isAssignable(Number.class, myClass, "Number expected");</pre>
   *
   * @param superType the super type to check against
   * @param subType   the sub type to check
   * @param message   a message which will be prepended to provide further context.
   *                  If it is empty or ends in ":" or ";" or "," or ".", a full exception message
   *                  will be appended. If it ends in a space, the name of the offending sub type
   *                  will be appended. In any other case, a ":" with a space and the name of the
   *                  offending sub type will be appended.
   * @throws IllegalArgumentException if the classes are not assignable
   */
  public static void isAssignable(Class<?> superType, Class<?> subType, String message) {
    notNull(superType, "Super type to check against must not be null");
    if (subType == null || !superType.isAssignableFrom(subType)) {
      assignableCheckFailed(superType, subType, message);
    }
  }

  /**
   * Assert that {@code superType.isAssignableFrom(subType)} is {@code true}.
   * <pre class="code">Assert.isAssignable(Number.class, myClass);</pre>
   *
   * @param superType the super type to check
   * @param subType   the sub type to check
   * @throws IllegalArgumentException if the classes are not assignable
   */
  public static void isAssignable(Class<?> superType, Class<?> subType) {
    isAssignable(superType, subType, "");
  }


  private static void instanceCheckFailed(Class<?> type, Object obj, String msg) {
    String className = (obj != null ? obj.getClass().getName() : "null");
    String result = "";
    boolean defaultMessage = true;
    if (hasLength(msg)) {
      if (endsWithSeparator(msg)) {
        result = msg + " ";
      } else {
        result = messageWithTypeName(msg, className);
        defaultMessage = false;
      }
    }
    if (defaultMessage) {
      result = result + ("Object of class [" + className + "] must be an instance of " + type);
    }
    throw new IllegalArgumentException(result);
  }

  private static void assignableCheckFailed(Class<?> superType, Class<?> subType, String msg) {
    String result = "";
    boolean defaultMessage = true;
    if (hasLength(msg)) {
      if (endsWithSeparator(msg)) {
        result = msg + " ";
      } else {
        result = messageWithTypeName(msg, subType);
        defaultMessage = false;
      }
    }
    if (defaultMessage) {
      result = result + (subType + " is not assignable to " + superType);
    }
    throw new IllegalArgumentException(result);
  }

  private static boolean endsWithSeparator(String msg) {
    return (msg.endsWith(":") || msg.endsWith(";") || msg.endsWith(",") || msg.endsWith("."));
  }

  private static String messageWithTypeName(String msg, Object typeName) {
    return msg + (msg.endsWith(" ") ? "" : ": ") + typeName;
  }

}
