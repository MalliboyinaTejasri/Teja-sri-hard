import java.util.*;

// Class representing a user
class User {
    private String username;
    private String fullName;
    private String email;

    // Constructor to initialize user details
    public User(String username, String fullName, String email) {
        this.username = username;
        this.fullName = fullName;
        this.email = email;
    }

    // Getter method for username
    public String getUsername() {
        return username;
    }

    // toString method to represent user details as a string
    @Override
    public String toString() {
        return "Username: " + username + ", Full Name: " + fullName + ", Email: " + email;
    }
}

// Class representing a post
class Post {
    private String content;
    private User author;
    private List<Comment> comments;

    // Constructor to initialize post details
    public Post(String content, User author) {
        this.content = content;
        this.author = author;
        this.comments = new ArrayList<>(); // Initialize comments list
    }

    // Method to add a comment to the post
    public void addComment(Comment comment) {
        comments.add(comment);
    }

    // toString method to represent post details as a string
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Author: ").append(author.getUsername()).append("\n");
        sb.append("Content: ").append(content).append("\n");
        sb.append("Comments:\n");
        for (Comment comment : comments) {
            sb.append(comment).append("\n");
        }
        return sb.toString();
    }
}

// Class representing a comment
class Comment {
    private String content;
    private User author;

    // Constructor to initialize comment details
    public Comment(String content, User author) {
        this.content = content;
        this.author = author;
    }

    // toString method to represent comment details as a string
    @Override
    public String toString() {
        return "Comment by " + author.getUsername() + ": " + content;
    }
}

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        List<User> userList = createUserList(); // Create a list of users
        List<Post> postList = createPosts(userList); // Create a list of posts
        displayAllPosts(postList); // Display all posts
        scanner.close();
    }

    // Method to create a list of users
    private static List<User> createUserList() {
        List<User> users = new ArrayList<>();
        System.out.println("Welcome to MySocialApp!\nLet's start by creating some users.");
        System.out.println("Enter user details (username, full name, email). Type 'done' to finish:");

        while (true) {
            System.out.print("Username: ");
            String username = scanner.nextLine();
            if (username.equals("done")) break;
            System.out.print("Full Name: ");
            String fullName = scanner.nextLine();
            System.out.print("Email: ");
            String email = scanner.nextLine();

            User user = new User(username, fullName, email);
            users.add(user);
        }
        return users;
    }

    // Method to create posts for each user
    private static List<Post> createPosts(List<User> users) {
        List<Post> posts = new ArrayList<>();
        for (User user : users) {
            System.out.println("Hello, " + user.getUsername() + "! Let's create some posts.");
            System.out.println("Type your post content. Type 'done' when finished.");

            while (true) {
                System.out.print("Post content: ");
                String content = scanner.nextLine();
                if (content.equals("done")) break;

                Post post = new Post(content, user);
                posts.add(post);

                System.out.println("Would you like to add a comment to this post? (yes/no)");
                String addComment = scanner.nextLine();
                while (addComment.equalsIgnoreCase("yes")) {
                    System.out.print("Comment content: ");
                    String commentContent = scanner.nextLine();

                    Comment comment = new Comment(commentContent, user);
                    post.addComment(comment);

                    System.out.println("Add another comment? (yes/no)");
                    addComment = scanner.nextLine();
                }
            }
        }
        return posts;
    }

    // Method to display all posts
    private static void displayAllPosts(List<Post> posts) {
        System.out.println("\nAll Posts:");
        for (Post post : posts) {
            System.out.println(post);
        }
    }
}
