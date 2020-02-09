import java.util.Scanner;

public class Duke {
    public static final int CAPACITY = 100;
    public static final String BORDER = "____________________________________________________________";
    public static final int TODO = 5;
    public static final int DEADLINE = 9;
    public static final int EVENT = 6;
    public static final int DONE = 5;
    public static final int DATE = 3;

    public static void main(String[] args) {
        printWelcomeMessage();
        Task[] tasks = new Task[CAPACITY];
        int taskCounter = 0;
        Scanner input = new Scanner(System.in);

        while(true) {
            String line;
            line = input.nextLine();
            String[] sentence = line.split(" ");
            String taskType = sentence[0].toLowerCase();
            try {
                switch(taskType) {
                case "bye":
                    printByeMessage();
                    break;
                case "list":
                    printList(tasks, taskCounter);
                    break;
                case "todo":
                    line = line.substring(TODO);
                    Task t = new Todo(line);
                    tasks[taskCounter] = t;
                    printAcknowledgement(tasks[taskCounter], taskCounter);
                    taskCounter++;
                    break;
                case "deadline":
                    String[] deadlineWords = line.split("/");
                    String deadlineDescription = deadlineWords[0].substring(DEADLINE);
                    String by = deadlineWords[1].substring(DATE);
                    Task d = new Deadline(deadlineDescription, by);
                    tasks[taskCounter] = d;
                    printAcknowledgement(tasks[taskCounter], taskCounter);
                    taskCounter++;
                    break;
                case "event":
                    String[] eventWords = line.split("/");
                    String eventDescription = eventWords[0].substring(EVENT);
                    String at = eventWords[1].substring(DATE);
                    Task e = new Event(eventDescription, at);
                    tasks[taskCounter] = e;
                    printAcknowledgement(tasks[taskCounter], taskCounter);
                    taskCounter++;
                    break;
                case "done":
                    String number = line.substring(DONE);
                    int taskNumber = Integer.parseInt(number);
                    tasks[taskNumber - 1].markAsDone();
                    System.out.println(BORDER);
                    System.out.println("Nice! I've marked this task as done: " + tasks[taskNumber - 1].description);
                    System.out.println(BORDER);
                    break;
                default:
                    throw new DukeException();
                }
            } catch (DukeException e) {
                System.out.println(BORDER);
                System.out.println("☹ OH NO!!! I'm sorry, but I don't know what that means! :o(");
                System.out.println(BORDER);
            } catch (NullPointerException e) {
                System.out.println(BORDER);
                System.out.println("☹ OH NO!!! There is no such task to be done! :o(");
                System.out.println(BORDER);
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println(BORDER);
                System.out.println("☹ OH NO!!! The description of a " + taskType + " cannot be empty! :o(");
                System.out.println(BORDER);
            }
        }
    }

    private static void printList(Task[] tasks, int taskCounter) {
        System.out.println(BORDER);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskCounter; i++){
            System.out.println(i+1 + ". " + tasks[i].toString());
        }
        System.out.println(BORDER);
    }

    private static void printAcknowledgement(Task task, int counter) {
        System.out.println(BORDER);
        System.out.println("Got it! I've added this task: ");
        System.out.println(task.toString());
        System.out.println("Now you have " + (counter + 1) + " tasks in your list!");
        System.out.println(BORDER);
    }

    private static void printByeMessage() {
        System.out.println(BORDER);
        System.out.println("Bye! Hope to see you again soon!");
        System.out.println(BORDER);
    }

    private static void printWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(BORDER);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(BORDER);
    }
}
