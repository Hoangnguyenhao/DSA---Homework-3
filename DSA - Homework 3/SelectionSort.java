import java.util.List;

public class SelectionSort {
    public static void sort(List<Student> students) {
        for (int i = 0; i < students.size() - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < students.size(); j++) {
                if (students.get(j).getGrade() < students.get(minIndex).getGrade()) {
                    minIndex = j;
                }
            }
            // Swap the found minimum element with the first element
            Student temp = students.get(minIndex);
            students.set(minIndex, students.get(i));
            students.set(i, temp);
        }
    }
}
