import java.util.*;

class Job {
    int id;
    int deadline;
    int profit;

    Job(int id, int deadline, int profit) {
        this.id = id;
        this.deadline = deadline;
        this.profit = profit;
    }
}

public class Jobsecheduling {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of jobs: ");
        int n = scanner.nextInt();

        List<Job> jobs = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            System.out.println("Job " + (i + 1) + ":");
            System.out.print("Deadline: ");
            int deadline = scanner.nextInt();
            System.out.print("Profit: ");
            int profit = scanner.nextInt();
            jobs.add(new Job(i + 1, deadline, profit));
        }

        // Sort jobs based on profit in descending order
        Collections.sort(jobs, (a, b) -> b.profit - a.profit);

        int[] result = new int[n];
        boolean[] slot = new boolean[n];

        for (int i = 0; i < n; i++) {
            for (int j = Math.min(n, jobs.get(i).deadline) - 1; j >= 0; j--) {
                if (!slot[j]) {
                    result[j] = jobs.get(i).id;
                    slot[j] = true;
                    break;
                }
            }
        }

        System.out.println("Job Scheduling Order:");
        for (int i = 0; i < n; i++) {
            if (slot[i]) {
                System.out.print(result[i] + " ");
            }
        }
    }
}
