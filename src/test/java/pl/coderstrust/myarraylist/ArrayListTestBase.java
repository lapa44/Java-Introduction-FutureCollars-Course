package pl.coderstrust.myarraylist;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

abstract class ArrayListTestBase {
    abstract List getArrayList();
    abstract List getArrayList(Long[] input);

    @ParameterizedTest
    @DisplayName("Creating empty ArrayList and adding some objects.")
    @MethodSource("constructorAddArguments")
    void shouldCreateArrayAndAddValue(List<Long> expected, Long d1, Long d2, Long d3) {
        List<Long> arrayToTest = getArrayList();
        arrayToTest.add(d1);
        arrayToTest.add(0, d2);
        arrayToTest.add(1, d3);
        assertArrayEquals(expected.toArray(), arrayToTest.toArray());
    }

    private static Stream<Arguments> constructorAddArguments() {
        return Stream.of(
                Arguments.of(Arrays.asList(2L, 3L, 1L), 1L, 2L, 3L),
                Arguments.of(Arrays.asList(20L, 40L, 10L), 10L, 20L, 40L));
    }

    @Test
    @DisplayName("Removing object from list by reference.")
    void shouldRemoveElementByObject() {
        Long[] arr = {1L, 2L, 3L, 4L};
        List<Long> array = getArrayList(arr);
        array.remove(3L);
        assertArrayEquals(new Long[] {1L, 2L, 4L}, array.toArray());
    }

    @Test
    @DisplayName("Remove object from list by index..")
    void shouldRemoveElementByIndex() {
        Long[] arr = {1L, 2L, 3L, 4L};
        List<Long> array = getArrayList(arr);
        array.remove(2);
        assertArrayEquals(new Long[] {1L, 2L, 4L}, array.toArray());
    }

    @Test
    @DisplayName("Removing object from list by index should throw exception for invalid input")
    void shouldThrowExceptionForElementByIndex() {
        List<Long> array = getArrayList(new Long[] {1L, 2L, 3L, 4L});
        assertThrows(IndexOutOfBoundsException.class, () -> array.remove(5));
    }

    @Test
    @DisplayName("Removing elements from another collection to the end of list.")
    void shouldRemoveAllElementsFromAnotherCollection() {
        List<Long> array = getArrayList(new Long[] {1L, 2L, 3L});
        ArrayList<Long> anotherArray = new ArrayList<>(Arrays.asList(2L, 3L));
        array.removeAll(anotherArray);
        assertArrayEquals(new Long[] {1L}, array.toArray());
    }

    @Test
    @DisplayName("Clearing list and checking isEmpty.")
    void shouldClearArrayListAndReturnTrueForIsEmpty() {
        List<Long> array = getArrayList(new Long[] {1L, 2L, 3L, 4L});
        array.clear();
        assertTrue(array.isEmpty());
    }

    @Test
    @DisplayName("Checking isEmpty for non empty list.")
    void shouldReturnFalseForIsEmpty() {
        List<Long> array = getArrayList(new Long[] {1L, 2L, 3L, 4L});
        assertFalse(array.isEmpty());
    }

    @Test
    @DisplayName("Adding elements from another collection to the end of arraylist.")
    void shouldAddAllElementsFromAnotherCollection() {
        List<Long> array = getArrayList(new Long[] {1L, 2L, 3L});
        ArrayList<Long> anotherArray = new ArrayList<Long>(Arrays.asList(1L, 2L, 3L));
        array.addAll(anotherArray);
        assertArrayEquals(new Long[] {1L, 2L, 3L, 1L, 2L, 3L}, array.toArray());
    }

    @ParameterizedTest
    @DisplayName("Adding elements from another collection to the chosen index of arraylist.")
    @MethodSource("addAllIndexArguments")
    void shouldAddAllElementsFromAnotherCollectionAtIndex0(int index, Long[] expected, List<Long> actual) {
        List<Long> array = getArrayList(new Long[] {1L, 2L, 3L});
        array.addAll(index, actual);
        assertArrayEquals(expected, array.toArray());
    }

    private static Stream<Arguments> addAllIndexArguments() {
        return Stream.of(
                Arguments.of(0, new Long[] {4L, 5L, 6L, 1L, 2L, 3L}, Arrays.asList(4L, 5L, 6L)),
                Arguments.of(1, new Long[] {1L, 4L, 5L, 6L, 2L, 3L}, Arrays.asList(4L, 5L, 6L))
        );
    }

    @Test
    @DisplayName("Retaining elements from another list.")
    void shouldRetainElementsAndReturnSmallerArray() {
        List<Long> array = getArrayList(new Long[] {1L, 2L, 3L, 4L});
        List<Long> array2 = getArrayList(new Long[] {1L, 4L});
        array.retainAll(array2);
        assertArrayEquals(new Long[] {1L, 4L}, array.toArray());
    }

    @Test
    @DisplayName("Creating a sublist of given list.")
    void shouldReturnSubListFromIndex1ToIndex2() {
        List<Long> array = getArrayList(new Long[] {1L, 2L, 3L, 4L});
        assertArrayEquals(new Long[] {2L, 3L}, array.subList(1, 3).toArray());
    }

    @Test
    @DisplayName("Creating iterator and testing it's next() method.")
    void iteratorShouldReturnNextElements() {
        List<Long> array = getArrayList(new Long[] {1L, 2L, 3L});
        Iterator it = array.iterator();
        assertEquals(1L, it.next());
        assertEquals(2L, it.next());
        assertEquals(3L, it.next());
    }

    @Test
    @DisplayName("Checking containsAll method - should return true")
    void shouldReturnTrueForContainsAll() {
        List<Long> myArray = getArrayList(new Long[] {1L, 2L, 3L, 4L});
        ArrayList<Long> array = new ArrayList<> (Arrays.asList(1L, 2L, 3L));
        assertTrue(myArray.containsAll(array));
    }

    @Test
    @DisplayName("Checking containsAll method - should return false")
    void shouldReturnFalseForContainsAll() {
        List<Long> myArray = getArrayList(new Long[] {1L, 2L, 3L, 4L});
        ArrayList<Long> array = new ArrayList<> (Arrays.asList(1L, 99L, 3L));
        assertFalse(myArray.containsAll(array));
    }

    @Test
    @DisplayName("Checking iterator's hasNext() method on non empty arrayList")
    void shouldReturnTrueForHasNextMethod() {
        List<Long> array = getArrayList(new Long[] {1L, 2L, 3L, 4L});
        Iterator it = array.iterator();
        assertTrue(it.hasNext());
    }

    @Test
    @DisplayName("Checking iterator's hasNext() method on empty arrayList")
    void shouldReturnFalseForHasNextMethod() {
        List<Long> array = getArrayList();
        Iterator it = array.iterator();
        assertFalse(it.hasNext());
    }

    @Test
    @DisplayName("Getting an object from list by index.")
    void shouldReturnElementFromSpecifiedIndex() {
        List<Long> array = getArrayList(new Long[] {1L, 2L, 3L});
        assertEquals(2L, array.get(1));
    }

    @Test
    @DisplayName("Getting an object from list by index - should throw exception for invalid input.")
    void shouldThrowExceptionForGetMethod() {
        List<Long> array = getArrayList(new Long[] {1L, 2L, 3L, 4L});
        assertThrows(IndexOutOfBoundsException.class, () -> array.get(5));
    }

    @Test
    @DisplayName("Setting element from list to other value by index.")
    void shouldSetElementAtSpecifiedIndex() {
        List<Long> array = getArrayList(new Long[] {1L, 2L, 3L});
        array.set(1, 4L);
        assertArrayEquals(new Long[] {1L, 4L, 3L}, array.toArray());
    }

    @Test
    @DisplayName("Setting element from list to other value by index - should throw exception for invalid index.")
    void shouldThrowExceptionForSetMethod() {
        List<Long> array = getArrayList(new Long[] {1L, 2L, 3L, 4L});
        assertThrows(IndexOutOfBoundsException.class, () -> array.set(5, 10L));
    }

    @Test
    @DisplayName("Getting lastIndexOf by reference.")
    void shouldReturnLastIndexOfInput() {
        List<Long> array = getArrayList(new Long[] {1L, 2L, 1L});
        assertEquals(2, array.lastIndexOf(1L));
    }

    @Test
    @DisplayName("Getting size after adding and removing some elements.")
    void shouldReturnCorrectSize() {
        List<Long> array = getArrayList(new Long[] {1L, 2L, 3L});
        array.add(4L);
        array.remove(3L);
        array.add(0, 5L);
        assertEquals(4, array.size());
    }

    @Test
    @DisplayName("Creating listIterator by index and getting it's previous element")
    void shouldCreateListItrAtGivenIndexAndReturnPreviousElement() {
        List<Long> array = getArrayList(new Long[] {1L, 2L, 3L});
        ListIterator it = array.listIterator(1);
        assertEquals(1L, it.previous());
    }

    @Test
    @DisplayName("indexOf method should throw exception for null object")
    void shouldThrowNPEinIndexOfMethodForNullObject() {
        List<Long> array = getArrayList(new Long[] {1L, 2L, 3L});
        assertThrows(NullPointerException.class, () -> array.indexOf(null));
    }
}
