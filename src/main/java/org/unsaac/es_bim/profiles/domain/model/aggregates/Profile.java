package org.unsaac.es_bim.profiles.domain.model.aggregates;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.unsaac.es_bim.blog.domain.model.aggregate.Blog;
import org.unsaac.es_bim.iam.domain.model.aggregates.User;
import org.unsaac.es_bim.profiles.domain.model.valueObjects.Genre;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Entity
public class Profile extends AbstractAggregateRoot<Profile> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String profileImageUrl;

    private String firstName;

    private String lastName;

    @Nullable
    private Date birthDay;

    private String country;

    private String city;

    @Enumerated(EnumType.STRING)
    private Genre gender;

    private String phoneNumber;

    private String description;

    @OneToMany(mappedBy = "profile",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Blog> blogs;

    public void addBlog(Blog blog){

        this.blogs.add(blog);
        blog.setAuthor(this);
    }
    public String getGenderOrNull(){
        if(gender==null){
            return null;
        }
        return gender.toString();
    }
    public void setAccount(User user){
        this.user=user;
    }
    public Profile(){
        this.user=new User();
    }
    public Profile(String firstName,String lastName,String country,String phoneNumber){
        this.firstName=firstName;
        this.lastName=lastName;
        this.country=country;
        this.phoneNumber=phoneNumber;
        this.blogs= new ArrayList<>();

    }
    public void EditImageProfile(String imageUrl){
        this.profileImageUrl=imageUrl;
    }
    public void editProfile( String firstName, String lastName, Date birthDay,String country, String city, String gender, String phoneNumber, String des){

        this.firstName=firstName;
        this.lastName=lastName;
        this.birthDay=birthDay;
        this.country=country;
        this.city=city;
        this.gender =Genre.valueOf(gender);
        this.phoneNumber=phoneNumber;
        this.description=des;
    }
    public Profile(String imageUrl, String firstName, String lastName, Date birthDay, String country, String city, String gender, String phoneNumber, String des){

        this.profileImageUrl=imageUrl;
        this.firstName=firstName;
        this.lastName=lastName;
        this.birthDay=birthDay;
        this.country=country;
        this.city=city;
        this.gender =Genre.valueOf(gender);
        this.phoneNumber=phoneNumber;
        this.description=des;
    }

    public void updateNumber(String newNumber){
        this.phoneNumber=newNumber;
    }

}
