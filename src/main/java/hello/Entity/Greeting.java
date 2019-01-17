package hello.Entity;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Entity
public class Greeting {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String content;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
