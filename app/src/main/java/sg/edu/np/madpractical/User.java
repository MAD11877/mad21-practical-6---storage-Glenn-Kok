package sg.edu.np.madpractical;

public class User {
    private String name;
    private String description;
    private int id;
    private boolean follow;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isFollow() {
        return follow;
    }

    public void setFollow(boolean follow) {
        this.follow = follow;
    }

    public User() {
    }

    public User(String name, String description, boolean follow) {
        this.name = name;
        this.description = description;
        this.follow = follow;
    }

    public User(int id, String name, String description, boolean follow) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.follow = follow;
    }


}
