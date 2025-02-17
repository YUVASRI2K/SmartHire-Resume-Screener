import java.util.*;

class Resume {
    private String name;
    private String education;
    private int experience;
    private List<String> skills;

    // Constructor
    public Resume(String name, String education, int experience, List<String> skills) {
        this.name = name;
        this.education = education;
        this.experience = experience;
        this.skills = skills;
    }

    // Getters
    public String getName() { return name; }
    public String getEducation() { return education; }
    public int getExperience() { return experience; }
    public List<String> getSkills() { return skills; }
}

class JobRole {
    private String roleName;
    private List<String> requiredSkills;

    public JobRole(String roleName, List<String> requiredSkills) {
        this.roleName = roleName;
        this.requiredSkills = requiredSkills;
    }

    public String getRoleName() { return roleName; }
    public List<String> getRequiredSkills() { return requiredSkills; }

    // Method to calculate match percentage
    public int calculateMatch(Resume resume) {
        int matchCount = 0;
        
        // Normalize both resume skills and required skills to lower case for case-insensitive comparison
        for (String skill : resume.getSkills()) {
            if (requiredSkills.contains(skill.trim().toLowerCase())) {
                matchCount++;
            }
        }
        
        // Calculate match based on number of skills required for the role
        return (int) ((matchCount / (double) requiredSkills.size()) * 100);
    }
}

class ResumeScreeningSystem {
    private List<JobRole> jobRoles;

    public ResumeScreeningSystem() {
        this.jobRoles = new ArrayList<>();
        initializeJobRoles();
    }

    // Initializing predefined job roles
    private void initializeJobRoles() {
        jobRoles.add(new JobRole("Software Engineer", Arrays.asList("java", "python", "data structures", "algorithms")));
        jobRoles.add(new JobRole("Data Scientist", Arrays.asList("python", "machine learning", "data science", "ai")));
        jobRoles.add(new JobRole("AI Engineer", Arrays.asList("ai", "machine learning", "deep learning")));
        jobRoles.add(new JobRole("Full-Stack Developer", Arrays.asList("javascript", "react", "node.js", "html", "css")));
        jobRoles.add(new JobRole("Cloud Engineer", Arrays.asList("aws", "azure", "docker", "kubernetes")));
        jobRoles.add(new JobRole("Cybersecurity Analyst", Arrays.asList("network security", "penetration testing", "firewalls", "cryptography")));
        jobRoles.add(new JobRole("DevOps Engineer", Arrays.asList("ci/cd", "docker", "kubernetes", "jenkins")));
        jobRoles.add(new JobRole("Database Administrator", Arrays.asList("sql", "nosql", "oracle", "mongodb")));
    }

    // Method to match resume with job roles
    public void matchResume(Resume resume) {
        System.out.println("\nAI-Powered Screening in Progress...");
        Map<String, Integer> matchScores = new HashMap<>();

        for (JobRole role : jobRoles) {
            int matchPercentage = role.calculateMatch(resume);
            if (matchPercentage > 0) {
                matchScores.put(role.getRoleName(), matchPercentage);
            }
        }

        if (matchScores.isEmpty()) {
            System.out.println("No suitable job roles found for this resume.");
        } else {
            // Sorting job roles by match percentage
            matchScores.entrySet().stream()
                    .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
                    .forEach(entry -> System.out.println(entry.getKey() + " – " + entry.getValue() + "% Match"));
        }
    }
}

public class ResumeScreeningApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ResumeScreeningSystem screeningSystem = new ResumeScreeningSystem();

        while (true) {
            System.out.println("\nEnter Resume Details:");

            System.out.print("Name: ");
            String name = scanner.nextLine();

            System.out.print("Education: ");
            String education = scanner.nextLine();

            System.out.print("Years of Experience: ");
            int experience = Integer.parseInt(scanner.nextLine());

            System.out.print("Skills (comma separated): ");
            List<String> skills = Arrays.asList(scanner.nextLine().split(","));

            // Creating Resume object
            Resume resume = new Resume(name, education, experience, skills);

            // Matching resume with job roles
            screeningSystem.matchResume(resume);

            System.out.print("\nEnter another resume? (yes/no): ");
            String choice = scanner.nextLine();
            if (!choice.equalsIgnoreCase("yes")) {
                System.out.println("Exiting Resume Screening System...");
                break;
            }
        }
        scanner.close();
    }
}
