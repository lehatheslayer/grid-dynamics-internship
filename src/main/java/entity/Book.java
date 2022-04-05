package entity;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({"name", "description", "author", "cost"})
public class Book {
    private int id;
    private String name;
    private String description;
    private String author;
    private Double cost;

    public Book(String name, String description, String author, Double cost) {
        this.name = name;
        this.description = description;
        this.author = author;
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Book)) {
            return false;
        }

        final Book v = (Book) o;

        return v.getName().equals(name)
                && v.getDescription().equals(description)
                && v.getAuthor().equals(author)
                && v.getCost().compareTo(cost) == 0;
    }

    @Override
    public String toString() {
        return name + " " + description + " " + author + " " + cost.toString();
    }
}
